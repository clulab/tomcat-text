//  ObsMessage
//
//  Author:  Joseph Astier, Adarsh Pyarelal
//  Date:  2021 January
//
//  
package org.clulab.asist

/** Part of the ObsMessage class */
case class ObsMessageMsg(
  val experiment_id: String = "",  // "123e4567-e89b-12d3-a456-426655440000"
  val trial_id: String = "",  // "123e4567-e89b-12d3-a456-426655440000"
  val timestamp: String = "",  // "2019-12-26T14:05:02.1412Z"
  val source: String = "",  // "simulator"
  val sub_type: String = "",  // "Event:Chat"
  val version: String = ""  // "0.4"
)

/** Part of the ObsMessage class */
case class ObsMessageData(
  val mission_timer: String = "", // "8 : 36"
  val sender: String = "", // "Aptiminer1"
  val addressees: Seq[String] = Seq.empty, // ["Player746"]
  val text: String = ""  // "I'm in room 210"
)

/** Contains the full structure of a chat/observations message */
case class ObsMessage (
  val data: ObsMessageData,   // Data before header in ASR struct
  val header: MessageHeader,
  val msg: ObsMessageMsg
)
