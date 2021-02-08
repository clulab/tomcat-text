/**
 *  Author:  Joseph Astier, Adarsh Pyarelal
 *  Updated:  2021 January
 *
 *  A file-compatible DialogAgent that will take over the roles
 *  of ExtractDirSearch and ExtractInfoSearch
 *
 *  inputFilename - a .vtt file that has been converted to .json by
 *                  scripts/vtt_to_json_msgs
 *
 *  outputFilename - direct our processing output here.
 */
package org.clulab.asist

import java.io.{File, PrintWriter}
import org.slf4j.LoggerFactory
import scala.io.{BufferedSource, Source}
import scala.util.control.Exception._


class DialogAgentFile(
    val inputFilenames: List[String] = List.empty,
    val outputFilename: String = "output_events.json"
) extends DialogAgent
    with DialogAgentJson {

  private val logger = LoggerFactory.getLogger(this.getClass())

  /** Try to open an input stream for the filename */
  def inputOpt(filename: String): Option[BufferedSource] = try {
    val file = new File(filename)
    if(file.isFile) {
      Some(Source.fromFile(file))
    }
    else None
  } catch {
    case t: Throwable => {
      logger.error("Problem opening %s for reading".format(filename))
      logger.error(t.toString)
      None
    }
  }

  /** convert VttJsonMessage json to DialogAgentMessage json, write to file*/
  def processLine(vttJson: String, output: PrintWriter): Unit = {
    List(toVttJsonMessage(vttJson)).flatten match {
      case List(a: VttJsonMessage) => 
        output.write("%s\n".format(toJson(toDialogAgentMessage(a))))
      case _ => logger.error("Could not process '%s'".format(vttJson))
    }
  }

  /** Convert all input lines and write to file */
  def processFile(
    input: BufferedSource, 
    output: PrintWriter): Unit = {
      val lines = input.getLines
      while(lines.hasNext) processLine(lines.next, output)
      input.close
  }


  /** process one filename */
  def processFile(
    filename: String, 
    output: PrintWriter): Unit = inputOpt(filename).map(processFile(_,output))


  /** process each of the filenames sequentially */
  def processFiles(filenames: List[String], output: PrintWriter): Unit =
  filenames match {
    case filename :: tail => {
      processFile(filename, output)
      processFiles(tail, output)
    }
    case _ =>
  }

  /** Try to open the output stream */
  try {
    val output = new PrintWriter(new File(outputFilename))
    processFiles(inputFilenames, output)
    output.close
  } catch {
    case t: Throwable => {
      logger.error("Problem opening %s for writing".format(outputFilename))
      logger.error(t.toString)
      None
    }
  }
}
