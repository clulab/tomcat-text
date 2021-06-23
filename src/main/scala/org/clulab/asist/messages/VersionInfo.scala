package org.clulab.asist.messages


/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  updated:  2021 June
 *
 *  Dialog Agent Info, based on:
 *
 *  https://gitlab.asist.aptima.com/asist/testbed/-/blob/develop/MessageSpecs/Agent/versioninfo/agent_versioninfo.md
 *
 *  This message is generated whenever there is a trial start message 
 *  published on the message bus
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
  agent_name: String = "Not Set", // "tomcat_textAnalyzer"
  owner: String = "Not Set", // "University of Arizona"
  version: String = "Not Set", // "2.0.0"
  source: Seq[String] = List(), // "https://gitlab.asist.aptima.com:5050/asist/testbed/uaz_dialog_agent:2.0.0"
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
