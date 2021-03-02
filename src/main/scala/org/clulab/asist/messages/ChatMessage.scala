/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Updated:  2021 February
 *
 *  Observation (Obs) message
 *
 *  ChatMessages are received on the message bus 
 */
package org.clulab.asist

/** Part of the ChatMessage class */
case class ChatMessageMsg(
  val experiment_id: String = null,  // "123e4567-e89b-12d3-a456-426655440000"
  val trial_id: String = null,  // "123e4567-e89b-12d3-a456-426655440000"
  val timestamp: String = null,  // "2019-12-26T14:05:02.1412Z"
  val source: String = null,  // "simulator"
  val sub_type: String = null,  // "Event:Chat"
  val version: String = null  // "0.4"
)

/** Part of the ChatMessage class */
case class ChatMessageData(
  val mission_timer: String = null, // "8 : 36"
  val sender: String = null, // "Aptiminer1"
  val addressees: Seq[String] = Seq.empty, // ["Player746"]
  val text: String = null  // "I'm in room 210"
)

/** Contains the full structure of a chat message */
case class ChatMessage (
  val header: MessageHeader,
  val data: ChatMessageData,
  val msg: ChatMessageMsg
)
