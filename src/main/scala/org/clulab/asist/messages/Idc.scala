package org.clulab.asist.messages
import scala.collection.mutable.Queue

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Case classes used by the IdcWorker class
 */

// IDC processing data for one message
case class IdcData(
  topic: String = "N/A",
  extractions: Seq[DialogAgentMessageUtteranceExtraction] = Seq()

  // put anything at all here
)

// All the variables used by a IdcWorker
case class IdcWorkerState(
  val queue: Queue[IdcData] = new Queue,

  // this is included just to demonstrate that the IDQ worker can keep
  // a persistent state, and that this state can be reset.
  var commandCount: Int = 0
)
