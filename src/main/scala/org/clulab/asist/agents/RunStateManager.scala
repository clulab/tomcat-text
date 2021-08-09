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

// Advise the user of reprocessing status
case class RunState(
  fileInfoIterator: Iterator[(String, Int)] = Iterator(),
  lineIterator: Iterator[String] = Iterator(),
  fileWriter: Option[PrintWriter] = None,

  // Dialog Agent Messages generated from Dialog Agent metadata
  reprocessed: Int = 0,

  // Dialog Agent Messages generated from Dialog Agent error reports
  agentRecovered: Int = 0,

  infoWrites: Int = 0, // VersionInfo messages written at trial starts
  lineReads: Int = 0,  // total lines read
  lineWrites: Int = 0, // total lines written
  dacQueries: Int = 0, // DAC server responses
  dacResets: Int = 0, // DAC resets 
  errors: Int = 0,  // errors and exceptions encountered
  terminated: Boolean = false // set true to halt processing
)


trait RunStateManager extends LazyLogging {
  // End processing
  def terminate(s: RunState): RunState = s.copy(terminated = true)

  // increment state variables as we go
  def addDacReset(s: RunState): RunState = s.copy(dacResets = s.dacResets + 1)
  def addDacQuery(s: RunState): RunState = s.copy(dacQueries = s.dacQueries + 1)
  def addAgentReprocessed(s: RunState): RunState = s.copy(reprocessed = s.reprocessed+1)
  def addInfoWrite(s: RunState): RunState = s.copy(infoWrites = s.infoWrites + 1)
  def addLineRead(s: RunState): RunState = s.copy(lineReads = s.lineReads + 1)
  def addLineWrite(s: RunState): RunState = s.copy(lineWrites = s.lineWrites + 1)
  def addError(s: RunState): RunState = s.copy(errors = s.errors + 1)

  // show statistics for one file
  def stateReport(s: RunState): Unit = {
    logger.info("Total lines read:              %d".format(s.lineReads))
    logger.info("Total lines written:           %d".format(s.lineWrites))
    logger.info("DialogAgent lines reprocessed: %d".format(s.reprocessed))
    logger.info("VersionInfo lines written:     %d".format(s.infoWrites))
    logger.info("DialogAgent error recoveries:  %d".format(s.agentRecovered))
    logger.info("DAC server resets:             %d".format(s.dacResets))
    logger.info("DAC classifications:           %d".format(s.dacQueries))
    logger.info("Processing errors              %d".format(s.errors))
  }
}
