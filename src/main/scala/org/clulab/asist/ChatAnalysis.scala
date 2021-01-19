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
import java.time.Clock
import java.util.Properties
import org.clulab.odin.{EventMention, Mention, TextBoundMention}
import scala.collection.immutable
import scala.io.Source
import spray.json._
import spray.json.DefaultJsonProtocol._

class ChatAnalysis {
  val version = 0.1
  val messageType = "event" // always?
  val source = "ChatAnalysis" 
  val experimentId = "experiment_id"  // where to get?
  val subType = "Event:dialogue_action"  // always?

  // Build an extractor for our tokens
  val pipeline = new StanfordCoreNLP(new Properties {
    setProperty(
      "annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref"
    )
  })

  val taxonomy_map_json = JsonParser(
    Source.fromResource("taxonomy_map.json").mkString
  )
  val taxonomy_map = taxonomy_map_json
    .convertTo[immutable.Map[String, Array[immutable.Map[String, String]]]]

  val extractor = new Extractor(pipeline, new AsistEngine(), taxonomy_map)

  // create a list of ChatAnalysisMessages based on the text extractions
  def toChatAnalysisMessages(chatText: String): List[ChatAnalysisMessage] = {
    val (extractions, extracted_doc) = extractor.runExtraction(chatText, "")
    extractions.map(ex => toChatAnalysisMessage(chatText, ex)).toList
  }

  // Compose a ChatAnalysisMessage using chat text and pipeline extractions
  def toChatAnalysisMessage(
      chatText: String, 
      extraction: Array[Any]
    ): ChatAnalysisMessage = {

    val mention = extraction(0).asInstanceOf[Mention]
    val timestamp = Clock.systemUTC.instant.toString
    val argumentLabels =
      for (key <- mention.arguments.keys)
        yield mention.arguments.get(key).get(0).label
    val taxonomyMatches =
      taxonomy_map(mention.label).map(x => (x("term") -> x("score"))).toSeq

    ChatAnalysisMessage(
      ChatAnalysisMessageHeader(
        timestamp,
        messageType,
        version
      ),
      ChatAnalysisMessageMsg(
        source,
        experimentId,
        timestamp,
        subType,
        version
      ),
      ChatAnalysisMessageData(
        mention.label,
        mention.words.mkString(" "),
        argumentLabels.mkString(" "),
        chatText,
        timestamp,
        taxonomyMatches
      )
    )
  }
}
