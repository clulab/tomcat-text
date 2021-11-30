package org.clulab.asist.messages

import org.clulab.odin.Attachment

import org.json4s.jackson.Serialization.read

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 *  Updated:  2021 August
 *
 *  Dialog Agent Message
 *
 *  DialogAgentMessages are produced by the Dialog Agent, either
 *  as file output or on the message bus
 */

/** Part of the DialogAgentMessageData class */
case class DialogAgentMessageUtteranceSource(
  source_type: String = null,
  source_name: String = null
)

/** Part of the DialogAgentMessageData class */
case class DialogAgentMessageUtteranceExtraction(
  labels: Seq[String] = Seq.empty,
  span: String = null,
  arguments: Map[String, Seq[DialogAgentMessageUtteranceExtraction]] = Map.empty,
  attachments: Set[Attachment] = Set.empty, // Json strings
  start_offset: Int = 0,
  end_offset: Int = 0,
  rule: String = null, // The rule used to produce the extraction.
)

/** Part of the DialogAgentMessage class */
case class DialogAgentMessageData(
  participant_id: String = null,
  asr_msg_id: String = null,
  text: String = null,
  dialog_act_label: String  = null,  // Dialog Act Classifier query result.
  utterance_source: DialogAgentMessageUtteranceSource,
  extractions:Seq[DialogAgentMessageUtteranceExtraction] = Seq.empty
)

/** Contains the full analysis data of one chat message */
case class DialogAgentMessage (
  header: CommonHeader,
  msg: CommonMsg,
  data: DialogAgentMessageData
)

object DialogAgentMessage {
  def readDialogAgentMessage(s: String): DialogAgentMessage = read[DialogAgentMessage](s)
}
