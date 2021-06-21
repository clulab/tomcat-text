package org.clulab.asist

import java.time.Clock

import ai.lum.common.ConfigFactory
import com.typesafe.config.Config
import org.clulab.odin.{EventMention, Mention, TextBoundMention}
import org.json4s._
import org.json4s.jackson.{Serialization, prettyJson}
import org.json4s.jackson.Serialization.{write, writePretty}
import org.slf4j.LoggerFactory

import scala.collection.immutable
import scala.io.Source
import spray.json.DefaultJsonProtocol._
import spray.json.JsonParser


/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 *  Updated:  2021 June
 *
 *  Create extractions from text for the ToMCAT project.
 *
 *  https://github.com/clulab/tomcat-text/blob/master/README.md
 *
 *  @param nMatches maximum number of taxonomy_matches to return (up to 5)
 */

class DialogAgent (val nMatches: Int = 0) {

  private lazy val logger = LoggerFactory.getLogger(this.getClass())
  private val config: Config = ConfigFactory.load()
  private val pretty: Boolean = config.getBoolean("DialogAgent.pretty_json")

  val dialogAgentMessageType = "event"
  val dialogAgentSource = "tomcat_textAnalyzer"
  val dialogAgentSubType = "Event:dialogue_event"
  val dialogAgentVersion = "2.0.0"

  // metadata topics
  val topicSubChat = "minecraft/chat"
  val topicSubUazAsr = "agent/asr/final"
  val topicSubAptimaAsr = "status/asistdataingester/userspeech"
  val topicSubTrial = "trial"
  val topicPubDialogAgent = "agent/dialog"
  val topicPubVersionInfo = "agent/tomcat_textAnalyzer/versioninfo"

  val subscriptions = List(
    topicSubChat,
    topicSubUazAsr,
    topicSubAptimaAsr,
    topicSubTrial
  )

  val publications = List(
    topicPubDialogAgent,
    topicPubVersionInfo
  )

  // Used so Json serializers can recognize case classes
  implicit val formats = Serialization.formats(NoTypeHints)
  def writeJson[A <: AnyRef](a: A)(implicit formats: Formats): String = {
    if (pretty) {
      writePretty(a)
    } else {
      write(a)
    }
  }

  // Load the taxonomy map from resource file
  val taxonomy_map = JsonParser(
    Source.fromResource("taxonomy_map.json").mkString
  ).convertTo[immutable.Map[String, Array[immutable.Map[String, String]]]]

  // Create the extractor and run it to get lazy init out of the way 
  logger.info("Initializing Extractor (this may take a few seconds) ...")
  val extractor = new Extractor(new AsistEngine(), taxonomy_map)
  extractor.runExtraction("green victim")
  logger.info("Extractor initialized.")

  def commonHeader(timestamp: String): CommonHeader = CommonHeader(
    timestamp = timestamp,
    message_type = dialogAgentMessageType,
    version = dialogAgentVersion
  )

  def commonMsg(timestamp: String): CommonMsg = CommonMsg(
    timestamp = timestamp,
    source = dialogAgentSource,
    sub_type = dialogAgentSubType,
    version = dialogAgentVersion,
  )

  def dialogAgentMessageData(
    participant_id: String,
    asr_msg_id: String, 
    source_type: String,
    source_name: String,
    text: String
  ): DialogAgentMessageData = {
    val (extractions, extracted_doc) = 
      extractor.runExtraction(Option(text).getOrElse(""))
    DialogAgentMessageData(
      participant_id = participant_id,
      asr_msg_id = asr_msg_id,
      text = text,
      DialogAgentMessageDataSource(
        source_type = source_type,
        source_name = source_name
      ),
      extractions.map(extraction)
    )
  }


  /** map the mention label to the taxonomy map
   * @param mentionLabel For taxonomy mapping
   */
  def taxonomyMatches(mentionLabel: String): Seq[(String, String)] = 
    if(nMatches == 0) Seq() 
    else {
      val matches = taxonomy_map.getOrElse(mentionLabel, Array.empty)
      val seq = matches.map(x => (x("term") -> x("score"))).toSeq
      seq.take(nMatches)
    }
  
  /** Create a DialogAgent extraction from Extractor data 
   *  @param mention Contains text to analyze
   */
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

  /** create a DialogAgentMessage with metadata
   *  @param source_type Source of message data, either message_bus or a file
   *  @param source_name topic or filename
   *  @param topic Originating process for message
   *  @param metadata Experiment data 
   */
  def dialogAgentMessage(
    source_type: String,
    source_name: String,
    topic: String,
    metadata: Metadata
  ): DialogAgentMessage = {
    val timestamp = Clock.systemUTC.instant.toString
    val participant_id = topic match {
      case `topicSubChat` => (metadata.data.sender)
      case `topicSubUazAsr` => (metadata.data.participant_id)
      case `topicSubAptimaAsr` => (metadata.data.playername)
      case _ => null
    }
    DialogAgentMessage(
      commonHeader(timestamp),
      CommonMsg(
        experiment_id = metadata.msg.experiment_id,
        trial_id = metadata.msg.trial_id,
        timestamp = timestamp,
        source = dialogAgentSource,
        sub_type = dialogAgentSubType,
        version = dialogAgentVersion,
        replay_root_id = metadata.msg.replay_root_id,
        replay_id = metadata.msg.replay_id
      ),
      dialogAgentMessageData(
        participant_id,
        metadata.data.id,
        source_type,
        source_name,
        metadata.data.text
      )
    )
  }

  /** create a DialogAgentMessage without metadata
   *  @param source_type Source of message data, either keyboard or a file
   *  @param source_name topic or filename
   *  @param participant_id The individual who has spoken
   *  @param text Spoken text to be analyzed
   */
  def dialogAgentMessage(
    source_type: String,
    source_name: String,
    participant_id: String,
    text: String
  ): DialogAgentMessage = {
    val timestamp = Clock.systemUTC.instant.toString
    DialogAgentMessage(
      commonHeader(timestamp),
      commonMsg(timestamp),
      dialogAgentMessageData(
        participant_id,
        null,  // ...
        source_type,
        source_name,
        text
      )
    )
  }
}
