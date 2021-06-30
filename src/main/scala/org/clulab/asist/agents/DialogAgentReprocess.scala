package org.clulab.asist.agents

import java.io.{File, PrintWriter}
import java.nio.file.Paths
import java.time.Clock

import org.clulab.asist.messages._
import org.clulab.utils.LocalFileUtils
import org.slf4j.LoggerFactory
import org.json4s._
import org.json4s.jackson.Serialization.read
import org.json4s.jackson.JsonMethods

import scala.annotation.tailrec
import scala.io.Source

/**
 * Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 * Updated:  2021 June
 *
 * Reprocess Dialog Agent metadata files by reading them line by line and then
 * writing the processed results to the output file.
 *
 * Reprocessed DialogAgentMessage metdata is a copy of existing DialogAgentMessage
 * metdata, with the only difference being that the extractions are regenerated. 
 *
 * If existing metadata is not DialogAgent metadata, copy it to the output
 * file unchanged.
 *
 * Do not process metadata files that do not contain DialogAget metadata.
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

  // find the files in the input directory that contain DialogAgent metadata
  val fileNames = LocalFileUtils.getFileNames(inputDirName)
    .filter(a => a.endsWith(".metadata"))
    .filter(a => hasDialogAgentMetadata(LocalFileUtils.lineIterator(a)))

  if(fileNames.length > 0) {  // make sure we have DialogAgent metadata
    if(LocalFileUtils.ensureDir(outputDirName)) { // make sure dir exists 
      logger.info(s"Using output directory: $outputDirName")
      logger.info("Reprocessing...")
      val results = fileNames.map(processFile)
      if(!results.contains(false)) 
        logger.info("All reprocessing completed successfully.")
      else 
        logger.error("There were problems during reprocessing.")
    }
  } else 
    logger.error("No .metadata files containing DialogAgent data were found")

  def readLookahead(line: String): MetadataLookahead = try {
    Option(read[MetadataLookahead](line))
      .getOrElse(new MetadataLookahead)
  } catch {
      case t: Throwable => new MetadataLookahead
  }

  def readTrialMessage(line: String): TrialMessage = try {
    Option(read[TrialMessage](line))
      .getOrElse(new TrialMessage(new CommonMsg))
  } catch {
      case t: Throwable => new TrialMessage(new CommonMsg)
  }

  /** True if the iterator contains any DialogAgent metadata.
   *  @param iter:  An iterator containing json strings
   *  @returns: True if any of the strings contains the DialogAgent publication topic
   */
  @tailrec
  private def hasDialogAgentMetadata(
    iter: Iterator[String]
  ): Boolean = if(!iter.hasNext) false else {
    if(readLookahead(iter.next).topic == topicPubDialogAgent) true
    else hasDialogAgentMetadata(iter)
  }

  /** process one input file
   * @param inputFileName The namee of the file to be processed
   * @returns true if successful
   */
  def processFile(inputFileName: String): Boolean = {
    val outputFileName = ta3FileName(inputFileName)
    logger.info(s"$inputFileName => $outputFileName")
    val fileWriter = new PrintWriter(new File(outputFileName))
    val bufferedSource = Source.fromFile(inputFileName)
    val iterator = LocalFileUtils.lineIterator(inputFileName)
    val ret = processLines(iterator, fileWriter, true)
    fileWriter.close
    ret
  }


  /** process all the metadata lines from one input file
   * @param lines An iterator with all of the lines for this file
   * @param fileWriter Writes to the output file
   * @param result The accumulated outcome of processing each line
   * @returns true if all lines were processed successfully
   */
  @tailrec
  private def processLines(
    lines: Iterator[String],
    fileWriter: PrintWriter,
    result: Boolean
  ): Boolean = {
    if(!lines.hasNext) result
    else {
      val currentResult = processLine(lines.next, fileWriter)
      processLines(lines, fileWriter, currentResult && result)
    }
  }

  /** Reprocess line if DialogAgent metadata, otherwise write it as-is.
   * @param line One metadata JSON line
   * @param fileWriter Writes to the output file
   * @returns true if the line was processed successfully
   */
  def processLine(
    line: String,
    fileWriter: PrintWriter
  ): Boolean = {
    val lookahead = 
      Option(read[MetadataLookahead](line)).getOrElse(new MetadataLookahead)
    lookahead.topic match {
      case `topicPubDialogAgent` => processDialogAgentMetadata(line, fileWriter)
// FIXME      case `topicSubTrial` => processTrialMetadata(line, fileWriter)
      case _ => 
//  FIXME       fileWriter.write(s"$line\n")
        true
    }
  }

  /** Parse a TrialMessage and report Testbed config if trial start.
   * @param line JSON representation of one DialogAgentMessage struct
   * @param fileWriter Writes to the output file
   * @returns true
   */
  def processTrialMetadata(
    line: String,
    fileWriter: PrintWriter
  ): Boolean = {

    // Copy the input trial message string to the output file in all cases
    fileWriter.write(s"${line}\n")

    // If this is the start of a trial, follow with a VersionInfo message 
    // containing the DialogAgent Testbed configuration
    try {
      val trialMessage = read[TrialMessage](line)
      if(trialMessage.msg.sub_type == "start") {
        val timestamp = Clock.systemUTC.instant.toString
        val json = writeJson(VersionInfo(this, timestamp))
        fileWriter.write(s"${json}\n")
      }
      true
    } catch {
        case t: Throwable => 
          logger.error(s"Error parsing TrialMessage from JSON: ${line}\n")
          logger.error(t.toString)
          false
    }
  }


  /** reprocess the data.text of DialogAgentMessage JSON input, copy all else.
   * @param line JSON representation of one DialogAgentMessage struct
   * @param fileWriter Writes to the output file
   * @returns true if the line was processed successfully
   */
  def processDialogAgentMetadata(
    line: String,
    fileWriter: PrintWriter
  ): Boolean = {

    val metadata: Option[JValue] = getMetadata(line)

    if(metadata.isDefined) {
      val data = getData(metadata.head)
      if (data.isDefined) {
        val newMetadata = reprocessMetadata(metadata.head, data.head)

        // now write it to file
        true
      } else false
    } else false
  }

  def reprocessMetadata(metadata: JValue, data: JValue): JValue = {
    metadata
  }


  // find the data substructure within the metadata. 
  def getData(metadata: JValue): Option[JValue] = {

    val dataFieldOpt: Option[JField] = metadata.findField{
      case (n,v) => n == "data"
    }

    // FIXME with an idiomatic solution
    if(dataFieldOpt.isEmpty) {
      logger.error("Could not find data field in metadata JValue")
      None
    } else Some(dataFieldOpt.head._2)
  }


  // parse the metadata text into a JValue to fix nonstandard JSON issues
  def getMetadata(line: String): Option[JValue] = try {
    Some(JsonMethods.parse(s""" ${line} """))
  } catch {
    case t: Throwable => 
      logger.error(s"Error parsing metadata AST from input string: ${line}\n")
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
