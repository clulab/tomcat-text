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
import org.json4s.JField

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

  // Dialog Act Classification
  val dacClient = new DacClient(this)

  @tailrec
  private def findDialogAgentMetadataFiles(
    allFiles: List[String],
    result: List[String]
  ): List[String] = allFiles match {
    case head::tail =>
      if(head.endsWith(".metadata") && 
        fileHasDialogAgentMetadata(LocalFileUtils.lineIterator(head), 0)
      ) {
        println(s"Yes: ${head}")
        findDialogAgentMetadataFiles(tail, head::result)
      }
      else {
        println(s"No:  ${head}")
        findDialogAgentMetadataFiles(tail, result)
      }
    case _ => result
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
    iter: Iterator[String],
    counter: Int
  ): Boolean = if(!iter.hasNext) false
  else {
    val lookAhead = readMetadataLookahead(iter.next)
    if(lookAhead.topic == topicPubDialogAgent) true
    else fileHasDialogAgentMetadata(iter, counter+1)
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
  override def iteration(s: RunState): Unit = {
    // this can be set for instance if we lose contact with the DAC server
    if(s.terminated) {
      logger.info("File processing terminated.")
      finish(s)
    }
    // if we have another line, run it.
    else if(s.lineIterator.hasNext) {
      val done = s.copy (lineReads = s.lineReads +1)
      processLine(done, s.lineIterator.next)
    }
    else {
      // otherwise shut the output down 
      s.fileWriter.foreach(fw => fw.close)

      // report status
      RSM.stateReport(s)

      fileIteration(s)
    }
  }

  def fileIteration(rs: RunState): Unit = {

    // if we have another file, run it
    if(rs.fileInfoIterator.hasNext) {
      // next file iteration
      val fileInfo = rs.fileInfoIterator.next

      val inputFileName = fileInfo._1
      val inputFileLines = fileInfo._2
      val outputFileName = ta3FileName(inputFileName)

      val newState = rs.copy(
        lineIterator = LocalFileUtils.lineIterator(inputFileName),
        fileWriter = Some(new PrintWriter(new File(outputFileName))),
      )
      val advisory = if (inputFileLines == 1) 
        s"Reading 1 line from ${inputFileName}"
      else 
        s"Reading ${inputFileLines} lines from ${inputFileName}"

      logger.info(advisory)

      if(withClassifications) 
        dacClient.resetServer(this, newState)
      else
        iteration(newState)
    }
    else {
      logger.info("All file processing has finished.")
      finish(rs)
    }
  }

  // we are done
  def finish(rs: RunState) {
    finalReport(rs)
    system.terminate()
    dacClient.shutdown
  }


  // the line iterator of the run state is presumed to have a next value
  /** Reprocess line if DialogAgent-related metadata, otherwise copy
   * @param line One metadata JSON line
   * @param fileWriter output to result file
   * @return either the orignal line or a reprocessed version if it's ours
   */
  def processLine(s: RunState, line: String): Unit = {
    // increment the number of metadata lines we have handled.
    readMetadataLookahead(line).topic match {
      case `topicSubTrial` => processTrialMetadata(s, line)
//      case `topicPubDialogAgent` => reprocessDialogAgentMetadata(s, line)
      case `topicPubVersionInfo` => 
        futureIteration(s, List()) // DialogAgent-generated VersionInfo deleted
      case _ => 
        futureIteration(s, List(line)) // transcribe unhandled cases
    }
  }

  /** Parse a TrialMessage and report Testbed config if trial start.
   * @param line JSON representation of one DialogAgentMessage struct
   * @return The original line always, with VersionInfo if trial start
   */
  def processTrialMetadata(s: RunState, line: String): Unit = try {
    val trialMessage = read[TrialMessage](line)

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

      // JSON representation of struct
      val versionInfoJValue = Extraction.decompose(versionInfoMetadata)

      // Merge of @timestamp into JSON 
      val outputJValue = versionInfoJValue.merge(metadataTimestamp)

      // TODO Is this step needed?
      val versionInfoJson = write(outputJValue)

      // we write the original line and then the Version Info line
      val done = s.copy(infoWrites = s.infoWrites + 1)
      futureIteration(done, List(line, versionInfoJson))

    } else {
      // if not a trial start just write the input line
      futureIteration(s, List(line))
    }
  } catch {
    case NonFatal(t) => 
      logger.error(s"processTrialMetadata: Could not parse: ${line}\n")
      logger.error(t.toString)
      val done = s.copy(errors = s.errors+1)
      futureIteration(done, List(line))
  }

  /** Replace DialogAgentMessage data extractions with fresh ones.
   * @param line JSON representation of one DialogAgentMessage struct
   * @return The processed JSON line
   */
  def reprocessDialogAgentMetadata(s: RunState, line: String): Unit = {
    /*
    // parse the metadata text into AST to fix nonstandard JSON issues
    parseJValue(line).toList.map{metadata => 
      reprocessDialogAgentMetadata(s, line, metadata)
    }
    */
  }

  def reprocessDialogAgentMetadata(
    rs: RunState, 
    line: String,
    metadata: JValue
  ): Unit = {
    /*
    // parse the metadata text into AST to fix nonstandard JSON issues
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
          Extraction.decompose(getExtractions(text))
        )
      )
    }) match {
      // Successfully reprocessed the line as a current DialogAgentMessage 
      case reprocessed::Nil =>
        val done = s.copy(reprocessed = s.reprocessed + 1)
        dacClient match {
          case Some(dc: DacClient) =>
            dc.runClassification(done, reprocessed, metadata)
          case None => 
            futureIteration(done, List(reprocessed))
        }
      // Could not reprocess the line, so the next thing to try is to see if
      // it is an error report about a DialogAgentMessage 
      case _ => 
        reprocessDialogAgentErrorMetadata(s, line) 
    }
    */
  }


  /* Replace the error field with a reprocessed DialogAgentMessageData struct
   * @param line A JSON representation DialogAgentMessage error struct
   * @return a DialogAgentMessage with new extractions
   */
  def reprocessDialogAgentErrorMetadata(s: RunState, line: String): Unit = {
    parseJValue(line).toList.map{metadata => 
      reprocessDialogAgentErrorMetadata(s, line, metadata)
    }
  }

  def reprocessDialogAgentErrorMetadata(
    rs: RunState, 
    line: String,
    metadata: JValue
  ): Unit = {
  /*
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
    match {
      // Successfully recovered error report into a DialogAgentMessage
      case reprocessed::Nil => 
        val done1 = RSM.setInputLine(rs, line)
        val done2 = RSM.setOutputLine(done1, reprocessed)
        val done3 = RSM.addReprocessed(done2)
        val done4 = RSM.addRecovered(done3)

        if(withClassifications) DacClient.runClassification(
          this,
          done4,

        dacClient match {
          case Some(dc: DacClient) =>
            dc.runClassification(
              done, 
              reprocessed,
              metadata
            )
          case None =>
            futureIteration(done, List(reprocessed))
        }

      // Could not reprocess the line as either a DialogAgentMessage or
      // as a recoverable Dialog Agent Error Report
      case _ => 
        val done = s.copy (errors = s.errors + 1)
        logger.error("reprocessDialogAgentErrorMetadata:")
        logger.error("Could not parse: {}\n",line)
        futureIteration(done, List(line))
    }
  */
  }

  // Write all the lines in the input list then resume asynchronous iterating
  private def futureIteration(rs: RunState, lines: List[String]): Unit = {

    // we need a new thread or will overflow the stack
    val f: Future[RunState] = Future {rs}  // make this the write method...

    f onComplete {
      case Success(fs: RunState) => lines match {
        case line::tail => 
          val done = RSM.setOutputLine(fs, line)
          futureIteration(writeLine(done), tail)
        case _ => iteration(fs)
      }
      case Failure(t) => 
        logger.error(s"An error occured: ${t}")
        iteration(RSM.addError(rs)) 
    }
  }

  /** A line to be written to the MessageBus
   * @param rs The runState sent with the orignal message to the DAC client
   * @return The run state with the lineWrites var incremented by 1
   */
  override def writeLine(rs: RunState): RunState = rs.fileWriter match {
    case Some(fw: PrintWriter) => 
      try {
        fw.write(s"${rs.outputLine}\n")
        RSM.addLineWrite(rs)
      } catch {
        case NonFatal(t) =>
          logger.error(s"Error writing to output file:  ${t}")
          RSM.addError(rs)
      }
    case _ => 
      logger.error("write called without a PrintWriter")
      RSM.addError(rs)
  }

  /* Update extractions for a DialogAgentMessageData struct
   * @param json A JSON representation of a DialogAgentMessageData struct
   * @return A new DialogAgentMessageData struct with new extractions
   */
  def reprocessDialogAgentMessageData(
    json: String
  ): DialogAgentMessageData = {
    val data = readDialogAgentMessageData(json)
    data.copy(extractions = getExtractions(data.text))
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
}
