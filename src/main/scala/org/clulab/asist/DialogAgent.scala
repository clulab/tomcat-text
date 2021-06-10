package org.clulab.asist

import java.time.Clock
import org.clulab.odin.{EventMention, Mention, TextBoundMention}
import org.json4s._
import org.json4s.jackson.Serialization
import org.slf4j.LoggerFactory
import spray.json.DefaultJsonProtocol._
import spray.json.JsonParser

import scala.collection.immutable
import scala.io.Source

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
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

  val dialogAgentMessageType = "event"
  val dialogAgentSource = "tomcat_textAnalyzer"
  val dialogAgentSubType = "Event:dialogue_event"
  val dialogAgentVersion = "2.0.0"

  // metadata topics
  val topicChat: String = "minecraft/chat"
  val topicUazAsr: String = "agent/asr/final"
  val topicAptimaAsr: String = "status/asistdataingester/userspeech"
  val topicTrial: String = "trial"
  val inputTopics = List(topicChat, topicUazAsr, topicAptimaAsr, topicTrial)
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

  // report our testbed configuration
  def versionInfo: VersionInfo = {
    val timestamp = Clock.systemUTC.instant.toString
    VersionInfo(
      CommonHeader(
        timestamp = timestamp,
        message_type = dialogAgentMessageType,
        version = dialogAgentVersion
      ),
      CommonMsg(
        timestamp = timestamp,
        source = dialogAgentSource,
        sub_type = dialogAgentSubType,
        version = dialogAgentVersion
      ),
      VersionInfoData(
        agent_name = "tomcat_textAnalyzer",
        owner = "University of Arizona",
        version = dialogAgentVersion,
        source = List(
          "https://gitlab.asist.aptima.com:5050/asist/testbed/uaz_dialog_agent:2.0.0"
        ),
        dependencies = List(),
        config = List(),
        publishes = List(
          VersionInfoDataPublishes(
            topic = outputTopic,
            message_type = dialogAgentMessageType,
            sub_type = dialogAgentSubType
          )
          VersionInfoDataPublishes(
            topic = outputTopic,
            message_type = "agent/versioninfo"
            sub_type = dialogAgentSubType
          )
        ),
        subscribes = List(
          VersionInfoDataSubscribes(
            topic = topicTrial,
            message_type = "agent/versioninfo",
            sub_type = "start"
          ),
          VersionInfoDataSubscribes(
            topic = topicChat,
            message_type = "",
            sub_type = ""
          ),
          VersionInfoDataSubscribes(
            topic = topicUazAsr,
            message_type = "",
            sub_type = ""
          ),
          VersionInfoDataSubscribes(
            topic = topicAptimaAsr,
            message_type = "",
            sub_type = ""
          ),
        )
      )
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
  def toDialogAgentMessage(
    source_type: String,
    source_name: String,
    topic: String,
    metadata: Metadata
  ): DialogAgentMessage = {
    val (extractions, extracted_doc) = 
      extractor.runExtraction(Option(metadata.data.text).getOrElse(""))
    val timestamp = Clock.systemUTC.instant.toString
    DialogAgentMessage(
      CommonHeader(
        timestamp = timestamp,
        message_type = dialogAgentMessageType,
        version = dialogAgentVersion
      ),
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
      DialogAgentMessageData(
        participant_id = topic match {
          case `topicChat` => (metadata.data.sender)
          case `topicUazAsr` => (metadata.data.participant_id)
          case `topicAptimaAsr` => (metadata.data.playername)
          case _ => null
        },
        asr_msg_id = metadata.data.id,
        text = metadata.data.text,
        DialogAgentMessageDataSource(
          source_type = source_type,
          source_name = source_name
        ),
        extractions.map(extraction)
      )
    )
  }

  /** create a DialogAgentMessage without metadata
   *  @param source_type Source of message data, either keyboard input or a file
   *  @param source_name topic or filename
   *  @param participant_id The individual who has spoken
   *  @param text Spoken text to be analyzed
   */
  def toDialogAgentMessage(
    source_type: String,
    source_name: String,
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
        timestamp = timestamp,
        source = dialogAgentSource,
        sub_type = dialogAgentSubType,
        version = dialogAgentVersion,
      ),
      DialogAgentMessageData(
        participant_id = participant_id,
        asr_msg_id = null,
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
