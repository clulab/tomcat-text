package org.clulab.asist.messages

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 *  Updated:  2021 June
 *
 *  https://gitlab.asist.aptima.com/asist/testbed/-/blob/hackathon2021/MessageSpecs/Common_Message/common_message.md
 *
 *  Components used by more than one message class.
 */

case class CommonHeader(
  timestamp: String = null, // "2019-12-26T12:47:23.1234Z"
  message_type: String = null, // "event"
  version: String = "1.1" // Should track the common header version in the Aptima Gitlab repo
)

/* This is not the complete CommonMsg struct, it only contains the fields we use
 */
case class CommonMsg (
  experiment_id: String = null, // "123e4567-e89b-12d3-a456-426655440000"
  trial_id: String = null, //  "123e4567-e89b-12d3-a456-426655440000"
  timestamp: String = null, // "2019-12-26T14:05:02.1412Z"
  source: String = null, // "tomcat_asr_agent"
  sub_type: String = null, // "asr"
  version: String = null, // "0.1"
  replay_root_id: String = null, // "123e4567-e89b-12d3-a456-426655440000"
  replay_id: String = null // "876e4567-ab65-cfe7-b208-426305dc1234"
)

