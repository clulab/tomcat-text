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
  port: String = "1883") extends MqttCallback {

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
  val uri = "tcp://%s:%s".format(host,port)

  /** MQTT quality of service */
  val qos = 2

  /** Identify DialogAgentMessage structure input source */
  val input_source = "message_bus"

  /** Instantiate the text analysis pipeline */
  Info("Creating text processor (this may take a few seconds) ...")
  val tp = new TextProcessor

  /** Complete the pipeline initialization by giving it a task */
  Info("Initializing text processor (this may take a few seconds) ...")
  val foo = tp.process(
    "TEST", 
    "saving green victim",
    "TEST",
    "TEST",
    "TEST") // for now

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

  // should have a test where we send a string to the TextProcessor
  // and test that we get back what we expect.
  //
  // There should be a test for each message type.   Perhaps this 
  // could be done by an external class.

  // Test the MQTT broker connection before proceeding.
  ((!subscriber.isEmpty && subscriber.head.isConnected) &&
   (!publisher.isEmpty && publisher.head.isConnected)) match {
    case true => {
      Info("Connected to MQTT broker at %s".format(uri))
      Info("Ready.") // Go
    }
    case false => {
      Error("Could not connect to MQTT broker at %s".format(uri))
      System.exit(1)  // It is impossible to run without the broker
    }
  }

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
        publish(tp.process(
          topic,
          a.msg.experiment_id, 
          a.msg.participant_id,
          a.data.text,
          input_source)
        )
      } 
      case TOPIC_INPUT_OBS =>  {
        val a: ObsMessage = read[ObsMessage](input)
        publish(tp.process(
          topic,
          a.msg.experiment_id,
          a.data.sender,
          a.data.text,
          input_source)
        )
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
