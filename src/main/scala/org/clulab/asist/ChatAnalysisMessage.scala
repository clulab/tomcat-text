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
import org.json4s.jackson.Serialization.{read, write => swrite}


case class ChatAnalysisMessageHeader(
  val timestamp: String = "timestamp",
  val messageType: String = "message_type",
  val version: Double = 0.1
)

case class ChatAnalysisMessageMsg(
  val source: String = "source",
  val experimentId: String = "experiment_id",
  val timestamp: String = "timestamp",
  val subType: String = "sub_type",
  val version: Double = 0.1
)

case class ChatAnalysisMessageData(
  val label: String = "label",
  val span: String = "span",
  val arguments: String = "arguments",
  val text: String = "text",
  val timestamp: String = "timestamp",
  val taxonomyMatches: Seq[(String, String)] = Seq.empty
)

case class ChatAnalysisMessage (
  val header: ChatAnalysisMessageHeader,
  val msg: ChatAnalysisMessageMsg,
  val data: ChatAnalysisMessageData
) 


object ChatAnalysisMessageUtils {
  implicit  val formats = Serialization.formats(NoTypeHints)

  def toJson(cam: ChatAnalysisMessage): String = swrite(cam)

  // Test the Json serialization by using it to create a copy
  // of the original.   If the serializations match, then
  // we know it is working correctly.
  def test(original: ChatAnalysisMessage): Boolean = {
    Report("Testing ChatAnalysisMessage serialization ... ")

    val jsonOriginal = toJson(original)

    val copy = read[ChatAnalysisMessage](jsonOriginal)

    val jsonCopy = toJson(copy)


    if(jsonOriginal == jsonCopy) {
      Report("PASS") 
      true
    } else {
      Report("FAIL")
      Report(" Serialization readback comparison failed.")
      Report(" ORIGINAL:\n%s".format(jsonOriginal))
      Report("")
      Report(" READBACK:\n%s".format(jsonCopy))
      false
    }
  }
}

