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
import scala.annotation.tailrec


/**
 * Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 * Process a file or the first level of a directory of files
 *
 *
 * This class does some preliminary checking of input parameters and
 * then creates a DialogAgentFileSafe instance.
 *
 * @param inputFilename A file or directory of files to process.
 * @param outputFilename The results of all file processing are written here
 */
class DialogAgentFile(
  val inputFilename: String = "",
  val outputFilename: String = "",
) extends DialogAgent with LazyLogging {

  // First set up the output stream
  if (outputFilename == "/dev/null") {
    begin(None)
  } else try {
    begin(Some(new PrintWriter(new File(outputFilename))))
  } catch {
    case NonFatal(t)  =>
      logger.error(s"Problem opening ${outputFilename} for writing:")
      logger.error(t.toString)
  }

  // then work through all supported file types
  def begin(printWriter: Option[PrintWriter]): Unit = {

    // process metadata files
    LocalFileUtils.getFileNames(inputFilename)
      .filter(_.endsWith(".metadata"))
      .map(MetadataFileProcessor(_, printWriter, this))
      .flatten
      .foreach(MetadataReport(_))

    // process VTT input

  }



      /*
    // web vtt input
    val webVttJobs: List[DialogAgentMessage] = fileNames
      .filter(_.endsWith(".vtt"))
      .map(WebVttFileProcessor(_, this))
      .flatten
    if(!webVttJobs.isEmpty)
      logger.info(s"WebVtt messages read: ${webVttJobs.length}")

    // plain text input
    val textJobs: List[DialogAgentMessage] = fileNames
      .filter(_.endsWith(".txt"))
      .map(TextFileProcessor(_, this))
      .flatten
    if(!textJobs.isEmpty)
      logger.info(s"Text messages read: ${textJobs.length}")

    List(metadataJobs, webVttJobs, textJobs).flatten
  }
  else List.empty

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
    doNextJob
  }

  private def processDialogAgentMessage(m: DialogAgentMessage): Unit = {
    logger.info(
      s"Processing DialogAgentMessage, timestamp = ${m.header.timestamp}"
    )
    val json = JsonUtils.writeJson(m)
    writeOutput(topicPubDialogAgent,json)
    doNextJob
  }

  // need to get topic in output
  private def writeOutput(
    topic: String,
    text: String
  ): Unit = {
    val jsonNoNulls = JsonUtils.noNulls(topic, text) // do not publish nulls
    printWriter.foreach(_.write(s"${jsonNoNulls}\n"))
  }

  def doNextJob(): Unit = 
  if(jobsIter.hasNext) 
    process(jobsIter.next)
  else {
    logger.info("All operations completed successfully.")
    shutdown
  }

  def writeOutput(messages: List[BusMessage]): Unit =  
    messages.foreach(m => writeOutput(m.topic, m.text))

  def shutdown: Unit = {
    logger.info("Agent is shutting down")
    printWriter.foreach(_.close)
  }
  */
}

object WebVttFileProcessor extends LazyLogging {

  private val source_type = "web_vtt_file"

  /** Process a Message Bus metadata file
  *  @param fileName The name of the web VTT file to process
  *  @param agent The agent calling this object
  *  @return A list of DialogAgentMessages based on input
  */
  def apply(
    fileName: String,
    agent: DialogAgent
  ): List[DialogAgentMessage] = try {
    logger.info(s"processing '${fileName}' ...")
    VttDissector(new FileInputStream(new File(fileName))) match {
      case Success(blocks) =>
        blocks.map{block =>
          processWebVttElement(
            fileName,
            agent,
            block.lines.toList
          )
        }.toList.flatten
      case Failure(f) =>
        logger.error("VttDissector could not parse '%s'".format(fileName))
        logger.error(f.toString)
        List.empty
    }
  } catch {
    case NonFatal(t) =>
      logger.error(s"Could not process '${fileName}'")
      logger.error(t.toString)
      List.empty
  }

  /** process one web_vtt block
  *  @param lines The line sequence from a single SubtitleBlock instance
  *  @param fileName The name of the input file where the block was read
  *  @return A DialogAgentMessage based on the inputs
  */
  private def processWebVttElement(
    fileName: String,
    agent: DialogAgent,
    lines: List[String]
  ): Option[DialogAgentMessage] = lines match {
    case head::tail =>  
      // if a colon is in the first line, text to left is participant id
      val foo = head.split(':')
      if(foo.length == 1) Some(
        DialogAgentMessage(
          source_type,
          fileName,
          None,
          lines.mkString(" "),
          agent
        )
      )
      else Some(
        DialogAgentMessage(
          source_type,
          fileName,
          Some(foo(0)),
          (foo(1)::tail).mkString(" "),
          agent
        )
      )
    case _ => None  // no lines
  }
}

case class MetadataReport(
  fileName: String = "not_set",
  read_chat: Int = 0,
  read_asr: Int = 0,
  read_trial_start: Int = 0,
  read_trial_stop: Int = 0,
  read_unknown: Int = 0,
  read_rollcall: Int = 0,
  written_dialog_agent: Int = 0,
  written_version_info: Int = 0,
  written_rollcall: Int = 0,
  lines_read: Int = 0,
)

object MetadataReport extends LazyLogging {
  def apply(report: MetadataReport): Unit = {
    logger.info("")
    logger.info(s"PROCESSED METADATA FILE: ${report.fileName}")
    logger.info("Messages read:")
    logger.info(s" Chat: ${report.read_chat}")
    logger.info(s" ASR: ${report.read_asr}")
    logger.info(s" Trial start: ${report.read_trial_start}")
    logger.info(s" Trial stop: ${report.read_trial_stop}")
    logger.info(s" Rollcall request: ${report.read_rollcall}")
    logger.info(s" Others: ${report.read_unknown}")
    logger.info(s" Total lines read: ${report.lines_read}")
    logger.info("Messages written:")
    logger.info(s" DialogAgent: ${report.written_dialog_agent}")
    logger.info(s" Rollcall response: ${report.written_rollcall}")
    logger.info(s" Version info: ${report.written_version_info}")
  }
}

/* Uses files instead of the Message Bus to process data */
object MetadataFileProcessor extends LazyLogging {

  private val source_type = "message_bus"

  /** Process a Message Bus metadata file
  *  @param inputFile Read metadata lines as if from the Message Bus
  *  @param outputFile Processed metadata is written here including topic
  *  @return A list of DialogAgent Message Bus output messages based on input
  */
  def apply(
    inputFileName: String,
    printWriter: Option[PrintWriter],
    agent: DialogAgent
  ): Option[MetadataReport] = {
    logger.info("")
    logger.info(s"processing '${inputFileName}' ...")

    val inputFile: File = new File(inputFileName);
    if(!inputFile.exists) {
      logger.error(s"File: ${inputFileName} does not exist")
      None
    }
    else Some(
      processLines (
        Source.fromFile(inputFileName).getLines,
        printWriter,
        agent,
        new MetadataReport(fileName = inputFileName)
      )
    )
  }

  @tailrec
  private def processLines(
    lineIterator: Iterator[String],
    printWriter: Option[PrintWriter],
    agent: DialogAgent,
    report: MetadataReport
  ): MetadataReport = if(!lineIterator.hasNext) {
    report
  } else { 
    processLines(
      lineIterator,
      printWriter, 
      agent, 
      processLine(
        printWriter,
        agent,
        lineIterator.next, 
        report
      )
    )
  }

  private def processLine(
    printWriter: Option[PrintWriter],
    agent: DialogAgent,
    line: String,
    previous_report: MetadataReport
  ): MetadataReport = {
    val topic: String = 
      JsonUtils.readJson[Topic](line).getOrElse(Topic()).topic 
    val report = previous_report.copy(
      lines_read = previous_report.lines_read +1
    )
    topic match {
      case agent.topicSubAsr =>
        logger.info(s"line ${report.lines_read} topic = ${topic}")
        AsrMessage(line).foreach(asr => {
          val msg = DialogAgentMessage(source_type, topic, asr, agent)
          val json = JsonUtils.writeJsonNoNulls(msg)
          printWriter.foreach(_.write(json))
        })
        report.copy(
          read_asr = report.read_asr + 1,
          written_dialog_agent = report.written_dialog_agent + 1
        )

      case agent.topicSubChat =>
        logger.info(s"line ${report.lines_read} topic = ${topic}")
        ChatMessage(line).foreach(chat => {
          val msg = DialogAgentMessage(source_type, topic, chat, agent)
          val json = JsonUtils.writeJsonNoNulls(msg)
          printWriter.foreach(_.write(json))
        })
        report.copy(
          read_chat = report.read_chat + 1,
          written_dialog_agent = report.written_dialog_agent + 1
        )
      case agent.topicSubRollcallRequest =>
        logger.info(s"line ${report.lines_read} topic = ${topic}")
        RollcallRequestMessage(line).foreach(request => {
          val msg = RollcallResponseMessage(agent.uptimeMillis, request)
          val json = JsonUtils.writeJsonNoNulls(msg)
          printWriter.foreach(_.write(json))
        })
        report.copy(
          read_rollcall = report.read_rollcall + 1,
          written_rollcall = report.written_rollcall + 1
        )
      case agent.topicSubTrial =>
        TrialMessage(line) match {
          case Some(trial) => 
            if(TrialMessage.isStart(trial)) { // Trial Start
              logger.info(s"line ${report.lines_read} topic = ${topic}.start")
              val msg = VersionInfo(trial)
              val json = JsonUtils.writeJsonNoNulls(msg)
              printWriter.foreach(_.write(json))
              report.copy(
                read_trial_start = report.read_trial_start + 1,
                written_version_info = report.written_version_info + 1
              )
            }
            else if (TrialMessage.isStop(trial)) {
              logger.info(s"line ${report.lines_read} topic = ${topic}.stop")
              report.copy(
                read_trial_stop = report.read_trial_stop +1
              )
            }
            else report.copy (
              read_unknown = report.read_unknown + 1
            )
          case _ =>
            report.copy(
              read_unknown = report.read_unknown + 1
            )
         }
      case _ => // do not process unknown topics
        report.copy(
          read_unknown = report.read_unknown + 1
        )

    }
  }
}

/* Create DialogAgentMessages from a text file.  Each line of input data
 * is processed into one DialogAgent Message */
object TextFileProcessor extends LazyLogging {

  private val source_type = "text_file"
  private val participant_id: Option[String] = None

  /** Process a text file, creating a DialogAgentMessage from each line
  *  @param fileName The name of the text file to process
  *  @param agent The agent calling this object
  *  @return A list of DialogAgentMessages based on the file input
  */
  def apply(
    fileName: String,
    agent: DialogAgent
  ): List[DialogAgentMessage] = {
    logger.info(s"processing '${fileName}' ...")
    Source.fromFile(new File(fileName))
      .getLines
      .toList
      .map(processLine(fileName,agent,_))
  }

  private def processLine(
    fileName: String,
    agent: DialogAgent,
    text: String
  ): DialogAgentMessage = 
    DialogAgentMessage(
      source_type, 
      fileName, 
      participant_id,
      text,
      agent
    )
}
