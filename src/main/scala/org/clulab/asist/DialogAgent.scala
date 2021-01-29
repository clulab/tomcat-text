//  DialogAgent
//
//  Author:  Joseph Astier, Adarsh Pyarelal
//  Updated:  2021 January
//
package org.clulab.asist

import org.json4s._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write}
import org.json4s.Xml.{toJson, toXml}
import scala.util.control.Exception._

/** coordinator class for all things chatbot */
class DialogAgent(
  host: String = "localhost", 
  port: String = "1883") extends MqttAgent(
    host, 
    port,
    "dialog_agent",
    List("agent/tomcat_chatbot"),
    List("observations/chat", "agent/asr")) {

  /** Used so Json serializer can recognize case classes */
  implicit val formats = Serialization.formats(NoTypeHints)

  /** watch these MQTT topics for incoming messages */
  val TOPIC_INPUT_OBS = "observations/chat"
  val TOPIC_INPUT_ASR = "agent/asr"

  /** publish message analysis to this MQTT topic */
  val TOPIC_OUTPUT = "agent/tomcat_chatbot"


  /** Create the text analysis pipeline */
  info("Creating text processor (this may take a few seconds) ...")
  val tp = new TextProcessor

  override def info(str: String): Unit = Info("DialogAgent", str)
  override def error(str: String): Unit = Error("DialogAgent", str)

  // Test the MQTT broker connection before proceeding.
  override def start: Unit = if(ready) {
    info("Connected to MQTT broker at %s".format(uri))
    info("Ready.") // Go
  } else {
    error("Could not connect to MQTT broker at %s".format(uri))
    System.exit(1)  // It is impossible to run without the broker
  }


  /** Publish a DialogAgentMessage as a Json serialization */
  def publish(a: DialogAgentMessage): Unit = publish(write(a))

  /** Translate an AsrMessage to a DialogAgentMessage  */
  def toDialogAgentMessage(
    a: AsrMessage, 
    topic: String): DialogAgentMessage = tp.process(
      topic,
      a.msg.experiment_id, 
      a.msg.participant_id,
      a.data.text,
      "message_bus"
    )

  /** Translate an ObsMessage to a DialogAgentMessage  */
  def toDialogAgentMessage(
    a: ObsMessage, 
    topic: String): DialogAgentMessage = tp.process(
      topic,
      a.msg.experiment_id,
      a.data.sender,
      a.data.text,
      "message_bus"
    )

  /** Publish analysis of messages received on subscription topics */
  override def messageArrived(topic: String, input: String): Unit = 
    topic match {
      case TOPIC_INPUT_ASR => {
        allCatch.opt {
          read[AsrMessage](input)
        }.map(a => publish(toDialogAgentMessage(a, topic)))
      } 
      case TOPIC_INPUT_OBS =>  {
        allCatch.opt {
          read[ObsMessage](input)
        }.map(a => publish(toDialogAgentMessage(a, topic)))
      } 
      case _ =>
    }
}
