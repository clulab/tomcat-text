package org.clulab.asist.messages

import ai.lum.common.ConfigFactory
import com.typesafe.config.Config
import org.clulab.asist.agents.JsonUtils

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  UAZ ASR messages subscribed on the Message Bus
 */

//  part of the AsrMessage class
case class AsrMessageData(
  participant_id: String = "N/A", 
  id: String = "N/A", 
  text: String = "N/A"
)

// subscribed on the Message Bus
case class AsrMessage(
  header: CommonHeader,
  msg: CommonMsg,
  data: AsrMessageData
)

// member functions
object AsrMessage{
  private val config: Config = ConfigFactory.load()

  // subscription filter
  private val header_message_type: String = 
    config.getString("Asr.header.message_type")
  private val msg_sub_type: String =
    config.getString("Asr.msg.sub_type")

  /* Build from text
   * @param text a JSON string to be converted to an ASR message
   * @return an ASR message meeting the subscrption criteria or None
   */
  def apply(text: String): Option[AsrMessage] = 
    JsonUtils.readJson[AsrMessage](text).filter(isSubscribed)

  /* only messages meeting these criteria are processed
   * @param asr An ASR message that may or may not meet the criteria
   * @return true if the message meets the criteria
   */
  def isSubscribed(asr: AsrMessage): Boolean = (
    (asr.header.message_type == header_message_type) &&
    (asr.msg.sub_type == msg_sub_type)
  )
}
