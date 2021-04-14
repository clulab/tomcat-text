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

  // Used so Json serializer can recognize case classes
  implicit val formats = Serialization.formats(NoTypeHints)

  /** The participant_id field is in a different place in different structs */
  def participant_id(
    topic: String,
    md: MetadataMessage): Option[String] = topic match {
    case `topicChat` => Some(md.data.sender)
    case `topicUazAsr` => Some(md.data.participant_id)
    case `topicAptimaAsr` => Some(md.data.playername)
    case _ => None
  }

  def toMetadataMessage(json: String): Option[MetadataMessage] = 
    allCatch.opt(read[MetadataMessage](json))

  // use the topic we find in the metadata
  def outputJson(
      source_type: String,
      source_name: String,
      json: String
  ): Option[String] = allCatch.opt(read[MetadataMessage](json)) match {
    case Some(metadata) => 
      outputJson(source_type, source_name, metadata.topic, metadata) 
    case _ => None
  }

  // use the passed-in topic
  def outputJson(
      source_type: String,
      source_name: String,
      topic: String,
      json: String
  ): Option[String] = allCatch.opt(read[MetadataMessage](json)) match {
    case Some(metadata) => 
      outputJson(source_type, source_name, topic, metadata) 
    case _ => None
  }

  def outputJson(
      source_type: String,
      source_name: String,
      topic: String,
      metadata: MetadataMessage
  ): Option[String] = participant_id(topic, metadata) match {
    case Some(id) => outputJson(
      source_type,
      topic,
      metadata.msg,
      id,
      metadata.data.text
    )
    case _ => None
  }

  def outputJson(
    source_type: String,
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
