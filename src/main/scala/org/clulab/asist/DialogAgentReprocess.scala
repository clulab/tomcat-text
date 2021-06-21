/**
 * Authors:  Joseph Astier, Adarsh Pyarelal
 *
 * Updated:  2021 June
 *
 * Run DialogAgentMessage .metadata files again, using the same filenames in a 
 * new directory. The header and msg timestamps are copied from the input files.
 *
 * If the input filename has a TA3 version number, increment the number by 1.
 *
 */
package org.clulab.asist

import java.io.{File, PrintWriter}
import org.json4s.jackson.Serialization.read
import org.slf4j.LoggerFactory

import scala.annotation.tailrec
import scala.io.Source

class DialogAgentReprocess (
  val inputDirname: String = "",
  val outputDirname: String = "",
  override val nMatches: Int = 0
) extends DialogAgent {

  private lazy val logger = LoggerFactory.getLogger(this.getClass())

  // Make sure we have a place to put the output files.
  def outputDirOK: Boolean = {
    val file = new File(outputDirname)
    if(file.exists) {
      if(file.isDirectory) true  // use existing dir
      else {  // don't clobber non-dir file of the same name
        logger.error("Can't create output directory '%s',"
          .format(outputDirname))
        logger.error("A file with the same name is in the way.")
        false
      }
    }
    else file.mkdir // create dir if needed
  }


  // If a .metadata file ends with Vers-<N>.metadata, the corresponding file 
  // in the output directory should end with Vers-<N+1>.metadata (instead of 
  // preserving the original filename). This is to comply with the TA3 file 
  // naming scheme.
  def ta3Filename(inputFilename: String): String = {

    // get local filename out of the input directory
    val localFilename = inputFilename.split("/").last

    // the output filename is the local filename in the output directory
    val outputFilename = "%s/%s".format(outputDirname,localFilename)

    // if the output filename contains a TA3 version number, increment it by 1
    val regex = raw"Vers-(\d+).metadata".r
    regex.replaceAllIn(outputFilename, _ match {
      case regex(version) => "Vers-%s.metadata".format(version.toInt + 1)
      case _ => outputFilename
    })
  }

  // process a single DialogAgentMessage metadata string
  def processLine(
    inputLine: String,
    fileWriter: PrintWriter
  ): Boolean = try {
    val message: DialogAgentMessage = read[DialogAgentMessage](inputLine)
    val newMessage = new DialogAgentMessage(
      header = message.header,
      msg = message.msg,
      data = dialogAgentMessageData(
        message.data.participant_id,
        message.data.asr_msg_id,
        message.data.source.source_type,
        message.data.source.source_name,
        message.data.text  // reprocessing happens now
      ) 
    ) 
    fileWriter.write("%s\n".format(writeJson(newMessage)))
    true
  }
  catch {
    case t: Throwable => {
      logger.error("Error parsing input line: %s\n".format(inputLine))
      logger.error(t.toString)
    }
    false
  }
   
  // process all the metadata lines from one input file
  @tailrec
  private def processLines(
    lines: Iterator[String],
    fileWriter: PrintWriter,
    result: Boolean
  ): Boolean = {
    if(!lines.hasNext) result
    else {
      val currentResult = processLine(lines.next, fileWriter)
      processLines(lines, fileWriter, currentResult && result)
    }
  }

  // process one input file
  def processFile(
    inputFilename: String
  ): Boolean = {
    if(inputFilename.endsWith(".metadata")) {
      val outputFilename= ta3Filename(inputFilename)
      logger.info("%s' => '%s'".format(inputFilename, outputFilename))
      val fileWriter = new PrintWriter(new File(outputFilename))
      fileWriter.write("head\n")
      val bufferedSource = Source.fromFile(inputFilename)
      val ret = processLines(bufferedSource.getLines, fileWriter, true)
      fileWriter.close
      ret
    }
    else {
      logger.error("Can't reprocess file '%s',".format(inputFilename))
      logger.error("File extension must be '.metadata'")
      false
    }
  }


  // process all of the input files
  def processFiles(filenames: List[String]): Unit = if(outputDirOK) {
    logger.info("Using output directory: %s".format(outputDirname))
    logger.info("Reprocessing...")
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
  def getFiles(inputDirname: String): List[String] = {
    val input = new File(inputDirname)
    if(input.exists) {
      if(input.isDirectory) {
        logger.info("Using input directory '%s'".format(inputDirname))
        input.listFiles.toList.map(_.getPath).sorted
      }
      else {
        logger.info("Using input file '%s'".format(inputDirname))
        List(input.getPath)
      }
    }
    else {
      logger.error("File not found '%s'".format(inputDirname))
      List()
    }
  }

  processFiles(getFiles(inputDirname))
}
