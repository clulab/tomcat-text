package org.clulab.asist.agents

/**
 * Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 * Extenders of this class can own a DacClient and use the
 * TAMU Dialog Act Classifier (TDAC)
 */

abstract trait DacAgent {

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
  def tdacServerUrl: String
}
