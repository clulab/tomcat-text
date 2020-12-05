//  DialogAgent
//
//  Author:  Joseph Astier, Adarsh Pyarelal
//  Date:  2020 November
//
//  Based on the Eclipse Paho MQTT API: www.eclipse.org/paho/files/javadoc
//
package org.clulab.asist

import edu.stanford.nlp.pipeline.StanfordCoreNLP
import java.io.{File, PrintWriter}
import java.util.Properties
import org.clulab.odin.Mention
import org.eclipse.paho.client.mqttv3._
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence
import org.json4s._
import scala.collection.immutable
import scala.io.Source
import scala.util.parsing.json.JSON
import spray.json._
import spray.json.DefaultJsonProtocol._


//  Asynchronous event-driven MQTT agent. Messages read on one
//  bus topic are published on another
class DialogAgent(val host: String, val port: Int) extends MqttCallback {
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

  // Build an extractor for our tokens
  val pipeline = new StanfordCoreNLP(new Properties() {
    setProperty("annotators","tokenize, ssplit, pos, lemma, ner, parse, dcoref")
  })
  val json_tax_map = JsonParser(
    Source.fromResource("taxonomy_map.json").mkString
  ).convertTo[immutable.Map[String, Array[immutable.Map[String, String]]]]
  val extractor = new Extractor(pipeline, new AsistEngine(), json_tax_map)

  subscriber.setCallback(this)

  // output status updates to stdout if the verbose flag is true
  def report(msg: String): Unit = if(verbose) {
    print("%s: %s".format(id, msg))
  } 

  // relay a message 
  def relay(msg: MqttMessage): Unit = {
    val text = msg.toString
    report("Relaying '%s' from [%s] to [%s]\n".format(text, relaySrc, relayDst))

    val (extractions, extracted_doc) = extractor.runExtraction(text, "")

    report("extractions:\n")
    extractions.foreach(e => report("  %s\n".format(e.toString)))

    report("extracted_doc = %s\n".format(extracted_doc))
     
    // ... 

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
      report("[%s] published '%s'\n".format(topic, msg.toString))
      true
    } else {
      report ("[%s] can't publish anything, not conected\n".format(topic))
      false
    }
  } catch {
    case e: Exception => {
      report("[%s] caught exception during publish: %s\n".format(topic, e.toString))
      false
    }
  }

  // Subscribe to any topic.  This could also be a list of topics.
  def subscribe(topic: String): Boolean = try {
    if (subscriber.isConnected) {
      subscriber.subscribe(topic, qos)
      report("Subscribed to [%s]\n".format(topic))
      true
    } else {
      report("Can't subscribe to [%s], not connected\n".format(topic))
      false
    }
  } catch {
    case e: Exception => {
      report("Exception during subscribe to [%s]: '%s'\n".format(topic, e.toString))
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
    report("[%s] will be relayed to [%s]\n".format(relaySrc, relayDst))
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
    report("[%s] read '%s'\n".format(topic, msg.toString))
    if (topic == relaySrc) relay(msg)
  }
}
