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

  /** Build a pipeline using annotation tokens */
  logger.info("Creating Extractor (this may take a few seconds) ...")
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

  /** map the mention label to the taxonomy map */
  def taxonomyMatches(mentionLabel: String) =
    taxonomy_map(mentionLabel).map(x => (x("term") -> x("score"))).toSeq

  /** Create a DialogAgent extraction from Extractor data */
  def extraction(e: Array[Any]): DialogAgentMessageDataExtraction = {
    val mention = e(0).asInstanceOf[Mention]
    val argument_labels = mention.arguments.keys
      .map(mention.arguments.get(_).get(0).label)
    val taxonomy_matches = taxonomyMatches(mention.label)
    DialogAgentMessageDataExtraction(
      mention.label,
      mention.words.mkString(" "),
      argument_labels.mkString(" "),
      taxonomy_matches
    )
  }

  /** Translate an AsrMessage to a DialogAgentMessage */
  def toDialogAgentMessage(
      a: AsrMessage,
      topic: String,
      source_type: String
  ): DialogAgentMessage = {
    toDialogAgentMessage(
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
    toDialogAgentMessage(
      topic,
      a.msg.experiment_id,
      a.data.sender,
      a.data.text,
      source_type
    )
  }

  /** Translate a json-converted .vtt file line to a DialogAgentMessage */
  def toDialogAgentMessage(
      a: VttJsonMessage,
  ): DialogAgentMessage = {
    toDialogAgentMessage(
      a.data.source_filename,
      a.msg.experiment_id,
      a.msg.participant_id,
      a.data.text,
      "file"
    )
  }

  /** create a DialogAgentMessage from text */
  def toDialogAgentMessage(
      topic: String,
      experiment_id: String,
      participant_id: String,
      text: String,
      source_type: String
  ): DialogAgentMessage = {
    val (extractions, extracted_doc) = extractor.runExtraction(text, "")
    val timestamp = Clock.systemUTC.instant.toString
    val version = "0.1"
    DialogAgentMessage(
      MessageHeader(
        timestamp = timestamp,
        message_type = "event",
        version = version
      ),
      DialogAgentMessageMsg(
        source = "tomcat_textAnalyzer",
        experiment_id = experiment_id,
        timestamp = timestamp,
        sub_type = "Event:dialogue_event",
        version = version
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
}
