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
  val inputFilename: String = "",
  val outputFilename: String = "",
  override val nMatches: Int = 0
) extends DialogAgent {

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

  def foo(json: String): String = {
    logger.info("foo with %s\n".format(json))
    "reprocessed JSON"
  }


  // metadata elements are one per line
  def reprocessMetadata(filename: String): Boolean = {
    logger.info("Reprocessing input file '%s',".format(filename))
    val outfile = outputFilename + "/" + (filename.split("/").last)
    logger.info("Reprocessing output file '%s',".format(outfile))
    try {
      val bufferedSource = Source.fromFile(filename)
      val inputLines = bufferedSource.getLines
      val output = new PrintWriter(new File(outfile))
      while(inputLines.hasNext) {
        val inputLine = inputLines.next
        val outputLine = foo(inputLine)
        output.write("%s\n".format(outputLine))))
      }
      output.close
      true
    } 
    catch {
      case t: Throwable => {
        logger.error("Problem reprocessing %s".format(filename))
        logger.error(t.toString)
      }
      false
    }
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

    if(!results.contains(false)) {
      logger.info("All operations completed successfully.")
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

  processFiles(getFiles(inputFilename))
}
