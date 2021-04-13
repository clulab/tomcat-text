/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Updated:  2021 April
 *
 */
package org.clulab.asist

import org.json4s._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write}
import scala.util.control.Exception._


abstract class AgentMetadata extends DialogAgent {

  val topicChat: String = "minecraft/chat"
  val topicUazAsr: String = "agent/asr/final"
  val topicAptimaAsr: String = "status/asistdataingester/userspeech"

  val topics: List[String] = List(topicChat, topicUazAsr, topicAptimaAsr)

  // Used so Json serializer can recognize case classes
  implicit val formats = Serialization.formats(NoTypeHints)

  /** The participant_id field is different in each type */
  def participant_id(
    topic: String,
    md: MetadataMessage): Option[String] = topic match {
    case `topicChat` => Some(md.data.sender)
    case `topicUazAsr` => Some(md.data.participant_id)
    case `topicAptimaAsr` => Some(md.data.playername)
    case _ => None
  }



  /** Serialize a DialogAgentMessage to a Json string
   *  @param data the DialogAgentMessage struct
   *  @return The struct represented as a json string
   */
  def toJson(data: DialogAgentMessage): String = write(data)

  def toMetadata(json: String): Option[MetadataMessage] = try {
    Some(read[MetadataMessage](json))
  } catch {
    case t: Throwable => {
      println("AgentJson.toMetadata")
      println("caught throwable: %s".format(t.toString))
    }
    None
  }

  def foo(
      source_type: String,
      source_name: String,
      topic: String,
      metadata: MetadataMessage
  ): Option[DialogAgentMessage] = {

    println("AgentJson.bar with metadata")
    println("  topic: %s".format(metadata.topic))

    participant_id(topic, metadata) match {
      case Some(id) => Some(
        toDialogAgentMessage(
          source_type,
          topic,
          metadata.msg,
          id,
          metadata.data.text
        )
      )
      case _ => None
    }
  }
}
