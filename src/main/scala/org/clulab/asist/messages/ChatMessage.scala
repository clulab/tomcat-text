/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Updated:  2021 March Hackathon
 *
 *  ChatMessages are received on message bus topic 'minecraft/chat'
 */
package org.clulab.asist


/** Part of the ChatMessage class */
case class ChatMessageData(
  val mission_timer: String = null, // "8 : 36"
  val sender: String = null, // "Aptiminer1"
  val addressees: Seq[String] = Seq.empty, // ["Player746"]
  val text: String = null  // "I'm in room 210"
)

/** Contains the full structure of a chat message */
case class ChatMessage (
  val header: CommonHeader,
  val data: ChatMessageData,
  val msg: CommonMsg
)
