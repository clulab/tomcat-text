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

/** global settings */
object DialogAgentMqttSettings {
  // default message bus broker location
  val host: String = "localhost"
  val port: String = "1883"

  // subscription from message bus
  val topicInputChat: String = "minecraft/chat"
  val topicInputUazAsr: String = "agent/asr"
  val topicInputAptimaAsr: String = "status/asistdataingester/userspeech"

  // publication to message bus
  val topicOutput: String = "agent/dialog"
}


/** Message bus connectivity for dialog agents */
class DialogAgentMqtt(
    override val host: String = DialogAgentMqttSettings.host,
    override val port: String = DialogAgentMqttSettings.port
) extends AgentMqtt (
      host,
      port,
      id = "dialog_agent",
      inputTopics = List(
        DialogAgentMqttSettings.topicInputChat, 
        DialogAgentMqttSettings.topicInputUazAsr,
        DialogAgentMqttSettings.topicInputAptimaAsr
      ),
      outputTopics = List(DialogAgentMqttSettings.topicOutput)
    )
    with DialogAgent
    with DialogAgentJson {

  private lazy val logger = LoggerFactory.getLogger(this.getClass())

  // Make sure we're connected to the broker.  Can't run without it.
  if (mqttConnected) logger.info("Running.") else System.exit(1)

  /** Publish a DialogAgentMessage as a Json serialization
   *  @param a:  A compete DialogAgent output message
   */
  def publish(a: DialogAgentMessage): Unit = publish(toJson(a))


  /** Convert a json-serialized ChatMessage to a DialogAgent message
   *  and publish to the message bus.
   *  @param msg:  input text from the Minecraft chat textfield
   */
  def processChat(msg: ChatMessage): Unit = 
    publish(toDialogAgentMessage(msg, "message_bus", DialogAgentMqttSettings.topicInputChat))


  /** Convert a json-serialized UazAsrMessage to a DialogAgent message
   *  and publish to the message bus if the 'is_final' flag is set.
   *  @param msg: Input from the Minecraft microphone
   */
  def processUazAsr(msg: UazAsrMessage): Unit = if(msg.data.is_final)
    publish(toDialogAgentMessage(msg, "message_bus", DialogAgentMqttSettings.topicInputUazAsr))


  /** Convert a json-serialized AptimaAsrMessage to a DialogAgent message
   *  and publish to the message bus 
   *  @param msg: Input from the Minecraft microphone
   */
  def processAptimaAsr(msg: AptimaAsrMessage): Unit = 
    publish(toDialogAgentMessage(msg, "message_bus", DialogAgentMqttSettings.topicInputAptimaAsr))


  /** Publish analysis of messages received on subscription topics 
   *  @param topic:  The message bus topic where the input appeared 
   *  @param json:  A json representation of a case class data struct
   */
  override def messageArrived(topic: String, json: String): Unit = {
    topic match {
      case DialogAgentMqttSettings.topicInputChat => 
        toChatMessage(json).map(a => processChat(a))
      case DialogAgentMqttSettings.topicInputUazAsr => 
        toUazAsrMessage(json).map(a => processUazAsr(a))
      case DialogAgentMqttSettings.topicInputAptimaAsr => 
        toAptimaAsrMessage(json).map(a => processAptimaAsr(a))
      case _ =>
    }
  }
}
