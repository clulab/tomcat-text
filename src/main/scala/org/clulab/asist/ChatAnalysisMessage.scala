//  ChatAnalysisMessage
//
//  Author:  Joseph Astier, Adarsh Pyarelal
//  Date:  2020 November
//
//
//  Data for JSON seriialization

package org.clulab.asist

import java.text.SimpleDateFormat
import org.clulab.odin.Mention
import scala.collection.mutable.ArrayBuffer
import scala.collection.immutable

case class ChatAnalysisMessage(val timestamp: SimpleDateFormat,
    val experimentId: String,
    val extractions: ArrayBuffer[Array[Any]],
    val tax_map: immutable.Map[String, Array[immutable.Map[String, String]]],
    val doc: org.clulab.processors.Document,
    val mention: Mention) {

  val message_type = "event"
  val version = "0.1"
  val timeFormat = new SimpleDateFormat("HH:mm:ss.SSS")
  val msg = Msg(version, experimentId, timeFormat)
  val data = Data(mention, timestamp)

  case class Msg(var version: String, 
      var experiment_id: String,
      var timestamp: SimpleDateFormat) {
    val source = "DialogAgent"
    val filename = "file_name"
    val sub_type = "sub_type"
  }

  case class Data (var mention: Mention, var timestamp: SimpleDateFormat)  {
    val argument_labels =
      for (key <- mention.arguments.keys)
        yield mention.arguments.get(key).get(0).label
    val taxonomy_matches =
      tax_map(mention.label).map(x => (x("term") -> x("score"))).toSeq
    val label = mention.label
    val span = mention.words.mkString(" ") 
    val arguments = argument_labels.mkString(" ")
    val text = doc.sentences(mention.sentence).getSentenceText
  }
}
