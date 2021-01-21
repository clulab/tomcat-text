/**  ChatAnalysis
 *
 *  Author:  Joseph Astier, Adarsh Pyarelal
 *  Date:  2020 December
 *
 *  Convert a text chat message to a DialogAgentMessage object
 */
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

/** Process text using the StanfordCoreNLP */
class LanguageProcessor {
  val version = "0.1"
  val messageType = "event" // always?
  val source = "ChatAnalysis" 
  val experimentId = "experiment_id"  // where to get?
  val subType = "Event:dialogue_action"  // always?

  /** Build an extractor for our tokens */
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

  /** Compose a list of DialogAgentMessages based on language extractions */
  def languageAnalysis(lang: String): List[DialogAgentMessage] = {
    val (extractions, extracted_doc) = extractor.runExtraction(lang, "")
    extractions.map(ex => toDialogAgentMessage(lang, ex)).toList
  }

  /** Compose a DialogAgentMessage using chat text and extractions */
  private def toDialogAgentMessage(
      chatText: String, 
      extraction: Array[Any]
    ): DialogAgentMessage = {

    val mention = extraction(0).asInstanceOf[Mention]
    val timestamp = Clock.systemUTC.instant.toString
    val argumentLabels =
      for (key <- mention.arguments.keys)
        yield mention.arguments.get(key).get(0).label
    val taxonomyMatches =
      taxonomy_map(mention.label).map(x => (x("term") -> x("score"))).toSeq

    DialogAgentMessage(
      DialogAgentMessageHeader(
        timestamp,
        messageType,
        version
      ),
      DialogAgentMessageMsg(
        source,
        experimentId,
        timestamp,
        subType,
        version
      ),
      DialogAgentMessageData(
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
