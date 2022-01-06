package org.clulab.asist.messages

import org.clulab.odin.Attachment
import java.time.Clock
import com.typesafe.config.Config
import buildinfo.BuildInfo
import org.clulab.asist.agents.{DialogAgent, JsonUtils}
import ai.lum.common.ConfigFactory

import org.json4s.jackson.Serialization.read

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 *  Dialog Agent Message
 *
 *  DialogAgentMessages are produced by the Dialog Agent, either
 *  as files or on the message bus
 */

/** Part of the DialogAgentMessageData class */
case class DialogAgentMessageUtteranceSource(
  source_type: String = "N/A",
  source_name: String = "N/A"
)

/** Part of the DialogAgentMessageData class */
case class DialogAgentMessageUtteranceExtraction(
  labels: Seq[String] = Seq.empty,
  span: String = "N/A",
  arguments: Map[String, Seq[DialogAgentMessageUtteranceExtraction]] =
    Map.empty,
  attachments: Set[Attachment] = Set.empty, // Json strings
  start_offset: Int = 0,
  end_offset: Int = 0,
  rule: String = "N/A", // The rule used to produce the extraction.
)

/** Part of the DialogAgentMessage class */
case class DialogAgentMessageData(
  participant_id: String = "N/A",
  asr_msg_id: String = "N/A",
  text: String = "N/A",
  dialog_act_label: String  = "N/A",  // Dialog Act Classifier query result.
  utterance_source: DialogAgentMessageUtteranceSource,
  extractions:Seq[DialogAgentMessageUtteranceExtraction] = Seq.empty
)

/** Contains the full analysis data of one chat message */
case class DialogAgentMessage (
  header: CommonHeader,
  msg: CommonMsg,
  data: DialogAgentMessageData
)


/** DialogAgentMessage member functions */
object DialogAgentMessage {
  val config: Config = ConfigFactory.load()

  def readDialogAgentMessage(s: String): DialogAgentMessage = 
    read[DialogAgentMessage](s)

  // remember config settings
  val template:DialogAgentMessage = DialogAgentMessage(
    CommonHeader(
      message_type = config.getString("DialogAgent.header.message_type"),
      version = config.getString("CommonHeader.version")
    ),
    CommonMsg(
      source = config.getString("DialogAgent.msg.source"),
      sub_type = config.getString("DialogAgent.msg.sub_type"),
      version = BuildInfo.version
    ),
    DialogAgentMessageData(
      utterance_source = DialogAgentMessageUtteranceSource()
    )
  )

  /** build from Minecraft chat object
   *  @param source_type Source of message data
   *  @param source_name topic or filename
   *  @param chat Minecraft chat object
   *  @param agent text processor
   */
  def apply(
    source_type: String,
    source_name: String,
    chat: ChatMessage,
    agent: DialogAgent
  ):DialogAgentMessage = {
    val timestamp = Clock.systemUTC.instant.toString
    DialogAgentMessage(
      CommonHeader(
        timestamp = timestamp,
        message_type = template.header.message_type,
        version = chat.header.version
      ),
      CommonMsg(
        experiment_id = chat.msg.experiment_id,
        trial_id = chat.msg.trial_id,
        timestamp = timestamp,
        source = template.msg.source,
        sub_type = template.msg.sub_type,
        version = BuildInfo.version,
        replay_root_id = chat.msg.replay_root_id,
        replay_id = chat.msg.replay_id
      ),
      DialogAgentMessageData(
        participant_id = chat.data.sender,
        text = chat.data.text,
        utterance_source = DialogAgentMessageUtteranceSource(
          source_type,
          source_name
        ),
        extractions = agent.getExtractions(chat.data.text)
      )
    )
  }

  /** Build from UAZ ASR object
  *  @param source_type Source of message data
  *  @param source_name topic or filename
  *  @param asr UAZ ASR object
  *  @param agent text processor
  */
  def apply(
    source_type: String,
    source_name: String,
    asr: AsrMessage,
    agent: DialogAgent
  ):DialogAgentMessage = {
    val timestamp = Clock.systemUTC.instant.toString
    DialogAgentMessage(
      CommonHeader(
        timestamp = timestamp,
        message_type = template.header.message_type,
        version = asr.header.version
      ),
      CommonMsg(
        experiment_id = asr.msg.experiment_id,
        trial_id = asr.msg.trial_id,
        timestamp = timestamp,
        source = template.msg.source,
        sub_type = template.msg.sub_type,
        version = BuildInfo.version,
        replay_root_id = asr.msg.replay_root_id,
        replay_id = asr.msg.replay_id
      ),
      DialogAgentMessageData(
        participant_id = asr.data.participant_id,
        asr_msg_id = asr.data.id,
        text = asr.data.text,
        utterance_source = DialogAgentMessageUtteranceSource(
          source_type,
          source_name
        ),
        extractions = agent.getExtractions(asr.data.text)
      )
    )
  }

  /** build from text
  *  @param source_type Source of message data
  *  @param source_name topic or filename
  *  @param participant_id The individual who has spoken
  *  @param text Spoken text to be analyzed
  *  @param agent text processor
  */
  def apply(
    source_type: String,
    source_name: String,
    participant_id: Option[String],
    text: String,
    agent: DialogAgent
  ):DialogAgentMessage = {
    val timestamp = Clock.systemUTC.instant.toString
    DialogAgentMessage(
      CommonHeader(
        timestamp = timestamp,
        message_type = template.header.message_type,
        version = template.header.version
      ),
      CommonMsg(
        timestamp = timestamp,
        source = template.msg.source,
        sub_type = template.msg.sub_type,
        version = BuildInfo.version,
      ),
      DialogAgentMessageData(
        participant_id = participant_id.getOrElse("N/A"),
        text = text,
        utterance_source = DialogAgentMessageUtteranceSource(
          source_type,
          source_name
        ),
        extractions = agent.getExtractions(text)
      )
    )
  }
}
