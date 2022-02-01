package org.clulab.asist.agents

import akka.actor.ActorSystem
import com.typesafe.scalalogging.LazyLogging
import java.io.{File, PrintWriter}
import java.nio.file.Paths
import java.time.Clock
import org.clulab.asist.messages._
import org.clulab.utils.LocalFileUtils
import org.json4s.{Extraction,_}

import scala.annotation.tailrec
import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import scala.util.control.NonFatal
import scala.util.{Failure, Success}

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
  tdacUrl: Option[String] = None,
) extends TdacAgent(tdacUrl) with LazyLogging {

  logger.info(s"DialogAgentReprocessor version ${BuildInfo.version}")

  // for the JSON extractor
  implicit val formats = org.json4s.DefaultFormats

  // init the TDAC server connection
  tdacInit

  // actors
  implicit val ec = ExecutionContext.global
  implicit val system: ActorSystem = ActorSystem("DialogAgentReprocessor")

  /** Scan all of the input files for those containing Dialog Agent metadata
   *  @param iter:  An iterator containing json strings
   *  @return: True if DialogAgent publication topic and data.text are found
   */
  @tailrec
  private def findDialogAgentMetadataFiles(
    allFiles: List[String],
    dialogAgentFiles: List[String]
  ): List[String] = allFiles match {
    case head::tail =>
      if(head.endsWith(".metadata") && 
        fileHasDialogAgentMetadata(LocalFileUtils.lineIterator(head))
      ) {
        println(s"Yes: ${head}")  // update user with file status
        findDialogAgentMetadataFiles(tail, head::dialogAgentFiles)
      }
      else {
        println(s"No:  ${head}")  // update user with file status
        findDialogAgentMetadataFiles(tail, dialogAgentFiles)
      }
    case _ => dialogAgentFiles
  }

  val startTime = Clock.systemUTC.millis
  val allFiles: List[String] = LocalFileUtils.getFileNames(inputDirName)

  logger.info(s"Files in input directory: ${allFiles.length}")
  logger.info("Checking files for DialogAgent metadata...")

  val fileNames = findDialogAgentMetadataFiles(allFiles, List())
  val nFiles = fileNames.length
  val fileLineCounts = fileNames.map(n =>LocalFileUtils.lineIterator(n).length)
  val reprocessingStartTime = Clock.systemUTC.millis
  val fileInfo = fileNames.zip(fileLineCounts)
  val fileInfoIterator = fileInfo.iterator

  // weak.   make a double nested list of iterators
  var lineIterator: Iterator[String] = List().iterator
  var fileWriter: Option[PrintWriter] = None

  // Only create the output directory if DialogAgent metadata exists
  if(nFiles > 0) {  
    // make sure the output directory is available
    if(LocalFileUtils.ensureDir(outputDirName)) { 
      logger.info(s"Using output directory: ${outputDirName}")
      logger.info(s"Files to be processed: ${nFiles}")
      logger.info(s"Total lines to be processed: ${fileLineCounts.sum}")
      logger.info("Reprocessing files...")
      doNextJob
    } 
  }  else {
    logger.error("No files with DialogAgent metadata were found")
    finish
  }

  /** Scan a string iterator for valid DialogAgent JSON
   *  @param iter:  An iterator containing json strings
   *  @return: True if DialogAgent publication topic and data.text are found
   */
  @tailrec
  private def fileHasDialogAgentMetadata(
    iter: Iterator[String]
  ): Boolean = 
    if(!iter.hasNext) false
    else if(readTopic(iter.next) == topicPubDialogAgent) true
    else fileHasDialogAgentMetadata(iter)

  /** Scan the line for just the topic so we can branch on it
   *  @param line:  A single JSON line
   *  @return the topic if found, or the Topic.topic default value
   */
  def readTopic(line: String): String =
    JsonUtils.readJson[Topic](line).getOrElse(new Topic).topic

  /** Reprocess line if DialogAgent-related metadata, otherwise copy
   * @param rs State with input line loaded
   */
  def processLine(inputText: String): Unit = readTopic(inputText) match {
    case `topicSubTrial` => processTrialMetadata(inputText)
    case `topicPubDialogAgent` => reprocessDialogAgentMetadata(inputText)
    case `topicPubVersionInfo` => 
      // Delete existing DialogAgent-generated VersionInfo
      doNextJob
    case _ => 
      // Transcribe Unhandled cases
      finishIteration(BusMessage("",inputText)) 
  }

  /** Parse a TrialMessage and report our configuration if trial start.
   * @param inputText JSON representation of one Trial message
   */
  def processTrialMetadata(
    inputText: String
  ): Unit = JsonUtils.readJson[TrialMessage](inputText) match {
    case Some(trialMessage) =>

      // transcribe the trial start message 
      val trialOutput = BusMessage(topicSubTrial, inputText)

      // If this is the start of a trial, write the input line and 
      // then follow with a VersionInfo message 
      if(trialMessage.msg.sub_type == "start") {  // TODO use TrialMessage filter

        // metadata timestamp JValue
        val metadataTimestamp:JValue = 
          Extraction.decompose(("@timestamp",trialMessage.msg.timestamp))

        // VersionInfo struct
        val versionInfo:VersionInfo = VersionInfo(trialMessage)

        // JValue representation of struct
        val versionInfoJValue:JValue = Extraction.decompose(versionInfo)

        // Merge of @timestamp into JValue 
        val outputJValue:JValue = 
          versionInfoJValue.merge(metadataTimestamp)

        // Write JValue to JSON
        val versionInfoJson:String = JsonUtils.writeJson(outputJValue)

        // write the version info message
        val versionInfoOutput:BusMessage = 
          BusMessage(topicPubVersionInfo, versionInfoJson)

        // we send out the original trial message and the version info
        val outputMessages:List[BusMessage] = 
          List(trialOutput, versionInfoOutput)

        tdacClient.foreach(_.resetServer)
        finishIteration(outputMessages)
      } else {
        // if not a trial start just transcribe the trial message
        finishIteration(trialOutput)
      }
    case _ =>
  }

  /** Reprocess a metadata line that has the Dialog Agent topic
   * @param inputText: Metadata line to reprocess
   */
  def reprocessDialogAgentMetadata(
    inputText: String
  ): Unit = JsonUtils.parseJValue(inputText) match {
    case Some(metadataJValue: JValue) =>
      metadataJValue \ "data" match { 
        case dataJObject: JObject => 
          val data = dataJObject.extract[DialogAgentMessageData]
          val newData = data.copy(extractions = getExtractions(data.text))
          tdacClient match {
            case Some(tc: TdacClient) => 
              tc.runClassification("", newData, metadataJValue)
            case None => 
              val newMetadata = metadataJValue.replace(
                "data"::Nil,
                Extraction.decompose(newData)
              )
              finishIteration(
                BusMessage(
                  "", 
                  JsonUtils.writeJson(newMetadata)
                )
              )
          }
        case JNothing =>
          reprocessDialogAgentError(inputText, metadataJValue)
        case _ => 
          reportProblem(
            inputText, 
            "Unexpected non-JObject data in top level metadata"
          )
      }
    case _ => 
      reportProblem(
        inputText,
        "Unexpected non-JValue data in top level metadata"
      )
  }

  /** Recover a Dialog Agent Error report as a Dialog Agent Message
   * @param inputText: JSON text for error reporting
   * @param metadataJValue: JSON representation of input line
   */
  def reprocessDialogAgentError(
    inputText: String,
    metadataJValue: JValue
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
            tdacClient match {
              case Some(tc: TdacClient) => 
                tc.runClassification("", data, newMetadata)
              case None =>
                finishIteration
            }
          case None =>
            finishIteration
        }
      case _ =>
        reportProblem(
          inputText,
          "Expected error/data field not found in metadata"
        )
    }
  }

  /** Write the output of the current iteration and start the next
   * @param messages: List of messages to be written to the Message Bus
   */
  private def finishIteration(messages: List[BusMessage]): Unit = {

    // we need a new thread or will overflow the stack
    val future: Future[Unit] = Future {writeOutput(messages)}
    future onComplete {
      case Success(value:Unit) => 
        doNextJob
      case Failure(t) => 
        logger.error(s"An error occured: ${t}")
        doNextJob
    }
  }

  private def finishIteration(message: BusMessage): Unit =
    finishIteration(List(message))

  private def finishIteration(): Unit = finishIteration(List())

  def writeOutput(text: String): Unit = fileWriter match {
    case Some(fw: PrintWriter) =>  
      try {
        fw.write(text+"\n")
      } catch {
        case NonFatal(t) =>
          logger.error(s"Error writing to output file:  ${t}")
      }   
    case _ =>  
      logger.error("write called without a PrintWriter")
  }

  /** Write the iteration results to the output file.
   * @param messages a list of message to write to the bus
   * @return The run state updated with the results of the write
   */
  override def writeOutput(messages: List[BusMessage]): Unit = 
    messages.foreach(m => writeOutput(m.text))

  /** Nested iteration through files and their lines of metadata */
  def doNextJob(): Unit = {
   
    // if we have another metadata line, run it.
    if(lineIterator.hasNext) {
      processLine(lineIterator.next)
    }
    else {
      // otherwise close out this file
      fileWriter.foreach(fw => fw.close)

      // if we have another file, run it
      if(fileInfoIterator.hasNext) {
        val fileInfo = fileInfoIterator.next
        val inputFileName = fileInfo._1
        val inputFileLines = fileInfo._2
        val outputFileName = ta3FileName(inputFileName)
        lineIterator = LocalFileUtils.lineIterator(inputFileName)
        fileWriter = Some(new PrintWriter(new File(outputFileName)))

        val advisory = if (inputFileLines == 1) 
          s"Reading 1 line from ${inputFileName}"
        else 
          s"Reading ${inputFileLines} lines from ${inputFileName}"

        logger.info(advisory)

        doNextJob()
      }
      // if no more files, we are done
      else finish
    }
  }

  /** Graceful agent shutdown */
  def finish() {
    logger.info("All file processing has finished.")
    system.terminate
    tdacClient.foreach(_.terminateActorSystem)
    finalReport
  }

  /** show statistics at current iteration */
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

  /** log the problem and write the input line to the output file
   * @param report: A description of what happened
   * @param inputText: The input that caused it to happen
   */
  def reportProblem (
    report: String,
    inputText: String
  ): Unit = {
    logger.error(s"${report}: ${inputText}")
    finishIteration(BusMessage("", inputText))
  }

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
}
