//  DialogAgent
//
//  Author:  Joseph Astier, Adarsh Pyarelal
//  Date:  2020 November
//
//  Based on the Eclipse Paho MQTT API: www.eclipse.org/paho/files/javadoc
//
package org.clulab.asist

import sys.process._
import java.net.ConnectException
import org.eclipse.paho.client.mqttv3._
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence

object Error {
  def apply(str: String): Unit = 
    System.err.println("DialogAgent: %s".format(str))
}

object Info {
  def apply(str: String): Unit =
    System.out.println("DialogAgent: %s".format(str))
}


/** Generic message topic handler */
abstract class Subscriber {
  val topic: String
  def language(json: String): List[String]
}

/** Handler for chat messages */
class SubscriberObs extends Subscriber {
  val topic = "observations/chat"
  def language(json: String): List[String] = 
    ObsMessageJson(json).toList.map(om => om.data.text)
}

/** Handler for ASR messages */
class SubscriberAsr extends Subscriber {
  val topic = "agent/asr"
  def language(json: String): List[String] = 
    AsrMessageJson(json).toList.map(am => am.data.text)
}


/** coordinator class for all things chatbot */
class DialogAgent(
  host: String = "localhost", 
  port: Int = 1883) extends MqttCallback {


  // set up the analysis pipeline for chat dialog
  Info("Initializing language processor ...")
  val lp = new LanguageProcessor


  // create listeners for any subscribed topics
  val subs = List(
    new SubscriberObs,
    new SubscriberAsr
  )

  // connect to the MQTT broker 
  Info("Connecting to port %d on %s ...".format(port, host))
  private val uri = "tcp://%s:%d".format(host,port)

  /** Write chat message analysis here */
  val outputTopic = "agent/tomcat_chatbot" 

  /** MQTT quality of service */
  val qos = 2
  private val subscriber = connectSubscriber("DialogAgentSubscriber")
  private val publisher = connectPublisher("DialogAgentPublisher")

  // Report the status of this agent.  
  connected match {
    case true => Info("Ready.")
    case false => Error("Could not connect to %d on %s".format(port,host))
  }

  // Describe any throwables we catch.
  private def toString(t: Throwable): String = t match {
    case e: Exception => "  Exception: %s".format(e.toString)
    case _: Throwable => "  Throwable: %s".format(t.toString)
  }

  /** Return true if the subscriber and publisher are connected to the
   *  MQTT broker.
   */
  def connected: Boolean = !subscriber.isEmpty && !publisher.isEmpty

  // Connect publisher to the broker URI
  private def connectPublisher(name: String): Option[MqttClient] = try {
    val pub = new MqttClient(uri, name, new MemoryPersistence())
    pub.connect(new MqttConnectOptions)
    pub.isConnected match {
      case true => {
        Info("Publisher is connected to MQTT broker at %s".format(uri))
        Some(pub)
      }
      case false => {
        Error("Publisher could not connect to MQTT broker at %s".format(uri))
        None
      }
    }
  } catch {
    case t: Throwable => {
      Error("Publisher could not connect to MQTT broker at %s".format(uri))
      Error(toString(t))
      None
    }
  }

  // Connect subscriber to the broker URI on the relay destination topic
  private def connectSubscriber(name: String): Option[MqttAsyncClient] = try {
    val sub = new MqttAsyncClient(uri, name, new MemoryPersistence())
    sub.setCallback(this)
    sub.connect(new MqttConnectOptions).waitForCompletion
    subs.foreach(s=>sub.subscribe(s.topic,qos))
    Info("subscriber is connected to MQTT broker at %s".format(uri))
    Some(sub)
  } catch {
    case t: Throwable => {
      Error("subscriber could not connect to MQTT broker at %s".format(uri))
      Error(toString(t))
      None
    }
  }

  def relay(sub: Subscriber, input: String): Unit = 
    sub.language(input).foreach(chat => publish(lp.languageAnalysis(chat)))

  /** Publish a list DialogAgentMessage as Json serializations */
  def publish(output: List[DialogAgentMessage]): Unit = output.map(publish(_))

  /** Publish a DialogAgentMessage as a Json serialization */
  def publish(output: DialogAgentMessage): Unit =
    publish(DialogAgentMessageJson(output))

  /** Publish a string to the chatbot topic.  */
  def publish(text: String): Unit = publish(text.getBytes)

  /** Publish a byte array to the chatbot topic. */
  def publish(payload: Array[Byte]): Unit =
    publish(new MqttMessage(payload))

  /** Publish a MQTT message to the chatbot topic. */
  def publish(msg: MqttMessage): Unit = try {
    publisher.foreach(pub => {
      pub.publish(outputTopic, msg)
      Info("Published to %s: %s".format(outputTopic, msg.toString))
    })
  } catch {
    case t: Throwable => { 
      Error("Could not publish to %s".format(outputTopic))
      Error(toString(t))
    }
  }

  /** Report an issue that resulted in loss of contact with the MQTT broker.
   */
  override def connectionLost(t: Throwable): Unit = {
    Error("Connection to MQTT broker lost.")
    Error(toString(t))
  }

  /** Implementation of this method is needed for MqttCallback extension but 
   *  is otherwise not used.
   */
  override def deliveryComplete(token: IMqttDeliveryToken): Unit =
    Info("deliveryComplete: %s" + token.getMessage)

  /** Messages received are processed and then published on the chatbot topic
   */
  override def messageArrived(topic: String, msg: MqttMessage): Unit = {
    val input = msg.toString
    Info("Read from %s: %s".format(topic, input))
    subs.filter(topic == _.topic).foreach(s=>relay(s,input))
  }
}

