package org.clulab.asist

import org.clulab.asist.agents.DialogAgentMqtt
import org.eclipse.paho.client.mqttv3._
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence
import org.slf4j.LoggerFactory

/**
 * Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 * Updated:  2021 June
 *
 * Publish and subscribe to message bus topics
 * Based on the Eclipse Paho MQTT API: www.eclipse.org/paho/files/javadoc
 *
 * @param host MQTT host to connect to.
 * @param port MQTT network port to connect to.
 * @param subscriptions MQTT topics from which messages to process are read.
 * @param publications MQTT topic for publishing processed messages
 * @param owner A DialogAgentMQTT that will process input messages
 */

class MessageBusClient(
  val host: String = "",
  val port: String = "",
  val subscriptions: List[String] = List.empty,
  val publications: List[String] = List.empty,
  val owner: DialogAgentMqtt
) extends MqttCallback {

  private lazy val logger = LoggerFactory.getLogger(this.getClass())

  val uri = "tcp://%s:%s".format(host,port)
  val qos = 2 // highest quality of service, send msg exactly once.
  val publisher: MqttClient = 
    new MqttClient(uri, "dialog_agent_subscriber", new MemoryPersistence()) 
  val subscriber: MqttAsyncClient = 
    new MqttAsyncClient(uri,"dialog_agent_publisher", new MemoryPersistence())

  // Connect to the Message Bus
  publisher.connect(new MqttConnectOptions)
  subscriber.connect(new MqttConnectOptions).waitForCompletion
  subscriptions.map(topic => subscriber.subscribe(topic, qos))
  subscriber.setCallback(this)

  // Report status of our connection to the Message Bus
  if(subscriber.isConnected && publisher.isConnected) {
    logger.info("Connected to MQTT broker at %s".format(uri))
    logger.info("Subscribed to: %s".format(subscriptions.mkString(", ")))
    logger.info("Publishing on: %s".format(publications.mkString(", ")))
    logger.info("Running.")
  } else {
    logger.error("Could not connect to MQTT broker at %s".format(uri))
    System.exit(1)
  }

  /** Publish a MQTT message to one topic
   *  @param topic
   *  @param text String to publish on the Message Bus
   */
  def publish(topic: String, text: String): Unit = try {
    publisher.publish(topic, new MqttMessage(text.getBytes))
  } catch {
    case t: Throwable => { 
      logger.error("Could not publish to %s".format(topic))
      logger.error(t.toString)
    }
  } 

  /** Called when the connection to the server is lost.
   * @param cause The reason behind the loss of connection.
   */
  override def connectionLost(cause: Throwable): Unit = {
    logger.error("Connection to MQTT broker lost.")
    logger.error(cause.toString)
  }

  /** Called when delivery for a message has been completed.
   * @param token the delivery token associated with the message.
   */
  override def deliveryComplete(token: IMqttDeliveryToken): Unit =
    logger.debug("deliveryComplete: %s" + token.getMessage)

  /** This method is called when a message arrives on the message bus
   * @param topic The message topic
   * @param mm Contains metadata in Json format
   */
  override def messageArrived(topic: String, mm: MqttMessage): Unit = try {
    owner.messageArrived(topic, mm.toString)
  } catch {
    case t: Throwable => {
      logger.error("Problem reading message on %s".format(topic))
      logger.error(t.toString)
    }
  }
}
