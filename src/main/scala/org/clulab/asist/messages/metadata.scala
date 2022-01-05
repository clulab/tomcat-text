package org.clulab.asist.messages

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 *  JSON objects receved on the Message Bus
 */

// Trial start and stop messages
case class TrialMessage (
  header: CommonHeader,
  msg: CommonMsg
) 

// Minecraft chat
case class ChatMessageData(
  sender: String = "N/A", 
  text: String = "N/A"
)
case class ChatMessage(
  header: CommonHeader,
  msg: CommonMsg,
  data: ChatMessageData
)

// UAZ ASR messages
case class AsrMessageData(
  participant_id: String = "N/A", 
  id: String = "N/A", 
  text: String = "N/A"
)
case class AsrMessage(
  header: CommonHeader,
  msg: CommonMsg,
  data: AsrMessageData
)

