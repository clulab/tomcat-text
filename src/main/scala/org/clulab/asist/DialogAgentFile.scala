package org.clulab.asist

import java.io.{File, PrintWriter}
import org.slf4j.LoggerFactory


/**
 * Authors:  Joseph Astier, Adarsh Pyarelal
 *
 * Updated:  2021 June
 *
 * Process a file or the first level of a directory of files.
 *
 * @param inputFilename A file or directory of files to process.
 * @param outputFilename The results of all file processing are written here
 * @param nMatches  maximum number of taxonomy_matches to return (up to 5)
 */

class DialogAgentFile(
  val inputFilename: String = "",
  val outputFilename: String = "",
  override val nMatches: Int = 0
) extends DialogAgent with FileHandler {

  private lazy val logger = LoggerFactory.getLogger(this.getClass())

  /** process one input file
   * @param filename a single input file
   * @param output Printwriter to the output file
   */
  override def processFile(filename: String, output: PrintWriter): Unit = 
    filename.substring(filename.lastIndexOf(".")) match {
      case ".vtt" => {
        logger.info("processing WebVTT file '%s' ...".format(filename))
        AgentFileWebVtt(filename, this, output)
        logger.info("finished processing %s".format(filename))
      }
      case ".metadata" =>  {
        logger.info("processing Metadata file: '%s' ...".format(filename))
        AgentFileMetadata(filename, this, output)
        logger.info("finished processing '%s'".format(filename))
      }
      case _ => {
        logger.error("Can't process file '%s'".format(filename))
        RunDialogAgent.usageText.map(line => (logger.error(line)))
      }
    }


  this(inputFilename, outputFilename)
}
