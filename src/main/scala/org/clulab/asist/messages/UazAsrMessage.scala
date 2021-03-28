/** 
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  updated:  2021 March
 *
 *  University of Arizona Automated Speech Recognition (ASR) message 
 *
 */
package org.clulab.asist

/** Part of the AsrMessage class */
case class UazAsrMessageData(
  val text: String = null, // "I am going to save a green victim."
  val asr_system: String = null,  // "Google"
  val is_final: Boolean = true,
  val participant_id: String = null  // "participant_1"
)

/** Contains the full structure of a University of Arizona ASR message */
case class UazAsrMessage (
  val data: UazAsrMessageData,   
  val header: CommonHeader,
  val msg: CommonMsg
)
