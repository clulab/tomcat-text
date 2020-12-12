//  ChatAnalysisMessage
//
//  Author:  Joseph Astier, Adarsh Pyarelal
//  Date:  2020 December
//
//
//  JSON serializable message for DialogAgent
package org.clulab.asist


case class Header(
  val timestamp: String = "timestamp",
  val message_type: String = "message_type",
  val version: String = "version",
)


case class Msg(
  val source: String = "source",
  val experiment_id: String = "experiment_id",
  val filename: String = "filename",
  val timestamp: String = "timestamp",
  val sub_type: String = "sub_type",
  val version: String = "version",
)


case class Data(
  val label: String = "label",
  val span: String = "span",
  val arguments: String = "arguments",
  val test: String = "test",
  val timestamp: String = "timestamp",
  val taxonomy_matches: String = "taxonomy_matches",
)


case class ChatAnalysisMessage(
  val header: Header = new Header,
  val msg: Msg = new Msg,
  val data: Data = new Data,
)

