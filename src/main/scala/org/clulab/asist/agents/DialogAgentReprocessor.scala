package org.clulab.asist.agents

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.util.ByteString
import com.typesafe.scalalogging.LazyLogging
import java.io.{File, PrintWriter}
import java.nio.file.Paths
import java.time.Clock
import org.clulab.asist.messages._
import org.clulab.utils.LocalFileUtils
import org.json4s.{Extraction,_}
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read,write}

import scala.annotation.tailrec
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.language.postfixOps
import scala.util.control.NonFatal
import scala.util.{Failure, Success}

/**
 * Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 * Updated:  2021 August
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

case class Error(
  data: String = "",
)

case class DialogMessageError(
  topic: String,
  error: Error
)

class DialogAgentReprocessor (
  val inputDirName: String = "",
  val outputDirName: String = "",
  override val args: DialogAgentArgs = new DialogAgentArgs
) extends DialogAgent(args)
    with DacAgent
    with LazyLogging {

  // actors
  implicit val ec = ExecutionContext.global
  implicit val system: ActorSystem = ActorSystem("DialogAgentReprocessor")

  // json
  implicit val formats = org.json4s.DefaultFormats

  // Dialog Act Classification.  No instantiation if not used.
  val dacClient: Option[DacClient] = 
    if(withClassifications) Some (new DacClient(this)) else None

  @tailrec
  private def findDialogAgentMetadataFiles(
    allFiles: List[String],
    dialogAgentFiles: List[String]
  ): List[String] = allFiles match {
    case head::tail =>
      if(head.endsWith(".metadata") && 
        fileHasDialogAgentMetadata(LocalFileUtils.lineIterator(head))
      ) {
        println(s"Yes: ${head}")
        findDialogAgentMetadataFiles(tail, head::dialogAgentFiles)
      }
      else {
        println(s"No:  ${head}")
        findDialogAgentMetadataFiles(tail, dialogAgentFiles)
      }
    case _ => dialogAgentFiles
  }

  val allFiles: List[String] = LocalFileUtils.getFileNames(inputDirName)

  logger.info(s"Files in input directory: ${allFiles.length}")

  val startTime = Clock.systemUTC.millis

  logger.info("Checking files for DialogAgent metadata...")

  val fileNames = findDialogAgentMetadataFiles(allFiles, List())
  val nFiles = fileNames.length

  val reprocessingStartTime = Clock.systemUTC.millis

  // Only create the output directory if DialogAgent metadata exists
  if(nFiles > 0) {  
    // make sure the output directory is available
    if(LocalFileUtils.ensureDir(outputDirName)) { 
      logger.info(s"Using output directory: ${outputDirName}")

      // give the user a heads up as to how much data will be run
      // TODO profile this call on a full bucket and see how long it takes.
      val fileSizes = 
        fileNames.map(n =>LocalFileUtils.lineIterator(n).length)

      logger.info(s"Files to be processed: ${nFiles}")
      logger.info(s"Total lines to be processed: ${fileSizes.sum}")

      logger.info("Reprocessing files...")

      val startState = RunState(
        fileInfoIterator = fileNames.zip(fileSizes).iterator
      )

      fileIteration(startState)
    } 
  }  else {
    logger.error("No files with DialogAgent metadata were found")
    finish(new RunState)
  }

  /** Scan a string iterator for valid DialogAgent JSON
   *  @param iter:  An iterator containing json strings
   *  @return: True if DialogAgent publication topic and data.text are found
   */
  @tailrec
  private def fileHasDialogAgentMetadata(
    iter: Iterator[String]
  ): Boolean = if(!iter.hasNext) false
  else {
    val string = iter.next
    val lookAhead = readMetadataLookahead(string)
    if(lookAhead.topic == topicPubDialogAgent) true
    else fileHasDialogAgentMetadata(iter)
  }

  /** Scan the line as a MetadataLookahead, return default if not readable
   *  @param line:  A single JSON line
   *  @return A MetadataLookahead struct
   */
  def readMetadataLookahead(line: String): MetadataLookahead = try {
    read[MetadataLookahead](line)
  } catch {
    case NonFatal(t) => new MetadataLookahead
  }

  // Nested iteration through files and their lines of metadata 
  override def iteration(rs: RunState): Unit = {
    // this can be set for instance if we lose contact with the DAC server
    if(rs.terminated) {
      logger.info("File processing terminated.")
      finish(rs)
    }
    // if we have another line, run it.
    else if(rs.lineIterator.hasNext) {
      val rs1 = RSM.addLineRead(rs)
      val rs2 = RSM.setInputLine(rs1, rs.lineIterator.next)
      processLine(rs2)
    }
    else {
      // otherwise close out this file
      rs.fileWriter.foreach(fw => fw.close)

      // start next file
      fileIteration(rs)
    }
  }

  def fileIteration(rs: RunState): Unit = {

    // if we have another file, run it
    if(rs.fileInfoIterator.hasNext) {
      val fileInfo = rs.fileInfoIterator.next
      val inputFileName = fileInfo._1
      val inputFileLines = fileInfo._2
      val outputFileName = ta3FileName(inputFileName)
      val rs1 = rs.copy(
        lineIterator = LocalFileUtils.lineIterator(inputFileName),
        fileWriter = Some(new PrintWriter(new File(outputFileName))),
      )
      val advisory = if (inputFileLines == 1) 
        s"Reading 1 line from ${inputFileName}"
      else 
        s"Reading ${inputFileLines} lines from ${inputFileName}"
      logger.info(advisory)
      iteration(rs1)
    }
    // otherwise done
    else {
      logger.info("All file processing has finished.")
      finish(rs)
    }
  }

  // we are done
  def finish(rs: RunState) {
    system.terminate()
    dacClient.foreach(_.shutdown)
    finalReport(rs)
  }

  /** Reprocess line if DialogAgent-related metadata, otherwise copy
   * @param rs State with input line loaded
   */
  def processLine(rs: RunState): Unit = {
    logger.info("processLine")
    readMetadataLookahead(rs.inputLine).topic match {
      case `topicSubTrial` => processTrialMetadata(rs)
      case `topicPubDialogAgent` => reprocessDialogAgentMetadata(rs)
      case `topicPubVersionInfo` => 
        // Delete existing DialogAgent-generated VersionInfo
        futureIteration(rs)
      case _ => 
        // Trascribe Unhandled cases
        val rs1 = RSM.setOutputLine(rs, rs.inputLine)
        futureIteration(rs1) 
    }
  }

  /** Parse a TrialMessage and report Testbed config if trial start.
   * @param line JSON representation of one DialogAgentMessage struct
   * @return The original line always, with VersionInfo if trial start
   */
  def processTrialMetadata(rs: RunState): Unit = try {
    logger.info("processTrialMetadata")
    val trialMessage = read[TrialMessage](rs.inputLine)

    // If this is the start of a trial, write the input line and 
    // then follow with a VersionInfo message 
    if(trialMessage.msg.sub_type == "start") {

      // current timestamp string
      val currentTimestamp = Clock.systemUTC.instant.toString

      // metadata timestamp JValue
      val metadataTimestamp = 
        Extraction.decompose(("@timestamp",trialMessage.msg.timestamp))

      // VersionInfoMetadata struct
      val versionInfoMetadata = 
        VersionInfoMetadata(this, trialMessage, currentTimestamp)

      // JValue representation of struct
      val versionInfoJValue = Extraction.decompose(versionInfoMetadata)

      // Merge of @timestamp into JValue 
      val outputJValue = versionInfoJValue.merge(metadataTimestamp)

      // Write JValue to JSON
      val versionInfoJson = write(outputJValue)

      // output the original line and then the Version Info line
      val rs1 = RSM.setOutputLines(rs, List(rs.inputLine, versionInfoJson))
      val rs2 = RSM.addInfoWrite(rs1)
 
      if(withClassifications) 
        dacClient.foreach(_.resetServer(this, rs2))
      else
       futureIteration(rs2)
    } else {
      // if not a trial start just transcribe the input line
      val rs1 = RSM.setOutputLine(rs, rs.inputLine)
      futureIteration(rs1)
    }
  } catch {
    case NonFatal(t) =>
      logger.error(t.toString)
      reportProblem(rs, "Could not parse Trial metadata")
  }

  def reprocessDialogAgentMetadata(
    rs: RunState
  ): Unit = parseJValue(rs.inputLine) match {
    case Some(metadataJValue: JValue) =>
      logger.info("found metadataJValue")
      reprocessDialogAgentMessage(rs, metadataJValue)
    case _ => reportProblem(rs, "Could not parse metadata")
  }

  def reprocessDialogAgentMessage(
    rs: RunState,
    metadataJValue: JValue
  ): Unit = {
    logger.info("reprocessDialogAgentMessage")
    metadataJValue \ "data" match { 
      case dataJObject: JObject => 
        val data = dataJObject.extract[DialogAgentMessageData]
        val newData = data.copy(extractions = getExtractions(data.text))
        val rs1 = RSM.addReprocessed(rs)
        if(withClassifications) 
          dacClient.foreach(
            _.runClassification(this, rs1, newData, metadataJValue)
          )
        else {
          val newMetadata = metadataJValue.replace(
            "data"::Nil,
            Extraction.decompose(newData)
          )
          val rs2 = RSM.setOutputLine(rs1, write(newMetadata))
          futureIteration(rs2)
        }
      case JNothing =>
        reprocessDialogAgentError(rs, metadataJValue)
      case _ => 
        reportProblem(rs, "Unexpected non-JObject data in top level metadata")
    }
  }

  def reprocessDialogAgentError(
    rs: RunState,
    metadataJValue: JValue
  ): Unit = {
    logger.info("reprocessDialogAgentError")
    metadataJValue \ "error" \ "data" match {
      case dataJString: JString => 
        val data = readDialogAgentMessageData(dataJString.extract[String])
        val newMetadata = metadataJValue.transformField {
          case ("error", _) => ("data", Extraction.decompose(data))
        }
        val rs1 = RSM.addReprocessed(rs)
        val rs2 = RSM.addRecovered(rs1)
        if(withClassifications) dacClient.foreach(
          _.runClassification(this, rs2, data, newMetadata)
        ) else {
          val rs3 = RSM.setOutputLine(rs2, write(newMetadata))
          futureIteration(rs3)
        }
      case _ =>
        reportProblem(rs, "Could parse DialogAgentMessage error metadata")
    }
  }

  // Write the output of the runstate then resume asynchronous iterating
  private def futureIteration(rs: RunState): Unit = {

    // we need a new thread or will overflow the stack
    val f: Future[RunState] = 
      Future {writeOutput(rs)}

    f onComplete {
      case Success(rs1: RunState) => 
        iteration(rs1)
      case Failure(t) => 
        logger.error(s"An error occured: ${t}")
        iteration(RSM.addError(rs)) 
    }
  }

  /** Write output lines to the file
   * @param rs The runState sent with the orignal message to the DAC client
   * @return The run state with the lineWrites var incremented by 1
   */
  override def writeOutput(rs: RunState): RunState = rs.fileWriter match {
    case Some(fw: PrintWriter) => 
      try {
        rs.outputLines match {
          case line::tail =>
            fw.write(s"${line}\n")
            val rs1 = RSM.addLineWrite(rs)
            val rs2 = RSM.setOutputLines(rs1, tail)
            writeOutput(rs2)
          case _ => rs
        }
      } catch {
        case NonFatal(t) =>
          logger.error(s"Error writing to output file:  ${t}")
          RSM.addError(rs)
      }
    case _ => 
      logger.error("write called without a PrintWriter")
      RSM.addError(rs)
  }

  /** If the fileName has a TA3 version number, either set it or increment it.
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
      case regex(version) => 
        val newVersion: Int = ta3Version.getOrElse(version.toInt +1)
        s"Vers-${newVersion}.metadata"
      case _ => outputFileName  // otherwise do not change the inputFileName
    })
  }

  // show statistics for entire run
  def finalReport(s: RunState): Unit = {
    val stopTime = Clock.systemUTC.millis
    val runSecs = (stopTime-startTime)/1000.0
    val prepSecs = (reprocessingStartTime-startTime)/1000.0
    val compSecs = runSecs - prepSecs
    logger.info("")
    logger.info("METADATA REPROCESSING SUMMARY:")
    logger.info("Output directory:              %s".format(outputDirName))
    logger.info("Input directory:               %s".format(inputDirName))
    logger.info("Input files present            %d".format(allFiles.length))
    logger.info("Input Files reprocessed        %d".format(nFiles))
    RSM.stateReport(s)
    logger.info("DialogAgent file scan:         %.1f minutes".format(prepSecs/60.0))
    logger.info("Time to reprocess:             %.1f minutes".format(compSecs/60.0))
  }

  // log the problem and write the input line to the output file
  def reportProblem(rs: RunState, gripe: String): Unit = {
    logger.error(s"${gripe}: ${rs.inputLine}")
    val rs1 = RSM.setOutputLine(rs, rs.inputLine)
    val rs2 = RSM.addError(rs1)
    futureIteration(rs2)
  }
}
