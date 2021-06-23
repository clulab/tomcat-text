/**
 * Authors:  Joseph Astier, Adarsh Pyarelal
 *
 * Updated:  2021 June
 *
 * Run DialogAgentMessage .metadata files again, using the same filenames in a 
 * new directory. The header and msg timestamps are copied from the input files.
 *
 * If a .metadata file ends with Vers-<N>.metadata, the corresponding file 
 * in the output directory should end with Vers-<N+1>.metadata (instead of 
 * preserving the original filename). This is to comply with the TA3 file 
 * naming scheme.
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

  /** Make sure we have a place to put the output files.
   * @returns true if the output file directory was found or created
   */
  def outputDirOK: Boolean = {
    val file = new File(outputDirname)
    if(file.exists) {
      if(file.isDirectory) true  // use existing dir
      else {  // don't clobber non-dir file of the same name
        logger.error("Can't create directory '%s',".format(outputDirname))
        logger.error("A file with the same name is in the way.")
        false
      }
    }
    else {
      val ret = file.mkdir // create dir if needed
      if(!ret) 
        logger.error("Can't create directory '%s',".format(outputDirname))
      ret
    }
  }

  /** If the filename has a TA3 version number, increment by 1.
   * @param inputFilename filename that may have a TA3 version number
   * @returns the inputFilename, with any TA3 version number incremented
   */
  def ta3Filename(inputFilename: String): String = {

    // get local filename out of the input directory
    val localFilename = inputFilename.split("/").last

    // the output filename is the local filename in the output directory
    val outputFilename = "%s/%s".format(outputDirname,localFilename)

    // if the output filename contains a TA3 version number, increment it by 1
    val regex = raw"Vers-(\d+).metadata".r
    regex.replaceAllIn(outputFilename, _ match {
      case regex(version) => "Vers-%s.metadata".format(version.toInt + 1)
      case _ => outputFilename  // otherwise do not change the inputfilename
    })
  }

  /** process a single DialogAgentMessage metadata string
   * @param line JSON representation of a DialogAgentMessage
   * @param fileWriter Writes to the output file
   * @returns true if the line was processed successfully
   */
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
   
  /** process all the metadata lines from one input file
   * @param lines An iterator with all of the lines for this file
   * @param fileWriter Writes to the output file
   * @param result The accumulated outcome of processing each line
   * @returns true if all lines were processed successfully
   */
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

  /** process one input file
   * @param inputFilename The file to be processed
   * @returns true if successful
   */
  def processFile(
    inputFilename: String
  ): Boolean = {
    if(inputFilename.endsWith(".metadata")) {
      val outputFilename = ta3Filename(inputFilename)
      logger.info("%s' => '%s'".format(inputFilename, outputFilename))
      val fileWriter = new PrintWriter(new File(outputFilename))
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


  /** process all of the input files sequentially
   * @param filenames List of all files to be processed
   * @returns nothing
   */
  def processFiles(filenames: List[String]): Unit = if(outputDirOK) {
    logger.info("Using output directory: %s".format(outputDirname))
    logger.info("Reprocessing...")
    val results = filenames.map(processFile)
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
