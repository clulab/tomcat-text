//  DialogAgent
//
//  Author:  Joseph Astier, Adarsh Pyarelal
//  Date:  2020 November
//
//  Based on the Eclipse Paho MQTT API: www.eclipse.org/paho/files/javadoc
//
package org.clulab.asist

import java.net.ConnectException
import org.eclipse.paho.client.mqttv3._
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence
import org.json4s._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write}
import sys.process._

/** Report an error message to stderr */
object Error {
  def apply(str: String): Unit = 
    System.err.println("DialogAgent: %s".format(str))
}

/** Report a message to stdout */
object Info {
  def apply(str: String): Unit =
    System.out.println("DialogAgent: %s".format(str))
}


/** coordinator class for all things chatbot */
class DialogAgent(
  host: String = "localhost", 
  port: Int = 1883) extends MqttCallback {

  /** watch these mqtt topics for incoming messages */
  val TOPIC_OBS = "observations/chat"
  val TOPIC_ASR = "agent/asr"

  /** publish message analysis on this mqtt topic */
  val TOPIC_OUTPUT = "agent/tomcat_chatbot"

  /** MQTT broker connection identities */
  val SUB_ID = "dialog_agent_subscriber"
  val PUB_ID = "dialog_agent_publisher"

  /** set up the analysis pipeline for chat dialog */
  Info("Initializing language processor (this may take a few seconds) ...")
  val lp = new LanguageProcessor

  /** Used so Json serializer can recognize case classes */
  implicit val formats = Serialization.formats(NoTypeHints)

  /** MQTT broker connection port */
  val uri = "tcp://%s:%d".format(host,port)

  /** MQTT quality of service */
  val qos = 2

  /** Describe any throwables we catch. */
  private def toString(t: Throwable): String = t match {
    case e: Exception => "  Exception: %s".format(e.toString)
    case _: Throwable => "  Throwable: %s".format(t.toString)
  }

  /** All message analysis goes out on this topic */
  val publisher: Option[MqttClient] = try {
    val pub = new MqttClient(uri, PUB_ID, new MemoryPersistence())
    pub.connect(new MqttConnectOptions)
    pub.isConnected match {
      case true => {
        Info("%s connected to MQTT broker at %s".format(PUB_ID, uri))
        Some(pub)
      }
      case false => {
        Error("%s could not connect to MQTT broker at %s".format(PUB_ID, uri))
        None
      }
    }
  } catch {
    case t: Throwable => {
      Error("%s could not connect to MQTT broker at %s".format(PUB_ID, uri))
      Error(toString(t))
      None
    }
  }

  /** Connect subscriber to the broker URI on the relay destination topic */
  val subscriber: Option[MqttAsyncClient] = try {
    val sub = new MqttAsyncClient(uri, SUB_ID, new MemoryPersistence())
    sub.setCallback(this)
    sub.connect(new MqttConnectOptions).waitForCompletion
    sub.subscribe(TOPIC_ASR,qos)
    sub.subscribe(TOPIC_OBS,qos)
    Info("%s connected to MQTT broker at %s".format(SUB_ID, uri))
    Some(sub)
  } catch {
    case t: Throwable => {
      Error("%s could not connect to MQTT broker at %s".format(SUB_ID, uri))
      Error(toString(t))
      None
    }
  }

  // If we have a publisher and a subscriber we are open for business.
  (!subscriber.isEmpty && !publisher.isEmpty) match {
    case true => Info("Ready.")
    case false => Error("Not ready.")
  }

  /** Publish a list DialogAgentMessage as Json serializations */
  def publish(output: List[DialogAgentMessage]): Unit = output.foreach(publish)

  /** Publish a DialogAgentMessage as a Json serialization */
  def publish(output: DialogAgentMessage): Unit = publish(write(output))

  /** Publish a string to the chatbot topic.  */
  def publish(text: String): Unit = publish(text.getBytes)

  /** Publish a byte array to the chatbot topic. */
  def publish(payload: Array[Byte]): Unit = publish(new MqttMessage(payload))

  /** Publish a MQTT message to the chatbot topic. */
  def publish(msg: MqttMessage): Unit = try {
    publisher.foreach(pub => {
      pub.publish(TOPIC_OUTPUT, msg)
      Info("Published to %s: %s".format(TOPIC_OUTPUT, msg.toString))
    })
  } catch {
    case t: Throwable => { 
      Error("Could not publish to %s".format(TOPIC_OUTPUT))
      Error(toString(t))
    }
  }

  /** Report an issue that resulted in loss of contact with the MQTT broker.*/
  override def connectionLost(t: Throwable): Unit = {
    Error("Connection to MQTT broker lost.")
    Error(toString(t))
  }

  /** Needed for MqttCallback extension but otherwise not used. */
  override def deliveryComplete(token: IMqttDeliveryToken): Unit =
    Info("deliveryComplete: %s" + token.getMessage)

  /** Publish analysis of messages received on subscription topics */
  override def messageArrived(topic: String, msg: MqttMessage): Unit = try {
    val input = msg.toString
    Info("Read from %s: %s".format(topic, input))
    topic match {
      case TOPIC_ASR => {
        val a: AsrMessage = read[AsrMessage](input)
        publish(lp.processExtractions(a.msg.experiment_id, a.data.text))
      } 
      case TOPIC_OBS =>  {
        val a: ObsMessage = read[ObsMessage](input)
        publish(lp.processExtractions(a.msg.experiment_id, a.data.text))
      } 
      case _ =>
    }
  } catch {
    case t: Throwable => {
      Error("Did not understand message on %s".format(topic))
      Error(toString(t))
    }
  }
}
