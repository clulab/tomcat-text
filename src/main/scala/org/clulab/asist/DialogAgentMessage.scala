//  DialogAnalysisMessage
//
//  Author:  Joseph Astier, Adarsh Pyarelal
//  Date:  2020 December
//
//
//  JSON serializable message for DialogAgent
package org.clulab.asist

import org.json4s._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write}


/** Part of the DialogAgentMessage class */
case class DialogAgentMessageHeader(
  val timestamp: String = "",  
  val messageType: String = "",
  val version: String = ""
)

/** Part of the DialogAgentMessage class */
case class DialogAgentMessageMsg(
  val source: String = "",
  val experimentId: String = "",
  val timestamp: String = "",
  val subType: String = "",
  val version: String = ""
)

/** Part of the DialogAgentMessage class */
case class DialogAgentMessageData(
  val label: String = "",
  val span: String = "",
  val arguments: String = "",
  val text: String = "",
  val timestamp: String = "",
  val taxonomyMatches: Seq[(String, String)] = Seq.empty
)

/** Contains the full analysis data of one chat message */
case class DialogAgentMessage (
  val header: DialogAgentMessageHeader,
  val msg: DialogAgentMessageMsg,
  val data: DialogAgentMessageData
) 


/** Serialization and deserialization of DialogAgentMessages via Json */
object DialogAgentMessageJson {
  implicit  val formats = Serialization.formats(NoTypeHints)

  /** Create a Json serialization from a DialogAgentMessage */
  def apply(cam: DialogAgentMessage): String = write(cam)

  /** Create a DialogAgentMessage from a Json serialization */
  def apply(json: String): DialogAgentMessage = read[DialogAgentMessage](json)
}
