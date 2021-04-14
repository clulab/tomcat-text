/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Updated:  2021 April
 *
 *  This trait is to group file processing functionality in one place.
 *
 */
package org.clulab.asist

import java.io.{FileInputStream, File, PrintWriter}
import org.slf4j.LoggerFactory

trait AgentFile {

  private lazy val logger = LoggerFactory.getLogger(this.getClass())

  def getFiles(filename: String): List[String] = {
    val f = new File(filename)
    if(f.isDirectory) f.listFiles.toList.map(_.getPath)
    else List(f.getPath)
  }

  def processFiles(
    inputFilename: String, 
    outputFilename: String
  ): Unit = {
    logger.info("Using input file '%s'".format(inputFilename))
    logger.info("Using output file '%s'".format(outputFilename))
    try {
      val output = new PrintWriter(new File(outputFilename))
      getFiles(inputFilename).map(filename => {
        logger.info("processing %s".format(filename))
        processFile(filename, output)
        logger.info("finished processing %s".format(filename))
      })
      output.close
      logger.info("All operations completed successfully.")
    } catch {
      case t: Throwable => {
        logger.error("Problem writing to %s".format(outputFilename))
        logger.error(t.toString)
      }
    }
  }

  /** Manage one file
   * @param filename a single input file
   * @param output Printwriter to the output file
   */
  def processFile(filename: String, output: PrintWriter): Unit
}
