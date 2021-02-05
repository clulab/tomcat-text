/**
 * Authors:  Joseph Astier, Adarsh Pyarelal
 *
 * Updated:  2021 January
 */
package org.clulab.asist

import org.slf4j.LoggerFactory

/** Single point of truth for DialogAgent topics on the message bus */
object DialogAgentMqttDefaults {

  /** subscribe to these MQTT topics for messages */
  val TOPIC_INPUT_OBS: String = "observations/chat"
  val TOPIC_INPUT_ASR: String = "agent/asr"

  /** publish message analysis to this MQTT topic */
  val TOPIC_OUTPUT: String = "agent/tomcat_chatbot"
}

/** Message bus connectivity for dialog agents */
class DialogAgentMqtt(
    override val host: String = MqttAgentDefaults.HOST,
    override val port: String = MqttAgentDefaults.PORT,
    val topicInputObs: String = DialogAgentMqttDefaults.TOPIC_INPUT_OBS,
    val topicInputAsr: String = DialogAgentMqttDefaults.TOPIC_INPUT_ASR,
    val topicOutput: String = DialogAgentMqttDefaults.TOPIC_OUTPUT
) extends MqttAgent(
      host,
      port,
      id = "dialog_agent",
      inputTopics = List(topicInputAsr, topicInputObs),
      outputTopics = List(topicOutput)
    )
    with DialogAgent
    with DialogAgentJson {

  private val logger = LoggerFactory.getLogger(this.getClass())

  // Kickstart the extractor with a task to get lazy init out of the way
  logger.info("Initializing Extractor (this may take a few seconds) ...")
  extractor.runExtraction("green victim", "")
  logger.info("Extractor initialized.")

  // Make sure we're connected to the broker.  Can't run without it.
  if (mqttConnected) logger.info("Ready.") else System.exit(1)

  /** Publish a DialogAgentMessage as a Json serialization */
  def publish(a: DialogAgentMessage): Unit = publish(toJson(a))

  /** Translate an AsrMessage to a DialogAgentMessage */
  def toDialogAgentMessage(a: AsrMessage, topic: String): DialogAgentMessage =
    toDialogAgentMessage(a, topic, "message_bus")

  /** Translate an ObsMessage to a DialogAgentMessage */
  def toDialogAgentMessage(a: ObsMessage, topic: String): DialogAgentMessage =
    toDialogAgentMessage(a, topic, "message_bus")

  /** Publish analysis of messages received on subscription topics */
  override def messageArrived(topic: String, input: String): Unit = {
    logger.info(input)
    topic match {
      case `topicInputObs` =>
        toObsMessage(input).map(a => publish(toDialogAgentMessage(a, topic)))
      case `topicInputAsr` =>
        toAsrMessage(input).map(a => publish(toDialogAgentMessage(a, topic)))
      case _ =>
    }
  }
}

