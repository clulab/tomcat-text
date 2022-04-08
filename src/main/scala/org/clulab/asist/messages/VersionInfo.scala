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

// Part of the VersionInfo class 
case class VersionInfoDataConfig(
  name: String = "N/A",
  value: String = "N/A",
)

// Part of the VersionInfo class 
case class VersionInfoDataMessageChannel(
  topic: String = "N/A",
  message_type: String = "N/A",
  sub_type: String = "N/A",
)

// Part of the VersionInfo class 
case class VersionInfoData(
  agent_name: String = "N/A",
  owner: String = "N/A",
  version: String = "N/A",
  source: Seq[String] = List(),
  dependencies: Seq[String] = List(),
  config: Seq[VersionInfoDataConfig] = List(),
  publishes: Seq[VersionInfoDataMessageChannel] = List(),
  subscribes: Seq[VersionInfoDataMessageChannel] = List()
)

// published on the Message Bus
case class VersionInfo (
  header: CommonHeader,
  msg: CommonMsg,
  data: VersionInfoData
) 

// member functions
object VersionInfo 
{
  // remember config settings
  private val config: Config = ConfigFactory.load()
  private val testbed = config.getString("VersionInfo.testbed")
  private val dataSource = s"${testbed}:${BuildInfo.version}"

  val header: CommonHeader = CommonHeader(
    message_type = config.getString("VersionInfo.header.message_type"),
  )
  val msg: CommonMsg = CommonMsg(
    source = config.getString("VersionInfo.msg.source"),
    sub_type = config.getString("VersionInfo.msg.sub_type"),
    version = BuildInfo.version,
  )
  val data: VersionInfoData = VersionInfoData(
    agent_name = config.getString("VersionInfo.data.agent_name"),
    owner = config.getString("VersionInfo.data.owner"),
    version = BuildInfo.version,
    source = List(dataSource),
    dependencies = List(),
    config = List(),
    publishes = List(
      VersionInfoDataMessageChannel(
        config.getString("DialogAgent.topic"),
        config.getString("DialogAgent.header.message_type"),
        config.getString("DialogAgent.msg.sub_type")
      ),
      VersionInfoDataMessageChannel(
        config.getString("VersionInfo.topic"),
        config.getString("VersionInfo.header.message_type"),
        config.getString("VersionInfo.msg.sub_type")
      ),
      VersionInfoDataMessageChannel(
        config.getString("Heartbeat.topic"),
        config.getString("Heartbeat.header.message_type"),
        config.getString("Heartbeat.msg.sub_type")
      )
    ),
    subscribes = List(
      VersionInfoDataMessageChannel(
        config.getString("Trial.topic"),
        config.getString("Trial.header.message_type"),
        config.getString("Trial.msg.sub_type.trial_start")
      ),
      VersionInfoDataMessageChannel(
        config.getString("Trial.topic"),
        config.getString("Trial.header.message_type"),
        config.getString("Trial.msg.sub_type.trial_stop")
      ),
      VersionInfoDataMessageChannel(
        config.getString("Asr.topic"),
        config.getString("Asr.header.message_type"),
        config.getString("Asr.msg.sub_type")
      ),
      VersionInfoDataMessageChannel(
        config.getString("Chat.topic"),
        config.getString("Chat.header.message_type"),
        config.getString("Chat.msg.sub_type")
      )
    )
  )
  
  /** Build from Trial Message
   *  @param trial A testbed Trial object
   */
  def apply(trialMessage: TrialMessage): VersionInfo = {
    val timestamp = Clock.systemUTC.instant.toString
    VersionInfo(
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
