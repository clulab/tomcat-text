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

// Use this for easily controlled printf debugging
object Report {
  val verbose = true  // unset to silence debug output
  val id = "DialogAgent"
  def apply(text: String): Unit = 
    if(verbose) println("%s: %s".format(id,text))
}

//  Asynchronous event-driven MQTT agent. Messages read are translated
//  to Json and published on a different topic
class DialogAgent(host: String = "localhost", port: Int = 1883)
    extends MqttCallback {

  // set up the analysis pipeline for chat dialog
  Report("Initializing chat analysis pipeline ...")
  val chatAnalysis = new ChatAnalysis

  // connect to the MQTT broker 
  Report("Connecting to port %d on %s ...".format(port, host))
  val uri = "tcp://%s:%d".format(host,port)
  val subTopic = "observations/chat" // Read messages here
  val pubTopic = "agent/tomcat_chatbot" // write converted messages here
  val qos = 2
  val subscriber = connectSubscriber("DialogAgentSubscriber")
  val publisher = connectPublisher("DialogAgentPublisher")

  // Report the status of this agent.  
  if(connected) {
    Report("Topic %s will be relayed to %s".format(subTopic, pubTopic))
    Report("Ready.")
  } else Report("Could not connect to %d on %s".format(port,host))

  // Describe any throwables we catch.
  def toString(t: Throwable): String = t match {
    case e: Exception => "  Exception: %s".format(e.toString)
    case _: Throwable => "  Throwable: %s".format(t.toString)
  }

  // return true if there were no connection issues.
  def connected: Boolean = !subscriber.isEmpty && !publisher.isEmpty

  // Connect publisher to the broker URI
  def connectPublisher(name: String): Option[MqttClient] = try {
    val pub = new MqttClient(uri, name, new MemoryPersistence())
    pub.connect(new MqttConnectOptions)
    if (pub.isConnected) {
      Report("Publisher is connected to MQTT broker at %s".format(uri))
      Some(pub)
    } else {
      Report("Publisher could not connect to MQTT broker at %s".format(uri))
      None
    }
  } catch {
    case t: Throwable => {
      Report("Publisher could not connect to MQTT broker at %s".format(uri))
      Report(toString(t))
      None
    }
  }

  // Connect subscriber to the broker URI on the relay destination topic
  def connectSubscriber(name: String): Option[MqttAsyncClient] = try {
    val sub = new MqttAsyncClient(uri, name, new MemoryPersistence())
    sub.setCallback(this)
    sub.connect(new MqttConnectOptions).waitForCompletion
    sub.subscribe(subTopic, qos)
    Report("subscriber is connected to MQTT broker at %s".format(uri))
    Some(sub)
  } catch {
    case t: Throwable => {
      Report("subscriber could not connect to MQTT broker at %s".format(uri))
      Report(toString(t))
      None
    }
  }

  // Publish a string
  def publish(text: String): Unit = publish(text.getBytes)

  // Publish a byte array
  def publish(payload: Array[Byte]): Unit =
    publish(new MqttMessage(payload))

  // Publish a MQTT message
  def publish(msg: MqttMessage): Unit = try {
    publisher.foreach(pub => {
      pub.publish(pubTopic, msg)
      Report("Published '%s' to %s".format(msg.toString, pubTopic))
    })
  } catch {
    case t: Throwable => { 
      Report("Could not publish '%s' to %s".format(msg.toString,pubTopic))
      Report(toString(t))
    }
  }

  // Needed for MqttCallback extension
  override def connectionLost(t: Throwable): Unit = {
    Report("Connection to MQTT broker lost.")
    Report(toString(t))
  }

  // Needed for MqttCallback extension
  override def deliveryComplete(token: IMqttDeliveryToken): Unit =
    Report("deliveryComplete: %s" + token.getMessage)

  // When a message is received on the relay topic, convert it to Json and 
  // publish it to the chatbot topic.  Needed for MqttCallback extension
  override def messageArrived(topic: String, msg: MqttMessage): Unit = {
    val text = msg.toString
    Report("Read '%s' on %s".format(text, topic))
    if (topic == subTopic) 
      chatAnalysis.toJson(text).foreach(str => publish(str))
  }
}

