/**
 *  Author:  Joseph Astier, Adarsh Pyarelal
 *  Updated:  2021 January
 *
 *  A file-compatible DialogAgent that will take over the roles
 *  of ExtractDirSearch and ExtractInfoSearch
 *
 *  inputFilenames- a list of .vtt files that have been converted to .json by
 *                  scripts/vtt_to_json_msgs
 *
 *  outputFilename - the results of processing the input filenames.
 */
package org.clulab.asist

import java.io.{File, PrintWriter}
import org.slf4j.LoggerFactory
import scala.io.{BufferedSource, Source}

class DialogAgentFile(
    val inputFilenames: List[String] = List.empty,
    val outputFilename: String = "output_events.json"
) extends DialogAgent
    with DialogAgentJson {

  private val logger = LoggerFactory.getLogger(this.getClass())

  /** Try to open an input stream */
  def openInput(filename: String): Option[BufferedSource] = try {
    Some(Source.fromFile(new File(filename)))
  } catch {
    case t: Throwable => {
      logger.error("Problem opening %s for reading".format(filename))
      logger.error(t.toString)
      None
    }
  }

  /** Try to open the output stream */
  def openOutput(filename: String): Option[PrintWriter] = try {
    Some(new PrintWriter(new File(outputFilename)))
  } catch {
    case t: Throwable => {
      logger.error("Problem opening %s for writing".format(outputFilename))
      logger.error(t.toString)
      None
    }
  }

  /** Convert VttJsonMessage json to DialogAgentMessage json and write to file*/
  def processLine(vttJson: String, output: PrintWriter) = toVttJsonMessage(
    vttJson
  ) match {
    case Some(a: VttJsonMessage) =>
      output.write("%s\n".format(toJson(toDialogAgentMessage(a))))
    case _ => logger.error("Could not process '%s'".format(vttJson))
  }

  /** process one filename */
  def processFile(filename: String, output: PrintWriter): Unit =
    openInput(filename).map(source => {
      val lines = source.getLines
      while (lines.hasNext) processLine(lines.next, output)
      source.close
  })

  /** process each of the filenames sequentially */
  def processFiles(filenames: List[String], output: PrintWriter): Unit =
  filenames match {
    case filename :: tail => {
      processFile(filename, output)
      processFiles(tail, output)
    }
    case _ => output.close
  }

  openOutput(outputFilename).map(processFiles(inputFilenames,_))
}
