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
import org.slf4j.LoggerFactory
import scala.collection.immutable
import scala.io.Source
import spray.json._
import spray.json.DefaultJsonProtocol._

import scala.collection.mutable.{ArrayBuffer}

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
  def extraction(e: Array[Any]): Option[DialogAgentMessageDataExtraction] = 
    if(e.size > 0) {
      val mention = e(0).asInstanceOf[Mention]
      val argument_labels = mention.arguments.keys.map(
        mention.arguments.get(_).get(0).label
      )
      val taxonomy_matches = taxonomyMatches(mention.label)
      val charOffsets: Tuple2[Int, Int] = mention match {
        case e: EventMention => (e.trigger.startOffset, e.trigger.endOffset)
        case e: TextBoundMention => (e.startOffset, e.endOffset)
        case _ => (-1,-1)
      }
      Some(
        DialogAgentMessageDataExtraction(
          mention.label,
          mention.words.mkString(" "),
          argument_labels.mkString(" "),
          charOffsets._1,
          charOffsets._2,
          taxonomy_matches
        )
      )
    } else None

  /** Translate an AsrMessage to a DialogAgentMessage */
  def toDialogAgentMessage(
      a: AsrMessage,
      topic: String,
      source_type: String
  ): DialogAgentMessage = {
    toDialogAgentMessage(
      source_type,
      topic,
      a.msg.experiment_id,
      a.data.participant_id,
      a.data.text
    )
  }

  /** Translate an ObsMessage to a DialogAgentMessage */
  def toDialogAgentMessage(
      a: ObsMessage,
      topic: String,
      source_type: String
  ): DialogAgentMessage = {
    toDialogAgentMessage(
      source_type,
      topic,
      a.msg.experiment_id,
      a.data.sender,
      a.data.text
    )
  }

  /** Translate a json-converted .vtt file line to a DialogAgentMessage */
  def toDialogAgentMessage(
      a: VttJsonMessage,
  ): DialogAgentMessage = {
    toDialogAgentMessage(
      "vtt_file",
      a.data.source_filename,
      a.msg.experiment_id,
      a.data.participant_id,
      a.data.text
    )
  }

  /** Return the extractions for the given text, which may be null */
  def runExtraction(text: String): 
    (ArrayBuffer[Array[Any]], org.clulab.processors.Document) = text match {
      case s: String => extractor.runExtraction(s,"")
      case _ => extractor.runExtraction("","")
    }

  /** create a DialogAgentMessage from text */
  def toDialogAgentMessage(
      source_type: String,
      source_name: String,
      experiment_id: String,
      participant_id: String,
      text: String
  ): DialogAgentMessage = {
    val (extractions, extracted_doc) = runExtraction(text)
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
          source_name = source_name
        ),
        extractions.map(extraction).toList.flatten
      )
    )
  }
}
