/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Updated:  2021 April
 *
 *  Translate MetadataMessages into DialogAgentMessages
 *
 *   ChatMessage
 *   UazAsrMessage
 *   AptimAsrMessage
 *
 *  @param inputFilename a file containing Messages in Json format.
 *  @param outputFilename a file containing DialogAgentMessage in Json format.
 *  @param nMatches An optional maximum number of taxonomy_matches to return
 */
package org.clulab.asist

import scala.io.Source
import java.io.{File, PrintWriter}
import spray.json.DefaultJsonProtocol._
import spray.json.JsonParser

trait AgentMetadata {

  val topicChat: String = "minecraft/chat"
  val topicUazAsr: String = "agent/asr/final"
  val topicAptimaAsr: String = "status/asistdataingester/userspeech"

  /*
  def toMessage(
    source_type: String,
    source_name: String,
    md: Metadata): Option[DialogAgentMessage] = md.topic match {
    case `topicChat` => toDialogAgentMessage(
      source_type,
      md.topic,
      md.msg,
      md.data.sender,
      md.data.text
    )
    case `topicUazAsr` => toDialogAgentMessage(
      source_type,
      md.topic,
      md.msg,
      md.data.participant_id,
      md.data.text
    )
    case `topicAptimaAsr` => toDialogAgentMessage(
      source_type,
      md.topic,
      md.msg,
      md.data.playername,
      md.data.text
    )
    */

}
