package org.clulab.asist.messages

import org.json4s.jackson.Serialization.read

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 *  Updated:  2021 June
 *
 *  Dialog Agent Message
 *
 *  DialogAgentMessages are produced by the Dialog Agent, either
 *  as file output or on the message bus
 */

/** Part of the DialogAgentMessage class */
case class DialogAgentMessageData(
  participant_id: String = null,
  asr_msg_id: String = null,
  text: String = null,
  source: DialogAgentMessageDataSource,
  extractions:Seq[DialogAgentMessageDataExtraction] = Seq.empty
)

/** Part of the DialogAgentMessageData class */
case class DialogAgentMessageDataSource(
  source_type: String = null,
  source_name: String = null
)

/** Part of the DialogAgentMessageData class */
case class DialogAgentMessageDataExtraction(
  label: String = null,
  span: String = null,
  arguments: Map[String, Seq[DialogAgentMessageDataExtraction]] = Map.empty,
  start_offset: Int = 0,
  end_offset: Int = 0,
  taxonomy_matches: Seq[(String, String)] = Seq.empty
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
