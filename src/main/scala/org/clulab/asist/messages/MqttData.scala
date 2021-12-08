package org.clulab.asist.messages
import scala.collection.mutable.Queue

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Case classes used only within Dialog agents.  Not for publication on
 *  the Message Bus.
 *
 *  For internal use only.
 */

// a single Message Bus message
case class BusMessage(
  topic: String,
  text: String // may contain newlines
)

// IDC processing data for one message
case class IdcData(
  topic: String = "",
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

