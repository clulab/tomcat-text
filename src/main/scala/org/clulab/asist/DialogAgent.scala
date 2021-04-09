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

  val DIALOG_AGENT_MESSAGE_TYPE = "event"
  val DIALOG_AGENT_SOURCE = "tomcat_textAnalyzer"
  val DIALOG_AGENT_SUB_TYPE = "Event:dialogue_event"
  val DIALOG_AGENT_VERSION = "1.0"

  private lazy val logger = LoggerFactory.getLogger(this.getClass())

  /** Load the taxonomy map from resource file */
  val taxonomy_map = JsonParser(
    Source.fromResource("taxonomy_map.json").mkString
  ).convertTo[immutable.Map[String, Array[immutable.Map[String, String]]]]

  /** Create the extractor using the pipeline and taxonomy map */
  logger.info("Initializing Extractor (this may take a few seconds) ...")
  val extractor = new Extractor(new AsistEngine(), taxonomy_map)
  // Kickstart the extractor with this task to get lazy init out of the way
  extractor.runExtraction("green victim")
  logger.info("Extractor initialized.")


  /** map the mention label to the taxonomy map */
  def taxonomyMatches(mentionLabel: String) = {
    val matches = taxonomy_map.getOrElse(mentionLabel, Array.empty)
    matches.map(x => (x("term") -> x("score"))).toSeq
  }

  /** Create a DialogAgent extraction from Extractor data */
  def extraction(
    mention: Mention, 
    nMatches: Int
  ): DialogAgentMessageDataExtraction = {
    new DialogAgentMessageDataExtraction
  }

  /** Create a DialogAgent extraction from Extractor data */
  def extraction(mention: Mention): DialogAgentMessageDataExtraction = {

    val originalArgs = mention.arguments.toArray
    val extractionArguments = for {
      (role, ms) <- originalArgs
      converted = ms.map(extraction)
    } yield (role, converted)
    val taxonomy_matches = taxonomyMatches(mention.label)
    val charOffsets: Tuple2[Int, Int] = mention match {
      case e: EventMention => (e.trigger.startOffset, e.trigger.endOffset)
      case e: TextBoundMention => (e.startOffset, e.endOffset)
      case _ => (-1, -1)
    }
    DialogAgentMessageDataExtraction(
        mention.label,
        mention.words.mkString(" "),
        extractionArguments.toMap,
        charOffsets._1,
        charOffsets._2,
        taxonomy_matches
      )
  }


  /** Translate an AptimaAsrMessage to a DialogAgentMessage */
  def toDialogAgentMessage(
      a: AptimaAsrMessage,
      source_type: String,
      source_name: String
  ): DialogAgentMessage = {
    toDialogAgentMessage(
      source_type,
      source_name,
      a.msg,
      a.data.playername,
      a.data.text
    )
  }

  /** Translate an UazAsrMessage to a DialogAgentMessage */
  def toDialogAgentMessage(
      a: UazAsrMessage,
      source_type: String,
      source_name: String
  ): DialogAgentMessage = {
    toDialogAgentMessage(
      source_type,
      source_name,
      a.msg,
      a.data.participant_id,
      a.data.text
    )
  }

  /** Translate a ChatMessage to a DialogAgentMessage */
  def toDialogAgentMessage(
      a: ChatMessage,
      source_type: String,
      source_name: String
  ): DialogAgentMessage = {
    toDialogAgentMessage(
      source_type,
      source_name,
      a.msg,
      a.data.sender,
      a.data.text
    )
  }


  /** create a DialogAgentMessage from text */
  def toDialogAgentMessage(
      source_type: String,
      source_name: String,
      msg: CommonMsg,
      participant_id: String,
      text: String
  ): DialogAgentMessage = {
    val (extractions, extracted_doc) = 
      extractor.runExtraction(Option(text).getOrElse(""))
    val timestamp = Clock.systemUTC.instant.toString
    DialogAgentMessage(
      CommonHeader(
        timestamp = timestamp,
        message_type = DIALOG_AGENT_MESSAGE_TYPE,
        version = DIALOG_AGENT_VERSION
      ),
      CommonMsg(
        experiment_id = msg.experiment_id,
        trial_id = msg.trial_id,
        timestamp = timestamp,
        source = DIALOG_AGENT_SOURCE,
        sub_type = DIALOG_AGENT_SUB_TYPE,
        version = DIALOG_AGENT_VERSION,
        replay_root_id = msg.replay_root_id,
        replay_id = msg.replay_id
      ),
      DialogAgentMessageData(
        participant_id = participant_id,
        text = text,
        DialogAgentMessageDataSource(
          source_type = source_type,
          source_name = source_name
        ),
        extractions.map(extraction)
      )
    )
  }
}
