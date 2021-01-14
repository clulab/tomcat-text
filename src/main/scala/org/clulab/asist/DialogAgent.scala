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

// this object allows a test of the DialogAgent
object DialogAgentTest extends App {

  val host = "127.0.0.1"
  val port = 1883
  val agent = new DialogAgent(host, port)

  // Send a message on the relay source topic.   It should be relayed to
  // the relay destination topic.
  agent.publish(agent.relaySrc, "Relay this message")

  // Send a message on the relay destination topic.  It should not be relayed.
  agent.publish(agent.relayDst, "Do not relay this message")
}


//  Asynchronous event-driven MQTT agent. Messages read on one
//  bus topic are resent on another
class DialogAgent(host: String, port: Int) extends MqttCallback {
  val id = "DialogAgent"
  val uri = "tcp://%s:%d".format(host, port)
  val relaySrc = "observations/chat" // relay messages from here
  val relayDst = "agent/tomcat_chatbot" // relay messages to here
  val qos = 2
  val verbose = true // set true for debug printf output
  val subscriber = connectSubscriber("DialogAgentSubscriber")
  val publisher = connectPublisher("DialogAgentPublisher")

  // print status reports if the 'verbose' flag is set.
  private def report(msg: String): Unit = 
    if (verbose) println("%s: %s".format(id,msg))

  report("Topic %s will be relayed to %s".format(relaySrc, relayDst))

  // Describe any throwables we catch.
  def toString(t: Throwable): String = t match {
    case e: ConnectException => "ConnectException: %s".format(e.toString)
    case e: Exception => "Exception: %s".format(e.toString)
    case _: Throwable => "Throwable: %s".format(t.toString)
  }

  // Connect publisher to the broker URI
  def connectPublisher(name: String): Option[MqttClient] = try {
    val pub = new MqttClient(uri, name, new MemoryPersistence())
    pub.connect(new MqttConnectOptions)
    if (pub.isConnected) {
      report("publisher is connected to MQTT broker at %s".format(uri))
      Some(pub)
    } else {
      report("publisher could not connect to MQTT broker at %s".format(uri))
      None
    }
  } catch {
    case t: Throwable => {
      report("publisher could not connect to MQTT broker at %s".format(uri))
      report(toString(t))
      None
    }
  }

  // Connect subscriber to the broker URI on the relay destination topic
  def connectSubscriber(name: String): Option[MqttAsyncClient] = try {
    val sub = new MqttAsyncClient(uri, name, new MemoryPersistence())
    sub.setCallback(this)
    sub.connect(new MqttConnectOptions).waitForCompletion
    sub.subscribe(relaySrc, qos)
    sub.subscribe(relayDst, qos)
    report("subscriber is connected to MQTT broker at %s".format(uri))
    Some(sub)
  } catch {
    case t: Throwable => {
      report("subscriber could not connect to MQTT broker at %s".format(uri))
      report(toString(t))
      None
    }
  }

  // Publish a string
  def publish(topic: String, text: String): Unit =
    publish(topic, text.getBytes)

  // Publish any byte array
  def publish(topic: String, payload: Array[Byte]): Unit =
    publish(topic, new MqttMessage(payload))

  // Publish message
  def publish(topic: String, msg: MqttMessage): Unit = try {
    publisher.foreach(pub => {
      pub.publish(topic, msg)
      report("Published '%s' to %s".format(msg.toString, topic))
    })
  } catch {
    case t: Throwable => { 
      report("Could not publish '%s' to %s".format(msg.toString,topic))
      report(toString(t))
    }
  }

  // Needed for MqttCallback extension
  override def connectionLost(t: Throwable): Unit = {
    report("Connection to MQTT broker lost.")
    report(toString(t))
  }

  // Needed for MqttCallback extension
  override def deliveryComplete(token: IMqttDeliveryToken): Unit =
    report("deliveryComplete: %s" + token.getMessage)

  // When a message is received on the relay source topic, publish it to the
  // relay destination topic.
  override def messageArrived(topic: String, msg: MqttMessage): Unit = {
    report("Read '%s' on %s".format(msg.toString, topic))
    if (topic == relaySrc) {
      report("Relaying to %s".format(relayDst))
      publish(relayDst, msg)
    }
  }
}

