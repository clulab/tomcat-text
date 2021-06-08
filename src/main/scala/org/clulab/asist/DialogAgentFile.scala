package org.clulab.asist

import java.io.File
import java.io.PrintWriter
import org.slf4j.LoggerFactory

/**
 * Authors:  Joseph Astier, Adarsh Pyarelal
 *
 * Updated:  2021 April
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
) extends DialogAgent {

  private lazy val logger = LoggerFactory.getLogger(this.getClass())

  /** process one input file
   * @param filename a single input file
   * @param output Printwriter to the output file
   */
  def processFile(filename: String, output: PrintWriter): Unit = 
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

  /** process all of the input files
   * @param filenames the input files as determined by the inputFilename
   */
  def processFiles(filenames: List[String]): Unit = {
    logger.info("Using output file '%s'".format(outputFilename))
    try {
      val output = new PrintWriter(new File(outputFilename))
      filenames.map(processFile(_, output))
      output.close
      logger.info("All operations completed successfully.")
    } catch {
      case t: Throwable => {
        logger.error("Problem writing to '%s'".format(outputFilename))
      logger.error(t.toString)
      }
    }
  }

  /** Determine the list of input files to process
   * @param filename User input arg, may be a file or directory of files
   * @returns A list of zero or more filenames to process
   */
  def getFiles(filename: String): List[String] = {
    val input = new File(inputFilename)
    if(input.exists) {
      if(input.isDirectory) {
        logger.info("Using input directory '%s'".format(inputFilename))
        input.listFiles.toList.map(_.getPath).sorted
      }
      else {
        logger.info("Using input file '%s'".format(inputFilename))
        List(input.getPath)
      }
    } 
    else {
      logger.error("File not found '%s'".format(inputFilename))
      List()
    }
  }

  // start
  processFiles(getFiles(inputFilename))
}
