package org.clulab.asist.agents

import akka.actor.ActorSystem
import akka.http.scaladsl._
import akka.http.scaladsl.model._
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
 */

// A single struct to pass around as we process the files
case class RunState(
  fileInfoIterator: Iterator[(String, Int)] = Iterator(),
  lineIterator: Iterator[String] = Iterator(),
  fileWriter: Option[PrintWriter] = None,
  dialogAgentMessagesWritten: Int = 0,
  dialogAgentErrorReportsRead: Int = 0,
  versionInfoWritten: Int = 0,
  trialStarts: Int = 0,
  totalLinesRead: Int = 0,
  totalLinesWritten: Int = 0,
  dacQueries: Int = 0,
  errors: Int = 0
)

class DialogAgentReprocessor (
  val inputDirName: String = "",
  val outputDirName: String = "",
  override val args: DialogAgentArgs = new DialogAgentArgs
) extends DialogAgent(args) with LazyLogging {

  implicit val ec = ExecutionContext.global
  implicit val system: ActorSystem = ActorSystem("test")

  val startTime = Clock.systemUTC.millis
  logger.info("Checking files for DialogAgent metadata...")

  /* Find the names of files containing DialogAgent metadata */
  val fileNames: List[String] = LocalFileUtils.getFileNames(inputDirName)
    .filter(a => a.endsWith(".metadata"))
    .filter(a => fileHasDialogAgentMetadata(LocalFileUtils.lineIterator(a), 0))

  val nFiles = fileNames.length
  val reprocessingStartTime = Clock.systemUTC.millis

  // Only create the output directory if DialogAgent metadata exists
  if(nFiles > 0) {  
    // make sure the output directory is available
    if(LocalFileUtils.ensureDir(outputDirName)) { 
      logger.info("Using output directory: {}", outputDirName)

      // give the user a heads up as to how much data will be run
      // TODO profile this call on a full bucket and see how long it takes.
      val fileSizes = 
        fileNames.map(n =>LocalFileUtils.lineIterator(n).length)

      logger.info("Files to be processed: {}", nFiles)
      logger.info("Total Lines to be processed: {}", fileSizes.sum)

      logger.info("Reprocessing files...")

      val startState = RunState(
        fileInfoIterator = fileNames.zip(fileSizes).iterator
      )

      iteration(startState)
    } 
  }  else logger.error("No files with DialogAgent metadata were found")


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

  // two dimensional iteration through files and their contents
  def iteration(s: RunState): Unit = {
    // if we have another line, run it.
    if(s.lineIterator.hasNext) {
      processLine(s)
    }
    else {
      // otherwise shut the output down 
      s.fileWriter.foreach(fw => fw.close)

      // if we have another file, run it
      if(s.fileInfoIterator.hasNext) {
        // next file iteration
        val fileInfo = s.fileInfoIterator.next

        val inputFileName = fileInfo._1
        val inputFileLines = fileInfo._2
        val outputFileName = ta3FileName(inputFileName)

        val newState = s.copy(
          lineIterator = LocalFileUtils.lineIterator(inputFileName),
          fileWriter = Some(new PrintWriter(new File(outputFileName))),
        )
        val theKingsEnglish = if(inputFileLines == 1) "line" else "lines"
        logger.info("Reading {} {} from {} ...", inputFileLines, theKingsEnglish, inputFileName)
        processLine(newState)
      }
      else {
        // done
        logger.info("No more files to process")
        system.terminate() 
        showReport(s)
      }
    }
  }


  // the line iterator of the run state is presumed to have a next value
  /** Reprocess line if DialogAgent-related metadata, otherwise copy
   * @param line One metadata JSON line
   * @param fileWriter output to result file
   * @return either the orignal line or a reprocessed version if it's ours
   */
  def processLine(s: RunState): Unit = {
    val line = s.lineIterator.next
    // increment the number of metadata lines we have handled.
    val withRead = s.copy(
      totalLinesRead = s.totalLinesRead+1,
    ) 
    readMetadataLookahead(line).topic match {
      case `topicSubTrial` => processTrialMetadata(withRead, line)
      case `topicPubDialogAgent` => reprocessDialogAgentMetadata(withRead, line)
      case `topicPubVersionInfo` => futureIteration(withRead) // VersionInfo lines deleted
      case _ => 
        val withWrite = withRead.copy(
          totalLinesWritten = withRead.totalLinesWritten+1
        )
        futureIteration(withWrite, line) // transcribe unhandled cases
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
      val twoLines = s"${line}\n${json}"
      val withTrialStart = s.copy(
        trialStarts = s.trialStarts + 1,
        versionInfoWritten = s.versionInfoWritten + 1,
        totalLinesWritten = s.totalLinesWritten + 2
      )
      futureIteration(withTrialStart, twoLines)
    } else {
      // if not a trial start just write the input line
      val withWrite = s.copy(totalLinesWritten = s.totalLinesWritten + 1)
      futureIteration(withWrite, line)
    }
  } catch {
    case NonFatal(t) => 
      logger.error(s"processTrialMetadata: Could not parse: ${line}\n")
      logger.error(t.toString)
      val withError = s.copy(
        errors = s.errors +1,
        totalLinesWritten = s.totalLinesWritten + 2
      )
      futureIteration(withError, line)
  }

  /** Replace DialogAgentMessage data extractions with fresh ones.
   * @param line JSON representation of one DialogAgentMessage struct
   * @return The processed JSON line
   */
  def reprocessDialogAgentMetadata(s: RunState, line: String): Unit = {

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
            Extraction.decompose(getExtractions(text))
          )
        )
      })
    }).flatten match {
      case reprocessed::Nil =>
        val withDialogAgent = s.copy(
          dialogAgentMessagesWritten = s.dialogAgentMessagesWritten +1,
          totalLinesWritten = s.totalLinesWritten +1
        )
        futureIteration(withDialogAgent, reprocessed)
      case _ => 
        reprocessDialogAgentErrorMetadata(s, line) 
    }
  }


  /* Replace the error field with a reprocessed DialogAgentMessageData struct
   * @param line A JSON representation DialogAgentMessage error struct
   * @return a DialogAgentMessage with new extractions
   */
  def reprocessDialogAgentErrorMetadata(s: RunState, line: String): Unit = {
    parseJValue(line).toList.map{metadata => 
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
    }.flatten match {
      case reprocessed::Nil => 
        val withDialogAgentErrorReport = s.copy(
          dialogAgentMessagesWritten = s.dialogAgentMessagesWritten +1,
          totalLinesWritten = s.totalLinesWritten +1,
          dialogAgentErrorReportsRead = s.dialogAgentErrorReportsRead +1
        )
        futureIteration(withDialogAgentErrorReport, reprocessed)
      case _ => 
        logger.error("reprocessDialogAgentErrorMetadata:")
        logger.error("Could not parse: {}\n",line)
        val withError = s.copy(
          errors = s.errors+1,
          totalLinesWritten = s.totalLinesWritten+1)
        futureIteration(withError, line)
    }
  }

  // schedule the next iteration
  def futureIteration(s: RunState): Unit = {

    // we need a new thread or will overflow the stack
    val f: Future[RunState] = Future {s}

    f onComplete {
      case Success(fs: RunState) => 
        iteration(fs)
      case Failure(t) => 
        logger.error(s"An error occured: ${t}")
        iteration(s)  // worth a shot
    }
  }

  // schedule the next iteration with output
  def futureIteration(s: RunState, line: String): Unit = {

    // we need a new thread or will overflow the stack
    val f: Future[Tuple2[RunState, String]] = Future {(s, line)}

    f onComplete {
      case Success(t2: Tuple2[RunState, String]) => 
        s.fileWriter.foreach(fw => fw.write(s"${t2._2}\n"))
        iteration(t2._1)
      case Failure(t) => 
        logger.error(s"An error occured: ${t}")
        iteration(s)  // worth a shot
    }
  }


  /* Update extractions for a DialogAgentMessageData struct
   * @param json A JSON representation of a DialogAgentMessageData struct
   * @return A new DialogAgentMessageData struct with new extractions
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
      extractions = getExtractions(data.text)
    )
  } catch {
    case NonFatal(t) => {
      logger.error(s"reprocessDialogAgentMessageData could not parse ${json}")
      logger.error(t.toString)
      new DialogAgentMessageData(source = new DialogAgentMessageDataSource)
    }
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

  // show run statistics 
  def showReport(s: RunState): Unit = {
    val stopTime = Clock.systemUTC.millis
    val runSecs = (stopTime-startTime)/1000.0
    val prepSecs = (reprocessingStartTime-startTime)/1000.0
    val compSecs = runSecs - prepSecs
    logger.info("")
    logger.info("METADATA REPROCESSING COMPLETE:")
    logger.info("Input directory:                 %s".format(inputDirName))
    logger.info("Output directory:                %s".format(outputDirName))
    logger.info("Files reprocessed                %d".format(nFiles))
    logger.info("Trial starts read:               %d".format(s.trialStarts))
    logger.info("Dialog Agent Error Reports read: %d".format(s.dialogAgentErrorReportsRead))
    logger.info("Total lines read:                %d".format(s.totalLinesRead))
    logger.info("Dialog Agent Messages written:   %d".format(s.dialogAgentMessagesWritten))
    logger.info("Version Info messages written:   %d".format(s.versionInfoWritten))
    logger.info("Total lines written:             %d".format(s.totalLinesWritten))
    logger.info("DAC server queries:              %d".format(s.dacQueries))
    logger.info("Processing errors                %d".format(s.errors))
    logger.info("DialogAgent file scan:           %.1f minutes".format(prepSecs/60.0))
    logger.info("Time to reprocess:               %.1f minutes".format(compSecs/60.0))
  }
}
