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
package org.clulab.asist

import java.io.File
import java.io.PrintWriter
import org.slf4j.LoggerFactory

trait FileHandler {

  private lazy val logger = LoggerFactory.getLogger(this.getClass())

  /** process one input file
   * @param filename a single input file
   * @param output Printwriter to the output file
   */
  def processFile(filename: String, output: PrintWriter): Unit

  /** process all of the input files
   * @param filenames the input files as determined by the inputFilename
   */
  def apply(inputFilename: String, outputFilename: String): Unit = {
    logger.info("Using output file '%s'".format(outputFilename))
    try {
      val output = new PrintWriter(new File(outputFilename))
      val filenames = getFiles(inputFilename)
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
  def getFiles(inputFilename: String): List[String] = {
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
}
