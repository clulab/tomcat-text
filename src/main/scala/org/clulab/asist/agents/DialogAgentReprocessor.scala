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
  fileIterator: Iterator[String] = Iterator(),
  lineIterator: Iterator[String] = Iterator(),
  fileWriter: Option[PrintWriter] = None

  // keep statistics here
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
      val totalInputLines = 
        fileNames.map(n =>LocalFileUtils.lineIterator(n).length).sum

      logger.info("Files to be processed: {}", nFiles)
      logger.info("Lines to be processed: {}", totalInputLines)

      logger.info("Reprocessing files...")

      val startState = RunState(
        fileIterator = fileNames.iterator
      )

      iteration(startState)
    } 
  }  else logger.error("No files with DialogAgent metadata were found")

  /*
      // be nice to count these
      val totalLines = -1
      fileNames.map(reprocessFile)

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
*/

  //FIXME This routine takes a long time.  Maybe grep?
  /** Scan a string iterator for valid DialogAgent JSON
   *  @param iter:  An iterator containing json strings
   *  @return: True if DialogAgent publication topic and data.text are found
   */
  @tailrec
  private def fileHasDialogAgentMetadata(
    iter: Iterator[String],
    counter: Int
  ): Boolean = if(!iter.hasNext) {
    logger.info("No DialogAgent metadata found in {} lines", counter)
    false 
  }else {
    val line = iter.next
    val lookAhead = readMetadataLookahead(line)
    if(lookAhead.topic == topicPubDialogAgent) {
      logger.info("DialogAgent metadata found after searching {} lines", counter +1)
      true
    }
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
      logger.info("iteration, next line")
      processLine(s)
    }
    else {
      // otherwise shut the output down 
      s.fileWriter.foreach(fw => fw.close)

      // if we have another file, run it
      if(s.fileIterator.hasNext) {
        logger.info("iteration,  next file")
        // next file iteration
        val inputFileName = s.fileIterator.next
        val outputFileName = ta3FileName(inputFileName)
        val newState = RunState(
          fileIterator = s.fileIterator,
          lineIterator = LocalFileUtils.lineIterator(inputFileName),
          fileWriter = Some(new PrintWriter(new File(outputFileName)))
        )
        processLine(newState)
      }
      else {
        // otherwise we are done
        logger.info("iteration, done")
        // done
        logger.info("No more files to process")

        // Shut down Actor system
        system.terminate() 

        // show full run statistics 
        logger.info("Run statistics:")
        logger.info("blah:")
        logger.info("blah:")
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
    readMetadataLookahead(line).topic match {
      case `topicSubTrial` => processTrialMetadata(s, line)
      case `topicPubDialogAgent` => reprocessDialogAgentMetadata(s, line)
      case `topicPubVersionInfo` => futureIteration(s) // VersionInfo lines deleted
      case _ => futureIteration(s, line) // transcribe unhandled cases
    }
  }

  /** Parse a TrialMessage and report Testbed config if trial start.
   * @param line JSON representation of one DialogAgentMessage struct
   * @return The original line always, with VersionInfo if trial start
   */
  def processTrialMetadata(s: RunState, line: String): Unit = {

    try {
    // If this is the start of a trial, follow with a VersionInfo message 
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

        val twoLines = s"${line}\n${json}"
        futureIteration(s, twoLines)
      }
    } catch {
      case NonFatal(t) => 
        logger.error(s"processTrialMetadata: Could not parse: ${line}\n")
        logger.error(t.toString)
        futureIteration(s, line)
    }
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
      case reprocessed::Nil => futureIteration(s, reprocessed)
      case _ => reprocessDialogAgentErrorMetadata(s, line) 
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
      case reprocessed::Nil => futureIteration(s, reprocessed)
      case _ => 
        logger.error("reprocessDialogAgentErrorMetadata:")
        logger.error("Could not parse: {}\n",line)
        futureIteration(s, line)
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
}
