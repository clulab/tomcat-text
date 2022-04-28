package org.clulab.asist.agents

import com.typesafe.scalalogging.LazyLogging
import java.io.{File, PrintWriter}
import java.nio.file.Paths
import java.time.Clock
import org.clulab.asist.messages._
import org.clulab.utils.LocalFileUtils
import org.json4s.{Extraction,_}

import scala.annotation.tailrec
import scala.util.control.NonFatal
import scala.io.Source


/**
 * Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
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
 */

class DialogAgentReprocessor (
  val inputDirName: String = "",
  val outputDirName: String = "",
  val ta3Version: Option[Int] = None,
) extends DialogAgent with LazyLogging {

  logger.info(s"DialogAgentReprocessor version ${BuildInfo.version} running")

  // for the JSON extractor
  implicit val formats = org.json4s.DefaultFormats

  // make sure output directory is available
  if((outputDirName == "/dev/null") || (LocalFileUtils.ensureDir(outputDirName))) {

    // Find the .metadata files and see if we can reprocess them
    LocalFileUtils
      .getFileNames(inputDirName)
      .filter(_.endsWith(".metadata"))
      .filter(name => 
         withDialogAgentMetadata(name, Source.fromFile(name).getLines)
       )
      .foreach(processFile(_))
  }

  // return true if the file has at least one DialogAgentMessage
  def withDialogAgentMetadata (
    fileName: String,
    iter: Iterator[String]
  ): Boolean = {
    if(!iter.hasNext) false
    else if(readTopic(iter.next) == topicPubDialogAgent) true
    else withDialogAgentMetadata(fileName, iter)
  }

  def processFile(inputFileName: String): Unit = {
    val outputFileName: String = ta3FileName(inputFileName)
    logger.info(s"Processing file: ${inputFileName}")

    val lineIterator = Source.fromFile(inputFileName).getLines

    if(outputDirName == "/dev/null") {
      processFileWithPrinter(lineIterator, None)
    } else try {
      processFileWithPrinter(
        lineIterator,
        Some(new PrintWriter(new File(outputFileName)))
      )
    } catch {
      case NonFatal(t)  =>
        logger.error(s"Problem opening ${outputFileName} for writing:")
        logger.error(t.toString)
    }
  }

  def processFileWithPrinter(
    lineIterator: Iterator[String],
    pw: Option[PrintWriter]
  ): Unit = {
    while(lineIterator.hasNext) {
      processLine(lineIterator.next, pw)
    }

    pw.foreach(_.close)
  }




  /** Reprocess line if DialogAgent-related metadata, otherwise copy
   * @param rs State with input line loaded
   */
  def processLine(
    inputText: String,
    pw: Option[PrintWriter]
  ): Unit = readTopic(inputText) match {
    case `topicSubTrial` => 
      processTrialMetadata(inputText, pw)
    case `topicPubDialogAgent` => 
      reprocessDialogAgentMetadata(inputText, pw)
    case `topicPubVersionInfo` => 
      // Delete existing DialogAgent-generated VersionInfo
    case `topicPubRollcallResponse` => 
      // write a new rollcallResponse
    case _ => 
      // Transcribe Unhandled cases
      //processUnprocessedData(inputText,pw)
  }

  /** Parse a TrialMessage and report our configuration if trial start.
   * @param inputText JSON representation of one Trial message
   */
  def processTrialMetadata(
    line: String,
    pw: Option[PrintWriter]
  ): Unit = JsonUtils.readJson[TrialMessage](line) match {
    case Some(trialMessage) =>
      // transcribe the trial message 
     pw.foreach(_.write(line))

      TrialMessage(line) match {
        case Some(trial) =>
          if(TrialMessage.isStart(trial)) { 
            // write the version info message if trial start

            // metadata timestamp JValue
            val metadataTimestamp:JValue = Extraction.decompose(
              "@timestamp",trialMessage.msg.timestamp
            )

            // VersionInfo struct
            val versionInfo:VersionInfo = VersionInfo(trialMessage)

            // JValue representation of struct
            val versionInfoJValue:JValue = Extraction.decompose(versionInfo)

            // Merge of @timestamp into JValue 
            val outputJValue:JValue = 
              versionInfoJValue.merge(metadataTimestamp)

            // Write JValue to JSON
            val versionInfoJson:String = 
              JsonUtils.writeJsonNoNulls(outputJValue) + "\n"

            // write the version info message
            // TEST
            pw.foreach(_.write(versionInfoJson))
          }
        case _ =>
      } 
    case _ =>
  }

  /** Reprocess a metadata line that has the Dialog Agent topic
   * @param inputText: Metadata line to reprocess
   */
  def reprocessDialogAgentMetadata(
    line: String,
    pw: Option[PrintWriter]
  ): Unit = JsonUtils.parseJValue(line) match {
    case Some(metadataJValue: JValue) =>
      metadataJValue \ "data" match { 
        case dataJObject: JObject => 
          val data = dataJObject.extract[DialogAgentMessageData]
          val newData = data.copy(extractions = getExtractions(data.text))
          val newMetadata = metadataJValue.replace(
            "data"::Nil,
            Extraction.decompose(newData)
          )
          val json = JsonUtils.writeJsonNoNulls(newMetadata) + "\n"
          // TEST
          pw.foreach(_.write(json))
        case JNothing =>
          reprocessDialogAgentError(line, metadataJValue, pw)
        case _ => 
          logger.error("Unexpected non-JObject data in top level metadata")
          // TEST
          pw.foreach(_.write(line))
      }
    case _ => 
      logger.error("Unexpected non-JValue data in top level metadata")
      // TEST
      pw.foreach(_.write(line))
  }

  /** Recover a Dialog Agent Error report as a Dialog Agent Message
   * @param line: JSON text for error reporting
   * @param metadataJValue: JSON representation of input line
   */
  def reprocessDialogAgentError(
    line: String,
    metadataJValue: JValue,
    pw: Option[PrintWriter]
  ): Unit = {
    metadataJValue \ "error" \ "data" match {
      case dataJString: JString => 
        val text: String = dataJString.extract[String]
        JsonUtils.readJson[DialogAgentMessageData](text) match {
          case Some(data) =>
            val newData:DialogAgentMessageData = data.copy(
              extractions = getExtractions(data.text)
            )
            val newJson = Extraction.decompose(newData)
            val newMetadata = metadataJValue.transformField {
              case ("error", _) => ("data", newJson)
            }
            // TEST that this output is correct
            val json = JsonUtils.writeJsonNoNulls(newMetadata) + "\n"
            pw.foreach(_.write(json))
          case None =>
        }
      case _ =>
        logger.error("Expected error/data field not found in metadata")
        pw.foreach(_.write(line))
    }
  }

  /** show statistics at current iteration */
  /*
  def finalReport(): Unit = {
    val stopTime = Clock.systemUTC.millis
    val runSecs = (stopTime-startTime)/1000.0
    val prepSecs = (reprocessingStartTime-startTime)/1000.0
    val compSecs = runSecs - prepSecs
    logger.info("")
    logger.info("METADATA REPROCESSING SUMMARY:")
    logger.info("Output directory:     %s".format(outputDirName))
    logger.info("Input directory:      %s".format(inputDirName))
    logger.info("Input files present   %d".format(allFiles.length))
    logger.info("Time to filter files: %.1f minutes".format(prepSecs/60.0))
    logger.info("Time to reprocess:    %.1f minutes".format(compSecs/60.0))
  }
  */

  /** Change filename if it has a TA3 version number
   * @param inputFileName fileName that may have a TA3 version number
   * @return the inputFileName, with any TA3 version number incremented
   */
  def ta3FileName(inputFileName: String): String = {

    // get local fileName out of the input directory
    val localFileName = inputFileName.split("/").last

    // the output fileName is the local fileName in the output directory
    val outputPath = Paths.get(outputDirName,localFileName)
    val outputFileName = outputPath.toFile.getAbsolutePath

    // if the output fileName has a TA3 version number, increment it by 1
    val regex = """Vers-(\d+).metadata""".r
    regex.replaceAllIn(outputFileName, _ match {
      case regex(version) => 
        val newVersion: Int = ta3Version.getOrElse(version.toInt +1)
        s"Vers-${newVersion}.metadata"
      case _ => outputFileName  // otherwise do not change the fileame
    })
  }

  /** Scan the line for just the topic so we can branch on it
   *  @param line:  A single JSON line
   *  @return the topic if found, or the Topic.topic default value
   */
  def readTopic(line: String): String =
    JsonUtils.readJson[Topic](line).getOrElse(new Topic).topic
}
