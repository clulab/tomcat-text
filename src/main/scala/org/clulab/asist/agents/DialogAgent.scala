package org.clulab.asist.agents

import ai.lum.common.ConfigFactory
import com.typesafe.config.Config
import com.typesafe.scalalogging.LazyLogging
import java.time.Clock
import org.clulab.asist.extraction.TomcatRuleEngine
import org.clulab.asist.messages._
import org.clulab.odin.Mention

import org.json4s._
import org.json4s.jackson.Serialization.{write, writePretty}
import spray.json.DefaultJsonProtocol._
import spray.json.JsonParser
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read,write}
import org.json4s.JField


import scala.util.control.NonFatal


import scala.collection.immutable
import scala.io.Source

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 *  Updated:  2021 August
 *
 *  Create extractions from text for the ToMCAT project.
 *
 *  https://github.com/clulab/tomcat-text/blob/master/README.md
 *
 *  @param nMatches maximum number of taxonomy_matches to return (up to 5)
 */

// A place to keep a growing number of settings for the Dialog Agent
case class DialogAgentArgs(
  // Number of taxonomy matches to include with extractions
  nMatches: Int = 0,
  // Query the Dialog Act Classification server
  withClassifications: Boolean = false,
  // Optionally hard-set the TA3 version number of reprocessed .metadata files
  // If this value is not set, existing version numbers are incremented by 1
  ta3Version: Option[Int] = None
)

class DialogAgent (
  val args: DialogAgentArgs = new DialogAgentArgs
) extends LazyLogging {

  private val config: Config = ConfigFactory.load()
  private val pretty: Boolean = config.getBoolean("DialogAgent.pretty_json")

  val dialogAgentMessageType = "event"
  val dialogAgentSource = "tomcat_textAnalyzer"
  val dialogAgentSubType = "Event:dialogue_event"
  val dialogAgentVersion = "2.1.0"

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

  def nMatches = args.nMatches
  def withClassifications = args.withClassifications
  def ta3Version = args.ta3Version

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
  val extractor = new TomcatRuleEngine()
  extractor.extractFrom("green victim")
  logger.info("Extractor initialized.")

  /** Create a CommonHeader data structure 
   *  @param timestamp When this data was created
   */
  def commonHeader(timestamp: String): CommonHeader = CommonHeader(
    timestamp = timestamp,
    message_type = dialogAgentMessageType,
    version = dialogAgentVersion
  )

  /** Create a CommonMsg data structure 
   *  @param timestamp When this data was created
   */
  def commonMsg(timestamp: String): CommonMsg = CommonMsg(
    timestamp = timestamp,
    source = dialogAgentSource,
    sub_type = dialogAgentSubType,
    version = dialogAgentVersion,
  )

  /**
   * Extract Odin mentions from text.
   * @param text String text to extract from, can be multiple sentences.
   * @return sequence of Odin Mentions
   */
  def extractMentions(text: String): Seq[Mention] = {
    extractor
      .extractFrom(Option(text).getOrElse(""), keepText = true)
      .sortBy(m => (m.sentence, m.label))
  }

  /** Create the data component of the DialogAgentMessage structure
   *  @param participant_id human subject who created the text
   *  @param asr_msg_id from the Automated Speech Recognition system
   *  @param source_type Source of message data, either message_bus or a file
   *  @param source_name topic or filename
   *  @param text The text to be analyzed by the pipeline 
   */
  def dialogAgentMessageData(
    participant_id: String,
    asr_msg_id: String, 
    source_type: String,
    source_name: String,
    text: String
  ): DialogAgentMessageData = 
    DialogAgentMessageData(
      participant_id,
      asr_msg_id,
      text,
      dialog_act_label = null, // ...
      DialogAgentMessageUtteranceSource(
        source_type,
        source_name
      ),
      getExtractions(text)
    )
  
  /** map the mention label to the taxonomy map, the mappings are static
   * and computed ahead of time and stored sorted // FIXME: is this true?.
   * @param mentionLabel For taxonomy mapping
   */
  def taxonomyMatches(mentionLabel: String): Seq[(String, String)] = 
    if(nMatches == 0) Seq() 
    else {
      val matches = taxonomy_map.getOrElse(mentionLabel, Array.empty)
      val seq = matches.map(x => (x("term") -> x("score"))).toSeq
      seq.take(nMatches)
    }

  /** Return an array of all extractions found in the input text
   *  @param text Speech to analyze
   */
  def getExtractions(text: String): Seq[DialogAgentMessageUtteranceExtraction] = 
    extractMentions(text).map(getExtraction)

  /** Create a DialogAgent extraction from Extractor data 
   *  @param mention Contains text to analyze
   */
  def getExtraction(mention: Mention): DialogAgentMessageUtteranceExtraction = {
    val originalArgs = mention.arguments.toArray
    val extractionArguments = for {
      (role, ms) <- originalArgs
      converted = ms.map(getExtraction)
    } yield (role, converted)
    val extractionAttachments = mention.attachments.map(writeJson(_))
    val taxonomy_matches = taxonomyMatches(mention.label)
    DialogAgentMessageUtteranceExtraction(
      mention.label,
      mention.words.mkString(" "),
      extractionArguments.toMap,
      extractionAttachments,
      mention.startOffset,
      mention.endOffset,
      taxonomy_matches
    )
  }

  /** create a DialogAgentMessage with metadata
   *  @param source_type Source of message data, either message_bus or a file
   *  @param source_name topic or filename
   *  @param topic Originating process for message
   *  @param metadata Experiment data 
   */
  def getDialogAgentMessage(
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
  def getDialogAgentMessage(
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

  /* Read a DialogAgentMessageData struct from JSON and replace the extractions
   * @param json A JSON representation of a DialogAgentMessageData struct
   * @return The JSON-defined struct or a defaut if the parsing fails.
   */
  def readDialogAgentMessageData(json: String): DialogAgentMessageData = try {
    val data = read[DialogAgentMessageData](json)
    data.copy(extractions = getExtractions(data.text))
  } catch {
    case NonFatal(t) => {
      logger.error(s"readDialogAgentMessageData could not parse ${json}")
      logger.error(t.toString)
      new DialogAgentMessageData(
        utterance_source = new DialogAgentMessageUtteranceSource
      )
    }
  }


  /** Parse a string into a JValue
   * @param line Hopefully JSON but could be anything the user tries to run
   * @return A JSON value parsed from the line or None
   */
  def parseJValue(line: String): Option[JValue] = try {
    Some(parse(s""" ${line} """))
  } catch {
    case NonFatal(t) =>
      logger.error(s"parseJValue: Could not parse: ${line}\n")
      logger.error(t.toString)
      None
  }
}
