//  ChatAnalysis
//
//  Author:  Joseph Astier, Adarsh Pyarelal
//  Date:  2020 December
//
//  Convert a text chat message to a ChatAnalysisMessage object
//
//
package org.clulab.asist

import edu.stanford.nlp.pipeline.{Annotation, StanfordCoreNLP}
import edu.stanford.nlp.pipeline.StanfordCoreNLP
import java.io.{File, PrintWriter}
import java.text.SimpleDateFormat
import java.util.Properties
import org.clulab.odin.{EventMention, Mention, TextBoundMention}
import org.clulab.odin.Mention
import org.json4s.jackson.JsonMethods.{compact, parse, render}
import org.json4s.JsonDSL._
import scala.collection.immutable
import scala.collection.{immutable, mutable}
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.{ArrayBuffer, Map}
import scala.io.Source
import scala.util.parsing.json.JSON
import spray.json._
import spray.json.DefaultJsonProtocol._

import org.json4s._
import org.json4s.jackson.JsonMethods.{compact, parse, render}
import org.json4s.JsonDSL._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write => swrite}
import spray.json._
import spray.json.DefaultJsonProtocol._



class ChatAnalysis {

  val verbose = true
  val time_format = new SimpleDateFormat("HH:mm:ss.SSS") // needs Date?
  val version = 0.1
  val messageType = "event" // always?
  val source = "ChatAnalysis" // this class
  val experimentId = "experiment_id"  // where to get?
  val subType = "Event:dialogue_action"  // always?


  // Build an extractor for our tokens
  val pipeline = new StanfordCoreNLP(new Properties() {
    setProperty(
      "annotators",
      "tokenize, ssplit, pos, lemma, ner, parse, dcoref"
    )
  })

  val taxonomy_json = JsonParser(
    Source.fromResource("taxonomy_map.json").mkString
  )
  val tax_map = taxonomy_json
    .convertTo[immutable.Map[String, Array[immutable.Map[String, String]]]]

  val extractor = new Extractor(pipeline, new AsistEngine(), tax_map)

  // write to stdout if the verbose flag is true
  private def report(msg: String): Unit = if (verbose) 
    println("ChatAnalysis: %s".format(msg))

  def toJson(rawText: String): List[String] = {
    val (extractions, extracted_doc) = extractor.runExtraction(rawText, "")
    val cams = extractions.map(
      extraction => 
        toChatAnalysisMessage("experiment_id", rawText, extraction)
    ).toList
    cams.map(cam => ChatAnalysisMessageUtils.toJson(cam))
  }

  def getTimeStamp(
      char_indices: ArrayBuffer[Int],
      date_values: ArrayBuffer[java.util.Date],
      index: Int
  ): java.util.Date = {
    var cur = char_indices(0)
    var i = 0
    while (cur < index) {
      i += 1
      cur = char_indices(i)
    }
    date_values(i)
  }


  def toChatAnalysisMessage(
      experiment_id: String,
      rawText: String, 
      extraction: Array[Any]
    ): ChatAnalysisMessage = {

    // will have to watch these vars as we loop
    var text: String = ""
    var startOffset = 0; 
    var endOffset = 0
    


    val mention = extraction(0).asInstanceOf[Mention]
    val argument_labels =
      for (key <- mention.arguments.keys)
        yield mention.arguments.get(key).get(0).label
    mention match {
      case cur: EventMention =>
        text = cur.trigger.text
        startOffset = cur.trigger.startOffset
        endOffset = cur.trigger.endOffset
      case cur: TextBoundMention =>
        text = cur.text
        startOffset = cur.startOffset
        endOffset = cur.endOffset
    }
    val timestamp = "timestamp" //getTimeStamp(time_lists._1, time_lists._2, startOffset)
//        ("timestamp" -> "timestamp") ~ //time_format.format(timestamp)) ~
    val timeStr = "timestamp"
    val taxonomy_matches =
      tax_map(mention.label).map(x => (x("term") -> x("score"))).toSeq

    val camHeader = new ChatAnalysisMessageHeader(
      timeStr, // timestamp
      messageType, // messageType
      version // version
    )
    val camMsg = new ChatAnalysisMessageMsg(
      source,  // source
      experimentId, // experimentId
      timeStr, // timestamp
      subType, // subType
      version // version
    )
    val camData = new ChatAnalysisMessageData(
      mention.label,  // label
      mention.words.mkString(" "), // span
      argument_labels.mkString(" "), // arguments
      rawText, // text
      timeStr, // timestamp
      taxonomy_matches // taxonomyMatches
    )
    val cam = ChatAnalysisMessage(camHeader, camMsg, camData)
    ChatAnalysisMessageUtils.test(cam)
    cam
  }
}
