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
  val message_type: String = "",  // "chat"
  val version: String = ""  // "0.4"
)

/** Part of the ObsMessage class */
case class ObsMessageMsg(
  val experiment_id: String = "",  // "123e4567-e89b-12d3-a456-426655440000"
  val trial_id: String = "",  // "123e4567-e89b-12d3-a456-426655440000"
  val timestamp: String = "",  // "2019-12-26T14:05:02.1412Z"
  val source: String = "",  // "simulator"
  val sub_type: String = "",  // "Event:Chat"
  val version: String = "",  // "0.4"
)

/** Part of the ObsMessage class */
case class ObsMessageData(
  val mission_timer: String = "", // "8 : 36"
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
object ObsMessage {
  implicit  val formats = Serialization.formats(NoTypeHints)

  /** Create a Json serialization from a ObsMessage */
  def json(o: ObsMessage): String = write(o)

  /** Create an ObsMessage from a Json serialization */
  def json(j: String): Option[ObsMessage] = try {
    Some(read[ObsMessage](j))
  } catch {
    case t: Throwable => {
      None
    }
  }

  /** Return the experiment ID for this message */
  def experiment_id(o: ObsMessage): String = o.msg.experiment_id


  /** Return the chat language for this message */
  def language(j: String): List[String] =
    json(j).toList.map(a => a.data.text)
}


