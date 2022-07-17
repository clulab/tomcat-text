package org.clulab.asist.messages

import buildinfo.BuildInfo
import com.typesafe.config.Config
import ai.lum.common.ConfigFactory

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Testbed specification:
 *  https://gitlab.asist.aptima.com/asist\
 *  /testbed/-/blob/develop/AsistDataIngesterContainer/src\
 *  /AsistDataIngester/Models/HeartbeatMessage.cs
 *
 */

// part of the HeartbeatMessage class
case class HeartbeatMessageData(
  state: String = "N/A",
  active: Boolean = false,
  status: String = "N/A"
)

// published on the Message Bus
case class HeartbeatMessage (
  topic: String = "N/A",
  header: CommonHeader,
  msg: CommonMsg,
  data: HeartbeatMessageData
) 

// member functions
object HeartbeatMessage {
  // remember config settings
  private val config: Config = ConfigFactory.load()

  // publication topic
  val topic: String = config.getString("Heartbeat.topic")

  val header: CommonHeader = CommonHeader(
    message_type = config.getString("Heartbeat.header.message_type"),
  )
  val msg: CommonMsg = CommonMsg(
    source = config.getString("CommonMsg.source"),
    sub_type = config.getString("Heartbeat.msg.sub_type"),
    version = BuildInfo.version,
  )
  val data: HeartbeatMessageData = HeartbeatMessageData(
    state = config.getString("Heartbeat.data.state"),
    active = config.getBoolean("Heartbeat.data.active"),
    status = config.getString("Heartbeat.data.status")
  )

  /** Build from nothing 
   *  @return A new HeartbeatMessage based on default values
   */
  def apply(): HeartbeatMessage = HeartbeatMessage(topic, header, msg, data)


  /** Build with custom data
   *  @return A new default HeartbeatMessage with custom data
   */
  def apply(
    state: String,
    active: Boolean,
    status: String
  ): HeartbeatMessage = HeartbeatMessage(
    topic,
    header,
    msg,
    new HeartbeatMessageData(state, active, status)
  )


  /** Build from a trial message
   *  @param trial A trial message
   *  @return A new HeartbeatMessage based on the trial message
   */
  def apply(
    trial: TrialMessage
  ): HeartbeatMessage = HeartbeatMessage(
    topic, 
    header.copy(
      version = trial.header.version
    ),
    msg.copy(
      trial_id = trial.msg.trial_id,
      experiment_id = trial.msg.experiment_id
    ),
    data.copy()
  )

  /** Set the timing on an existing HeartbeatMessage
   *  @param hm an existing HeartbeatMessage
   *  @param timestamp to be set on the copy
   *  @return the existing message with timestamps set
   */
  def apply(
      hm: HeartbeatMessage,
      timestamp: String
  ): HeartbeatMessage = HeartbeatMessage (
    hm.topic,
    hm.header.copy(timestamp = timestamp),
    hm.msg.copy(timestamp = timestamp),
    hm.data.copy()
  )
}
