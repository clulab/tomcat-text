/**
 * Authors:  Joseph Astier, Adarsh Pyarelal
 *
 * Updated:  2021 February
 *
 * This class reads input from the message bus on subscribed topics,
 * performs analysis on the input, and then publishes it on another
 * topic.
 *
 * Input and output are in json format.
 *
 */
package org.clulab.asist

import org.slf4j.LoggerFactory

/** Single point of truth for DialogAgent topics on the message bus */
object DialogAgentMqttDefaults {

  /** subscribe to these message bus topics for input */
  val TOPIC_INPUT_CHAT: String = "minecraft/chat"
  val TOPIC_INPUT_UAZ_ASR: String = "agent/asr"
  val TOPIC_INPUT_ADAPT_ASR: String = "status/asistdataingester/userspeech"

  /** publish input analysis to this message bus topic */
  val TOPIC_OUTPUT: String = "agent/dialog"
}

/** Message bus connectivity for dialog agents */
class DialogAgentMqtt(
    override val host: String = MqttAgentDefaults.HOST,
    override val port: String = MqttAgentDefaults.PORT,
    val topicInputChat: String = DialogAgentMqttDefaults.TOPIC_INPUT_CHAT,
    val topicInputUazAsr: String = DialogAgentMqttDefaults.TOPIC_INPUT_UAZ_ASR,
    val topicInputAdaptAsr: String = DialogAgentMqttDefaults.TOPIC_INPUT_UAZ_ASR,
    val topicOutput: String = DialogAgentMqttDefaults.TOPIC_OUTPUT
) extends MqttAgent(
      host,
      port,
      id = "dialog_agent",
      inputTopics = List(topicInputUazAsr, topicInputChat),
      outputTopics = List(topicOutput)
    )
    with DialogAgent
    with DialogAgentJson {

  private lazy val logger = LoggerFactory.getLogger(this.getClass())

  // Make sure we're connected to the broker.  Can't run without it.
  if (mqttConnected) logger.info("Ready.") else System.exit(1)

  /** Publish a DialogAgentMessage as a Json serialization
   *  @param a:  A compete DialogAgent output message
   */
  def publish(a: DialogAgentMessage): Unit = {
    val output = toJson(a)
    publish(output)
    logger.info("published on '%s': %s".format(
      DialogAgentMqttDefaults.TOPIC_OUTPUT,
      output)
    )
  }


  /** Convert a json-serialized ChatMessage to a DialogAgent message
   *  and publish to the message bus.
   *  @param msg:  input text from the Minecraft chat textfield
   */
  def processChat(msg: ChatMessage): Unit = 
    publish(toDialogAgentMessage(msg, "message_bus", topicInputChat))


  /** Convert a json-serialized UazAsrMessage to a DialogAgent message
   *  and publish to the message bus if the 'is_final' flag is set.
   *  @param msg: Input from the Minecraft microphone
   */
  def processUazAsr(msg: UazAsrMessage): Unit = if(msg.data.is_final)
    publish(toDialogAgentMessage(msg, "message_bus", topicInputUazAsr))


  /** Convert a json-serialized AdaptAsrMessage to a DialogAgent message
   *  and publish to the message bus if the 'is_final' flag is set.
   *  @param msg: Input from the Minecraft microphone
   */
  def processAdaptAsr(msg: AdaptAsrMessage): Unit = 
    publish(toDialogAgentMessage(msg, "message_bus", topicInputAdaptAsr))


  /** Publish analysis of messages received on subscription topics 
   *  @param topic:  The message bus topic where the input appeared 
   *  @param json:  A json representation of a case class data struct
   */
  override def messageArrived(topic: String, json: String): Unit = {
    logger.info("Received on '%s': %s".format(topic, json))
    topic match {
      case `topicInputChat` => toChatMessage(json).map(a => processChat(a))
      case `topicInputUazAsr` => toUazAsrMessage(json).map(a => processUazAsr(a))
      case `topicInputAdaptAsr` => toAdaptAsrMessage(json).map(a => processAdaptAsr(a))
      case _ =>
    }
  }
}
