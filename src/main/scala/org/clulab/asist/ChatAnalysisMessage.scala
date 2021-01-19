//  DialogAnalysisMessage
//
//  Author:  Joseph Astier, Adarsh Pyarelal
//  Date:  2020 December
//
//
//  JSON serializable message for DialogAgent
package org.clulab.asist

import org.json4s._
import org.json4s.jackson.JsonMethods.{compact, parse, render}
import org.json4s.JsonDSL._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write => swrite}
import spray.json._
import spray.json.DefaultJsonProtocol._


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

  def test(cam: ChatAnalysisMessage): Boolean = {
    println("Testing ChatAnalysisMessage serialization")

    val jsonOriginal = toJson(cam)

    val readback = read[ChatAnalysisMessage](jsonOriginal)

    val jsonReadback = toJson(readback)


    if(jsonOriginal == jsonReadback) {
      println("PASS") 
      true
    } else {
      println("FAIL")
      println(" Serialization readback comparison failed.")
      println(" ORIGINAL:\n%s".format(jsonOriginal))
      println()
      println(" READBACK:\n%s".format(jsonReadback))
      false
    }
  }
}

