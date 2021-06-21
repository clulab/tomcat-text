package org.clulab.asist

import java.time.Clock

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Updated:  2021 June
 *
 *  Create a VersionInfo data structure describing the state of the
 *  DialogAgent
 */

object AgentVersionInfo {

  // report the Dialog Agent testbed configuration
  def apply(da: DialogAgent): VersionInfo = {
    val timestamp = Clock.systemUTC.instant.toString
    VersionInfo(
      da.commonHeader(timestamp),
      da.commonMsg(timestamp),
      VersionInfoData(
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
          )
          // should we include the trial version info publication channel?
        ),
        subscribes = List(
          VersionInfoDataMessageChannel(
            topic = da.topicSubTrial,
            message_type = "agent/versioninfo",
            sub_type = "start"
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
          // should we incude the trial version info subscription channel?
        )
      )
    )
  }
}
