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

import org.slf4j.LoggerFactory


/** Message bus connectivity for dialog agents */
class DialogAgentMqtt(
    val host: String = "",
    val port: String = "",
    override val nMatches: Option[Int] = None
  ) extends DialogAgent with DialogAgentJson {

  // message bus
  val topicInputChat: String = "minecraft/chat"
  val topicInputUazAsr: String = "agent/asr/final"
  val topicInputAptimaAsr: String = "status/asistdataingester/userspeech"
  val topicOutput: String = "agent/dialog"
  
  val agentMqtt = new AgentMqtt(
    host,
    port,
    id = "dialog_agent",
    inputTopics = List(
      topicInputChat, 
      topicInputUazAsr,
      topicInputAptimaAsr
    ),
    outputTopics = List(topicOutput),
    this
  )


  private lazy val logger = LoggerFactory.getLogger(this.getClass())

  // Make sure we're connected to the broker.  Can't run without it.
  if (agentMqtt.connected) logger.info("Running.") else System.exit(1)


  def publish(a: DialogAgentMessage): Unit = agentMqtt.publish(toJson(a))


  /** Publish analysis of messages received on subscription topics 
   *  @param topic:  The message bus topic where the input appeared 
   *  @param json:  A json representation of a case class data struct
   */
  def messageArrived(topic: String, json: String): Unit = {
    topic match {
      case `topicInputChat` => toChatMessage(json).map(a => 
        publish(toDialogAgentMessage(a, "message_bus", topic))
      )
      case `topicInputUazAsr` => toUazAsrMessage(json).map(a => 
        publish(toDialogAgentMessage(a, "message_bus", topic))
      )
      case `topicInputAptimaAsr` => toAptimaAsrMessage(json).map(a => 
        publish(toDialogAgentMessage(a, "message_bus", topic))
      )
      case _ =>
    }
  }
}
