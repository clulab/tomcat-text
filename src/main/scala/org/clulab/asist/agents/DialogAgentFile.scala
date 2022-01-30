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

  private val printWriter: Option[PrintWriter] = if (outputFilename == "/dev/null")
    None
  else try {
    Some(new PrintWriter(new File(outputFilename)))
  } catch {
      case NonFatal(t)  =>
        logger.error(s"Problem opening ${outputFilename} for writing:")
        logger.error(t.toString)
        None
  }

  val outputOK: Boolean =
    (outputFilename == "/dev/null") || printWriter.isDefined

  // input can be DialogAgentMessage or VersionInfo
  private val metadataJobs: List[Any] = if(outputOK) filenames
    .filter(_.endsWith(".metadata"))
    .map(MetadataFileProcessor(_, this))
    .flatten
  else List.empty

  // web vtt input
  private val webVttJobs: List[DialogAgentMessage] = if(outputOK) filenames
    .filter(_.endsWith(".vtt"))
    .map(WebVttFileProcessor(_, this))
    .flatten
  else List.empty

  // plain text input
  private val textJobs: List[DialogAgentMessage] = if(outputOK) filenames
    .filter(_.endsWith(".txt"))
    .map(TextFileProcessor(_, this))
    .flatten
  else List.empty

  private val jobs: List[Any] = List(metadataJobs, webVttJobs, textJobs).flatten
  private val jobsIter: Iterator[Any] = jobs.iterator
  private val idcWorker: Option[IdcWorker] =
    if(idc) Some(new IdcWorker(this)) else None

  // start
  if(outputOK) {
    if(!webVttJobs.isEmpty)
      logger.info(s"webVtt messages read: ${webVttJobs.length}")
    if(!metadataJobs.isEmpty)
      logger.info(s"metadata messages read: ${metadataJobs.length}")
    if(!textJobs.isEmpty)
      logger.info(s"text messages read: ${textJobs.length}")

    logger.info(s"Processing ${jobs.length} messages...")
    logger.info(s"Writing output to ${outputFilename}")
    doNextJob
  } else shutdown

  private def process(a: Any): Unit = a match {
    case Some(v: VersionInfo) => 
      logger.info("Process Some(VersionInfo)")
      processVersionInfo(v)
    case Some(m: DialogAgentMessage) => 
      logger.info("Process Some(DialogAgentMessage)")
      processDialogAgentMessage(m)
    case v: VersionInfo => 
      logger.info("Process VersionInfo")
      processVersionInfo(v)
    case m: DialogAgentMessage => 
      logger.info("Process DialogAgentMessage")
      processDialogAgentMessage(m)
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
    logger.info(s"Processing DialogAgentMessage, timestamp = ${m.header.timestamp}")
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


object MetadataFileProcessor extends LazyLogging {

  private val source_type = "message_bus"

  // one file
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

/* Create DialogAgentMessages from lines of text */
object TextFileProcessor extends LazyLogging {

  private val source_type = "txt_file"
  private val participant_id: Option[String] = None

  // one file
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
    DialogAgentMessage(source_type, filename, participant_id, text, agent)
  
}
