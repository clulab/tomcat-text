/** 
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  updated:  2020 December
 *
 *  Automated Speech Recognition (ASR) message 
 *
 *  AsrMessages are received on the message bus
 */
package org.clulab.asist

/** Part of the AsrMessage class */
case class AsrMessageMsg(
  val timestamp: String = null,  // "2021-01-19T23:27:58.633967Z"
  val experiment_id: String = null,  // 
  val participant_id: String = null,  // "firstname lastname"
  val trial_id: String = null,  // 
  val version: String = null,  // "0.1"
  val source: String = null,  // "tomcat_asr_agent"
  val sub_type: String = null  // "asr"
)

/** Part of the AsrMessage class */
case class AsrMessageData(
  val text: String = null, // "I am going to save a green victim."
  val asr_system: String = null  // "Google"
)

/** Contains the full structure of an Asr message */
case class AsrMessage (
  val data: AsrMessageData,   // Data before header in ASR struct
  val header: MessageHeader,
  val msg: AsrMessageMsg
)
