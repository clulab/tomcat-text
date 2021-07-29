package org.clulab.asist.messages

import org.clulab.asist.agents.DialogAgent


/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 *  Updated:  2021 July
 *
 *  Testbed version info, based on:
 *
 *  https://gitlab.asist.aptima.com/asist/testbed/-/blob/develop/MessageSpecs/Agent/versioninfo/agent_versioninfo.md
 *
 *  This message is generated by the Dialog Agent whenever there is a trial 
 *  start message published on the message bus
 */

/** Configuration settings */
case class VersionInfoDataConfig(
  name: String = "Not Set",
  value: String = "Not Set"
)

/** channel on the Message Bus */
case class VersionInfoDataMessageChannel(
  topic: String = "Not Set",
  message_type: String = "Not Set",
  sub_type: String = "Not Set"
)

/** Part of the Info class */
case class VersionInfoData(
  agent_name: String = "Not Set",
  owner: String = "Not Set",
  version: String = "Not Set",
  source: Seq[String] = List(),
  dependencies: Seq[String] = List(),
  config: Seq[VersionInfoDataConfig] = List(),
  publishes: Seq[VersionInfoDataMessageChannel] = List(),
  subscribes: Seq[VersionInfoDataMessageChannel] = List()
)

/** Contains the full analysis data of one chat message */
case class VersionInfo (
  header: CommonHeader,
  msg: CommonMsg,
  data: VersionInfoData
) 

case class VersionInfoMetadata(
  topic: String,
  header: CommonHeader,
  msg: CommonMsg,
  data: VersionInfoData
) 

object VersionInfoMetadata {

  def apply(da: DialogAgent, timestamp: String): VersionInfoMetadata = {
    val versionInfo = VersionInfo(da, timestamp)
    VersionInfoMetadata(
      topic = da.topicPubVersionInfo,
      header = versionInfo.header,
      msg = versionInfo.msg,
      data = versionInfo.data
    )
  }

  def apply(
    da: DialogAgent, 
    trialMessage: TrialMessage,
    timestamp: String
  ): VersionInfoMetadata = {
    val versionInfo = VersionInfo(da, trialMessage, timestamp)
    VersionInfoMetadata(
      topic = da.topicPubVersionInfo,
      header = versionInfo.header,
      msg = versionInfo.msg,
      data = versionInfo.data
    )
  }
}

// Return a VersionInfo populated with the current DialogAgent 
// testbed configuration
object VersionInfo 
{
  // create a VersionInfo by copying some fields from the input CommonMsg
  def apply(
    da: DialogAgent,
    trialMessage: TrialMessage,
    timestamp: String): VersionInfo = VersionInfo(
      da.commonHeader(timestamp),
      CommonMsg(
        experiment_id = trialMessage.msg.experiment_id,
        trial_id = trialMessage.msg.trial_id,
        timestamp = timestamp,
        source = da.dialogAgentSource,
        sub_type = "versioninfo",
        version = da.dialogAgentVersion,
        replay_root_id = trialMessage.msg.replay_root_id,
        replay_id = trialMessage.msg.replay_id
      ),
      data(da)
    )

  def apply(
    da: DialogAgent, 
    timestamp: String): VersionInfo = VersionInfo(
      da.commonHeader(timestamp),
      da.commonMsg(timestamp),
      data(da)
    )

  def data(da: DialogAgent): VersionInfoData = VersionInfoData(
    agent_name = "tomcat_textAnalyzer",
    owner = "University of Arizona",
    version = da.dialogAgentVersion,
    source = List(
      "https://gitlab.asist.aptima.com:5050/asist/testbed/uaz_dialog_agent:2.0.0"
    ),
    dependencies = List(),
    config = List(),
    publishes = List(
      VersionInfoDataMessageChannel(
        topic = da.topicPubDialogAgent,
        message_type = da.dialogAgentMessageType,
        sub_type = da.dialogAgentSubType
      ),
      VersionInfoDataMessageChannel(
        topic = da.topicPubVersionInfo,
        message_type = "agent/versioninfo",
        sub_type = "versioninfo"
      )
    ),
    subscribes = List(
      VersionInfoDataMessageChannel(
        topic = da.topicSubTrial,
        message_type = "trial",
        sub_type = "versioninfo"
      ),
      VersionInfoDataMessageChannel(
        topic = da.topicSubChat,
        message_type = "chat",
        sub_type = "Event:Chat"
      ),
      VersionInfoDataMessageChannel(
        topic = da.topicSubUazAsr,
        message_type = "observation",
        sub_type = "asr"
      ),
      VersionInfoDataMessageChannel(
        topic = da.topicSubAptimaAsr,
        message_type = "observation",
        sub_type = "asr"
      )
    )
  )
}
