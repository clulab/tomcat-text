//  AsrMessage
//
//  Author:  Joseph Astier, Adarsh Pyarelal
//  Date:  2020 December
//
//
//  JSON serializable message for ASR system message input
package org.clulab.asist

import org.json4s._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write}


/** Part of the AsrMessage class */
case class AsrMessageHeader(
  val timestamp: String = "",  // "2021-01-19T23:27:58.633076Z"
  val message_type: String = "",  // "observation"
  val version: String = ""  // "0.1"
)

/** Part of the AsrMessage class */
case class AsrMessageMsg(
  val timestamp: String = "",  // "2021-01-19T23:27:58.633967Z"
  val experiment_id: String = "",  // null
  val participant_id: String = "",  // null
  val trial_id: String = "",  // null
  val version: String = "",  // "0.1"
  val source: String = "",  // "tomcat_asr_agent"
  val sub_type: String = ""  // "asr"
)

/** Part of the AsrMessage class */
case class AsrMessageData(
  val text: String = "", // "I am going to save a green victim."
  val asr_system: String = "",  // "Google"
)


/** Contains the full structure of an Asr message */
case class AsrMessage (
  val data: AsrMessageData,   // Data before header in ASR struct
  val header: AsrMessageHeader,
  val msg: AsrMessageMsg
)


/** Serialization and deserialization of AsrMessages via Json */
object AsrMessage {
  implicit  val formats = Serialization.formats(NoTypeHints)

  /** Create a Json serialization from a AsrMessage */
  def json(a: AsrMessage): String = write(a)

  /** Create an AsrMessage from a Json serialization */
  def json(j: String): Option[AsrMessage] = try {
    Some(read[AsrMessage](j))
  } catch {
    case t: Throwable => {
      None
    }
  }

  /** Return the experiment ID for this message */
  def experiment_id(a: AsrMessage): String = a.msg.experiment_id

  /** Return the chat language for this message */
  def language(j: String): List[String] = 
    json(j).toList.map(a => a.data.text)

}


