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
  val test: String = "test",
  val timestamp: String = "timestamp",
  val taxonomyMatches: String = "taxonomy_matches",
)


case class ChatAnalysisMessage(
  val header: Header = new Header,
  val msg: Msg = new Msg,
  val data: Data = new Data,
)

