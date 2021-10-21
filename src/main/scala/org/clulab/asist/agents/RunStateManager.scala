package org.clulab.asist.agents

import com.typesafe.scalalogging.LazyLogging
import java.io.PrintWriter

/**
 * Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 * Keep track of ongoing Dialog Agent input processing
 */

case class RunState(
  fileInfoIterator: Iterator[(String, Int)] = Iterator(),
  lineIterator: Iterator[String] = Iterator(),
  fileWriter: Option[PrintWriter] = None,

  inputTopic: String = "",
  inputLine: String = "",
  outputTopic: String = "",
  outputLines: List[String] = List(),
  metadataLogs: List[MetadataLog] = List(),

  // Dialog Agent Messages generated from Dialog Agent metadata
  reprocessed: Int = 0,

  // Dialog Agent Messages generated from Dialog Agent error reports
  recovered: Int = 0,

  fileReads: Int = 0, // number of input files read
  infoWrites: Int = 0, // VersionInfo messages written 
  lineReads: Int = 0,  // total lines read
  lineWrites: Int = 0, // total lines written
  tdacQueries: Int = 0, // TDAC server responses
  tdacResets: Int = 0, // TDAC resets 
  errors: Int = 0,  // errors and exceptions encountered
  terminated: Boolean = false // set true to halt processing
)


object RSM extends RunStateManager

trait RunStateManager extends LazyLogging {

  // value setters
  def setFileInfoIterator(
    s: RunState, 
    itr: Iterator[(String, Int)]
  ): RunState = s.copy(fileInfoIterator = itr)

  def setLineIterator(
    s: RunState, 
    itr: Iterator[String]
  ): RunState = s.copy(lineIterator = itr)

  def setFileWriter(
    s: RunState, 
    fw: Option[PrintWriter]
  ): RunState = s.copy(fileWriter = fw)

  def setInputTopic(
    s: RunState,
    topic: String
  ): RunState = s.copy(inputTopic = topic)

  def setOutputTopic(
    s: RunState,
    topic: String
  ): RunState = s.copy(outputTopic = topic)

  def setInputLine(
    s: RunState,
    line: String
  ): RunState = s.copy(inputLine = line)

  def setOutputLine(
    s: RunState, 
    line: String
  ): RunState = setOutputLines(s, List(line))

  def setOutputLines(
    s: RunState,
    lines: List[String]
  ): RunState = s.copy(outputLines = lines)

  def setMetadataLogs(
    s: RunState,
    logs: List[MetadataLog]
  ): RunState = s.copy(metadataLogs = logs)

  // state incrementers
  def addDacReset(s: RunState): RunState = 
    s.copy(dacResets = s.dacResets + 1)

  def addDacQuery(s: RunState): RunState = 
    s.copy(dacQueries = s.dacQueries + 1)

  def addReprocessed(s: RunState): RunState =
    s.copy(reprocessed = s.reprocessed+1)

  def addRecovered(s: RunState): RunState =
    s.copy(recovered = s.recovered+1)

  def addInfoWrite(s: RunState): RunState =
    s.copy(infoWrites = s.infoWrites + 1)

  def addLineRead(s: RunState): RunState =
    s.copy(lineReads = s.lineReads + 1)

  def addLineWrite(s: RunState): RunState =
    s.copy(lineWrites = s.lineWrites + 1)

  def addFileRead(s: RunState): RunState =
    s.copy(fileReads = s.fileReads + 1)

  def addError(s: RunState): RunState =
    s.copy(errors = s.errors + 1)

  // Keep a list of topics encountered in the metadata and when they occurred
  def logMetadata(
    s: RunState, 
    topic: String,
    timestamp: String
  ): RunState = {
    val logs = MetadataLogger.logMetadata(topic, timestamp, s.metadataLogs)
    setMetadataLogs(s, logs)
  }
  // End processing
  def terminate(s: RunState): RunState = s.copy(terminated = true)

  // Return a list of each state variable
  def stateReport(s: RunState): List[String] = List(
    "Input files read:              %d".format(s.fileReads),
    "Total lines read:              %d".format(s.lineReads),
    "Total lines written:           %d".format(s.lineWrites),
    "DialogAgent lines reprocessed: %d".format(s.reprocessed),
    "DialogAgent lines recovered:   %d".format(s.recovered),
    "VersionInfo lines written:     %d".format(s.infoWrites),
    "TDAC server resets:            %d".format(s.tdacResets),
    "TDAC server classifications:   %d".format(s.tdacQueries),
    "Processing errors              %d".format(s.errors)
  )
}
