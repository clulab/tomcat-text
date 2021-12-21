package org.clulab.asist.messages

import buildinfo.BuildInfo
import com.typesafe.config.Config


/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Heartbeat Message based on:
 *
 *  https://gitlab.asist.aptima.com/asist/testbed/-/blob/develop/AsistDataIngesterContainer/src/AsistDataIngester/Models/HeartbeatMessage.cs
 *
 */

case class HeartbeatMessageData(
  state: String = null,
  active: Boolean = false,
  status: String = null
)

/** Contains the complete specification for a Heartbeat message */
case class HeartbeatMessage (
  header: CommonHeader,
  msg: CommonMsg,
  data: HeartbeatMessageData
) 

object HeartbeatMessage {

  def apply(
    config: Config, 
    trialMessage: TrialMessage
  ): HeartbeatMessage = HeartbeatMessage(
    CommonHeader(
      // timestamp set when published
      message_type = config.getString("Heartbeat.header.message_type"),
      version = trialMessage.header.version
    ),
    CommonMsg(
      // timestamp set when published
      source = config.getString("Heartbeat.msg.source"),
      sub_type = config.getString("Heartbeat.msg.sub_type"),
      version = BuildInfo.version,
      trial_id = trialMessage.msg.trial_id,
      experiment_id = trialMessage.msg.experiment_id
    ),
    HeartbeatMessageData(
      state = config.getString("Heartbeat.data.state"),
      active = config.getBoolean("Heartbeat.data.active"),
      status = config.getString("Heartbeat.data.status")
    )
  )
}
