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
      case Success(blocks) => blocks.map(block => parse(block.lines.toList, filename, output))
      case Failure(f) => {
        logger.error("VttDissector could not parse input")
        logger.error(f.toString)
      }
    }

  def parse(
      lines: List[String],
      filename: String,
      output: PrintWriter): Unit = lines match {
    case head::tail => {
      val foo = head.split(':')
      if(foo.length == 0) {
        val text = lines.mkString(" ")
        val message = toDialogAgentMessage("file", filename, null, null, text)
        output.write("%s\n".format(toJson(message)))
      } else {
        val text = (foo(1)::tail).mkString(" ")
        val message = toDialogAgentMessage("file", filename, null, foo(0), text)
        output.write("%s\n".format(toJson(message)))
      }
    }
    case _ =>
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
