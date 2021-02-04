//  DialogAgent
//
//  Author:  Joseph Astier, Adarsh Pyarelal
//  Updated:  2021 January
//
package org.clulab.asist

import edu.stanford.nlp.pipeline.StanfordCoreNLP
import java.time.Clock
import java.util.Properties
import org.clulab.odin.Mention
import org.slf4j.LoggerFactory
import scala.collection.immutable
import scala.io.Source
import spray.json._
import spray.json.DefaultJsonProtocol._

/** Dialog language processor */
trait DialogAgent {

  private val logger = LoggerFactory.getLogger(this.getClass())

  logger.info("Creating Extractor (this may take a few seconds) ...")

  /** Build a pipeline using annotation tokens */
  val pipeline = new StanfordCoreNLP(new Properties {
    setProperty(
      "annotators",
      "tokenize, ssplit, pos, lemma, ner, parse, dcoref"
    )
  })

  /** Load the taxonomy map from resource file */
  val taxonomy_map = JsonParser(
    Source.fromResource("taxonomy_map.json").mkString
  ).convertTo[immutable.Map[String, Array[immutable.Map[String, String]]]]

  /** Create the extractor using the pipeline and taxonomy map */
  val extractor = new Extractor(pipeline, new AsistEngine(), taxonomy_map)
  logger.info("Extractor created.")

  /** Translate an AsrMessage to a DialogAgentMessage */
  def toDialogAgentMessage(
      a: AsrMessage,
      topic: String,
      source_type: String
  ): DialogAgentMessage = {
    process(
      topic,
      a.msg.experiment_id,
      a.msg.participant_id,
      a.data.text,
      source_type
    )
  }

  /** Translate an ObsMessage to a DialogAgentMessage */
  def toDialogAgentMessage(
      a: ObsMessage,
      topic: String,
      source_type: String
  ): DialogAgentMessage = {
    process(
      topic,
      a.msg.experiment_id,
      a.data.sender,
      a.data.text,
      source_type
    )
  }

  /** Compose a DialogAgentMessage based on language extractions */
  def process(
      topic: String,
      experiment_id: String,
      participant_id: String,
      text: String,
      source_type: String
  ): DialogAgentMessage = {
    val (extractions, extracted_doc) = extractor.runExtraction(text, "")
    val timestamp = Clock.systemUTC.instant.toString
    DialogAgentMessage(
      MessageHeader(
        timestamp = timestamp,
        message_type = "event",
        version = DialogAgentMessage.version
      ),
      DialogAgentMessageMsg(
        source = "tomcat_textAnalyzer",
        experiment_id = experiment_id,
        /** Return the taxonomy matches found in the mention label */
        timestamp = timestamp
      ),
      DialogAgentMessageData(
        participant_id = participant_id,
        text = text,
        DialogAgentMessageDataSource(
          source_type = source_type,
          topic = topic
        ),
        extractions.map(extraction).toList
      )
    )
  }

  def taxonomyMatches(mentionLabel: String) =
    taxonomy_map(mentionLabel).map(x => (x("term") -> x("score"))).toSeq

  /** Create a DialogAgent extraction from Extractor data */
  def extraction(e: Array[Any]): DialogAgentMessageDataExtraction = {
    val mention = e(0).asInstanceOf[Mention]
    val argument_labels =
      for (key <- mention.arguments.keys)
        yield mention.arguments.get(key).get(0).label
    val taxonomy_matches = taxonomyMatches(mention.label)
    DialogAgentMessageDataExtraction(
      mention.label,
      mention.words.mkString(" "),
      argument_labels.mkString(" "),
      taxonomy_matches
    )
  }
}
