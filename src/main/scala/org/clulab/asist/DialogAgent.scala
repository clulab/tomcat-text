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
import scala.util.control.Exception._
import sys.process._


/** Report a problem to stderr */
object Error {
  def apply(str: String): Unit = 
    System.err.println("DialogAgent: %s".format(str))
  
  def apply(t: Throwable): Unit = t match {
    case e: Exception => 
      System.err.println("DialogAgent:  %s".format(e.toString))
    case _: Throwable => 
      System.err.println("DialogAgent:  %s".format(t.toString))
  }
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

  /** Used so Json serializer can recognize case classes */
  implicit val formats = Serialization.formats(NoTypeHints)

  /** watch these MQTT topics for incoming messages */
  val TOPIC_INPUT_OBS = "observations/chat"
  val TOPIC_INPUT_ASR = "agent/asr"

  /** publish message analysis to this MQTT topic */
  val TOPIC_OUTPUT = "agent/tomcat_chatbot"

  /** MQTT broker connection identities */
  val SUB_ID = "dialog_agent_subscriber"
  val PUB_ID = "dialog_agent_publisher"

  /** MQTT broker connection address */
  val uri = "tcp://%s:%d".format(host,port)

  /** MQTT quality of service */
  val qos = 2

  /** Publisher sends our message analysis to the output topic */
  val publisher: Option[MqttClient] = allCatch.opt{
    val pub = new MqttClient(uri, PUB_ID, new MemoryPersistence())
    pub.connect(new MqttConnectOptions)
    pub
  }

  /** Subscriber receives messages on n topics */
  val subscriber: Option[MqttAsyncClient] = allCatch.opt {
    val sub = new MqttAsyncClient(uri, SUB_ID, new MemoryPersistence())
    sub.setCallback(this)
    sub.connect(new MqttConnectOptions).waitForCompletion
    sub.subscribe(TOPIC_INPUT_ASR,qos)
    sub.subscribe(TOPIC_INPUT_OBS,qos)
    sub
  }

  // Make sure we found the broker before initializing the pipeline.
  ((!subscriber.isEmpty && subscriber.head.isConnected) &&
   (!publisher.isEmpty && publisher.head.isConnected)) match {
    case true => Info("Connected to MQTT broker at %s".format(uri))
    case false => {
      Error("Could not connect to MQTT broker at %s".format(uri))
      System.exit(0) // fail fast if no broker
    }
  }

  /** Set up the language analysis pipeline */
  Info("Initializing language processor (this may take a few seconds) ...")
  val lp = new LanguageProcessor 

  // We are open for business.
  Info("Ready.")

  /** Publish a DialogAgentMessage as a Json serialization */
  def publish(output: DialogAgentMessage): Unit = publish(write(output))

  /** Publish a string */
  def publish(output: String): Unit = publish(output.getBytes)

  /** Publish a byte array */
  def publish(output: Array[Byte]): Unit = publish(new MqttMessage(output))

  /** Publish a MQTT message */
  def publish(output: MqttMessage): Unit = try { 
    publisher.map(pub=>pub.publish(TOPIC_OUTPUT, output))
    Info("Published to %s: %s".format(TOPIC_OUTPUT, output.toString))
  } catch {
    case t: Throwable => { 
      Error("Could not publish to %s".format(TOPIC_OUTPUT))
      Error(t)
    }
  }

  /** Report an issue that resulted in loss of contact with the MQTT broker.*/
  override def connectionLost(t: Throwable): Unit = {
    Error("Connection to MQTT broker lost.")
    Error(t)
  }

  /** Needed for MqttCallback extension but otherwise not used. */
  override def deliveryComplete(token: IMqttDeliveryToken): Unit =
    Info("deliveryComplete: %s" + token.getMessage)

  /** Publish analysis of messages received on subscription topics */
  override def messageArrived(topic: String, msg: MqttMessage): Unit = try {
    val input = msg.toString
    Info("Read from %s: %s".format(topic, input))
    topic match {
      case TOPIC_INPUT_ASR => {
        val a: AsrMessage = read[AsrMessage](input)
        publish(lp.process(a.msg.experiment_id, a.data.text))
      } 
      case TOPIC_INPUT_OBS =>  {
        val a: ObsMessage = read[ObsMessage](input)
        publish(lp.process(a.msg.experiment_id, a.data.text))
      } 
      case _ =>
    }
  } catch {
    case t: Throwable => {
      Error("Problem reading message on %s".format(topic))
      Error(t)
    }
  }
}
