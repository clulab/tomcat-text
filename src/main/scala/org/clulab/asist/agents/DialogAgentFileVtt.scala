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
class DialogAgentFileVtt(
  val inputFilename: String = "",
  val outputFilename: String = "",
  tdacUrl: Option[String] = None,
  val idc: Boolean = false
) extends TdacAgent(tdacUrl) with LazyLogging {

  val supported: List[String] = List(".vtt", ".metadata")
  val filenames: List[String] = LocalFileUtils
    .getFileNames(inputFilename)
    .filter(f => f.contains("."))
    .filter(f => supported.contains(f.substring(f.lastIndexOf("."))))

  val printWriter: Option[PrintWriter] = try {
    Some(new PrintWriter(new File(outputFilename)))
  } catch {
    case NonFatal(t)  =>
      logger.error(s"Problem opening ${outputFilename} for writing.")
      logger.error(t.toString)
      None
  } 

  val fileIterator: Iterator[String] = filenames.iterator

  printWriter.foreach {
    if(filenames.isEmpty) {
      logger.error("No input filenames found")
    }
    else {
      tdacInit
      logger.info(s"Output will be written to: ${outputFilename}")
      logger.info(s"Using input files: ${filenames.mkString(", ")}")
      filenames.foreach(file => processFile(file))
      logger.info("All operations completed successfully.")
    }
    _.close
  }

  /** process one input file
   * @param filename a single input file
   * @param output Printwriter to the output file
   */
  def processFile(
    filename: String
  ): Unit = if(filename.contains(".")) {
    filename.substring(filename.lastIndexOf(".")) match {
      case ".vtt" =>
        processWebVttFile(filename)
      case ".metadata" =>
        processMetadataFile(filename)
      case _ => usage(filename)
    }
  } else usage(filename)

  /** Manage one metadata file
   * @param filename a single input file
   * @param output Printwriter to the output file
   * @return true if the operation succeeded
   */
  def processMetadataFile(
    filename: String
  ): Unit = try { 
    val bufferedSource = Source.fromFile(filename)
    logger.info(s"processing '${filename}' ...")
    val lines = bufferedSource.getLines
    while(lines.hasNext) {
      processMetadataLine(filename, lines.next)
    }
    bufferedSource.close
    logger.info(s"finished processing '${filename}'")
  } catch {
    case NonFatal(t) =>
      logger.error(s"Could not process '${filename}'")
      logger.error(t.toString)
  }

  def processMetadataLine(
    filename: String, 
    line: String
  ): Unit = {
    val source_type: String = "message_bus" // file metadata originates here
    val t: Topic = JsonUtils.readJson[Topic](line).getOrElse(Topic())
    t.topic match {
      case `topicSubTrial` => 
        TrialMessage(line)
          .filter(TrialMessage.isStart)
          .map(trial => writeOutput(VersionInfo(trial),line))
      case `topicSubChat` => 
        ChatMessage(line)
          .map(DialogAgentMessage(source_type, filename, _, this))
          .map(writeOutput(_, line))
      case `topicSubAsr` => 
        AsrMessage(line)
          .map(DialogAgentMessage(source_type, filename, _, this))
          .map(writeOutput(_, line))
      case _ =>
    }
  }

  /** Manage one WebVtt file
  *  @param filename input filename
  *  @param output Printwriter to the output file
  */
  def processWebVttFile(
    filename: String
  ): Unit = try {
    VttDissector(new FileInputStream(new File(filename))) match {
      case Success(blocks) => 
        blocks.map{block => 
        logger.info(s"processing '${filename}' ...")
        processWebVttElement(
          block.lines.toList,
          filename
        ).map{message => 
          val json = JsonUtils.writeJson(message) 
          printWriter.foreach(_.write(s"${json}\n"))
          logger.info(s"finished processing '${filename}'")
        }
      }
      case Failure(f) => 
        logger.error("VttDissector could not parse '%s'".format(filename))
        logger.error(f.toString)
    }
  } catch {
    case NonFatal(t) =>
      logger.error(s"Could not process '${filename}'")
      logger.error(t.toString)
  }

  /** process one web_vtt block
  *  @param lines The line sequence from a single SubtitleBlock instance
  *  @param filename The name of the input file where the block was read
  *  @return A DialogAgentMessage based on the inputs
  */
  def processWebVttElement(
    lines: List[String],
    filename: String
  ): Option[DialogAgentMessage] = {
    val source_type = "web_vtt_file"
    lines match {
      case head::tail => 
        // if a colon is in the first line, text to left is participant id
        val foo = head.split(':')
        if(foo.length == 1) Some(
          DialogAgentMessage(
            source_type, 
            filename,
            None,
            lines.mkString(" "),
            this
          )
        )
        else Some(
          DialogAgentMessage(
            source_type, 
            filename,
            Some(foo(0)),
            (foo(1)::tail).mkString(" "),
            this
          )
        )
      case _ => None  // no lines
    }
  }

  override def doNextJob(): Unit = {
  }

  override def writeOutput(messages: List[BusMessage]): Unit = {
  }

  def writeOutput(
    ref: AnyRef, 
    line: String,
  ): Unit = {
    val json:String = JsonUtils.writeJson(ref)
    val jsonNoNulls:String = JsonUtils.noNulls(json) // do not publish nulls
    printWriter.foreach(_.write(s"${jsonNoNulls}\n"))
  }

  /** Give the user a hint
   * @param filename a single input file
   */
  def usage(filename: String): Unit = {
    logger.error(s"Can't process file '${filename}'")
    logger.error("Extension must be .vtt or .metadata")
  }

  logger.info(s"DialogAgentFile version ${BuildInfo.version}")
}
