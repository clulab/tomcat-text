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


/** Part of the ChatAnalysisMessage class */
case class ChatAnalysisMessageHeader(
  val timestamp: String = "",  
  val messageType: String = "",
  val version: String = ""
)

/** Part of the ChatAnalysisMessage class */
case class ChatAnalysisMessageMsg(
  val source: String = "",
  val experimentId: String = "",
  val timestamp: String = "",
  val subType: String = "",
  val version: String = ""
)

/** Part of the ChatAnalysisMessage class */
case class ChatAnalysisMessageData(
  val label: String = "",
  val span: String = "",
  val arguments: String = "",
  val text: String = "",
  val timestamp: String = "",
  val taxonomyMatches: Seq[(String, String)] = Seq.empty
)

/** Contains the full analysis data of one chat message */
case class ChatAnalysisMessage (
  val header: ChatAnalysisMessageHeader,
  val msg: ChatAnalysisMessageMsg,
  val data: ChatAnalysisMessageData
) 


/** Serialization and deserialization of ChatAnalysisMessages via Json */
object ChatAnalysisMessageJson {
  implicit  val formats = Serialization.formats(NoTypeHints)

  /** Create a Json serialization from a ChatAnalysisMessage */
  def apply(cam: ChatAnalysisMessage): String = write(cam)

  /** Create a ChatAnalysisMessage from a Json serialization */
  def apply(json: String): ChatAnalysisMessage = read[ChatAnalysisMessage](json)
}
