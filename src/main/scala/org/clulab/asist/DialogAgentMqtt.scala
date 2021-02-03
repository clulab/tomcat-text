//  DialogAgent
//
//  Author:  Joseph Astier, Adarsh Pyarelal
//  Updated:  2021 January
//
package org.clulab.asist

import java.time.Clock
import org.json4s._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write}
import org.json4s.Xml.{toJson, toXml}
import org.slf4j.LoggerFactory
import scala.util.control.Exception._
import org.clulab.odin.{EventMention, Mention, TextBoundMention}


/** coordinator class for all things chatbot */
class DialogAgentMqtt(
  val host: String = "localhost",  /** MQTT broker machine */
  val port: String = "1883", /** MQTT broker port on broker machine */
  val topicSubObs: String = "observations/chat",
  val topicSubAsr: String = "agent/asr",
  val topicPub: String = "agent/tomcat_chatbot"
  ) extends MqttAgent(
    host, 
    port,
    id = "dialog_agent",
    subTopics = List(topicSubAsr, topicSubObs),
    pubTopics = List(topicPub)
  ) with DialogAgent {

  private val logger = LoggerFactory.getLogger(this.getClass())

  // Kickstart the extractor with a task to get lazy init out of the way
  logger.info("Initializing Extractor (this may take a few seconds) ...")
  extractor.runExtraction("green victim","")
  logger.info("Extractor initialized.")

  // Test the MQTT broker connection before proceeding.
  if(ready) {
    logger.info("Connected to MQTT broker at %s".format(uri))
    logger.info("Ready.")
  } else {
    logger.error("Could not connect to MQTT broker at %s".format(uri))
    System.exit(1)  // It is impossible to run without the broker
  }


  /** Publish a DialogAgentMessage as a Json serialization */
  def publish(a: DialogAgentMessage): Unit = publish(write(a))

  /** Translate an AsrMessage to a DialogAgentMessage  */
  def toDialogAgentMessage(
    a: AsrMessage, 
    topic: String): DialogAgentMessage =
      toDialogAgentMessage(a, topic, "message_bus")

  /** Translate an ObsMessage to a DialogAgentMessage  */
  def toDialogAgentMessage(
    a: ObsMessage, 
    topic: String): DialogAgentMessage =
      toDialogAgentMessage(a, topic, "message_bus")


  /** Publish analysis of messages received on subscription topics */
  override def messageArrived(topic: String, input: String): Unit =  {
    logger.info(input)
    topic match {
      case `topicSubObs` => allCatch.opt {
        read[ObsMessage](input)
      }.map(a => publish(toDialogAgentMessage(a, topic)))
      case `topicSubAsr` => allCatch.opt {
        read[AsrMessage](input)
      }.map(a => publish(toDialogAgentMessage(a, topic)))
      case _ =>
    }
  }
}
