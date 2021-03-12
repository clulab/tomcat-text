/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  updated:  2021 February
 *
 *  Dialog Agent Message
 *
 *  DialogAgentMessages are produced by the Dialog Agent, either
 *  as file output or on the message bus
 */
package org.clulab.asist

/** Part of the DialogAgentMessage class */
case class DialogAgentMessageMsg(
  val source: String = null,
  val experiment_id: String = null,
  val timestamp: String = null,
  val sub_type: String = null,
  val version: String = null
)

/** Part of the DialogAgentMessage class */
case class DialogAgentMessageData(
  val participant_id: String = null,
  val text: String = null,
  val source: DialogAgentMessageDataSource,
  val extractions:Seq[DialogAgentMessageDataExtraction] = Seq.empty
)

/** Part of the DialogAgentMessageData class */
case class DialogAgentMessageDataSource(
  val source_type: String = null,
  val source_name: String = null 
)

/** Part of the DialogAgentMessageData class */
case class DialogAgentMessageDataExtraction(
  val label: String = null,
  val span: String = null,
  val arguments: Map[String, Seq[DialogAgentMessageDataExtraction]] = null,
  val start_offset: Int = 0,
  val end_offset: Int = 0,
  val taxonomy_matches: Seq[(String, String)] = Seq.empty
)

/** Contains the full analysis data of one chat message */
case class DialogAgentMessage (
  val header: MessageHeader,
  val msg: DialogAgentMessageMsg,
  val data: DialogAgentMessageData
) 
