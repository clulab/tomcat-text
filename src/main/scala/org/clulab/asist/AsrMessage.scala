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
  val messageType: String = "",  // "observation"
  val version: String = ""  // "0.1"
)

/** Part of the AsrMessage class */
case class AsrMessageMsg(
  val timestamp: String = "",  // "2021-01-19T23:27:58.633967Z"
  val experimentId: String = "",  // null
  val participantId: String = "",  // null
  val trialId: String = "",  // null
  val version: String = "",  // "0.1"
  val source: String = "",  // "tomcat_asr_agent"
  val subType: String = ""  // "asr"
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
object AsrMessageJson {
  implicit  val formats = Serialization.formats(NoTypeHints)

  /** Create a Json serialization from a AsrMessage */
  def apply(am: AsrMessage): String = write(am)

  /** Create an AsrMessage from a Json serialization */
  def apply(json: String): Option[AsrMessage] = try {
    Some(read[AsrMessage](json))
  } catch {
    case t: Throwable => {
      None
    }
  }
}


