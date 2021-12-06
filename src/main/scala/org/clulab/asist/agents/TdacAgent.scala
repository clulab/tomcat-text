package org.clulab.asist.agents

import com.typesafe.scalalogging.LazyLogging
import org.clulab.asist.messages.BusMessage

/**
 * Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 * Extenders of this class can use the TAMU Dialog Act Classifier (TDAC)
 * client variable state (Some, or None) to know if the TDAC is in use.  
 *
 */

abstract class TdacAgent (
  val urlMaybe: Option[String] = None
) extends DialogAgent with LazyLogging {
  
  // Dialog Act Classification.  No instantiation if not used.
  val tdacClient: Option[TdacClient] = urlMaybe match {
    case Some(url) =>
      logger.info(s"TDAC server URL: ${url}")
      Some (new TdacClient(this, url))
    case None =>
      logger.info("TDAC not enabled")
      None
  }

  def tdacInit: Unit = tdacClient.foreach(_.initServer)

  /** Write the runstate output to the output for the extending class
   * @param messages output for the message bus
   * @return The execution state of the agent after writing the output
   */
  def writeOutput(messages: List[BusMessage]): Unit

  /** Do the next thing in the processing queue. */
  def iteration(): Unit

  /** Take appropriate action if the tdacClient reports an exception. */
  def handleError(): Unit
}
