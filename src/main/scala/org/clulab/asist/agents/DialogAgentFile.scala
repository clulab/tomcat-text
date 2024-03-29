package org.clulab.asist.agents

import buildinfo.BuildInfo
import com.crowdscriber.caption.vttdissector.VttDissector
import com.typesafe.config.Config
import com.typesafe.scalalogging.LazyLogging
import java.io.{File, FileInputStream, PrintWriter}
import org.clulab.asist.messages._
import org.clulab.utils.LocalFileUtils
import org.clulab.asist.extraction.TomcatRuleEngine


import scala.annotation.tailrec
import scala.io.Source
import scala.util.control.Exception._
import scala.util.control.NonFatal
import scala.util.{Failure, Success}

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
  val nochat: Boolean = false,
  override val ruleEngine: TomcatRuleEngine = new TomcatRuleEngine

) extends DialogAgent(ruleEngine) with LazyLogging {

  // get rule engine lazy init out of the way
  startEngine()

  // First set up the output stream
  if (outputFilename == "/dev/null") {
    process_files(None)
  } else try {
    process_files(Some(new PrintWriter(new File(outputFilename))))
  } catch {
    case NonFatal(t)  =>
      logger.error(s"Problem opening ${outputFilename} for writing:")
      logger.error(t.toString)
  }

  // then work through all supported file types
  def process_files(printWriter: Option[PrintWriter]): Unit = {

    val fileNames = LocalFileUtils.getFileNames(inputFilename)

    // process metadata files
    val reports: List[MetadataReport] = fileNames
      .filter(_.endsWith(".metadata"))
      .map(MetadataFileProcessor(_, printWriter, this))
      .flatten

    // give comprehensive summary at end of Metadata processing.
    logger.info("")
    logger.info("")
    logger.info("All Metadata file processing has finished.")
    logger.info(s"Metadata files processed: ${reports.length}")
    MetadataReport(MetadataReport.summarize(reports))

    // process VTT files
    fileNames
      .filter(_.endsWith(".vtt"))
      .foreach(WebVttFileProcessor(_, printWriter, this))

    // process plaintext files
    fileNames
      .filter(_.endsWith(".txt"))
      .foreach(TextFileProcessor(_, printWriter, this))

    logger.info("")
    logger.info(s"File Agent Uptime seconds = ${uptimeSeconds}")
    logger.info("Agent is shutting down.")
    printWriter.foreach(_.close)
  }
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
    printWriter: Option[PrintWriter],
    agent: DialogAgent
  ): Unit = try {
    logger.info("")
    logger.info(s"processing '${fileName}' ...")
    VttDissector(new FileInputStream(new File(fileName))) match {
      case Success(blocks) =>
        val blockIterator = blocks.iterator
        while(blockIterator.hasNext) {
          processWebVttElement(
            fileName,
            printWriter,
            agent,
            blockIterator.next.lines.toList
          ).foreach(msg => {
            val json = JsonUtils.writeJsonNoNulls(msg) + "\n"
            printWriter.foreach(_.write(json))
          })
        }
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
    printWriter: Option[PrintWriter],
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

// keep track of what is encountered in metadata files
case class MetadataReport(
  title: String = "not_set",
  read_chat: Int = 0,
  read_asr: Int = 0,
  read_trial_start: Int = 0,
  read_trial_stop: Int = 0,
  read_rollcall: Int = 0,
  read_unknown: Int = 0,
  written_dialog_agent: Int = 0,
  written_version_info: Int = 0,
  written_rollcall: Int = 0,
  lines_read: Int = 0,
  start_time_s: Double = 0,
  processing_time_s: Double = 0
)

object MetadataReport extends LazyLogging {

  def summarize(reports: List[MetadataReport]): MetadataReport = 
    summarize(reports, new MetadataReport)
  
  private def summarize(
    reports: List[MetadataReport],
    result: MetadataReport
  ): MetadataReport = reports match {
    case head::tail =>
      summarize(
        tail,
        result.copy(
          title = "Summary of all Metadata file processing:",
          read_chat = 
            result.read_chat + head.read_chat,
          read_asr = 
            result.read_asr + head.read_asr,
          read_trial_start = 
            result.read_trial_start + head.read_trial_start,
          read_trial_stop = 
            result.read_trial_stop + head.read_trial_stop,
          read_rollcall =
            result.read_rollcall + head.read_rollcall,
          read_unknown =
            result.read_unknown + head.read_unknown,
          written_dialog_agent =
            result.written_dialog_agent + head.written_dialog_agent,
          written_version_info =
            result.written_version_info + head.written_version_info,
          written_rollcall =
            result.written_rollcall + head.written_rollcall,
          lines_read =
            result.lines_read + head.lines_read,
          processing_time_s =
            result.processing_time_s + head.processing_time_s
        )
      )
    case _ => result
  }

  def apply(report: MetadataReport): Unit = {
    logger.info("")
    logger.info(report.title)
    logger.info("Messages read:")
    logger.info(s"  ASR: ${report.read_asr}")
    logger.info(s"  Chat: ${report.read_chat}")
    logger.info(s"  Trial start: ${report.read_trial_start}")
    logger.info(s"  Trial stop: ${report.read_trial_stop}")
    logger.info(s"  Rollcall request: ${report.read_rollcall}")
    logger.info(s"  Others: ${report.read_unknown}")
    logger.info(s"  Total lines read: ${report.lines_read}")
    logger.info("Messages written:")
    logger.info(s"  Dialog Agent: ${report.written_dialog_agent}")
    logger.info(s"  Rollcall response: ${report.written_rollcall}")
    logger.info(s"  Version info: ${report.written_version_info}")

    val processing_time_s: Double = 
      BigDecimal(report.processing_time_s)
        .setScale(2, BigDecimal.RoundingMode.HALF_UP)
        .toDouble

    logger.info(s"Processing time: ${processing_time_s} seconds")
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
    fileName: String,
    printWriter: Option[PrintWriter],
    agent: DialogAgentFile
  ): Option[MetadataReport] = {
    logger.info("")
    logger.info(s"processing '${fileName}' ...")

    val inputFile: File = new File(fileName);
    if(!inputFile.exists) {
      logger.error(s"File: ${fileName} does not exist")
      None
    }
    else Some(
      processLines (
        Source.fromFile(fileName).getLines,
        printWriter,
        agent,
        new MetadataReport(
          title = s"Filename: ${fileName}", 
          start_time_s = agent.uptimeSeconds
        ),
      )
    )
  }

  @tailrec
  private def processLines(
    lineIterator: Iterator[String],
    printWriter: Option[PrintWriter],
    agent: DialogAgentFile,
    report: MetadataReport
  ): MetadataReport = if(!lineIterator.hasNext) {
    val final_report = report.copy(
      processing_time_s = agent.uptimeSeconds - report.start_time_s
    )
    MetadataReport(final_report)
    final_report
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
    agent: DialogAgentFile,
    line: String,
    previous_report: MetadataReport
  ): MetadataReport = {
    val topic: String = 
      JsonUtils.readJson[Topic](line).getOrElse(Topic()).topic 
    val report = previous_report.copy(
      lines_read = previous_report.lines_read +1
    )
    topic match {
      case AsrMessage.topic =>
        logger.info(s"line ${report.lines_read} topic = ${topic}")
        AsrMessage(line).foreach(asr => {
          val msg = DialogAgentMessage(source_type, topic, asr, agent)
          val json = JsonUtils.writeJsonNoNulls(msg) + "\n"
          printWriter.foreach(_.write(json))
        })
        report.copy(
          read_asr = report.read_asr + 1,
          written_dialog_agent = report.written_dialog_agent + 1
        )
      case ChatMessage.topic =>
        if(agent.nochat) report 
        else {
          logger.info(s"line ${report.lines_read} topic = ${topic}")
          ChatMessage(line).foreach(chat => {
            val msg = DialogAgentMessage(source_type, topic, chat, agent)
            val json = JsonUtils.writeJsonNoNulls(msg) + "\n"
            printWriter.foreach(_.write(json))
          })
          report.copy(
            read_chat = report.read_chat + 1,
            written_dialog_agent = report.written_dialog_agent + 1
          )
        }
      case RollcallRequestMessage.topic =>
        logger.info(s"line ${report.lines_read} topic = ${topic}")
        RollcallRequestMessage(line).foreach(request => {
          val msg = RollcallResponseMessage(agent.uptimeSeconds, request)
          val json = JsonUtils.writeJsonNoNulls(msg) + "\n"
          printWriter.foreach(_.write(json))
        })
        report.copy(
          read_rollcall = report.read_rollcall + 1,
          written_rollcall = report.written_rollcall + 1
        )
      case TrialMessage.topic =>
        TrialMessage(line) match {
          case Some(trial) => 
            if(TrialMessage.isStart(trial)) { // Trial Start
              logger.info(s"line ${report.lines_read} topic = ${topic}.start")
              val msg = VersionInfoMessage(trial)
              val json = JsonUtils.writeJsonNoNulls(msg) + "\n"
              printWriter.foreach(_.write(json))
              report.copy(
                read_trial_start = report.read_trial_start + 1,
                written_version_info = report.written_version_info + 1
              )
            }
            else if (TrialMessage.isStop(trial)) {
              logger.info(s"line ${report.lines_read} topic = ${topic}.stop")
              report.copy(read_trial_stop = report.read_trial_stop + 1)
            }
            else // unknown trial subtype
              report.copy (read_unknown = report.read_unknown + 1)
          case _ => // trial message that didn't parse
            report.copy(read_unknown = report.read_unknown + 1)
         }
      case _ =>  // unknown topic
        report.copy(read_unknown = report.read_unknown + 1)
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
    printWriter: Option[PrintWriter],
    agent: DialogAgent
  ): Unit = {
    logger.info("")
    logger.info(s"processing '${fileName}' ...")
    val lineIterator: Iterator[String] = 
      Source.fromFile(new File(fileName)).getLines
    while(lineIterator.hasNext) {
      processLine(fileName, printWriter, agent, lineIterator.next)
    }
  }

  private def processLine(
    fileName: String,
    printWriter: Option[PrintWriter],
    agent: DialogAgent,
    text: String
  ): Unit = {
    logger.info(s"Read: ${text}")
    val msg = DialogAgentMessage(
      source_type, 
      fileName, 
      participant_id,
      text,
      agent
    )
    val json = JsonUtils.writeJsonNoNulls(msg) + "\n"
    printWriter.foreach(_.write(json))
  }
}
