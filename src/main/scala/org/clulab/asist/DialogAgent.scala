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
  def log(msg: String): Unit = if(verbose) {
    print("%s: %s\n".format(id, msg))
  } 

  // relay a message 
  def relay(msg: MqttMessage): Unit = {
    val text = msg.toString
    log("Relaying '%s' from [%s] to [%s]".format(text, relaySrc, relayDst))

    val (extractions, extracted_doc) = extractor.runExtraction(text, "")

    log("extractions:")
    extractions.foreach(e => log("  %s".format(e.toString)))

    log("extracted_doc = %s".format(extracted_doc))
     
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
      log("[%s] published '%s'".format(topic, msg.toString))
      true
    } else {
      log ("[%s] can't publish anything, not conected".format(topic))
      false
    }
  } catch {
    case e: Exception => {
      log("[%s] caught exception during publish: %s".format(topic, e.toString))
      false
    }
  }

  // Subscribe to any topic.  This could also be a list of topics.
  def subscribe(topic: String): Boolean = try {
    if (subscriber.isConnected) {
      subscriber.subscribe(topic, qos)
      log("Subscribed to [%s]".format(topic))
      true
    } else {
      log("Can't subscribe to [%s], not connected".format(topic))
      false
    }
  } catch {
    case e: Exception => {
      log("Exception during subscribe to [%s]: '%s'".format(topic, e.toString))
      false
    }
  }

  // Connect subscriber to the broker URI
  def connectSubscriber(): Boolean = {
    subscriber.connect(new MqttConnectOptions).waitForCompletion
    if (subscriber.isConnected) {
      log("subscriber is connected to %s".format(uri))
      true
    } else {
      log("subscriber could not connect to %s".format(uri))
      false
    }
  }

  // Connect publisher to the broker URI
  def connectPublisher(): Boolean = {
    publisher.connect(new MqttConnectOptions)
    if (publisher.isConnected) {
      log("publisher is connected to %s".format(uri))
      true
    } else {
      log("publisher could not connect to %s".format(uri))
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
    log("[%s] will be relayed to [%s]".format(relaySrc, relayDst))
    true
  } else false

  // Deactivate relayer
  // def stop(): Boolean = ...

  // Needed for MqttCallback extension
  override def connectionLost(cause: Throwable): Unit =
    log("Connection lost: %s".format(cause.toString))

  // Needed for MqttCallback extension
  override def deliveryComplete(token: IMqttDeliveryToken): Unit =
    log("deliveryComplete: " + token.getMessage)

  // Report any activity on our topic subscriptions.  Relay anything
  // that arrives on the relay source topic.
  override def messageArrived(topic: String, msg: MqttMessage): Unit = {
    log("[%s] read '%s'".format(topic, msg.toString))
    if (topic == relaySrc) relay(msg)
  }
}
