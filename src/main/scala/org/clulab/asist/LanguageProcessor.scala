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
import scala.collection.mutable.{ArrayBuffer, Map}
import scala.io.Source
import spray.json._
import spray.json.DefaultJsonProtocol._

import org.clulab.utils.DisplayUtils

/** Process text using the StanfordCoreNLP */
class LanguageProcessor {
  val version = "0.1"
  val message_type = "event"
  val source = "tomcat_textAnalyzer" 
  val sub_type = "Event:dialogue_action"

  /** Build an extractor for our tokens */
  val pipeline = new StanfordCoreNLP(new Properties {
    setProperty(
      "annotators", 
      "tokenize, ssplit, pos, lemma, ner, parse, dcoref"
    )
  })

  val taxonomy_map_json = JsonParser(
    Source.fromResource("taxonomy_map.json").mkString
  )
  val taxonomy_map = taxonomy_map_json
    .convertTo[immutable.Map[String, Array[immutable.Map[String, String]]]]

  val extractor = new Extractor(pipeline, new AsistEngine(), taxonomy_map)

  /** Compose a DialogAgentMessage based on language extractions */
  def process(
      experiment_id: String,
      text: String
  ): DialogAgentMessage = {
    val (extractions, extracted_doc) = extractor.runExtraction(text, "")
    val timestamp = Clock.systemUTC.instant.toString

    DialogAgentMessage(
      DialogAgentMessageHeader(
        timestamp,
        message_type,
        version
      ),
      DialogAgentMessageMsg(
        source,
        experiment_id,
        timestamp,
        sub_type,
        version
      ),
      DialogAgentMessageData(
        text,
        extractions.map(extraction).toList
      )
    )
  }

  /** Create a DialogAgent extraction from Extractor data */
  def extraction(e: Array[Any]): DialogAgentMessageDataExtraction = {
    val mention = e(0).asInstanceOf[Mention]
    val argument_labels =
      for (key <- mention.arguments.keys)
        yield mention.arguments.get(key).get(0).label
    val taxonomy_matches =
      taxonomy_map(mention.label).map(x => (x("term") -> x("score"))).toSeq
    DialogAgentMessageDataExtraction(
      mention.label,
      mention.words.mkString(" "),
      argument_labels.mkString(" "),
      taxonomy_matches
    )
  }
}
