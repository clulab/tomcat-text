//  DialogAnalysisMessage
//
//  Author:  Joseph Astier, Adarsh Pyarelal
//  Date:  2020 December
//
//
//  JSON serializable message for DialogAgent
package org.clulab.asist

import org.json4s._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write}


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
  val label: String = "",
  val span: String = "",
  val arguments: String = "",
  val text: String = "",
  val timestamp: String = "",
  val taxonomy_matches: Seq[(String, String)] = Seq.empty
)

/** Contains the full analysis data of one chat message */
case class DialogAgentMessage (
  val header: DialogAgentMessageHeader,
  val msg: DialogAgentMessageMsg,
  val data: DialogAgentMessageData
) 


/** Serialization and deserialization of DialogAgentMessages via Json */
object DialogAgentMessage{
  implicit  val formats = Serialization.formats(NoTypeHints)

  /** Create a Json serialization from a DialogAgentMessage */
  def json(a: DialogAgentMessage): String = write(a)

  /** Create a DialogAgentMessage from a Json serialization */
  def json(a: String): Option[DialogAgentMessage] = try {
    Some(read[DialogAgentMessage](a))
  } catch {
    case t: Throwable => {
      None
    }
  }

  /** Return the experiment ID for this message */
  def experiment_id(a: DialogAgentMessage): String = a.msg.experiment_id
}
