package org.clulab.asist.messages

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 *  Components used for subscription and publication on the Message Bus
 *
 *  Optional fields are omitted from output if value is not known
 *
 *  Fields required by the Testbed have "not_set" values by default
 *
 *  Optional fields have "N/A" values, used in place of "null".  
 *  The DialogAgent will not publish a field with this value
 */

case class Topic(
  topic: String = "N/A"
)

// Testbed specification:
// https://gitlab.asist.aptima.com/asist/testbed/-/blob/master/MessageSpecs/Common_Header/common_header.json
case class CommonHeader(
  timestamp: String = "not_set",
  message_type: String = "not_set", 
  version: String = "not_set",
)

// Testbed specification:
// https://gitlab.asist.aptima.com/asist/testbed/-/blob/master/MessageSpecs/Common_Message/common_message.json
case class CommonMsg (
  experiment_id: String = "not_set",
  trial_id: String = "N/A",
  timestamp: String = "not_set",
  source: String = "not_set",
  sub_type: String = "not_set",
  version: String = "not_set",
  replay_root_id: String = "N/A",
  replay_id: String = "N/A"
)
