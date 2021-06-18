/**
 * Authors:  Joseph Astier, Adarsh Pyarelal
 *
 * Updated:  2021 June
 *
 * Run .metadata files again, using the same filenames in a new directory.
 * The header and msg timestamps are copied from the input files.
 *
 */
package org.clulab.asist

import java.io.{File, PrintWriter}
import org.json4s._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write}
import scala.io.Source
import scala.util.control.Exception._
import org.clulab.utils.FileUtils
import org.slf4j.LoggerFactory


class DialogAgentReprocess (
  override val inputFilename: String = "",
  override val outputFilename: String = "",
  override val nMatches: Int = 0
) extends DialogAgentFile {

  private lazy val logger = LoggerFactory.getLogger(this.getClass())


  // Make sure we have a place to put the output files.
  def outputDirOK: Boolean = {
    val file = new File(outputFilename)
    if(file.exists) {
      if(file.isDirectory) true  // use existing dir
      else {  // don't clobber non-dir file of the same name
        logger.error("Can't create output directory '%s',"
          .format(outputFilename))
        logger.error("A non-directory file with the same name is in the way.")
        false
      }
    }
    else file.mkdir // create dir if needed
  }


  // metadata elements are one per line
  def reprocessMetadata(filename: String): Unit = {
    val bufferedSource = Source.fromFile(filename)
    val lines = bufferedSource.getLines

  }


  def processFile(filename: String): Boolean = 
    if(filename.endsWith(".metadata")) {
      reprocessMetadata(filename)
      true
    }
    else {
      logger.error("Can't reprocess file '%s',".format(filename))
      logger.error("File extension must be '.metadata'")
      false
    }

  def processFiles(filenames: List[String]): Unit = if(outputDirOK) {
    logger.info("Using output directory: %s".format(outputFilename))
    val results = filenames.map(processFile)
    val passes = results.filter(r => r)

    if(results.contains(false)) {
    }
  }
}
