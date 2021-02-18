/**
 * Authors:  Joseph Astier, Adarsh Pyarelal
 *
 * Updated:  2021 February
 *
 * A message type for handling .vtt files that have been converted to
 * json by the "vtt_to_json_msgs" script
 *
 */
package org.clulab.asist

/** Part of the VttJsonMessage class */
case class VttJsonMessageHeader (
  val timestamp: String = null,  // "2021-02-04T19:41:57.205166Z"
  val version: String = null   // "0.1"
)

/** Part of the VttJsonMessage class */
case class VttJsonMessageMsg(
  val timestamp: String = null,  // "2021-02-04T19:41:57.205166Z"
  val experiment_id: String = null,  // 
  val trial_id: String = null,  // 
  val message_type: String = null, // "observation"
  val version: String = null,  // "0.1"
  val source: String = null  // "vtt_to_json_msgs_script"
)

/** Part of the VttJsonMessage class */
case class VttJsonMessageData(
  val asr_system: String = null,  // "Zoom"
  val source_filename: String = null, // "AudioTranscriptFile.vtt"
  val participant_id: String = null,  // "firstname lastname"
  val caption_start: String = null, // "00:00:24.090"
  val caption_end: String = null,  // "00:00:32.040"
  val text: String = null // "We are about to start the experiment."
)

/** Contains the full structure of an VttJson message */
case class VttJsonMessage (
  val header: VttJsonMessageHeader,
  val msg: VttJsonMessageMsg,
  val data: VttJsonMessageData
)
