/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Updated:  2021 February
 *
 *  A file-compatible DialogAgent that will perform extractions
 *  on directories and individual files.   Directories are 
 *  processed one level deep.
 *
 *  This class currently only reads .vtt, but could be extended to others
 *
 *  inputFilename  - Either a web_vtt file or directory of web_vtt files
 *
 *  outputFilename - The concatenated results of processing the input files.
 */
package org.clulab.asist

import java.io.{File, PrintWriter}
import org.slf4j.LoggerFactory
import scala.io.{BufferedSource, Source}
import java.io.{FileInputStream, File, PrintWriter}
import org.slf4j.LoggerFactory
import scala.io.{BufferedSource, Source}
import scala.util.{Failure, Success, Try}
import com.crowdscriber.caption.common.Vocabulary.SubtitleBlock
import com.crowdscriber.caption.vttdissector.VttDissector
import org.htmlcleaner.HtmlCleaner
import java.time.Clock


class DialogAgentFile(
    val inputFilename: String = "",
    val outputFilename: String = "output_events.json"
) extends DialogAgent 
    with DialogAgentJson {

  private val logger = LoggerFactory.getLogger(this.getClass())

  // List all the files to be processed.
  val allFiles: List[String] = {
    val f = new File(inputFilename)
    if(f.isDirectory) f.listFiles.toList.map(_.getAbsolutePath)
    else List(f.getAbsolutePath)
  }

  /** Process one file */
  def processFile(filename: String, output: PrintWriter): Unit = try {
    logger.info("Reading %s".format(filename))
    val stream = new FileInputStream(new File(filename))
    parseInputStream(stream, filename, output)
    stream.close
    logger.info("Closed %s".format(filename))
  } catch {
    case t: Throwable => {
      logger.error("Could not open '%s' for reading.".format(filename))
      logger.error(t.toString)
    }
  }

  /** Run a VttDissector on the file contents */
  def parseInputStream(stream: FileInputStream, filename: String, output: PrintWriter): Unit = 
    VttDissector(stream) match {
      case Success(blocks) => blocks.map(block => {
        val json = toJson(toDialogAgentMessage(block, filename))
        output.write("%s\n".format(json))
      })
      case Failure(f) => {
        logger.error("VttDissector could not parse input")
        logger.error(f.toString)
      }
    }


  def toDialogAgentMessage (sub: SubtitleBlock, filename: String): DialogAgentMessage = {
    val timestamp = Clock.systemUTC.instant.toString
    val version = "0.1"
    val lines: Seq[String] = sub.lines
    val start: Int = sub.start
    val end: Int = sub.end
    val extractions: Seq[DialogAgentMessageDataExtraction] = Seq.empty

    DialogAgentMessage(
      MessageHeader(
        timestamp = timestamp,
        message_type = "observation",
        version = version
      ),
      DialogAgentMessageMsg(
        source = "tomcat_text_analyzer",
        experiment_id = null,
        timestamp = timestamp,
        sub_type = "Event:dialogue_event",
        version = version
      ),
      DialogAgentMessageData(
        participant_id = null,
        text = null, // needed
        DialogAgentMessageDataSource(
          source_type = null, // needed
          source_name = filename
        ),
        extractions // needed
      )
    )
  }


  // open the output stream and process the files
  try {
    val output = new PrintWriter(new File(outputFilename))
    allFiles.map(processFile(_, output))
    output.close
  } catch {
    case t: Throwable => {
      logger.error("Problem writing to %s".format(outputFilename))
      logger.error(t.toString)
      None
    }
  }
}
