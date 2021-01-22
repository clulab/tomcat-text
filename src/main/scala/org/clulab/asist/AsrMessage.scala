//  AsrMessage
//
//  Author:  Joseph Astier, Adarsh Pyarelal
//  Date:  2020 December
//
//
package org.clulab.asist

/** Part of the AsrMessage class */
case class AsrMessageHeader(
  val timestamp: String = "",  // "2021-01-19T23:27:58.633076Z"
  val message_type: String = "",  // "observation"
  val version: String = ""  // "0.1"
)

/** Part of the AsrMessage class */
case class AsrMessageMsg(
  val timestamp: String = "",  // "2021-01-19T23:27:58.633967Z"
  val experiment_id: String = "",  // null
  val participant_id: String = "",  // null
  val trial_id: String = "",  // null
  val version: String = "",  // "0.1"
  val source: String = "",  // "tomcat_asr_agent"
  val sub_type: String = ""  // "asr"
)

/** Part of the AsrMessage class */
case class AsrMessageData(
  val text: String = "", // "I am going to save a green victim."
  val asr_system: String = ""  // "Google"
)

/** Contains the full structure of an Asr message */
case class AsrMessage (
  val data: AsrMessageData,   // Data before header in ASR struct
  val header: AsrMessageHeader,
  val msg: AsrMessageMsg
)
