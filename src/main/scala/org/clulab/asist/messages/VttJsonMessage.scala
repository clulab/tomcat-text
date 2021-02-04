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

case class VttJsonMessageHeader (
  val timestamp: String = "",  // "2021-02-04T19:41:57.205166Z"
  val version: String = ""   // "0.1"
)

/** Part of the VttJsonMessage class */
case class VttJsonMessageMsg(
  val timestamp: String = "",  // "2021-02-04T19:41:57.205166Z"
  val experiment_id: String = "",  // null
  val participant_id: String = "",  // "firstname lastname"
  val trial_id: String = "",  // null
  val message_type: String = "", // "observation"
  val version: String = "",  // "0.1"
  val source: String = ""  // "vtt_to_json_msgs_script"
)

/** Part of the VttJsonMessage class */
case class VttJsonMessageData(
  val asr_system: String = "",  // "Zoom"
  val source_filename: String = "", // "AudioTranscript_foobar.vtt"
  val caption_start: String = "", // "00:00:24.090"
  val caption_end: String = "",  // "00:00:32.040"
  val text: String = "" // "We are about to start the experiment."
)

/** Contains the full structure of an VttJson message */
case class VttJsonMessage (
  val header: VttJsonMessageHeader,
  val msg: VttJsonMessageMsg,
  val data: VttJsonMessageData
)
