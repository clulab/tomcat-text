package org.clulab.asist.messages

import com.typesafe.config.Config
import ai.lum.common.ConfigFactory
import org.clulab.asist.agents.JsonUtils


/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *
 */

// subscribed on the Message Bus
case class TdacHeartbeatMessage (
  header: CommonHeader,
  msg: CommonMsg,
  data: HeartbeatMessageData
)

// member functions
object TdacHeartbeatMessage {
  // remember config settings
  private val config: Config = ConfigFactory.load()

  // subscription filter
  private val header_message_type: String =
    config.getString("TdacHeartbeat.header.message_type")
  private val msg_sub_type: String =
    config.getString("TdacHeartbeat.msg.sub_type")

  // publication interval
  val beat_interval: Int = 
    config.getInt("TdacHeartbeat.beat_seconds")

  /* Build from text
   * @param text a JSON string to be converted to a TdacHeartbeat message
   * @return a TdacHeartbeat message meeting the subscrption criteria or None
   */
  def apply(text: String): Option[TdacHeartbeatMessage] =
    JsonUtils.readJson[TdacHeartbeatMessage](text).filter(isSubscribed)

  /* only messages meeting these criteria are processed
   * @param asr An ASR message that may or may not meet the criteria
   * @return true if the message meets the criteria
   */
  def isSubscribed(tdacHeartbeat: TdacHeartbeatMessage): Boolean = (
    (tdacHeartbeat.header.message_type == header_message_type) &&
    (tdacHeartbeat.msg.sub_type == msg_sub_type)
  )
}
