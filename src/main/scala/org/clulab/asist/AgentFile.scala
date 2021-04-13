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


  def processFiles(inputFilename: String, outputFilename: String): Boolean = try {
    val allFiles = getFiles(inputFilename)
    val output = new PrintWriter(new File(outputFilename))
    val results = allFiles.map(processFile(_, output))
    output.close
    if(results.contains(false)) {
      logger.error("Problems were encountered during this run.")
      false
    }
    else {
      logger.info("All operations completed successfully.")
      true
    }
  } catch {
    case t: Throwable => {
      logger.error("Problem writing to %s".format(outputFilename))
      logger.error(t.toString)
      false
    }
  }


  /** Manage one file
   * @param filename a single input file
   * @param output Printwriter to the output file
   * @return true if the operation succeeded
   */
  def processFile(filename: String, output: PrintWriter): Boolean 
}
