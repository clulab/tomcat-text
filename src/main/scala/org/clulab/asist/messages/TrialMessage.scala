package org.clulab.asist.messages

import ai.lum.common.ConfigFactory
import com.typesafe.config.Config
import org.clulab.asist.agents.JsonUtils

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  JSON objects receved on the Message Bus
 */

// Trial start and stop messages
case class TrialMessage (
  header: CommonHeader,
  msg: CommonMsg
) 

object TrialMessage {
  val config: Config = ConfigFactory.load()
  val trial_start = config.getString("Trial.msg.sub_type.trial_start")
  val trial_stop = config.getString("Trial.msg.sub_type.trial_stop")

  // subscription filter
  val message_type = config.getString("Trial.header.message_type")

  // return trial message if we can read it and subscribe to it
  def apply(text: String): Option[TrialMessage] = 
    JsonUtils.readJson[TrialMessage](text).filter(isSubscribed)

  // true if this is a trial start message
  def isStart(trial: TrialMessage): Boolean = 
    (trial.msg.sub_type == trial_start)

  // true if this is a trial stop message
  def isStop(trial: TrialMessage): Boolean = 
    (trial.msg.sub_type == trial_stop)

  def isSubscribed(trial: TrialMessage): Boolean = 
     (trial.header.message_type == message_type)
}
