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
 *  DialogAgentMessages published on the Message Bus
 *
 *  Fields required by the Testbed to be included with published JSON
 *  have "not_set" values by default
 *
 *  Optional fields have "N/A" values, used in place of "null".
 *  The DialogAgent will not publish a field with this value
 *
 *  Testbed specification:
 *  https://gitlab.asist.aptima.com/asist/testbed\
 *  /-/blob/master/MessageSpecs/DialogAgent/chat_analysis_message.json
 */

// Part of the DialogAgentMessageData class 
case class DialogAgentMessageUtteranceSource(
  source_type: String = "not_set",
  source_name: String = "N/A"
)

// Part of the DialogAgentMessageData class
case class DialogAgentMessageUtteranceExtraction(
  labels: Seq[String] = Seq.empty,
  span: String = "N/A",
  arguments: Map[String, Seq[DialogAgentMessageUtteranceExtraction]] =
    Map.empty,
  attachments: Set[Attachment] = Set.empty, 
  start_offset: Int = 0,
  end_offset: Int = 0,
  rule: String = "N/A", // The rule used to produce the extraction.
)

// Part of the DialogAgentMessage class
case class DialogAgentMessageData(
  participant_id: String = "not_set",
  asr_msg_id: String = "not_set",
  text: String = "not_set",
  utterance_source: DialogAgentMessageUtteranceSource,
  extractions:Seq[DialogAgentMessageUtteranceExtraction] =
    Seq.empty // required
)

// published on the Message Bus
case class DialogAgentMessage (
  topic: String = "N/A",
  header: CommonHeader, // required
  msg: CommonMsg, // required
  data: DialogAgentMessageData // required
)

// member functions
object DialogAgentMessage {
  // remember config settings
  private val config: Config = ConfigFactory.load()

  val topic: String = config.getString("DialogAgent.topic")

  val header: CommonHeader = CommonHeader(
    message_type = config.getString("DialogAgent.header.message_type"),
    version = config.getString("CommonHeader.version")
  )
  val msg: CommonMsg = CommonMsg(
    source = config.getString("DialogAgent.msg.source"),
    sub_type = config.getString("DialogAgent.msg.sub_type"),
    version = BuildInfo.version
  )

  /** build without topic
   *  @param header A CommonHeader object
   *  @param msg  A CommonMsg object
   *  @param data A DialogAgentMessageData object
   */
  def apply(
    header: CommonHeader, // required
    msg: CommonMsg, // required
    data: DialogAgentMessageData // required
  ): DialogAgentMessage = DialogAgentMessage(
    topic = "N/A",
    header,
    msg,
    data
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
      topic,
      header.copy(
        timestamp = timestamp,
        version = chat.header.version
      ),
      msg.copy(
        experiment_id = chat.msg.experiment_id,
        trial_id = chat.msg.trial_id,
        timestamp = timestamp,
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
      topic,
      header.copy(
        timestamp = timestamp,
        version = asr.header.version
      ),
      msg.copy(
        experiment_id = asr.msg.experiment_id,
        trial_id = asr.msg.trial_id,
        timestamp = timestamp,
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

  /** build from utterance text
  *  @param source_type Source of message data
  *  @param source_name topic or filename
  *  @param participant_id The individual who has spoken
  *  @param utterance_text Spoken text to be analyzed
  *  @param agent text processor
  */
  def apply(
    source_type: String,
    source_name: String,
    participant_id: Option[String],
    utterance_text: String,
    agent: DialogAgent
  ):DialogAgentMessage = {
    val timestamp = Clock.systemUTC.instant.toString
    DialogAgentMessage(
      topic,
      header.copy(
        timestamp = timestamp,
      ),
      msg.copy(
        timestamp = timestamp,
      ),
      DialogAgentMessageData(
        participant_id = participant_id.getOrElse("not_set"),
        text = utterance_text,
        utterance_source = DialogAgentMessageUtteranceSource(
          source_type,
          source_name
        ),
        extractions = agent.getExtractions(utterance_text)
      )
    )
  }

  /** build from JSON serialization
  *  @param json text serialization of DialogAgentMessage object
  *  @return A DialogAgentMessage or None
  */
  def apply(json: String): Option[DialogAgentMessage] =
    JsonUtils.readJson[DialogAgentMessage](json)

  /** read from text
   * @param s JSON representation of DialogAgentMessage
   * @return The result of parsing the JSON input string
   */
  def readDialogAgentMessage(s: String): DialogAgentMessage = 
    read[DialogAgentMessage](s)
}
