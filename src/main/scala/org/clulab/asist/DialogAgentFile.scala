/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Updated:  2021 February
 *
 *  A file-compatible DialogAgent that will perform extractions
 *  on directories and individual files.   Directories are 
 *  processed one level deep.
 *
 *  inputFilename  - Either a file or directory.
 *
 *  outputFilename - The results of processing the input filenames.
 */
package org.clulab.asist

import java.io.{File, PrintWriter}
import org.slf4j.LoggerFactory
import scala.io.{BufferedSource, Source}

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
    val source = Source.fromFile(new File(filename))
    val lines = source.getLines
    while (lines.hasNext)
      toVttJsonMessage(lines.next).map(a =>
        output.write("%s\n".format(toJson(toDialogAgentMessage(a)))))
    source.close
    logger.info("Closed %s".format(filename))
  } catch {
    case t: Throwable => {
      logger.error("Problem reading from %s".format(filename))
      logger.error(t.toString)
      None
    }
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
