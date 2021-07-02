package org.clulab.asist.agents

import java.io.{File, PrintWriter}
import java.nio.file.Paths
import java.time.Clock

import org.clulab.asist.messages._
import org.clulab.utils.LocalFileUtils
import org.slf4j.LoggerFactory
import org.json4s._
import org.json4s.jackson.Serialization.{read,write}
import org.json4s.jackson.JsonMethods.{mapper, parse, pretty}

import scala.annotation.tailrec
import scala.io.Source
import scala.util.control.NonFatal

/**
 * Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 * Updated:  2021 June
 *
 * Reprocess Dialog Agent metadata files by reading them lines by line and then
 * writing the processed results to the output file.
 *
 * Reprocessed DialogAgent metadata is a copy of existing DialogAgentMessage
 * metdata, with the only difference being that the extractions are regenerated. 
 *
 * If existing metadata is not DialogAgent metadata, copy it to the output
 * file unchanged.
 *
 * Do not process metadata files that do not contain DialogAgent metadata.
 *
 * If a .metadata file ends with Vers-<N>.metadata, the corresponding file
 * in the output directory should end with Vers-<N+1>.metadata (instead of
 * preserving the original fileName). This is to comply with the TA3 file
 * naming scheme.
 *
 * TODO Test if incuding the data.text field in the lookahead saves time.
 *
 * TODO Instead of returning boolean return a struct that counts the number
 *      of DialogAgent metadata items as well as the total number of all
 *      metadata items.
 */
class DialogAgentReprocess (
  val inputDirName: String,
  val outputDirName: String,
  override val nMatches: Int = 0
) extends DialogAgent {

  private lazy val logger = LoggerFactory.getLogger(this.getClass)

  /** Scan a string iterator for valid DialogAgent JSON
   *  @param iter:  An iterator containing json strings
   *  @returns: True if DialogAgent publication topic and data.text are found
   */
  @tailrec
  private def hasDialogAgentMetadata(
    iter: Iterator[String]
  ): Boolean = if(!iter.hasNext) false else {
    val line = iter.next
    val lookAheadOpt = readMetadataLookahead(line)
    if((lookAheadOpt.topic == topicPubDialogAgent) && 
      (getDialogAgentDataText(line).length > 0)) true
    else hasDialogAgentMetadata(iter)
  }

  val startTime = Clock.systemUTC.millis
  logger.info("Checking input files for DialogAgent metadata...")

  /* Find the names of files containing DialogAgent metadata */
  val fileNames: List[String] = LocalFileUtils.getFileNames(inputDirName)
    .filter(a => a.endsWith(".metadata"))
    .filter(a => hasDialogAgentMetadata(LocalFileUtils.lineIterator(a)))

  val reprocessingStartTime = Clock.systemUTC.millis
  // Only create the output directory if DialogAgent metadata exists
  if(fileNames.length > 0) {  
    logger.info(s"DialogAgent metadata files to be processed: ${fileNames.length}")
    // make sure the output directory is available
    logger.info("checking output directory")
    if(LocalFileUtils.ensureDir(outputDirName)) { 
      logger.info(s"Using output directory: $outputDirName")
      logger.info("Reprocessing...")
      val results = fileNames.map(processFile).flatten
      val all = results.length
      val passed = results.filter(a => a).length
      val failed = all - passed
      logger.info(s"Metadata processed: ${all}")
      logger.info(s"Errors encountered: ${failed}")
      if(failed == 0) 
        logger.info("All reprocessing completed successfully.")
      else 
        logger.error("There were problems during reprocessing.")
    }
  } else 
    logger.error("No .metadata files containing DialogAgent data were found")

  val stopTime = Clock.systemUTC.millis

  val runSecs = (stopTime-startTime)/1000.0
  val prepSecs = (reprocessingStartTime-startTime)/1000.0
  val compSecs = runSecs - prepSecs

  logger.info(s"Time to find DialogAgent metadata files:      ${prepSecs} seconds")
  logger.info(s"Time to reprocess DialogAgent metadata files: ${compSecs} seconds")
  logger.info(s"Reprocessor time (not including other init):  ${runSecs} seconds")

  /** Scan the line as a MetadataLookahead, containing the topic
   *  @param line:  A single JSON line
   *  @returns A MetadataLookahead struct
   */
  def readMetadataLookahead(line: String): MetadataLookahead = try {
    read[MetadataLookahead](line)
  } catch {
    case NonFatal(t) => new MetadataLookahead
  }
     

  def readTrialMessage(line: String): TrialMessage = try {
    read[TrialMessage](line)
  } catch {
    case NonFatal(t) => new TrialMessage(new CommonMsg)
  }

  /** process one input file
   * @param inputFileName The namee of the file to be processed
   * @returns true if successful
   */
  def processFile(inputFileName: String): List[Boolean] = {
    logger.info(s"Processing ${inputFileName}...")
    val start = Clock.systemUTC.millis
    val outputFileName = ta3FileName(inputFileName)
    val fileWriter = new PrintWriter(new File(outputFileName))
    val iterator = LocalFileUtils.lineIterator(inputFileName)
    val ret = processLines(iterator, fileWriter, List())
    val stop = Clock.systemUTC.millis
    val deltaSeconds = (stop-start)/1000.0
    val all = ret.length
    val passes = ret.filter(a => a).length
    val errors = all - passes
    logger.info(s"  Metatadata lines found: ${all}")
    logger.info(s"  Errors encountered:     ${errors}")

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
    result: List[Boolean]
  ): List[Boolean] = {
    if(!lines.hasNext) result
    else {
      val currentResult = processLine(lines.next, fileWriter)
      processLines(lines, fileWriter, currentResult::result)
    }
  }

  /** Reprocess line if DialogAgent-related metadata, otherwise copy
   * @param line One metadata JSON line
   * @param fileWriter Writes to the output file
   * @returns true if the line was processed successfully
   */
  def processLine(
    line: String,
    fileWriter: PrintWriter
  ): Boolean = readMetadataLookahead(line).topic match {
    case `topicPubDialogAgent` => processDialogAgentMetadata(line, fileWriter)
    case `topicSubTrial` => processTrialMetadata(line, fileWriter)
    case _ => 
      fileWriter.write(s"$line\n")
      true
  }

  /** Parse a TrialMessage and report Testbed config if trial start.
   * @param line JSON representation of one DialogAgentMessage struct
   * @param fileWriter Writes to the output file
   * @returns true
   */
  def processTrialMetadata(
    line: String,
    fileWriter: PrintWriter
  ): Boolean = {

    // Copy the input trial message string to the output file in all cases
    fileWriter.write(s"${line}\n")

    // If this is the start of a trial, follow with a VersionInfo message 
    // containing the DialogAgent Testbed configuration
    try {
      val trialMessage = read[TrialMessage](line)
      if(trialMessage.msg.sub_type == "start") {
        val timestamp = Clock.systemUTC.instant.toString
        val json = writeJson(VersionInfo(this, timestamp))
        fileWriter.write(s"${json}\n")
      }
      true
    } catch {
      case NonFatal(t) => 
        logger.error(s"Error parsing TrialMessage from JSON: ${line}\n")
        logger.error(t.toString)
        false
    }
  }

  /** reprocess DialogAgent metadata if possible, else copy existing metadata
   * @param line JSON representation of one DialogAgentMessage struct
   * @param fileWriter Writes to the output file
   * @returns true if the line was processed successfully
   */
  def processDialogAgentMetadata(
    line: String,
    fileWriter: PrintWriter
  ): Boolean = {
    val metadata: Option[JValue] = getMetadataOpt(line)
    if(metadata.isDefined) {
      val text = getDialogAgentDataText(line)
      if(text.length > 0){
        val newExtractions = getExtractions(text.head.toString)
        val newMetadata = metadata.head.replace(
          "data"::"extractions"::Nil, 
          newExtractions.head
        )
        fileWriter.write(s"${writeJson(newMetadata)}\n")
        true  // DialogAgent metadata successfully reprocessed
      } else {
        // dialog agent metadata but no text, 
        processDialogAgentOtherMetadata(line, fileWriter)
      }
    } else {  
      fileWriter.write(line)
      false // Not being able to parse the metadata is an error
    }
  }

  /** Process non-DialogAgent metadata that have the DialogAgent topic
   * @param line JSON representation of metadata
   * @param fileWriter Writes to the output file
   * @returns true if the line was processed successfully
   */
  def processDialogAgentOtherMetadata(
    line: String,
    fileWriter: PrintWriter
  ): Boolean = try {
    // try to read it as an error
    val mde = read[MetadataError](line)
    val error = mde.error
    val data = error.data
    true
  } catch {
    case NonFatal(t) => 
      fileWriter.write(line)
      logger.error(s"Could not parse DialogAgent report from metadata error")
      false
  }


  /** Get the DialogAgentMessage.data.text value
   *  @param line a JSON metadata line
   *  @returns The 'text' value in the 'data' struct of DialogAgent metadata
   */
  def getDialogAgentDataText(line: String): String = 
    try {
      val mdt = read[MetadataDataText](line)
      mdt.data.text
    } catch {
      case NonFatal(t) => 
        logger.error(s"Could not read data.text from ${line}")
        logger.error(t.toString)
        ""
    }


  /** Return the JSON AST value of a DialogAgentMessageDataExtraction array
   * @param text Get the extractions found in this text
   */
  def getExtractions(text: String): Option[JValue] = {
    val extract = extractions(text)  // case class array
    val json = write(extract)   // JSON string
    try Some(parse(json)) // JValue
    catch {
      case NonFatal(t) => None
    }
  }

  /** Return the value of the first field found in the jvalue matching the key
   * @param jvalue The value with key-value fields to search
   * @param key The key for the value to return
   * @returns The value found for the key, else None
   */
  def findFieldOpt(jvalue: JValue, key: String): Option[JValue] = {

    val fieldOpt: Option[JField] = jvalue.findField{
      case (n,v) => n == key
    }

    if(fieldOpt.isDefined) {
      Some(fieldOpt.head._2)
    } else {
      val json = write(jvalue)
      val json2 = write(parse(s""" ${json} """))
      logger.error(s"Could not find '${key}' field in ${json2}")
      None
    } 
  }

  // parse the metadata text into AST to fix nonstandard JSON issues
  def getMetadataOpt(line: String): Option[JValue] = try {
    Some(parse(s""" ${line} """))
  } catch {
    case NonFatal(t) => 
      logger.error(s"Error parsing metadata AST from input string: ${line}\n")
      logger.error(t.toString)
      None
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
