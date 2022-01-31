package org.clulab.asist.agents

import buildinfo.BuildInfo
import com.crowdscriber.caption.vttdissector.VttDissector
import com.typesafe.config.Config
import com.typesafe.scalalogging.LazyLogging
import java.io.{File, FileInputStream, PrintWriter}
import org.clulab.asist.messages._
import org.clulab.utils.LocalFileUtils

import scala.io.Source
import scala.util.control.Exception._
import scala.util.control.NonFatal
import scala.util.{Failure, Success}

/**
 * Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 * Process a file or the first level of a directory of files
 *
 * A file is processed by finding metadata elements published on any of the 
 * DialogAgent subscribed topics and creating new DialogAgentMessages with 
 * that data.
 *
 * This class does some preliminary checking of input parameters and
 * then creates a DialogAgentFileSafe instance.
 *
 * @param inputFilename A file or directory of files to process.
 * @param outputFilename The results of all file processing are written here
 * @param tdacUrl Network host and port of the TDAC server
 * @param idc the IDC Worker will be run if true
 */
class DialogAgentFile(
  val inputFilename: String = "",
  val outputFilename: String = "",
  tdacUrl: Option[String] = None,
  val idc: Boolean = false
) extends TdacAgent(tdacUrl) with LazyLogging {

  private val filenames: List[String] = 
    LocalFileUtils.getFileNames(inputFilename)

  private val printWriter: Option[PrintWriter] = 
    if (outputFilename == "/dev/null")
      None
    else try {
      Some(new PrintWriter(new File(outputFilename)))
    } catch {
        case NonFatal(t)  =>
          logger.error(s"Problem opening ${outputFilename} for writing:")
          logger.error(t.toString)
          None
    }

  // do not process input unless there is a place to write the output
  val outputOK: Boolean =
    (outputFilename == "/dev/null") || printWriter.isDefined

  // compose a list of output messages from all of the input files
  private val jobs: List[Any] = if(outputOK) {

    // Message Bus metadata
    val metadataJobs: List[Any] = filenames
      .filter(_.endsWith(".metadata"))
      .map(MetadataFileProcessor(_, this))
      .flatten
    if(!metadataJobs.isEmpty)
      logger.info(s"Metadata messages read: ${metadataJobs.length}")

    // web vtt input
    val webVttJobs: List[DialogAgentMessage] = filenames
      .filter(_.endsWith(".vtt"))
      .map(WebVttFileProcessor(_, this))
      .flatten
    if(!webVttJobs.isEmpty)
      logger.info(s"WebVtt messages read: ${webVttJobs.length}")

    // plain text input
    val textJobs: List[DialogAgentMessage] = filenames
      .filter(_.endsWith(".txt"))
      .map(TextFileProcessor(_, this))
      .flatten
    if(!textJobs.isEmpty)
      logger.info(s"Text messages read: ${textJobs.length}")

    List(metadataJobs, webVttJobs, textJobs).flatten
  }
  else List.empty

  private val idcWorker: Option[IdcWorker] =
    if(idc) Some(new IdcWorker(this)) else None

  private val jobsIter: Iterator[Any] = jobs.iterator

  // startup
  jobs.length match {
    case 0 =>
      logger.info("No input was found.")
      shutdown
    case n =>
      logger.info(s"Processing ${n} messages...")
      logger.info(s"Writing output to ${outputFilename}")
      doNextJob
  } 

  private def process(a: Any): Unit = a match {
    case v: VersionInfo => processVersionInfo(v)
    case d: DialogAgentMessage => processDialogAgentMessage(d)
    case _ => doNextJob
  }

  private def processVersionInfo(v: VersionInfo): Unit = {
    logger.info(s"Processing VersionInfo, timestamp = ${v.header.timestamp}")
    val json = JsonUtils.writeJson(v)
    writeOutput(
      topicPubVersionInfo,
      json
    )
    tdacClient match {
      case Some(tdac) => tdac.resetServer
      case None => doNextJob
    }
  }

  private def processDialogAgentMessage(m: DialogAgentMessage): Unit = {
    logger.info(
      s"Processing DialogAgentMessage, timestamp = ${m.header.timestamp}"
    )
    idcWorker.foreach(_.enqueue(m.data.extractions))
    val json = JsonUtils.writeJson(m)
    tdacClient match {
      case Some(tdac) =>
        JsonUtils.parseJValue(json).map{ jvalue =>
          tdac.runClassification(
            topicPubDialogAgent,
            m.data,
            jvalue
          )
        }
      case None => // no TDAC
        writeOutput(topicPubDialogAgent,json)
        doNextJob
    }
  }

  // need to get topic in output
  private def writeOutput(
    topic: String,
    text: String
  ): Unit = {
    val jsonNoNulls = JsonUtils.noNulls(topic, text) // do not publish nulls
    printWriter.foreach(_.write(s"${jsonNoNulls}\n"))
  }

  override def doNextJob(): Unit = 
  if(jobsIter.hasNext) 
    process(jobsIter.next)
  else {
    logger.info("All operations completed successfully.")
    shutdown
  }

  override def writeOutput(messages: List[BusMessage]): Unit =  
    messages.foreach(m => writeOutput(m.topic, m.text))

  def shutdown: Unit = {
    logger.info("Agent is shutting down")
    printWriter.foreach(_.close)
    tdacClient.foreach(_.close)
    idcWorker.foreach(_.close)
  }
}

object WebVttFileProcessor extends LazyLogging {

  private val source_type = "web_vtt_file"

  /** Process a Message Bus metadata file
  *  @param filename The name of the web VTT file to process
  *  @param agent The agent calling this object
  *  @return A list of DialogAgentMessages based on input
  */
  def apply(
    filename: String,
    agent: DialogAgent
  ): List[DialogAgentMessage] = try {
    logger.info(s"processing '${filename}' ...")
    VttDissector(new FileInputStream(new File(filename))) match {
      case Success(blocks) =>
        blocks.map{block =>
          processWebVttElement(
            filename,
            agent,
            block.lines.toList
          )
        }.toList.flatten
      case Failure(f) =>
        logger.error("VttDissector could not parse '%s'".format(filename))
        logger.error(f.toString)
        List.empty
    }
  } catch {
    case NonFatal(t) =>
      logger.error(s"Could not process '${filename}'")
      logger.error(t.toString)
      List.empty
  }

  /** process one web_vtt block
  *  @param lines The line sequence from a single SubtitleBlock instance
  *  @param filename The name of the input file where the block was read
  *  @return A DialogAgentMessage based on the inputs
  */
  private def processWebVttElement(
    filename: String,
    agent: DialogAgent,
    lines: List[String]
  ): Option[DialogAgentMessage] = lines match {
    case head::tail =>  
      // if a colon is in the first line, text to left is participant id
      val foo = head.split(':')
      if(foo.length == 1) Some(
        DialogAgentMessage(
          source_type,
          filename,
          None,
          lines.mkString(" "),
          agent
        )
      )
      else Some(
        DialogAgentMessage(
          source_type,
          filename,
          Some(foo(0)),
          (foo(1)::tail).mkString(" "),
          agent
        )
      )
    case _ => None  // no lines
  }
}

/* Process Message Bus metadata */
object MetadataFileProcessor extends LazyLogging {

  private val source_type = "message_bus"

  /** Process a Message Bus metadata file
  *  @param filename The name of the metadata file to process
  *  @param agent The agent calling this object
  *  @return A list of DialogAgent Message Bus output messages based on input
  */
  def apply(
    filename: String,
    agent: DialogAgent
  ): List[Any] = {
    logger.info(s"processing '${filename}' ...")
    Source.fromFile(new File(filename))
      .getLines
      .toList
      .map(processLine(filename,agent,_))
      .flatten
  }

  private def processLine(
    filename: String,
    agent: DialogAgent,
    text: String
  ): Option[Any] = {
    val topic = JsonUtils.readJson[Topic](text).getOrElse(Topic()).topic 
    topic match {
      case agent.topicSubTrial => TrialMessage(text)
        .filter(TrialMessage.isStart(_))
        .map(VersionInfo(_))
      case agent.topicSubChat => ChatMessage(text)
        .map(DialogAgentMessage(source_type, filename, _, agent))
      case agent.topicSubAsr => AsrMessage(text)
        .map(DialogAgentMessage(source_type, filename, _, agent))
      case _ => None
    }
  }
}

/* Create DialogAgentMessages from a text file.  Each line of input data
 * is processed into one DialogAgent Message */
object TextFileProcessor extends LazyLogging {

  private val source_type = "text_file"
  private val participant_id: Option[String] = None

  /** Process a text file, creating a DialogAgentMessage from each line
  *  @param filename The name of the text file to process
  *  @param agent The agent calling this object
  *  @return A list of DialogAgentMessages based on the file input
  */
  def apply(
    filename: String,
    agent: DialogAgent
  ): List[DialogAgentMessage] = {
    logger.info(s"processing '${filename}' ...")
    Source.fromFile(new File(filename))
      .getLines
      .toList
      .map(processLine(filename,agent,_))
  }

  private def processLine(
    filename: String,
    agent: DialogAgent,
    text: String
  ): DialogAgentMessage = 
    DialogAgentMessage(
      source_type, 
      filename, 
      participant_id,
      text,
      agent
    )
}
