package org.clulab.asist.agents

import com.typesafe.scalalogging.LazyLogging
import java.io.PrintWriter

/**
 * Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 * Updated:  2021 August
 *
 * Keep track of ongoing Dialog Agent input processing
 */

case class RunState(
  fileInfoIterator: Iterator[(String, Int)] = Iterator(),
  lineIterator: Iterator[String] = Iterator(),
  fileWriter: Option[PrintWriter] = None,

  // for the MQTT agent: TODO least-privilege these 
  topic: String = "",
  inputLine: String = "",
  outputLines: List[String] = List(),

  // Dialog Agent Messages generated from Dialog Agent metadata
  reprocessed: Int = 0,

  // Dialog Agent Messages generated from Dialog Agent error reports
  recovered: Int = 0,

  infoWrites: Int = 0, // VersionInfo messages written at trial starts
  lineReads: Int = 0,  // total lines read
  lineWrites: Int = 0, // total lines written
  dacQueries: Int = 0, // DAC server responses
  dacResets: Int = 0, // DAC resets 
  errors: Int = 0,  // errors and exceptions encountered
  terminated: Boolean = false // set true to halt processing
)


object RSM extends RunStateManager

trait RunStateManager extends LazyLogging {
  // set initial state
  def setTopic(s: RunState, topic: String) = s.copy(topic = topic)
  def setInputLine(s: RunState, line: String) = s.copy(inputLine = line)
  def setOutputLine(s: RunState, line: String) = setOutputLines(s, List(line))
  def setOutputLines(s: RunState, lines: List[String]) = s.copy(outputLines = lines)

  // increment state variables as we go
  def addDacReset(s: RunState): RunState = s.copy(dacResets = s.dacResets + 1)
  def addDacQuery(s: RunState): RunState = s.copy(dacQueries = s.dacQueries + 1)
  def addReprocessed(s: RunState): RunState = s.copy(reprocessed = s.reprocessed+1)
  def addRecovered(s: RunState): RunState = s.copy(recovered = s.recovered+1)
  def addInfoWrite(s: RunState): RunState = s.copy(infoWrites = s.infoWrites + 1)
  def addLineRead(s: RunState): RunState = s.copy(lineReads = s.lineReads + 1)
  def addLineWrite(s: RunState): RunState = s.copy(lineWrites = s.lineWrites + 1)
  def addError(s: RunState): RunState = s.copy(errors = s.errors + 1)

  // End processing
  def terminate(s: RunState): RunState = s.copy(terminated = true)

  // show statistics for one file
  def stateReport(s: RunState): Unit = {
    logger.info("Total lines read:              %d".format(s.lineReads))
    logger.info("Total lines written:           %d".format(s.lineWrites))
    logger.info("DialogAgent lines reprocessed: %d".format(s.reprocessed))
    logger.info("DialogAgent lines recovered:   %d".format(s.recovered))
    logger.info("VersionInfo lines written:     %d".format(s.infoWrites))
    logger.info("DAC server resets:             %d".format(s.dacResets))
    logger.info("DAC server classifications:    %d".format(s.dacQueries))
    logger.info("Processing errors              %d".format(s.errors))
  }
}

