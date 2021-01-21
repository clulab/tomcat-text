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

/**  Asynchronous event-driven MQTT agent. 
 *   Messages read on topic "observations/chat" are analyzed and then
 *   published as Json on topic "agent/tomcat_chatbot"
 */
class DialogAgent(host: String = "localhost", port: Int = 1883)
    extends MqttCallback {

  // set up the analysis pipeline for chat dialog
  Report("Initializing chat analysis pipeline ...")
  private val chatAnalysis = new ChatAnalysis

  // connect to the MQTT broker 
  Report("Connecting to port %d on %s ...".format(port, host))
  private val uri = "tcp://%s:%d".format(host,port)

  /** Read chat messages here */
  val inputTopic = "agent/asr"

  /** Write chat message analysis here */
  val outputTopic = "agent/tomcat_chatbot" 

  /** MQTT quality of service */
  val qos = 2
  private val subscriber = connectSubscriber("DialogAgentSubscriber")
  private val publisher = connectPublisher("DialogAgentPublisher")

  // Report the status of this agent.  
  connected match {
    case true => {
      Report("Topic %s will be relayed to %s".format(inputTopic, outputTopic))
      Report("Ready.")
    }
    case false => Report("Could not connect to %d on %s".format(port,host))
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
        Report("Publisher is connected to MQTT broker at %s".format(uri))
        Some(pub)
      }
      case false => {
        Report("Publisher could not connect to MQTT broker at %s".format(uri))
        None
      }
    }
  } catch {
    case t: Throwable => {
      Report("Publisher could not connect to MQTT broker at %s".format(uri))
      Report(toString(t))
      None
    }
  }

  // Connect subscriber to the broker URI on the relay destination topic
  private def connectSubscriber(name: String): Option[MqttAsyncClient] = try {
    val sub = new MqttAsyncClient(uri, name, new MemoryPersistence())
    sub.setCallback(this)
    sub.connect(new MqttConnectOptions).waitForCompletion
    sub.subscribe(inputTopic, qos)
    Report("subscriber is connected to MQTT broker at %s".format(uri))
    Some(sub)
  } catch {
    case t: Throwable => {
      Report("subscriber could not connect to MQTT broker at %s".format(uri))
      Report(toString(t))
      None
    }
  }

  /** Publish a string to the chatbot topic.  */
  def publish(text: String): Unit = publish(text.getBytes)

  /** Publish a byte array to the chatbot topic. */
  def publish(payload: Array[Byte]): Unit =
    publish(new MqttMessage(payload))

  /** Publish a MQTT message to the chatbot topic. */
  def publish(msg: MqttMessage): Unit = try {
    publisher.foreach(pub => {
      pub.publish(outputTopic, msg)
      Report("Published to %s: %s".format(outputTopic, msg.toString))
    })
  } catch {
    case t: Throwable => { 
      Report("Could not publish to %s".format(outputTopic))
      Report(toString(t))
    }
  }

  /** Translate the chat text ChatAnalysisMessage structures, and 
   *  publish their json serializations.
   */
  def relay(chatText: String): Unit = {
    val cams = chatAnalysis.toChatAnalysisMessages(chatText)
    cams.foreach(cam => publish(ChatAnalysisMessageJson(cam)))
  }

  /** Report an issue that resulted in loss of contact with the MQTT broker.
   */
  override def connectionLost(t: Throwable): Unit = {
    Report("Connection to MQTT broker lost.")
    Report(toString(t))
  }

  /** Implementation of this method is needed for MqttCallback extension but 
   *  is otherwise not used.
   */
  override def deliveryComplete(token: IMqttDeliveryToken): Unit =
    Report("deliveryComplete: %s" + token.getMessage)

  /** Messages received on the subscriber topic are analyzed and the analysis
   *  converted to Json then published on the chatbot topic.
   */
  override def messageArrived(topic: String, msg: MqttMessage): Unit = {
    val chatText = msg.toString
    Report("Read from %s: %s".format(topic, chatText))
    if (topic == inputTopic) relay(chatText)
  }
}

