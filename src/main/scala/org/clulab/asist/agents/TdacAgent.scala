package org.clulab.asist.agents

import com.typesafe.scalalogging.LazyLogging
import org.clulab.asist.messages.BusMessage

/**
 * Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *
 * @param tdacUrl TDAC server network location if set.
 */

abstract class TdacAgent (
  val tdacUrl: Option[String] = None
) extends DialogAgent with LazyLogging {
  
  // Dialog Act Classification.  No instantiation if not used.
  val tdacClient: Option[TdacClient] = tdacUrl match {
    case Some(url) =>
      Some (new TdacClient(this, url))
    case None =>
      logger.info("TDAC not enabled")
      None
  }

  def tdacInit: Unit = tdacClient.foreach(_.initServer)

  /** Write the runstate output to the output for the extending class
   * @param messages output for the message bus
   */
  def writeOutput(messages: List[BusMessage]): Unit 

  /** process the next message in the job queue. */
  def doNextJob(): Unit
}
