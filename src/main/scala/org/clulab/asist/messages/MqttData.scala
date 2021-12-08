package org.clulab.asist.messages
import buildinfo.BuildInfo

/**
 *  A data structure to hold one message bus 
 *
 *  Components used by more than one message class.
 */

// a single Message Bus message
case class BusMessage(
  topic: String,
  text: String // may contain newlines
)

// IDC processing data for one message
case class IdcData(
  extractions: Seq[DialogAgentMessageUtteranceExtraction] = Seq()
  topic: String = null, // "agent"
  
  // put anything at all here
)

