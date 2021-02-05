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
    val inputFilename: String = "",
    val outputFilename: String = ""
) extends DialogAgent
    with DialogAgentJson {

  private val logger = LoggerFactory.getLogger(this.getClass())

  /** Try to open the input stream for the filename */
  val inputOpt: Option[BufferedSource] = try {
    Some(Source.fromFile(inputFilename))
  } catch {
    case t: Throwable => {
      logger.error("Problem opening %s for reading".format(inputFilename))
      logger.error(t.toString)
      None
    }
  }

  /** Try to open the output stream for the filename */
  val outputOpt:Option[PrintWriter] = try {
    Some(new PrintWriter(new File(outputFilename)))
  } catch {
    case t: Throwable => {
      logger.error("Problem opening %s for writing".format(outputFilename))
      logger.error(t.toString)
      None
    }
  }

  /** run if we have input and output, otherwise shutdown gracefully */
  List(inputOpt, outputOpt).flatten match {
    case List(i: BufferedSource, o: PrintWriter) => processLines(i, o)
    case List(i: BufferedSource) => i.close
    case List(o: PrintWriter) => o.close
    case _ =>
  }

  /** Convert all input lines and write to file */
  def processLines(input: BufferedSource, output: PrintWriter): Unit = {
      val lines = input.getLines
      while(lines.hasNext) processLine(lines.next, output)
      input.close
      output.close
  }

  /** convert VttJsonMessage json to DialogAgentMessage json and write to file*/
  def processLine(json: String, output: PrintWriter): Unit = {
    List(toVttJsonMessage(json)).flatten match {
      case List(a: VttJsonMessage) => 
        output.write("%s\n".format(toJson(toDialogAgentMessage(a))))
      case _ => logger.error("Could not process '%s'".format(json))
    }
  }
}
