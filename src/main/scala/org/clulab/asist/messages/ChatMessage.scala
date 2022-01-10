package org.clulab.asist.messages

import ai.lum.common.ConfigFactory
import com.typesafe.config.Config
import org.clulab.asist.agents.JsonUtils

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Minecraft Chat messages subscribed on the Message Bus
 */

// part of the ChatMessage class
case class ChatMessageData(
  sender: String = "N/A", 
  text: String = "N/A"
)

// subscribed on the Messge Bus
case class ChatMessage(
  header: CommonHeader,
  msg: CommonMsg,
  data: ChatMessageData
)

// member functions
object ChatMessage{
  private val config: Config = ConfigFactory.load()

  // subscription filter
  private val header_message_type: String =
    config.getString("Chat.header.message_type")
  private val msg_sub_type: String =
    config.getString("Chat.msg.sub_type")

  /* Build from text
   * @param text a JSON string to be converted to a Chat message
   * @return a Chat message meeting the subscrption criteria or None
   */
  def apply(text: String): Option[ChatMessage] =
    JsonUtils.readJson[ChatMessage](text).filter(isSubscribed)

  /* only messages meeting these criteria are processed
   * @param chat A Chat message that may or may not meet the criteria
   * @return true if the message meets the criteria
   */
  def isSubscribed(chat: ChatMessage): Boolean = (
    (chat.header.message_type == header_message_type) &&
    (chat.msg.sub_type == msg_sub_type)
  )
}
