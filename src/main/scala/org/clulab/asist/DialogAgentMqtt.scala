/**
 * Authors:  Joseph Astier, Adarsh Pyarelal
 *
 * Updated:  2021 March
 *
 * This class reads input from the message bus on subscribed topics,
 * performs analysis on the input, and then publishes the analysis to
 * the output topic.
 *
 * Input and output are in json format.
 */
package org.clulab.asist

/** Message bus connectivity for dialog agents */
class DialogAgentMqtt(
    val host: String = "",
    val port: String = "",
    override val nMatches: Option[Int] = None
) extends DialogAgent 
    with DialogAgentJson {

  // message bus topics
  val topicInputChat: String = "minecraft/chat"
  val topicInputUazAsr: String = "agent/asr/final"
  val topicInputAptimaAsr: String = "status/asistdataingester/userspeech"
  val topicOutput: String = "agent/dialog"
  
  // message bus handler
  val bus = new AgentMqtt(
    host,
    port,
    inputTopics = List(
      topicInputChat, 
      topicInputUazAsr,
      topicInputAptimaAsr
    ),
    topicOutput,
    this
  )

  /** Publish analysis of messages received on subscription topics 
   *  @param topic:  The message bus topic where the input appeared 
   *  @param json:  A json representation of a case class data struct
   */
  def messageArrived(topic: String, json: String): Unit = {
    topic match {
      case `topicInputChat` => toChatMessage(json).map(a => 
        bus.publish(toJson(toDialogAgentMessage(a, "message_bus", topic)))
      )
      case `topicInputUazAsr` => toUazAsrMessage(json).map(b => 
        bus.publish(toJson(toDialogAgentMessage(b, "message_bus", topic)))
      )
      case `topicInputAptimaAsr` => toAptimaAsrMessage(json).map(c => 
        bus.publish(toJson(toDialogAgentMessage(c, "message_bus", topic)))
      )
      case _ =>
    }
  }
}
