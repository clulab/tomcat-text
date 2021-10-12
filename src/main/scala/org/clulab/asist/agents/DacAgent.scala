package org.clulab.asist.agents

import com.typesafe.scalalogging.LazyLogging

/**
 * Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 * Extenders of this class can use the TAMU Dialog Act Classifier (TDAC)
 *
 */

abstract class DacAgent (
  val args: DialogAgentArgs = new DialogAgentArgs
) extends DialogAgent with LazyLogging {

  // Dialog Act Classification.  No instantiation if not used.
  val dacClient: Option[DacClient] = if(tdacEnabled) {
    logger.info(s"TDAC enabled, server URL: ${tdacServerUrl}")
    Some (new DacClient(this))
  } else {
    logger.info("TDAC not enabled")
    None
  }

  /** Write the runstate output to the output for the extending class
   * @param rs The current execution state of the agent
   * @return The execution state of the agent after writing the output
   */
  def writeOutput(rs: RunState): RunState

  /** Do the next thing in the processing queue.
   * @param rs The current execution state of the agent
   */
  def iteration(rs: RunState): Unit

  /** Return the URL of the TDAC server, eg "http://localhost:8000" */
  def tdacServerUrl = args.tdacServerUrl
  def tdacEnabled: Boolean = args.tdacEnabled


}
