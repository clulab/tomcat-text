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
  agentOut: Int = 0, // DialogAgent messages written
  errorsIn: Int = 0, // Dialog Agent error reports read
  infoOut: Int = 0, // VersionInfo messages written
  startsIn: Int = 0, // trial start messages read
  linesIn: Int = 0,  // total lines read
  linesOut: Int = 0, // total lines written
  dacOut: Int = 0,  // DAC server requests
  dacIn: Int = 0, // DAC server responses
  errors: Int = 0  // errors and exceptions encountered
)

// sent back by the DAC server
case class Classification(name: String)

class DialogAgentReprocessor (
  val inputDirName: String = "",
  val outputDirName: String = "",
  override val args: DialogAgentArgs = new DialogAgentArgs
) extends DialogAgent(args) with LazyLogging {

  def bumpAgentOut(s: RunState): RunState = s.copy(agentOut = s.agentOut + 1)
  def bumpErrorsIn(s: RunState): RunState = s.copy(errorsIn = s.errorsIn + 1)
  def bumpInfoOut(s: RunState): RunState = s.copy(infoOut = s.infoOut + 1)
  def bumpStartsIn(s: RunState): RunState = s.copy(startsIn = s.startsIn + 1)
  def bumpLinesIn(s: RunState): RunState = s.copy(linesIn = s.linesIn + 1)
  def bumpLinesOut(s: RunState): RunState = s.copy(linesOut = s.linesOut + 1)
  def bumpDacOut(s: RunState): RunState = s.copy(dacOut = s.dacOut + 1)
  def bumpDacIn(s: RunState): RunState = s.copy(dacIn = s.dacIn + 1)
  def bumpErrors(s: RunState): RunState = s.copy(errors = s.errors + 1)

  // actors
  implicit val ec = ExecutionContext.global
  implicit val system: ActorSystem = ActorSystem("DialogAgentReprocessor")

  // json
  implicit val formats = org.json4s.DefaultFormats

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

        logger.info(
          "Reading {} {} from {} ...", 
          inputFileLines, 
          theKingsEnglish, 
          inputFileName
        )

        processLine(newState)
      }
      else {
        // done
        logger.info("All file processing has finished.")
        showReport(s)
        system.terminate()
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
    val withRead = bumpLinesIn(s)
    readMetadataLookahead(line).topic match {
      case `topicSubTrial` => processTrialMetadata(withRead, line)
      case `topicPubDialogAgent` => reprocessDialogAgentMetadata(withRead, line)
      case `topicPubVersionInfo` => futureIteration(withRead) // VersionInfo deleted
      case _ => 
        val withWrite = bumpLinesOut(withRead)
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
      val twoLines = s"${line}\n${json}"  // 2 lines out
      val withTrialStart = bumpStartsIn(bumpInfoOut(bumpLinesOut(bumpLinesOut(s))))
      futureIteration(withTrialStart, twoLines)
    } else {
      // if not a trial start just write the input line
      val withWrite = bumpLinesOut(s)
      futureIteration(withWrite, line)
    }
  } catch {
    case NonFatal(t) => 
      logger.error(s"processTrialMetadata: Could not parse: ${line}\n")
      logger.error(t.toString)
      val withError = bumpErrors(bumpLinesOut(bumpLinesOut(s)))
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
      
      // Successfully reprocessed the line as a current DialogAgentMessage 
      case reprocessed::Nil =>
        val withDialogAgent = bumpAgentOut(bumpLinesOut(s))
        if(withClassifications) 
          futureDacQuery(withDialogAgent, reprocessed)
        else 
          futureIteration(withDialogAgent, reprocessed)

      // Could not reprocess the line, so the next thing to try is to see if
      // it is an error report about a DialogAgentMessage 
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

      // Successfully recovered error report into a DialogAgentMessage
      case reprocessed::Nil => 
        val withErrorRecovery = bumpAgentOut(bumpLinesOut(bumpErrorsIn(s)))
        if(withClassifications) 
          futureDacQuery(withErrorRecovery, reprocessed)
        else 
          futureIteration(withErrorRecovery, reprocessed)

      // Could not reprocess the line as either a DialogAgentMessage or
      // as a recoverable Dialog Agent Error Report
      case _ => 
        logger.error("reprocessDialogAgentErrorMetadata:")
        logger.error("Could not parse: {}\n",line)
        val withError = bumpErrors(bumpLinesOut(s))
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
        val withError = bumpErrors(s)
        iteration(withError) 
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
        val withError = bumpErrors(s)
        iteration(withError) 
    }
  }

  // call the DAC for classification of this DialogAgentMessage
  def futureDacQuery(s: RunState, json: String): Unit = {
    val message = read[DialogAgentMessage](json)
    val data = message.data
    val requestJson = write(new DialogActClassifierMessage(
      data.participant_id,
      data.text,
      data.extractions)
    )
    val request = HttpRequest(
      uri = Uri("http://localhost:8000/classify"),
      entity = HttpEntity(ContentTypes.`application/json`,requestJson)
    )
    val future: Future[HttpResponse] = Http().singleRequest(request)
    val response: HttpResponse = Await.ready(future, 10 seconds).value.get.get

    val withDacOut = bumpDacOut(s)

    val futureClassification: Future[Classification] = response.entity.dataBytes
      .runReduce(_ ++ _)
      .map{ line =>
        val ret = line.utf8String.split(" ").headOption.map(Classification)
        val classification = ret.getOrElse(new Classification(""))
        classification
      }

    futureClassification onComplete {
      case Success(c: Classification) =>  
        parseJValue(json).toList.map{metadata =>
          val label = c.name.replace("\"","")
          val newData = data.copy(dialog_act_label = label)
          val newMetadata = metadata.replace(
            "data"::Nil,
            Extraction.decompose(newData)
          )
          val newJson = write(newMetadata)

          val withDacIn = bumpDacIn(withDacOut)

          withDacIn.fileWriter.foreach(fw => fw.write(s"${newJson}\n"))

          iteration(withDacIn)
        }
      case Failure(t) =>
        logger.error(s"An error occured:  ${t}")
        val withError = bumpErrors(s)
        futureIteration(withError, json)
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
    logger.info("Input directory:          %s".format(inputDirName))
    logger.info("Output directory:         %s".format(outputDirName))
    logger.info("Files reprocessed         %d".format(nFiles))
    logger.info("Trial starts read:        %d".format(s.startsIn))
    logger.info("Agent Error Reports read: %d".format(s.errorsIn))
    logger.info("Agent Messages written:   %d".format(s.agentOut))
    logger.info("Version Info written:     %d".format(s.infoOut))
    logger.info("Total lines read:         %d".format(s.linesIn))
    logger.info("Total lines written:      %d".format(s.linesOut))
    logger.info("DAC server requests:      %d".format(s.dacOut))
    logger.info("DAC server responses:     %d".format(s.dacIn))
    logger.info("Processing errors         %d".format(s.errors))
    logger.info("DialogAgent file scan:    %.1f minutes".format(prepSecs/60.0))
    logger.info("Time to reprocess:        %.1f minutes".format(compSecs/60.0))
  }
}
