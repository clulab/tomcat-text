package org.clulab.asist

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  updated:  2021 June
 *
 *  https://gitlab.asist.aptima.com/asist/testbed/-/blob/hackathon2021/MessageSpecs/Common_Message/common_message.md
 *
 *  Components used by more than one message class.
 */

case class CommonHeader(
  val timestamp: String = null, // "2019-12-26T12:47:23.1234Z" 
  val message_type: String = null, // "event"
  val version: String = null // "1.0"
)

/* This is not the complete CommonMsg struct, it only contains the fields we use
 */
case class CommonMsg (
  val experiment_id: String = null, // "123e4567-e89b-12d3-a456-426655440000"
  val trial_id: String = null, //  "123e4567-e89b-12d3-a456-426655440000"
  val timestamp: String = null, // "2019-12-26T14:05:02.1412Z"
  val source: String = null, // "tomcat_asr_agent"
  val sub_type: String = null, // "asr"
  val version: String = null, // "0.1"
  val replay_root_id: String = null, // "123e4567-e89b-12d3-a456-426655440000"
  val replay_id: String = null // "876e4567-ab65-cfe7-b208-426305dc1234"
)

