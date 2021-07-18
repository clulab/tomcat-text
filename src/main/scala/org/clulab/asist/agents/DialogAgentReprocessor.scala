package org.clulab.asist.agents

import com.typesafe.scalalogging.LazyLogging

import java.io.{File, PrintWriter}
import java.nio.file.Paths
import java.time.Clock

import org.clulab.asist.messages._
import org.clulab.utils.LocalFileUtils
import org.json4s.JField
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
 * Reprocess metadata JSON files by reading each line as a JValue and then 
 * processing according to the topic field.  Lines with topics not addressed
 * below are copied to the output file.
 *
 * If an input file has no dialog agent related metadata, an output file is
 * not generated.  If there are no output files to be written, the output
 * directory is not created.
 *
 * Reprocessed DialogAgentMessage metadata is a copy of the existing metadata
 * with the extractions regenerated. 
 *
 * Reprocessed Dialog Agent error messages use the error.data field to generate
 * a new DialogAgentMessageData struct with new extractions.  This replaces
 * the error field to transform the metadata into DialogAgentMessage metadata.
 *
 * VersionInfo metadata with the DialogAgent topic are not reprocessed or 
 * copied to the output file.
 *
 * Trial Start metadata are copied to the output file followed by a new
 * VersionInfo metadata message with the DialogAgent topic.
 *
 * If a .metadata file ends with Vers-<N>.metadata, the corresponding file
 * in the output directory should end with Vers-<N+1>.metadata (instead of
 * preserving the original fileName). This is to comply with the TA3 file
 * naming scheme.
 *
 *
 * At the time of this writing, the biggest computational bottleneck is
 * running the extractions.
 *
 */
class DialogAgentReprocessor (
  val inputDirName: String = "",
  val outputDirName: String = "",
  override val args: DialogAgentArgs = new DialogAgentArgs
) extends DialogAgent with LazyLogging {

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
      case `topicPubDialogAgent` => reprocessDialogAgentMetadata(line, result)
      case `topicSubTrial` => processTrialMetadata(line, result)
      case `topicPubVersionInfo` => result  // delete existing VersionInfo lines
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

        // current timestamp
        val timestamp = Clock.systemUTC.instant.toString

        // metadata timestamp
        val metadataTimestamp = 
          Extraction.decompose(("@timestamp",trialMessage.msg.timestamp))

        val versionInfoMetadata = 
          VersionInfoMetadata(this, trialMessage, timestamp)
        val versionInfoJValue = Extraction.decompose(versionInfoMetadata)
        val outputJValue = versionInfoJValue.merge(metadataTimestamp)
        val json = write(outputJValue)
        json::line::result // original then version info
      }
      else line::result
    } catch {
      case NonFatal(t) => 
        logger.error(s"processTrialMetadata: Could not parse: ${line}\n")
        logger.error(t.toString)
        line::result
    }
  }

  /** Replace DialogAgentMessage data extractions with fresh ones.
   * @param line JSON representation of one DialogAgentMessage struct
   * @returns The processed JSON line
   */
  def reprocessDialogAgentMetadata(
    line: String,
    result: List[String]): List[String] = 
    // parse the metadata text into AST to fix nonstandard JSON issues
    parseJValue(line).toList.map(metadata => {
      // if we have a data field, its DialogAgent metadata
      metadata.findField {
        case (n: String, data: JObject) => n == "data" 
        case _ => false
      }.toList.map(_._2.findField {
        case (n: String, text: JValue) => n == "text" 
        case _ => false
      }).toList.flatten.map(_._2).map (textJValue => {
        val text: String = textJValue.extract[String]
        write(  
          // replace the data.extractions field value
          metadata.replace( 
            "data"::"extractions"::Nil,
            Extraction.decompose(extractions(text))
          )
        )
      })
    }).flatten match {
      case reprocessed::Nil => reprocessed::result
      case _ => reprocessDialogAgentErrorMetadata(line, result) 
    }


  /* Replace the error field with a reprocessed DialogAgentMessageData struct
   * @param line A JSON representation DialogAgentMessage error struct
   * @returns a DialogAgentMessage with new extractions
   */
  def reprocessDialogAgentErrorMetadata(
    line: String,
    result: List[String]
  ): List[String] =  {
    parseJValue(line).toList.map(metadata => {
      // If we have an error field, it's an error report
      metadata.findField {
        case (n: String, data: JObject) => n == "error"
        case _ => false
      }.toList.map(_._2.findField {
        case (n: String, text: JValue) => n == "data"
        case _ => false
      }).toList.flatten.map(_._2).map(dataJString => {
        val data = dataJString.extract[String]
        val newData = reprocessDialogAgentMessageData(data)
          // reprocess error report by replacing the error struct 
          // with a DialogAgentMessageData struct with new extractions
        val newMetadata = metadata.transformField {
          case ("error", _) => ("data", Extraction.decompose(newData))
        }
        write(newMetadata)
      })
    }).flatten match {
      case reprocessed::Nil => reprocessed::result
      case _ => 
        logger.error(
          s"reprocessDialogAgentErrorMetadata: Could not parse: ${line}\n"
        )
        line::result
    }
  }

  /* Update extractions for a DialogAgentMessageData struct
   * @param json A JSON representation of a DialogAgentMessageData struct
   * @returns A new DialogAgentMessageData struct with new extractions
   */
  def reprocessDialogAgentMessageData(
    json: String
  ): DialogAgentMessageData = try {
    val data = read[DialogAgentMessageData](json)
    new DialogAgentMessageData(
      participant_id = data.participant_id,
      asr_msg_id = data.asr_msg_id,
      text = data.text,
      source = new DialogAgentMessageDataSource(
        source_type = data.source.source_type,
        source_name = data.source.source_name
      ),
      extractions = extractions(data.text)
    )
  } catch {
    case NonFatal(t) => {
      logger.error(s"reprocessDialogAgentMessageData could not parse ${json}")
      logger.error(t.toString)
      new DialogAgentMessageData(source = new DialogAgentMessageDataSource)
    }
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

  /** Parse the line into a JValue
   * @param line Hopefully JSON but could be anything the user tries to run
   * @return A JSON value parsed from the line or None
   */
  def parseJValue(line: String): Option[JValue] = try {
    Some(parse(s""" ${line} """))
  } catch {
    case NonFatal(t) => 
      logger.error(s"parseJValue: Could not parse: ${line}\n")
      logger.error(t.toString)
      None
  }
}
