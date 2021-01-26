//  DialogAnalysisMessage
//
//  Author:  Joseph Astier, Adarsh Pyarelal
//  Date:  2020 December
//
//
package org.clulab.asist

object DialogAgentMessage{
  val version: String = "0.1"
}

/** Part of the DialogAgentMessage class */
case class DialogAgentMessageMsg(
  val source: String = "",
  val experiment_id: String = "",
  val timestamp: String = "",
  val sub_type: String = "Event:dialogue_event",
  val version: String = DialogAgentMessage.version
)

/** Part of the DialogAgentMessage class */
case class DialogAgentMessageData(
  val participant_id: String = "",
  val text: String = "",
  val source: DialogAgentMessageDataSource,
  val extractions:Seq[DialogAgentMessageDataExtraction] = Seq.empty
)

/** Part of the DialogAgentMessageData class */
case class DialogAgentMessageDataExtraction(
  val label: String = "",
  val span: String = "",
  val arguments: String = "",
  val taxonomy_matches: Seq[(String, String)] = Seq.empty
)

/** Part of the DialogAgentMessageData class */
case class DialogAgentMessageDataSource(
  val source_type: String = "message_bus",
  val topic: String = ""
)

/** Contains the full analysis data of one chat message */
case class DialogAgentMessage (
  val header: MessageHeader,
  val msg: DialogAgentMessageMsg,
  val data: DialogAgentMessageData
) 
