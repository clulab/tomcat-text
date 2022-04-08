package org.clulab.asist.messages

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 *  Components used for subscription and publication on the Message Bus
 *
 *  Optional fields are omitted from output if value is not known
 */

case class Topic(
  topic: String = "N/A"
)

case class CommonHeader(
  timestamp: String = "N/A",
  message_type: String = "N/A",
  version: String = "N/A",
)

// This subset of the testbed CommonMsg struct contains only fields we use
case class CommonMsg (
  experiment_id: String = "N/A",
  trial_id: String = "N/A",
  timestamp: String = "N/A",
  source: String = "N/A",
  sub_type: String = "N/A",
  version: String = "N/A",
  replay_root_id: String = "N/A", // omitted if null
  replay_id: String = "N/A"  // omitted if null
)
