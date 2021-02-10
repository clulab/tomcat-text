/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *  Updated:  2021 February
 *
 *  A file-compatible DialogAgent that will perform extractions
 *  on directories and individual files.   Directories are 
 *  processed one level deep.
 *
 *  inputFilename  - Either a file or directory.   In the case of a directory, 
 *               only the first level of files is processed.
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

  /** Process one VttJsonMessage */
  def processLine(vttJson: String, output: PrintWriter): Unit = toVttJsonMessage(
    vttJson
  ) match {
    case Some(a: VttJsonMessage) => 
      output.write("%s\n".format(toJson(toDialogAgentMessage(a))))
    case _ => logger.error("Could not process '%s'".format(vttJson))
  }

  /** Process one input file */
  def processFile(filename: String, output: PrintWriter): Unit = try {
    val source = Source.fromFile(new File(filename))
    val lines = source.getLines
    while (lines.hasNext) processLine(lines.next, output)
    source.close
  } catch {
    case t: Throwable => {
      logger.error("Problem reading from %s".format(filename))
      logger.error(t.toString)
      None
    }
  }

  /** return the files to process */
  def inputFilenames(f: File): List[String] = {
    if(f.isDirectory) f.listFiles.toList.map(_.getAbsolutePath)
    else List(f.getAbsolutePath)
  }

  // open the output stream and run the input files
  try {
    val output = new PrintWriter(new File(outputFilename))
    inputFilenames(new File(inputFilename)).map(processFile(_, output))
    output.close
  } catch {
    case t: Throwable => {
      logger.error("Problem writing to %s".format(outputFilename))
      logger.error(t.toString)
      None
    }
  }
}
