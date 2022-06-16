package org.clulab.asist.messages

import ai.lum.common.ConfigFactory
import org.clulab.asist.agents.DialogAgent
import buildinfo.BuildInfo
import com.typesafe.config.Config
import java.time.Clock

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Testbed version info, based on:
 *
 *  https://gitlab.asist.aptima.com/asist/testbed/-/blob/develop/MessageSpecs/Agent/versioninfo/agent_versioninfo.md
 *
 *  This message is published on the Message Bus whenever a trial 
 *  start message is received
 */

// Part of the VersionInfoMessage class 
case class VersionInfoMessageDataConfig(
  name: String = "N/A",
  value: String = "N/A",
)

// Part of the VersionInfoMessage class 
case class VersionInfoMessageDataTopic(
  topic: String = "N/A",
  message_type: String = "N/A",
  sub_type: String = "N/A",
)

// Part of the VersionInfoMessage class 
case class VersionInfoMessageData(
  agent_name: String = "N/A",
  owner: String = "N/A",
  version: String = "N/A",
  source: Seq[String] = List(),
  dependencies: Seq[String] = List(),
  config: Seq[VersionInfoMessageDataConfig] = List(),
  publishes: Seq[VersionInfoMessageDataTopic] = List(),
  subscribes: Seq[VersionInfoMessageDataTopic] = List()
)

// published on the Message Bus
case class VersionInfoMessage (
  topic: String = "N/A",
  header: CommonHeader,
  msg: CommonMsg,
  data: VersionInfoMessageData
) 

// member functions
object VersionInfoMessage 
{
  // remember config settings
  private val config: Config = ConfigFactory.load()
  private val testbed = config.getString("VersionInfo.testbed")
  private val dataSource = s"${testbed}:${BuildInfo.version}"

  // publication topic
  val topic: String = config.getString("VersionInfo.topic")

  val header: CommonHeader = CommonHeader(
    message_type = config.getString("VersionInfo.header.message_type"),
  )
  val msg: CommonMsg = CommonMsg(
    source = config.getString("VersionInfo.msg.source"),
    sub_type = config.getString("VersionInfo.msg.sub_type"),
    version = BuildInfo.version,
  )
  val data: VersionInfoMessageData = VersionInfoMessageData(
    agent_name = config.getString("VersionInfo.data.agent_name"),
    owner = config.getString("VersionInfo.data.owner"),
    version = BuildInfo.version,
    source = List(dataSource),
    dependencies = List(),
    config = List(),
    publishes = List(
      VersionInfoMessageDataTopic(
        config.getString("DialogAgent.topic"),
        config.getString("DialogAgent.header.message_type"),
        config.getString("DialogAgent.msg.sub_type")
      ),
      VersionInfoMessageDataTopic(
        config.getString("Heartbeat.topic"),
        config.getString("Heartbeat.header.message_type"),
        config.getString("Heartbeat.msg.sub_type")
      ),
      VersionInfoMessageDataTopic(
        config.getString("RollcallResponse.topic"),
        config.getString("RollcallResponse.header.message_type"),
        config.getString("RollcallResponse.msg.sub_type")
      ),
      VersionInfoMessageDataTopic(
        config.getString("VersionInfo.topic"),
        config.getString("VersionInfo.header.message_type"),
        config.getString("VersionInfo.msg.sub_type")
      )
    ),
    subscribes = List(
      VersionInfoMessageDataTopic(
        config.getString("Asr.topic"),
        config.getString("Asr.header.message_type"),
        config.getString("Asr.msg.sub_type")
      ),
      VersionInfoMessageDataTopic(
        config.getString("Chat.topic"),
        config.getString("Chat.header.message_type"),
        config.getString("Chat.msg.sub_type")
      ),
      VersionInfoMessageDataTopic(
        config.getString("RollcallRequest.topic"),
        config.getString("RollcallRequest.header.message_type"),
        config.getString("RollcallRequest.msg.sub_type")
      ),
      VersionInfoMessageDataTopic(
        config.getString("Trial.topic"),
        config.getString("Trial.header.message_type"),
        config.getString("Trial.msg.sub_type.trial_start")
      ),
      VersionInfoMessageDataTopic(
        config.getString("Trial.topic"),
        config.getString("Trial.header.message_type"),
        config.getString("Trial.msg.sub_type.trial_stop")
      )
    )
  )
  
  /** Build from Trial Message
   *  @param trial A testbed Trial object
   */
  def apply(trialMessage: TrialMessage): VersionInfoMessage = {
    val timestamp = Clock.systemUTC.instant.toString
    VersionInfoMessage(
      topic,
      header.copy(
        timestamp = timestamp,
        version = trialMessage.header.version
      ),
      msg.copy(
        timestamp = timestamp,
        trial_id = trialMessage.msg.trial_id,
        experiment_id = trialMessage.msg.experiment_id
      ),
      data.copy()
    )
  }
}
