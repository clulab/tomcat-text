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


abstract class AgentJson extends DialogAgent {

  val topicChat: String = "minecraft/chat"
  val topicUazAsr: String = "agent/asr/final"
  val topicAptimaAsr: String = "status/asistdataingester/userspeech"

  val topics: List[String] = List(topicChat, topicUazAsr, topicAptimaAsr)

  val source_type: String

  // Used so Json serializer can recognize case classes
  implicit val formats = Serialization.formats(NoTypeHints)

  /** The participant_id field is in a different place in different structs */
  def participantId(
    topic: String,
    md: MetadataMessage
  ): Option[String] = topic match {
    case `topicChat` => Some(md.data.sender)
    case `topicUazAsr` => Some(md.data.participant_id)
    case `topicAptimaAsr` => Some(md.data.playername)
    case _ => None
  }
      
  /** Serialize a DialogAgentMessageDataExtraction to a Json string
   *  @param data the DialogAgentMessageDataExtraction struct
   *  @return The struct represented as a json string
   */
  def toJson(data: DialogAgentMessageDataExtraction): String = write(data)


  def participantId(md: MetadataMessage):Option[String] = 
    participantId(md.topic, md)

  def toMetadataMessage(json: String): Option[MetadataMessage] = 
    allCatch.opt(read[MetadataMessage](json))

  def toOutputJson(
    source_name: String,
    msg: CommonMsg,
    participant_id: String,
    text: String
  ): Option[String] = {
    val message = toDialogAgentMessage(
      source_type,
      source_name,
      msg,
      participant_id,
      text
    )
    val messageJson = write(message)
    Some(messageJson)
  }

}
