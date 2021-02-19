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
  val TOPIC_INPUT_OBS: String = "observations/chat"
  val TOPIC_INPUT_ASR: String = "agent/asr"

  /** publish input analysis to this message bus topic */
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

  // Kickstart the extractor with this task to get lazy init out of the way
  logger.info("Initializing Extractor (this may take a few seconds) ...")
  extractor.runExtraction("green victim", "")
  logger.info("Extractor initialized.")

  // Make sure we're connected to the broker.  Can't run without it.
  if (mqttConnected) logger.info("Ready.") else System.exit(1)

  /** Publish a DialogAgentMessage as a Json serialization */
  def publish(a: DialogAgentMessage): Unit = {
    val output = toJson(a)
    logger.info("published on '%s': %s".format(
      DialogAgentMqttDefaults.TOPIC_OUTPUT,
      output)
    )
    publish(output)
  }

  /** Publish analysis of messages received on subscription topics */
  override def messageArrived(topic: String, input: String): Unit = {
    logger.info("Received on '%s': %s".format(topic, input))
    topic match {
      case `topicInputObs` => toObsMessage(input).map(a => 
        publish(toDialogAgentMessage(a, topicInputObs, "message_bus")))
      case `topicInputAsr` => toAsrMessage(input).map(a => 
        publish(toDialogAgentMessage(a, topicInputAsr, "message_bus")))
      case _ =>
    }
  }
}
