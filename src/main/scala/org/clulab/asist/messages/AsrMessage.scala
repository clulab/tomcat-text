/** 
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  updated:  2021 March Hackathon
 *
 *  Automated Speech Recognition (ASR) message 
 *
 *  AsrMessages are received on the message bus topic agent/asr
 */
package org.clulab.asist

/** Part of the AsrMessage class */
case class AsrMessageData(
  val text: String = null, // "I am going to save a green victim."
  val asr_system: String = null,  // "Google"
  val is_final: Boolean = true,
  val participant_id: String = null  // "participant_1"
)

/** Contains the full structure of an Asr message */
case class AsrMessage (
  val data: AsrMessageData,   // Data before header in ASR struct
  val header: CommonHeader,
  val msg: CommonMsg
)
