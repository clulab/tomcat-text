package org.clulab.asist.messages

import ai.lum.common.ConfigFactory
import com.typesafe.config.Config
import org.clulab.asist.agents.JsonUtils

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  UAZ ASR messages receved on the Message Bus
 */

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

object AsrMessage{
  val config: Config = ConfigFactory.load()

  // subscribed if these conditions are met
  val asr_message_type = config.getString("Asr.header.message_type")
  val asr_sub_type = config.getString("Asr.msg.sub_type")

  def apply(text: String): Option[AsrMessage] = 
    JsonUtils.readJson[AsrMessage](text).filter(isSubscribed)

  // based on VersionInfo subscription
  def isSubscribed(asr: AsrMessage): Boolean = (
    (asr.header.message_type == asr_message_type) &&
    (asr.msg.sub_type == asr_sub_type)
  )
}
