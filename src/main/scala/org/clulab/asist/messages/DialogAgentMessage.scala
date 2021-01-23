//  DialogAnalysisMessage
//
//  Author:  Joseph Astier, Adarsh Pyarelal
//  Date:  2020 December
//
//
package org.clulab.asist

/** Part of the DialogAgentMessage class */
case class DialogAgentMessageHeader(
  val timestamp: String = "",  
  val message_type: String = "",
  val version: String = ""
)

/** Part of the DialogAgentMessage class */
case class DialogAgentMessageMsg(
  val source: String = "",
  val experiment_id: String = "",
  val timestamp: String = "",
  val sub_type: String = "",
  val version: String = ""
)

/** Part of the DialogAgentMessage class */
case class DialogAgentMessageData(
  val text: String = "",
  val extractions:Seq[DialogAgentMessageDataExtraction] = Seq.empty
)

/** Part of the DialogAgentMessageData class */
case class DialogAgentMessageDataExtraction(
  val label: String = "",
  val span: String = "",
  val arguments: String = "",
  val taxonomy_matches: Seq[(String, String)] = Seq.empty
)

/** Contains the full analysis data of one chat message */
case class DialogAgentMessage (
  val header: DialogAgentMessageHeader,
  val msg: DialogAgentMessageMsg,
  val data: DialogAgentMessageData
) 
