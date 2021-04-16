/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Updated:  2021 April
 *
 *  Create extractions from text for the ToMCAT project.
 *
 *  https://github.com/clulab/tomcat-text/blob/master/README.md
 *
 *  @param nMatches An optional maximum number of taxonomy_matches to return
 */
package org.clulab.asist

import java.time.Clock
import org.clulab.odin.{EventMention, Mention, TextBoundMention}
import org.json4s._
import org.json4s.jackson.Serialization
import org.slf4j.LoggerFactory
import scala.collection.immutable
import scala.io.Source
import spray.json.DefaultJsonProtocol._
import spray.json.JsonParser

class DialogAgent (val nMatches: Int = 0) {

  private lazy val logger = LoggerFactory.getLogger(this.getClass())

  val dialogAgentMessageType = "event"
  val dialogAgentSource = "tomcat_textAnalyzer"
  val dialogAgentSubType = "Event:dialogue_event"
  val dialogAgentVersion = "1.0"

  val topicChat: String = "minecraft/chat"
  val topicUazAsr: String = "agent/asr/final"
  val topicAptimaAsr: String = "status/asistdataingester/userspeech"

  val inputTopics = List(topicChat, topicUazAsr, topicAptimaAsr)
  val outputTopic = "agent/dialog"

  // Used so Json serializers can recognize case classes
  implicit val formats = Serialization.formats(NoTypeHints)

  // Load the taxonomy map from resource file
  val taxonomy_map = JsonParser(
    Source.fromResource("taxonomy_map.json").mkString
  ).convertTo[immutable.Map[String, Array[immutable.Map[String, String]]]]

  // Create the extractor and run it to get lazy init out of the way 
  logger.info("Initializing Extractor (this may take a few seconds) ...")
  val extractor = new Extractor(new AsistEngine(), taxonomy_map)
  extractor.runExtraction("green victim")
  logger.info("Extractor initialized.")

  /** map the mention label to the taxonomy map */
  def taxonomyMatches(mentionLabel: String): Seq[(String, String)] = 
    if(nMatches == 0) Seq() 
    else {
      val matches = taxonomy_map.getOrElse(mentionLabel, Array.empty)
      val seq = matches.map(x => (x("term") -> x("score"))).toSeq
      seq.take(nMatches)
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
        message_type = dialogAgentMessageType,
        version = dialogAgentVersion
      ),
      CommonMsg(
        experiment_id = msg.experiment_id,
        trial_id = msg.trial_id,
        timestamp = timestamp,
        source = dialogAgentSource,
        sub_type = dialogAgentSubType,
        version = dialogAgentVersion,
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
