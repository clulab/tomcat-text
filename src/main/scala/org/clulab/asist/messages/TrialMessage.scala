package org.clulab.asist.messages

import ai.lum.common.ConfigFactory
import com.typesafe.config.Config
import org.clulab.asist.agents.JsonUtils

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Testbed trial messages subscribed on the Message Bus
 */

// subscribed on the Message Bus
case class TrialMessage (
  header: CommonHeader,
  msg: CommonMsg
) 

// member functions
object TrialMessage {
  private val config: Config = ConfigFactory.load()

  // state flags
  private val trial_start: String = 
    config.getString("Trial.msg.sub_type.trial_start")
  private val trial_stop: String = 
    config.getString("Trial.msg.sub_type.trial_stop")
 
  // subscription filter
  private val header_message_type: String = 
    config.getString("Trial.header.message_type")

  /* Build from text
   * @param text a JSON string to be converted to a Trial message
   * @return a trial message meeting the subscrption criteria or None
   */
  def apply(text: String): Option[TrialMessage] = 
    JsonUtils.readJson[TrialMessage](text).filter(isSubscribed)

  /* test for trial start
   * @param trial A Trial message that may or may not be a trial start
   * @return true if the message is a Trial Start
   */
  def isStart(trial: TrialMessage): Boolean = 
    (trial.msg.sub_type == trial_start)

  /* test for trial stop
   * @param trial A Trial message that may or may not be a trial stop
   * @return true if the message is a Trial Stop
   */
  def isStop(trial: TrialMessage): Boolean = 
    (trial.msg.sub_type == trial_stop)

  /* only messages meeting these criteria are processed
   * @param trial A Chat message that may or may not meet the criteria
   * @return true if the message meets the criteria
   */
  def isSubscribed(trial: TrialMessage): Boolean = 
     (trial.header.message_type == header_message_type)
}
