package org.clulab.asist.agents

import java.io.{File, PrintWriter}
import java.nio.file.Paths
import java.time.Clock

import org.clulab.asist.messages._
import org.clulab.utils.LocalFileUtils
import org.slf4j.LoggerFactory
import org.json4s.{Extraction,_}
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization.{read,write}

import scala.annotation.tailrec
import scala.util.control.NonFatal

/**
 * Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 * Updated:  2021 July
 *
 * Reprocess Dialog Agent metadata files by reading them lines by line and then
 * writing the processed results to the output file.
 *
 * Reprocessed DialogAgent metadata is a copy of existing DialogAgentMessage
 * metdata with the extractions regenerated. 
 *
 * If existing metadata is not DialogAgent metadata, copy it to the output
 * file unchanged.
 *
 * If a .metadata file ends with Vers-<N>.metadata, the corresponding file
 * in the output directory should end with Vers-<N+1>.metadata (instead of
 * preserving the original fileName). This is to comply with the TA3 file
 * naming scheme.
 *
 * TODO:  Parallelize computation with actors
 *
 */
class DialogAgentReprocess (
  val inputDirName: String,
  val outputDirName: String,
  override val nMatches: Int = 0
) extends DialogAgent {

  private lazy val logger = LoggerFactory.getLogger(this.getClass)

  val startTime = Clock.systemUTC.millis
  logger.info("Checking input files for DialogAgent metadata...")

  /* Find the names of files containing DialogAgent metadata */
  val fileNames: List[String] = LocalFileUtils.getFileNames(inputDirName)
    .filter(a => a.endsWith(".metadata"))
    .filter(a => hasDialogAgentMetadata(LocalFileUtils.lineIterator(a)))

  val nFiles = fileNames.length

  val reprocessingStartTime = Clock.systemUTC.millis
  // Only create the output directory if DialogAgent metadata exists
  if(nFiles > 0) {  

    // give the user a heads up as to how much data will be run
    val totalInputLines = fileNames.map(n =>
      LocalFileUtils.lineIterator(n).length).sum

    logger.info(s"Files to be processed: ${nFiles}")
    logger.info(s"Lines to be processed: ${totalInputLines}")

    // make sure the output directory is available
    if(LocalFileUtils.ensureDir(outputDirName)) { 
      logger.info(s"Using output directory: $outputDirName")
      logger.info("Reprocessing files...")

      val totalLines = fileNames.map(processFile).sum

      val stopTime = Clock.systemUTC.millis
      val runSecs = (stopTime-startTime)/1000.0
      val prepSecs = (reprocessingStartTime-startTime)/1000.0
      val compSecs = runSecs - prepSecs
      logger.info("")
      logger.info("METADATA REPROCESSING COMPLETE:")
      logger.info("Input directory:    %s".format(inputDirName))
      logger.info("Output directory:   %s".format(outputDirName))
      logger.info("Files reprocessed   %d".format(nFiles))
      logger.info("Lines read:         %d".format(totalInputLines))
      logger.info("Lines written:      %d".format(totalLines))
      logger.info("DialogAgent scan:   %.1f minutes".format(prepSecs/60.0))
      logger.info("Time to reprocess:  %.1f minutes".format(compSecs/60.0))
    }
  } else 
    logger.error("No files containing DialogAgent metadata were found")


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
    if(lookAheadOpt.topic == topicPubDialogAgent) true
    else hasDialogAgentMetadata(iter)
  }

  /** Scan the line as a MetadataLookahead, return default if not readable
   *  @param line:  A single JSON line
   *  @returns A MetadataLookahead struct
   */
  def readMetadataLookahead(line: String): MetadataLookahead = try {
    read[MetadataLookahead](line)
  } catch {
    case NonFatal(t) => new MetadataLookahead
  }

  /** Scan the line as a TrialMessage, return default if not readable
   *  @param line:  A single JSON line
   *  @returns A TrialMessage struct
   */
  def readTrialMessage(line: String): TrialMessage = try {
    read[TrialMessage](line)
  } catch {
    case NonFatal(t) => new TrialMessage(new CommonMsg)
  }

  /** process one input file
   * @param inputFileName The namee of the file to be processed
   * @returns a list of the parse results for each JSON line in the file
   */
  def processFile(inputFileName: String): Int = {
    logger.info(s"Processing ${inputFileName}...")
    val outputFileName = ta3FileName(inputFileName)
    val iterator = LocalFileUtils.lineIterator(inputFileName)
    val outputLines = processLines(iterator, List()).reverse
    val length = outputLines.length
    logger.info(s"...lines processed: ${length}")
    // only write the output file if we have output
    if(length > 0) {
      val fileWriter = new PrintWriter(new File(outputFileName))
      outputLines.foreach(line => fileWriter.write(s"${line}\n"))
      fileWriter.close
    }
    length
  }

  /** Process all the lines in a string iterator
   * @param lines All the lines from an input file
   * @returns A list of all the lines, reprocessed
   */
  @tailrec
  private def processLines(
    lines: Iterator[String],
    result: List[String]
  ): List[String] = if(!lines.hasNext) result
    else processLines(lines, processLine(lines.next,result))

  /** Reprocess line if DialogAgent-related metadata, otherwise copy
   * @param line One metadata JSON line
   * @returns either the orignal line or a reprocessed version if it's ours
   */
  def processLine(
    line: String,
    result: List[String]): List[String] = 
    readMetadataLookahead(line).topic match {
      case `topicPubDialogAgent` => processDialogAgentMetadata(line, result)
      case `topicSubTrial` => processTrialMetadata(line, result)
      case _ => line::result
    }

  /** Parse a TrialMessage and report Testbed config if trial start.
   * @param line JSON representation of one DialogAgentMessage struct
   * @returns The original line always, with VersionInfo if trial start
   */
  def processTrialMetadata(
    line: String,
    result: List[String]): List[String] = {

    // return the line in all cases. If this is the start of a trial, 
    // follow with a VersionInfo message 
    try {
      val trialMessage = read[TrialMessage](line)
      if(trialMessage.msg.sub_type == "start") {
        val timestamp = Clock.systemUTC.instant.toString
        val json = write(VersionInfo(this, timestamp))
        line::json::result // original AND version info
      }
      else line::result
    } catch {
      case NonFatal(t) => 
        logger.error(s"processTrialMetadata: Could not parse: ${line}\n")
        logger.error(t.toString)
        line::result
    }
  }

  /** process DialogAgent metadata.
   * @param line JSON representation of one DialogAgentMessage struct
   * @returns The processed JSON line
   */
  def processDialogAgentMetadata(
    line: String,
    result: List[String]): List[String] = 
    // parse the metadata text into AST to fix nonstandard JSON issues
    getMetadataOpt(line).toList.map(metadata => {
      // if we have a data field, its DialogAgent metadata
      metadata.findField {
        case (n: String, data: JObject) => n == "data" 
        case _ => false
      }.toList.map(_._2.findField {
        case (n: String, text: JValue) => n == "text" 
        case _ => false
      }).toList.flatten.map(_._2.toString).map (text => {
        write(  
          metadata.replace( 
            "data"::"extractions"::Nil,
            JString(write(extractions(text)))
          )
        )
      })
    }).flatten match {
      case reprocessed::Nil => reprocessed::result
      case _ => processDialogAgentErrorMetadata(line, result) 
    }

  /** process DialogAgent error report metadata.
   * @param line JSON representation of one DialogAgentMessage struct
   * @returns The processed JSON line
   */
  def processDialogAgentErrorMetadata(
    line: String,
    result: List[String]
  ): List[String] =  {
    getMetadataOpt(line).toList.map(metadata => {
      // If we have an error field, it's an error report
      metadata.findField {
        case (n: String, v: JObject) => n == "error"
        case _ => false
      }.map(errorField => {
        // compose new data struct using existing fields and new extractions
        val mde = read[ErrorMetadata](line)
        val errorData = read[ErrorData](mde.error.data)
        val data = new DialogAgentMessageData(
          participant_id = errorData.participant_id,
          asr_msg_id = errorData.asr_msg_id,
          text = errorData.text,
          source = errorData.source,
          extractions = extractions(Option(errorData.text).getOrElse(""))
        )
        // substitute the new data struct for the error struct
        val newMetadata = metadata.transformField {
          case ("error", _) => ("data", JString(write(data)))
        }
        val newMetadataJson = write(newMetadata)
        logger.info(s"RECOVERING ERROR JSON:")
        logger.info(s"ORIGINAL:  ${line}")
        logger.info(s"RECOVERED: ${newMetadataJson}")
        newMetadataJson
      })
    }).flatten match {
      case reprocessed::Nil => reprocessed::result
      case _ => {  // If neither DialogAgent data or error, report problem.
        logger.error(
          s"processDialogAgentErrorMetadata: Could not parse: ${line}\n"
        )
        line::result
      }
    }
  }

  /** Parse the line into JSON 
   * @param line Hopefully JSON but could be anything the user tries to run
   * @return A JSON value parsed from the line or None
   */
  def getMetadataOpt(line: String): Option[JValue] = try {
    Some(parse(s""" ${line} """))
  } catch {
    case NonFatal(t) => 
      logger.error(s"getMetadataOpt: Could not parse: ${line}\n")
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
