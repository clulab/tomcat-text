package org.clulab.asist.agents

import java.io.{File, PrintWriter}
import java.nio.file.Paths

import org.clulab.asist.messages.DialogAgentMessage.readDialogAgentMessage
import org.clulab.asist.messages._
import org.clulab.utils.LocalFileUtils
import org.slf4j.LoggerFactory
import org.json4s._
import org.json4s.jackson.Serialization.read

import scala.annotation.tailrec
import scala.io.Source

/**
 * Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
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
class DialogAgentReprocess (
  val inputDirname: String,
  val outputDirname: String,
  override val nMatches: Int = 0
) extends DialogAgent {

  private lazy val logger = LoggerFactory.getLogger(this.getClass)

  // if the output dir doesn't exist, make it
  private val canWrite = LocalFileUtils.ensureDir(outputDirname)

  processFiles(LocalFileUtils.getFileNames(inputDirname))

  /** process all of the input files sequentially
   * @param filenames List of all files to be processed
   * @returns nothing
   */
  def processFiles(filenames: List[String]): Unit = {
    if(canWrite) {
      logger.info(s"Using output directory: ${outputDirname}")
      logger.info("Reprocessing...")
      val results = filenames.map(processFile)
      if(!results.contains(false)) {
        logger.info("All operations completed successfully.")
      }
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
      logger.info(s"'${inputFilename}' => '${outputFilename}'")
      val fileWriter = new PrintWriter(new File(outputFilename))
      val bufferedSource = Source.fromFile(inputFilename)
      val ret = processLines(bufferedSource.getLines, fileWriter, true)
      fileWriter.close
      ret
    }
    else {
      logger.error(s"Can't reprocess file '${inputFilename}',")
      logger.error("File extension must be '.metadata'")
      false
    }
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

  /** read a JSON input line and reprocess if DialogAgent metadata
   * @param json One metadata JSON line
   * @param fileWriter Writes to the output file
   * @returns true if the line was processed successfully
   */
  def processLine(
    json: String,
    fileWriter: PrintWriter
  ): Boolean = try {
    val lookahead = read[MetadataLookahead](json)
    if(lookahead.topic == topicPubDialogAgent) {
      reprocessDialogAgentMetadata(json, fileWriter)
    }
    else true // skip non-DialogAgent metadata
  } 
  catch {  
    case t: Throwable => 
      logger.error(s"Error parsing MetadataLookahead from JSON: ${json}\n")
      logger.error(t.toString)
      false
  }

  /** reprocess the data.text of DialogAgentMessage JSON input, copy all else.
   * @param json JSON representation of one DialogAgentMessage struct
   * @param fileWriter Writes to the output file
   * @returns true if the line was processed successfully
   */
  def reprocessDialogAgentMetadata(
    json: String,
    fileWriter: PrintWriter
  ): Boolean = try {
    val message = read[DialogAgentMessage](json)
    val newMessage = DialogAgentMessage(
      header = message.header,
      msg = message.msg,
      data = dialogAgentMessageData(
        message.data.participant_id,
        message.data.asr_msg_id,
        message.data.source.source_type,
        message.data.source.source_name,
        Option(message.data.text).getOrElse("")  // reprocessing happens now
      ) 
    ) 
    fileWriter.write(s"${writeJson(newMessage)}\n")
    true
  }
  catch {
    case t: Throwable => 
      logger.error(s"Error parsing DialogAgentMessage from JSON: ${json}\n")
      logger.error(t.toString)
      false
  }

  /** If the filename has a TA3 version number, increment by 1.
   * @param inputFilename filename that may have a TA3 version number
   * @return the inputFilename, with any TA3 version number incremented
   */
  def ta3Filename(inputFilename: String): String = {

    // get local filename out of the input directory
    val localFilename = inputFilename.split("/").last

    // the output filename is the local filename in the output directory
    val outputPath = Paths.get(outputDirname,localFilename)
    val outputFilename = outputPath.toFile.getAbsolutePath

    // if the output filename contains a TA3 version number, increment it by 1
    val regex = """Vers-(\d+).metadata""".r
    regex.replaceAllIn(outputFilename, _ match {
      case regex(version) => s"Vers-${version.toInt + 1}.metadata"
      case _ => outputFilename  // otherwise do not change the inputFilename
    })
  }
}
