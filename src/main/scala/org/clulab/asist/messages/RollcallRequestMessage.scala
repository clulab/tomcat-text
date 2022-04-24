package org.clulab.asist.messages

import buildinfo.BuildInfo
import com.typesafe.config.Config
import ai.lum.common.ConfigFactory
import org.clulab.asist.agents.JsonUtils

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  A subscribed structure from the MessageBus
 *
 *  Testbed specification:
 *  ???
 *
 */

// part of the RollcallRequestMessage class
case class RollcallRequestMessageData(
  rollcall_id: String = "not_set"
)

// published on the Message Bus
case class RollcallRequestMessage (
  header: CommonHeader,
  msg: CommonMsg,
  data: RollcallRequestMessageData
) 

// member functions
object RollcallRequestMessage {
  private val config: Config = ConfigFactory.load()

  // subscription filter
  private val header_message_type: String =
    config.getString("RollcallRequest.header.message_type")
  private val msg_sub_type: String =
    config.getString("RollcallRequest.msg.sub_type")

  /* Build from text
   * @param text a JSON string to be converted to a Chat message
   * @return a Chat message meeting the subscrption criteria or None
   */
  def apply(text: String): Option[RollcallRequestMessage] =
    JsonUtils.readJson[RollcallRequestMessage](text).filter(isSubscribed)

  /* only messages meeting these criteria are processed
   * @param chat A Chat message that may or may not meet the criteria
   * @return true if the message meets the criteria
   */
  def isSubscribed(rollcallRequest: RollcallRequestMessage): Boolean = (
    (rollcallRequest.header.message_type == header_message_type) &&
    (rollcallRequest.msg.sub_type == msg_sub_type)
  )
}
