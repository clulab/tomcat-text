package org.clulab.asist.agents

import java.io.{File, PrintWriter}
import java.nio.file.Paths

import org.clulab.asist.messages.DialogAgentMessage.readDialogAgentMessage
import org.clulab.asist.messages._
import org.clulab.utils.LocalFileUtils
import org.clulab.utils.LocalFileUtils.{getFileNames, ensureDir, getLines}
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
 * If a .metadata file contains DialogAgent output, reprocess the file.
 *
 * Reprocess the DialogAgentMessage metadata by copying it to new DialogAgentMessage
 * structs.  Replace the extractions with new ones from the DialogAgent.
 *
 * If a .metadata file ends with Vers-<N>.metadata, the corresponding file
 * in the output directory should end with Vers-<N+1>.metadata (instead of
 * preserving the original fileName). This is to comply with the TA3 file
 * naming scheme.
 *
 */
class DialogAgentReprocess (
  val inputDirName: String,
  val outputDirName: String,
  override val nMatches: Int = 0
) extends DialogAgent {

  private lazy val logger = LoggerFactory.getLogger(this.getClass)

  // find the files in the input directory that contain DialogAgent metadata
  val fileNames = LocalFileUtils.getFileNames(inputDirName)
    .filter(a => a.endsWith(".metadata"))
    .filter(hasDialogAgentMetadata)

  // make sure we have metadata to process
  val metadataOk = if(fileNames.length > 0) true else {
    logger.error("No .metadata files containing DialogAgent data were found")
    false
  }
  
  // make sure the output directory is available
  def outputDirOk: Boolean = if(LocalFileUtils.ensureDir(outputDirName)) {
    logger.info(s"Using output directory: ${outputDirName}")
    true
  } else false

  // If we have metadata and an output directory, proceed with reprocessing
  if(metadataOk && outputDirOk) {
    logger.info("Reprocessing...")
    val results = fileNames.map(processFile)
    if(!results.contains(false)) 
      logger.info("All reprocessing completed successfully.")
    else 
      logger.error("There were problems during reprocessing.")
  } 
  // otherwise advise the user and halt. 
  else logger.error("Unable to reprocess metadata.")

  // True if the file contains any DialogAgent metadata.
  def hasDialogAgentMetadata(inputFileName: String): Boolean = 
    hasDialogAgentMetadata(lineIterator(inputFileName))

  def lookahead(line: String): MetadataLookahead = try {
    Option(read[MetadataLookahead](line))
      .getOrElse(new MetadataLookahead)
  } catch {
      case t: Throwable => new MetadataLookahead
  }



  // True if the iterator contains any DialogAgent metadata.
  @tailrec
  private def hasDialogAgentMetadata(
    iter: Iterator[String]
  ): Boolean = if(!iter.hasNext) false else {
    if(lookahead(iter.next).topic == topicPubDialogAgent) true
    else hasDialogAgentMetadata(iter)
  }

  // Return a string iterator of the lines in an input file
  def lineIterator(fileName: String): Iterator[String] = 
    Source.fromFile(fileName).getLines

  /** process one input file
   * @param inputFileName The file to be processed
   * @returns true if successful
   */
  def processFile(inputFileName: String): Boolean = {
    val outputFileName = ta3FileName(inputFileName)
    logger.info(s"'${inputFileName}' => '${outputFileName}'")
    val fileWriter = new PrintWriter(new File(outputFileName))
    val bufferedSource = Source.fromFile(inputFileName)
    val iterator = lineIterator(inputFileName)
    val ret = processLines(iterator, fileWriter, true)
    fileWriter.close
    ret
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

  /** Reprocess json if DialogAgent metadata, otherwise write it as-is.
   * @param json One metadata JSON line
   * @param fileWriter Writes to the output file
   * @returns true if the line was processed successfully
   */
  def processLine(
    json: String,
    fileWriter: PrintWriter
  ): Boolean = {
    val lookahead = 
      Option(read[MetadataLookahead](json)).getOrElse(new MetadataLookahead)
    lookahead.topic match {
      case `topicPubDialogAgent` => processDialogAgentMetadata(json, fileWriter)
      case `topicSubTrial` => processTrialMetadata(json, fileWriter)
      case _ => 
        fileWriter.write(s"${writeJson(json)}\n")
        true
    }
  }

  /*

    val lookahead = read[MetadataLookahead](json)
    if(lookahead.topic == topicPubDialogAgent) {
      reprocessDialogAgentMetadata(json, fileWriter)
    }
    else {
      fileWriter.write(s"${writeJson(json)}\n")
      true // skip non-DialogAgent metadata
    }
  } 
  catch {  
    case t: Throwable => 
      logger.error(s"Error parsing MetadataLookahead from JSON: ${json}\n")
      logger.error(t.toString)
      false
  }
  */


  /** Generate a Dialog Agent Version Info message and save it to the file
   * @param json JSON representation of one DialogAgentMessage struct
   * @param fileWriter Writes to the output file
   * @returns true if the line was processed successfully
   */
  def processTrialMetadata(
    json: String,
    fileWriter: PrintWriter
  ): Boolean = {
    true
  }


  /** reprocess the data.text of DialogAgentMessage JSON input, copy all else.
   * @param json JSON representation of one DialogAgentMessage struct
   * @param fileWriter Writes to the output file
   * @returns true if the line was processed successfully
   */
  def processDialogAgentMetadata(
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
        message.data.text // reprocessing happens now
      ) 
    ) 
    fileWriter.write(s"${writeJson(newMessage)}\n")
    fileWriter.write("%s\n".format(writeJson(newMessage)))
    true
  }
  catch {
    case t: Throwable => 
      logger.error(s"Error parsing DialogAgentMessage from JSON: ${json}\n")
      logger.error(t.toString)
      false
  }

  /** If the fileName has a TA3 version number, increment by 1.
   * @param inputFileName fileName that may have a TA3 version number
   * @return the inputFileName, with any TA3 version number incremented
   */
  def ta3FileName(inputFileName: String): String = {

    // get local fileName out of the input directory
    val localFileName = inputFileName.split("/").last

    // the output fileName is the local fileName in the output directory
    val outputPath = Paths.get(outputDirName,localFileName)
    val outputFileName = outputPath.toFile.getAbsolutePath

    // if the output fileName contains a TA3 version number, increment it by 1
    val regex = """Vers-(\d+).metadata""".r
    regex.replaceAllIn(outputFileName, _ match {
      case regex(version) => s"Vers-${version.toInt + 1}.metadata"
      case _ => outputFileName  // otherwise do not change the inputFileName
    })
  }
}
