package org.clulab.asist.messages

import ai.lum.common.ConfigFactory
import com.typesafe.config.Config
import org.clulab.asist.agents.JsonUtils

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Minecraft Chat messages receved on the Message Bus
 */

case class ChatMessageData(
  sender: String = "N/A", 
  text: String = "N/A"
)
case class ChatMessage(
  header: CommonHeader,
  msg: CommonMsg,
  data: ChatMessageData
)

object ChatMessage{
  val config: Config = ConfigFactory.load()

  // subscribed if these conditions are met
  val chat_message_type = config.getString("Chat.header.message_type")
  val chat_sub_type = config.getString("Chat.msg.sub_type")

  def apply(text: String): Option[ChatMessage] =
    JsonUtils.readJson[ChatMessage](text).filter(isSubscribed)

  def isSubscribed(chat: ChatMessage): Boolean = (
    (chat.header.message_type == chat_message_type) &&
    (chat.msg.sub_type == chat_sub_type)
  )
}
