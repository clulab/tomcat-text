package org.clulab.asist.messages
import buildinfo.BuildInfo

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
  extractions: Seq[DialogAgentMessageUtteranceExtraction] = Seq(),
  topic: String = "",
  idx: Int = 0
  
  // put anything at all here
)

// All the variables used by a IdcWorker
case class IdcWorkerState(
  val queue: Queue[Seq[DialogAgentMessageUtteranceExtraction]] = new Queue,

  // this is included just to demonstrate that the IDQ worker can keep
  // a persistent state, and that this state can be reset.
  val commandCount = 0
}
