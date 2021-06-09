package org.clulab.asist

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
  val name: String = "Not Set",
  val value: String = "Not Set"
)

/** Incoming channel on the Message Bus */
case class VersionInfoDataSubscribes(
  val topic: String = "Not Set",
  val message_type: String = "Not Set",
  val sub_type: String = "Not Set"
)

/** Outgoing channel on the Message Bus */
case class VersionInfoDataPublishes(
  val topic: String = "Not Set",
  val message_type: String = "Not Set",
  val sub_type: String = "Not Set"
)

/** Part of the Info class */
case class VersionInfoData(
  val agent_name: String = "Not Set", // "tomcat_textAnalyzer"
  val owner: String = "Not Set", // "University of Arizona"
  val version: String = "Not Set", // "2.0.0"
  val source: Seq[String] = List(), // "https://gitlab.asist.aptima.com:5050/asist/testbed/uaz_dialog_agent:2.0.0"
  val dependencies: Seq[String] = List(),
  val config: Seq[VersionInfoDataConfig] = List(),
  val publishes: Seq[VersionInfoDataPublishes] = List(),
  val subscribes: Seq[VersionInfoDataSubscribes] = List()
)

/** Contains the full analysis data of one chat message */
case class VersionInfo (
  val header: CommonHeader,
  val msg: CommonMsg,
  val data: VersionInfoData
) 
