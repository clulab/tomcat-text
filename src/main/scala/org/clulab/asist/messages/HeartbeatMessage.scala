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
 * EXAMPLE:
 *
 * {
 *   "header": {
 *     "timestamp": "2021-10-16T01:07:52.824Z",
 *     "message_type": "status",
 *     "version": "0.1" // use testbed trial version if available
 *   },
 *   "msg": {
 *     "timestamp": "2021-10-16T01:07:52.824Z",
 *     "source": "uaz_dialog_agent",
 *     "sub_type": "heartbeat",
 *     "version": 3.1.1 (or whatever the current dialog agent version is)
 *     "trial_id": "t",
 *     "experiment_id": "e"
 *   },
 *   "data": {
 *     "state": "ok",
 *     "active": true,
 *     "status": "I am processing messages"
 *   }
 * }
 *
 */

case class HeartbeatMessageData(
  state: String = null,
  active: String = null,
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
      active = config.getString("Heartbeat.data.active"),
      status = config.getString("Heartbeat.data.status")
    )
  )
}
