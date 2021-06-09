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
package org.clulab.asist


/** Part of the DialogAgentInfo class */
case class VersionInfoData(
  val agent_name: String = "Not Set",
  val version: String = "Not Set",
  val owner: String = "Not Set"
)

/** Contains the full analysis data of one chat message */
case class VersionInfo (
  val header: CommonHeader,
  val msg: CommonMsg,
  val data: VersionInfoData
) 
