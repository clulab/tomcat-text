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
  val message_type = "event"
  val source = "tomcat_textAnalyzer" 
  val sub_type = "Event:dialogue_action"

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
  def processExtractions(
      experiment_id: String,
      text: String): List[DialogAgentMessage] = {
    val (extractions, extracted_doc) = extractor.runExtraction(text, "")
    extractions.map(ex => toDialogAgentMessage(experiment_id, text, ex)).toList
  }

  /** Compose a DialogAgentMessage using chat text and extractions */
  private def toDialogAgentMessage(
      experiment_id: String,
      text: String, 
      extraction: Array[Any]
    ): DialogAgentMessage = {

    val mention = extraction(0).asInstanceOf[Mention]
    val timestamp = Clock.systemUTC.instant.toString
    val argument_labels =
      for (key <- mention.arguments.keys)
        yield mention.arguments.get(key).get(0).label
    val taxonomy_matches =
      taxonomy_map(mention.label).map(x => (x("term") -> x("score"))).toSeq

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
        mention.label,
        mention.words.mkString(" "),
        argument_labels.mkString(" "),
        text,
        taxonomy_matches
      )
    )
  }
}
