//  DialogAgent
//
//  Author:  Joseph Astier, Adarsh Pyarelal
//  Date:  2020 November
//
//  Based on the Eclipse Paho MQTT API: www.eclipse.org/paho/files/javadoc
//
package org.clulab.asist

import org.eclipse.paho.client.mqttv3._
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence

import org.json4s._


// this object allows a test of the DialogAgent
object DialogAgentTest extends App {

  val host = "127.0.0.1"
  val port = 1883
  val agent = new DialogAgent(host, port)

  // Use hard-coded values already in agent for this test
  val relay_source_topic = agent.relaySrc
  val relay_destination_topic = agent.relayDst

  // Send a message on the relay source topic, and see if it is published
  // on the relay destination topic.
  if (agent.start()) {
    agent.publish(relay_source_topic, "Relay message")
  } else
    println("Could not start Dialog Relayer.")
}


//  Asynchronous event-driven MQTT agent. Messages read on one
//  bus topic are published on another
class DialogAgent(val host: String, val port: Int, val extractor: Option[Extractor])
    extends MqttCallback {
  val id = "DialogAgent"
  val subId = "Subscriber"
  val pubId = "Publisher"
  val uri = "tcp://%s:%d".format(host, port)
  val relaySrc = "observations/chat" // relay messages from here
  val relayDst = "agent/tomcat_chatbot" // relay messages to here
  val qos = 2
  val verbose = true // set true for debug printf output
  val subscriber = new MqttAsyncClient(uri, subId, new MemoryPersistence)
  val publisher = new MqttClient(uri, pubId, new MemoryPersistence)

  subscriber.setCallback(this)

  // output status updates to stdout if the verbose flag is true
  def report(msg: String): Unit = if(verbose) {
    print("%s: %s".format(id, msg))
  } 

  // relay same text as in MQTT message
  def this(host: String, port: Int) {
    this(host, port, None)
  }

  // relay JSON converted MQTT message text
  def this(host: String, port: Int, extractor: Extractor) {
    this(host, port, Some(extractor))
  }

  // relay a message 
  def relay(msg: MqttMessage): Unit = {
    val text = msg.toString
    extractor.foreach(e => {
      val (extractions, extracted_doc) = e.runExtraction(text, "")
      // ...
    })
    report("Relaying '%s' from '%s' to '%s'\n".format(text, relaySrc, relayDst))
    publish(relayDst, msg)
  }

  // Publish a string
  def publish(topic: String, text: String): Boolean =
    publish(topic, text.getBytes)

  // Publish any byte array
  def publish(topic: String, payload: Array[Byte]): Boolean =
    publish(topic, new MqttMessage(payload))

  // Publish message
  def publish(topic: String, msg: MqttMessage): Boolean = try {
    if (publisher.isConnected) {
      publisher.publish(topic, msg)
      report("Published '%s' to '%s'\n".format(msg.toString, topic))
      true
    } else {
      report(
        "Can't publish '%s' to '%s'. Not conected\n".format(msg.toString, topic)
      )
      false
    }
  } catch {
    case e: Exception => {
      report(
        "Can't publish '%s' to '%s'. %s\n".format(
          msg.toString,
          topic,
          e.toString
        )
      )
      false
    }
  }

  // Subscribe to any topic.  This could also be a list of topics.
  def subscribe(topic: String): Boolean = try {
    if (subscriber.isConnected) {
      subscriber.subscribe(topic, qos)
      report("Subscribed to '%s'\n".format(topic))
      true
    } else {
      report("Can't subscribe to '%s', not connected\n".format(topic))
      false
    }
  } catch {
    case e: Exception => {
      report("Exception during subscribe to " + topic + ", " + e)
      false
    }
  }

  // Connect subscriber to the broker URI
  def connectSubscriber(): Boolean = {
    subscriber.connect(new MqttConnectOptions).waitForCompletion
    if (subscriber.isConnected) {
      report("subscriber is connected to %s\n".format(uri))
      true
    } else {
      report("subscriber could not connect to %s\n".format(uri))
      false
    }
  }

  // Connect publisher to the broker URI
  def connectPublisher(): Boolean = {
    publisher.connect(new MqttConnectOptions)
    if (publisher.isConnected) {
      report("publisher is connected to %s\n".format(uri))
      true
    } else {
      report("publisher could not connect to %s\n".format(uri))
      false
    }
  }

  // Activate relayer
  def start(): Boolean = if (
    connectPublisher
    && connectSubscriber
    && subscribe(relayDst)
    && subscribe(relaySrc)
  ) {
    report(
      "Topic '%s' will be relayed to topic '%s'\n".format(relaySrc, relayDst)
    )
    true
  } else false

  // Deactivate relayer
  // def stop(): Boolean = ...

  // Needed for MqttCallback extension
  override def connectionLost(cause: Throwable): Unit =
    report("Connection lost: %s\n".format(cause.toString))

  // Needed for MqttCallback extension
  override def deliveryComplete(token: IMqttDeliveryToken): Unit =
    report("deliveryComplete: " + token.getMessage)

  // Report any activity on our topic subscriptions.  Relay anything
  // that arrives on the relay source topic.
  override def messageArrived(topic: String, msg: MqttMessage): Unit = {
    report("Read '%s' on '%s'\n".format(msg.toString, topic))
    if (topic == relaySrc) relay(msg)
  }
}
