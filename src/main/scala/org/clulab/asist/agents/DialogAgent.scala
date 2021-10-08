package org.clulab.asist.agents

import ai.lum.common.ConfigFactory
import org.clulab.processors.Document
import buildinfo.BuildInfo
import com.typesafe.config.Config
import com.typesafe.scalalogging.LazyLogging
import java.time.Clock
import org.clulab.asist.extraction.TomcatRuleEngine
import org.clulab.asist.messages._
import org.clulab.odin.Mention
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write, writePretty}
import org.json4s.JField
import spray.json.DefaultJsonProtocol._
import spray.json.JsonParser

import scala.collection.immutable
import scala.io.Source
import scala.util.control.NonFatal

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 *  Updated:  2021 August
 *
 *  Create extractions from text for the ToMCAT project.
 *
 *  https://github.com/clulab/tomcat-text/blob/master/README.md
 *
 */

// A place to keep a growing number of settings for the Dialog Agent
case class DialogAgentArgs(
  // Set this to true to include dialogue act classifications from the TAMU
  // Dialog Act Classification server.
  withClassifications: Boolean = false,
  // Optionally hard-set the TA3 version number of reprocessed .metadata files
  // If this value is not set, existing version numbers are incremented by 1
  ta3Version: Option[Int] = None
)

class DialogAgent (
  val args: DialogAgentArgs = new DialogAgentArgs,
  val engine: TomcatRuleEngine = new TomcatRuleEngine
) extends LazyLogging {

  private val config: Config = ConfigFactory.load()
  private val pretty: Boolean = config.getBoolean("DialogAgent.pretty_json")

  val dialogAgentMessageType = "event"
  val dialogAgentSource = config.getString("DialogAgent.msgSource") 
  val dialogAgentSubType = config.getString("DialogAgent.msgSubType")
  val dialogAgentVersion = BuildInfo.version

  // metadata topics
  val topicSubChat = "minecraft/chat"
  val topicSubUazAsr = "agent/asr/final"
  val topicSubAptimaAsr = "status/asistdataingester/userspeech"
  val topicSubTrial = "trial"
  val topicPubDialogAgent = config.getString("DialogAgent.outputTopic")
  val topicPubVersionInfo = config.getString("DialogAgent.versionInfoTopic")

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

  def withClassifications = args.withClassifications
  def ta3Version = args.ta3Version

  def writeJson[A <: AnyRef](a: A)(implicit formats: Formats): String = {
    if (pretty) {
      writePretty(a)
    } else {
      write(a)
    }
  }

  // Create the engine and run it to get lazy init out of the way 
  logger.info("Initializing Extractor (this may take a few seconds) ...")
  //val engine = new TomcatRuleEngine()
  engine.extractFrom("green victim")
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
    engine
      .extractFrom(Option(text).getOrElse(""), keepText = true)
      .sortBy(m => (m.sentence, m.label))
  }

  def extractMentions(doc: Document): Seq[Mention] = {
    engine
      .extractFrom(doc)
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

   /** Create the data component of the DialogAgentMessage structure
   *  @param participant_id human subject who created the text
   *  @param asr_msg_id from the Automated Speech Recognition system
   *  @param source_type Source of message data, either message_bus or a file
   *  @param source_name topic or filename
   *  @param text The text to be analyzed by the pipeline 
   *  @param extractions extractions obtained from the text
   */
  def dialogAgentMessageData(
    participant_id: String,
    asr_msg_id: String, 
    source_type: String,
    source_name: String,
    text: String,
    extractions: Seq[DialogAgentMessageUtteranceExtraction]
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
      extractions
    )
 
  /** Return an array of all extractions found in the input text
   *  @param text Text to analyze
   */
  def getExtractions(text: String): Seq[DialogAgentMessageUtteranceExtraction] = 
    extractMentions(text).map(getExtraction)

  def getExtractions(mentions: Seq[Mention]): Seq[DialogAgentMessageUtteranceExtraction] = 
    mentions.map(getExtraction)

  /** Create a DialogAgent extraction from Extractor data 
   *  @param mention Contains text to analyze
   */
  def getExtraction(mention: Mention): DialogAgentMessageUtteranceExtraction = {
    val originalArgs = mention.arguments.toArray
    val extractionArguments = for {
      (role, ms) <- originalArgs
      converted = ms.map(getExtraction)
    } yield (role, converted)
    DialogAgentMessageUtteranceExtraction(
      mention.labels,
      mention.words.mkString(" "),
      extractionArguments.toMap,
      mention.attachments,
      mention.startOffset,
      mention.endOffset,
      mention.foundBy,
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
