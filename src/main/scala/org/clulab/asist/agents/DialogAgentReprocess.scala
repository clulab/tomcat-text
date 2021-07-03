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
    if(lookAheadOpt.topic == topicPubDialogAgent) true
    else hasDialogAgentMetadata(iter)
  }

  val startTime = Clock.systemUTC.millis
  logger.info("Checking input files for DialogAgent metadata...")

  /* Find the names of files containing DialogAgent metadata */
  val fileNames: List[String] = LocalFileUtils.getFileNames(inputDirName)
    .filter(a => a.endsWith(".metadata"))
    .filter(a => hasDialogAgentMetadata(LocalFileUtils.lineIterator(a)))
  logger.info(s"DialogAgent metadata files to be processed: ${fileNames.length}")
  fileNames.foreach(logger.info)


  val reprocessingStartTime = Clock.systemUTC.millis
  // Only create the output directory if DialogAgent metadata exists
  if(fileNames.length > 0) {  
    // make sure the output directory is available
    if(LocalFileUtils.ensureDir(outputDirName)) { 
      logger.info(s"Using output directory: $outputDirName")
      logger.info("Reprocessing...")
      fileNames.map(processFile)
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

  /** Scan the line as a MetadataLookahead, return default struct if not readable
   *  @param line:  A single JSON line
   *  @returns A MetadataLookahead struct
   */
  def readMetadataLookahead(line: String): MetadataLookahead = try {
    read[MetadataLookahead](line)
  } catch {
    case NonFatal(t) => new MetadataLookahead
  }
     

  /** Scan the line as a TrialMessage, return default struct if not readable
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
  def processFile(inputFileName: String): Unit = {
    logger.info(s"Processing ${inputFileName}...")
    val start = Clock.systemUTC.millis
    val outputFileName = ta3FileName(inputFileName)
    val iterator = LocalFileUtils.lineIterator(inputFileName)
    val lines = processLines(iterator, List())
    // only write the output file if we have output
    if(lines.length > 0) {
      val fileWriter = new PrintWriter(new File(outputFileName))
      lines.reverse.foreach(line => fileWriter.write(s"${line}\n"))
      fileWriter.close
    }
    val stop = Clock.systemUTC.millis
    val deltaSeconds = (stop-start)/1000.0
  }

  @tailrec
  private def processLines(
    lines: Iterator[String],
    result: List[String]
  ): List[String] = if(!lines.hasNext) result
    else processLines(lines, processLine(lines.next)::result)
  

  /** Reprocess line if DialogAgent-related metadata, otherwise copy
   * @param line One metadata JSON line
   * @param fileWriter Writes to the output file
   * @returns true if the line was processed successfully
   */
  def processLine(
    line: String,
  ): String = readMetadataLookahead(line).topic match {
    case `topicPubDialogAgent` => processDialogAgentMetadata(line)
    case `topicSubTrial` => processTrialMetadata(line)
    case _ => line
  }

  /** Parse a TrialMessage and report Testbed config if trial start.
   * @param line JSON representation of one DialogAgentMessage struct
   * @param fileWriter Writes to the output file
   * @returns true if we could read parse the TrialMessage successfully
   */
  def processTrialMetadata(
    line: String,
  ): String = {

    // If this is the start of a trial, follow with a VersionInfo message 
    // containing the DialogAgent Testbed configuration
    try {
      val trialMessage = read[TrialMessage](line)
      if(trialMessage.msg.sub_type == "start") {
        val timestamp = Clock.systemUTC.instant.toString
        val json = write(VersionInfo(this, timestamp))
        s"${line}\n${json}"  // original AND version info if trial start
      }
      line  
    } catch {
      case NonFatal(t) => 
        logger.error(s"processTrialMetadata: Error parsing TrialMessage from: ${line}\n")
        logger.error(t.toString)
        line
    }
  }

  /** process DialogAgent metadata in its many forms
   * @param line JSON representation of one DialogAgentMessage struct
   * @param fileWriter Writes to the output file
   * @returns true if the line was processed successfully
   */
  def processDialogAgentMetadata(
    line: String
  ): String = {
    // parse the metadata text into AST to fix nonstandard JSON issues
    getMetadataOpt(line).toList.map(metadata => {

      // if we have a data field, its DialogAgent metadata
      metadata.findField {
        case (n: String, data: JObject) => n == "data" 
        case _ => false
        }.toList.map(f => f._2.findField {
          case (n: String, text: JValue) => n == "text" 
          case _ => false
        }).toList.flatten.map(_._2.toString).map (text => {
          val newExtractions = extractions(text)
          val newMetadata = metadata.replace(
          "data"::"extractions"::Nil, 
          parse(write(newExtractions)) // convert struct to json to JValue
        )
        write(newMetadata)
      })


    }).flatten match {
      case result::tail => result
      case _ => line
    }

    //TODO handle error case
  }

    /*
      val dataValue: List[JValue] = dataField.map(_._2)

      val textField: List[JField] = dataValue.map(_.findField {
          case (n: String, v: JValue) => n == "text"
          case _ => false
        }).flatten

      val textValue: List[JValue] = textField.map(_._2)

      val text: List[String] = textValue.map(value => {
        val newExtractions = extractions(value)
        val newMetadata = metadata.replace(
          "data"::"extractions"::Nil, 
          parse(write(newExtractions)) // convert struct to json to JValue
        )
        write(newMetadata)
      })

    }).flatten


    line
  }

      ::

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
        val dataJson = write(data)
        val dataJValue = parse(dataJson)

        // substitute the new data struct for the error struct
        val newMetadata = metadata.transformField {
          case ("error", _) => ("data", dataJValue)
        }
        val newMetadataJson = write(newMetadata)
        logger.info(s"RECOVERING ERROR JSON:")
        logger.info(s"ORIGINAL:  ${line}")
        logger.info(s"RECOVERED: ${newMetadataJson}")
        write(newMetadataJson)
      })
    })

    } else {  
      logger.error(s"processDialogAgentMetadata (data): Could not parse ${line}")
      fileWriter.write(s"${line}\n")
      false // Not being able to parse the metadata is an error
    }
     */

  /** Get the DialogAgentMessage.data.text value
   *  @param line a JSON metadata line
   *  @returns The 'text' value in the 'data' struct of DialogAgent metadata
   */
  def getDialogAgentDataText(line: String): String = 
    try {
      val mdt = read[MetadataDataText](line)
      Option(mdt.data.text).getOrElse("")
    } catch {
      case NonFatal(t) => 
        logger.error(s"getDialogAgentDataText: Could not parse: ${line}")
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

  // parse the metadata text into AST to fix nonstandard JSON issues
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
