/**
 * Authors:  Joseph Astier, Adarsh Pyarelal
 *
 * Updated:  2021 January
 */
package org.clulab.asist


import org.slf4j.LoggerFactory

/** Message bus connectivity for dialog agents */
class DialogAgentMqtt(
    val host: String = "localhost",
    val port: String = "1883",
    val topicInputObs: String = "observations/chat",
    val topicInputAsr: String = "agent/asr",
    val topicOutput: String = "agent/tomcat_chatbot"
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

