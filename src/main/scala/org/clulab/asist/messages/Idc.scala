package org.clulab.asist.messages

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Case classes used by the IdcWorker class
 */

// IDC processing data for one message
case class IdcData(
  extractions: Seq[DialogAgentMessageUtteranceExtraction] = Seq(),
  state: IdcWorkerState // put anything at all here
)

// All the variables used by a IdcWorker
case class IdcWorkerState(
  // this is included just to demonstrate that the IDQ worker can keep
  // a persistent state, and that this state can be reset.
  val commandCount: Int = 0
)
