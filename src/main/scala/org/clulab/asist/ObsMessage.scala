//  ObsMessage
//
//  Author:  Joseph Astier, Adarsh Pyarelal
//  Date:  2021 January
//
//
//  JSON serializable message for Observations/chat system message input
package org.clulab.asist

import org.json4s._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write}


/** Part of the ObsMessage class */
case class ObsMessageHeader(
  val timestamp: String = "",  // "2019-12-26T12:47:23.1234Z"
  val messageType: String = "",  // "chat"
  val version: String = ""  // "0.4"
)

/** Part of the ObsMessage class */
case class ObsMessageMsg(
  val experimentId: String = "",  // "123e4567-e89b-12d3-a456-426655440000"
  val trialId: String = "",  // "123e4567-e89b-12d3-a456-426655440000"
  val timestamp: String = "",  // "2019-12-26T14:05:02.1412Z"
  val source: String = "",  // "simulator"
  val subType: String = "",  // "Event:Chat"
  val version: String = "",  // "0.4"
)

/** Part of the ObsMessage class */
case class ObsMessageData(
  val missionTimer: String = "", // "8 : 36"
  val sender: String = "", // "Aptiminer1"
  val addressees: Seq[String] = Seq.empty, // ["Player746"]
  val text: String = "",  // "I'm in room 210"
)


/** Contains the full structure of an observations  message */
case class ObsMessage (
  val data: ObsMessageData,   // Data before header in ASR struct
  val header: ObsMessageHeader,
  val msg: ObsMessageMsg
)


/** Serialization and deserialization of ObsMessages via Json */
object ObsMessageJson {
  implicit  val formats = Serialization.formats(NoTypeHints)

  /** Create a Json serialization from a ObsMessage */
  def apply(am: ObsMessage): String = write(am)

  /** Create an ObsMessage from a Json serialization */
  def apply(json: String): Option[ObsMessage] = try {
    Some(read[ObsMessage](json))
  } catch {
    case t: Throwable => {
      None
    }
  }
}


