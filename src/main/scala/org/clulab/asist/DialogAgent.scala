//  DialogAgent
//
//  Author:  Joseph Astier, Adarsh Pyarelal
//  Updated:  2021 January
//
package org.clulab.asist

import edu.stanford.nlp.pipeline.StanfordCoreNLP
import java.time.Clock
import java.util.Properties
import org.clulab.odin.{EventMention, Mention, TextBoundMention}
import org.json4s._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write}
import org.json4s.Xml.{toJson, toXml}
import scala.collection.immutable
import scala.io.Source
import scala.util.control.Exception._
import spray.json._
import spray.json.DefaultJsonProtocol._

/** coordinator class for all things chatbot */
trait DialogAgent {

  def info(str: String): Unit = Info("DialogAgent", str)
  def error(str: String): Unit = Error("DialogAgent", str)

  info("Creating pipeline (this may take a few seconds) ...")

  /** Build a pipeline usingr our tokens */
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

  /** Used so Json serializer can recognize case classes */
  implicit val formats = Serialization.formats(NoTypeHints)

  /** Translate an AsrMessage to a DialogAgentMessage  */
  def toDialogAgentMessage(
    a: AsrMessage, 
    topic: String,
    source_type: String): DialogAgentMessage = {
      process(
      topic,
      a.msg.experiment_id, 
      a.msg.participant_id,
      a.data.text,
      source_type 
    )
  }

  /** Translate an ObsMessage to a DialogAgentMessage  */
  def toDialogAgentMessage(
    a: ObsMessage, 
    topic: String,
    source_type: String): DialogAgentMessage = {
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

  /** Return the taxonomy matches found in the mention label */
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
