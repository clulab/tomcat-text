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


case class ChatAnalysisMessageHeader(
  val timestamp: String = "",
  val messageType: String = "",
  val version: Double = 0
)

case class ChatAnalysisMessageMsg(
  val source: String = "",
  val experimentId: String = "",
  val timestamp: String = "",
  val subType: String = "",
  val version: Double = 0
)

case class ChatAnalysisMessageData(
  val label: String = "",
  val span: String = "",
  val arguments: String = "",
  val text: String = "",
  val timestamp: String = "",
  val taxonomyMatches: Seq[(String, String)] = Seq.empty
)

case class ChatAnalysisMessage (
  val header: ChatAnalysisMessageHeader,
  val msg: ChatAnalysisMessageMsg,
  val data: ChatAnalysisMessageData
) 


// translate between json strings and ChatAnalysisMessage datatypes
object ChatAnalysisMessageJson {
  implicit  val formats = Serialization.formats(NoTypeHints)
  def apply(cam: ChatAnalysisMessage): String = write(cam)
  def apply(json: String): ChatAnalysisMessage = read[ChatAnalysisMessage](json)
}


