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
import scala.util.control.Exception._
import org.clulab.odin.{EventMention, Mention, TextBoundMention}


/** coordinator class for all things chatbot */
class DialogAgent(
  val host: String = "localhost", 
  val port: String = "1883",
  val topicSubObs: String = "observations/chat",
  val topicSubAsr: String = "agent/asr",
  val topicPub: String = "agent/tomcat_chatbot"
  ) extends MqttAgent(
    host, 
    port,
    id = "dialog_agent",
    subTopics = List(topicSubAsr, topicSubObs),
    pubTopics = List(topicPub)
  ) {

  /** Used so Json serializer can recognize case classes */
  implicit val formats = Serialization.formats(NoTypeHints)

  /** Create the text analysis pipeline */
  info("Creating text processor (this may take a few seconds) ...")
  val tp = new TextProcessor

  override def info(str: String): Unit = Info("DialogAgent", str)
  override def error(str: String): Unit = Error("DialogAgent", str)

  // Test the MQTT broker connection before proceeding.
  if(ready) {
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
    topic: String): DialogAgentMessage = {
      process(
      topic,
      a.msg.experiment_id, 
      a.msg.participant_id,
      a.data.text,
      "message_bus"
    )
  }

  /** Translate an ObsMessage to a DialogAgentMessage  */
  def toDialogAgentMessage(
    a: ObsMessage, 
    topic: String): DialogAgentMessage = {
      process(
      topic,
      a.msg.experiment_id,
      a.data.sender,
      a.data.text,
      "message_bus"
    )
  }

  /** Compose a DialogAgentMessage based on language extractions */
  def process(
      topic: String,
      experiment_id: String,
      participant_id: String,
      text: String,
      source_type: String
  ): DialogAgentMessage = {


    // TODO do not use tp.extractor var directly, use a function instead.
    val (extractions, extracted_doc) = tp.extractor.runExtraction(text, "")

    val timestamp = Clock.systemUTC.instant.toString

    DialogAgentMessage(
      MessageHeader(
        timestamp = timestamp,
        message_type = "event",
        version = DialogAgentMessage.version
      ),
      DialogAgentMessageMsg(
        source = "tomcat_textAnalyzer",
        experiment_id = experiment_id,
        timestamp = timestamp
      ),
      DialogAgentMessageData(
        participant_id = participant_id,
        text = text,
        DialogAgentMessageDataSource(
          source_type = source_type,
          topic = topic
        ),
        extractions.map(extraction).toList
      )
    )
  }

  /** Create a DialogAgent extraction from Extractor data */
  def extraction(e: Array[Any]): DialogAgentMessageDataExtraction = {
    val mention = e(0).asInstanceOf[Mention]
    val argument_labels =
      for (key <- mention.arguments.keys)
        yield mention.arguments.get(key).get(0).label
    val taxonomy_matches = tp.taxonomyMatches(mention.label)
    DialogAgentMessageDataExtraction(
      mention.label,
      mention.words.mkString(" "),
      argument_labels.mkString(" "),
      taxonomy_matches
    )
  }

  /** Publish analysis of messages received on subscription topics */
  override def messageArrived(topic: String, input: String): Unit =  {
    info(input)
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
