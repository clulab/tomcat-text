//  DialogAnalysisMessage
//
//  Author:  Joseph Astier, Adarsh Pyarelal
//  Date:  2020 December
//
//
//  JSON serializable message for DialogAgent
package org.clulab.asist

import spray.json._
import spray.json.DefaultJsonProtocol._



case class Header(
  val timestamp: String = "timestamp",
  val messageType: String = "message_type",
  val version: String = "version",
)

case class Msg(
  val source: String = "source",
  val experimentId: String = "experiment_id",
  val filename: String = "filename",
  val timestamp: String = "timestamp",
  val subType: String = "sub_type",
  val version: String = "version",
)

case class Data(
  val label: String = "label",
  val span: String = "span",
  val arguments: String = "arguments",
  val text: String = "text",
  val timestamp: String = "timestamp",
  val taxonomyMatches: String = "taxonomy_matches",
)


class ChatAnalysisMessage (val h: Header, val m: Msg, val d: Data) {

  def this() {
    this(new Header, new Msg, new Data)
  }

  override def toString: String = {
    "ChatAnalysisMessage{\n  Header\n  Msg\n  Data\n}"
  }
}

