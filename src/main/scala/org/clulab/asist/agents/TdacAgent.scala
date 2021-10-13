package org.clulab.asist.agents

import com.typesafe.scalalogging.LazyLogging

/**
 * Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 * Extenders of this class can use the TAMU Dialog Act Classifier (TDAC)
 * client variable state (Some, or None) to know if the TDAC is in use.  
 *
 */

abstract class TdacAgent (
  val args: DialogAgentArgs = new DialogAgentArgs
) extends DialogAgent with LazyLogging {

  val tdac: String = "TAMU Dialog Act Classifier (TDAC)"
  val serverUrl: String = args.tdacServerUrl

  // Dialog Act Classification.  No instantiation if not used.
  val tdacClient: Option[TdacClient] = if(args.tdacEnabled) {
    logger.info(s"Using ${tdac} server at ${args.tdacServerUrl}")
    Some (new TdacClient(this))
  } else {
    logger.info(s"${tdac} not enabled")
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

  /** Take appropriate action if the tdacClient reports an exception.
   * @param rs The current execution state of the agent
   */
  def handleError(rs: RunState): Unit
}
