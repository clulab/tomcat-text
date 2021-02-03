//  MqttAgent
//
//  Author:  Joseph Astier, Adarsh Pyarelal
//  Updated:  2021 January
//
//  Based on the Eclipse Paho MQTT API: www.eclipse.org/paho/files/javadoc
//
package org.clulab.asist

import org.eclipse.paho.client.mqttv3._
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence
import org.slf4j.LoggerFactory
import scala.util.control.Exception._


/** coordinator class for anything needing connection to the message bus */
abstract class MqttAgent(
  host: String = "localhost", 
  port: String = "1883",
  id: String,
  subTopics: List[String],
  pubTopics: List[String]) extends MqttCallback {

  private val logger = LoggerFactory.getLogger(this.getClass())

  /** MQTT broker connection identities */
  val SUB_ID = "%s_subscriber".format(id)
  val PUB_ID = "%s_publisher".format(id)

  /** MQTT broker connection address */
  val uri = "tcp://%s:%s".format(host,port)

  /** MQTT quality of service */
  val qos = 2

  /** Publisher for sending messages to the publication topics
   *  @return an optional MqttClient publisher if successful, else None
   */
  val publisher: Option[MqttClient] = allCatch.opt{
    val pub = new MqttClient(uri, PUB_ID, new MemoryPersistence())
    pub.connect(new MqttConnectOptions)
    pub
  }

  /** Subscriber receives messages on the subscription topics 
   *  @return an optional MqttAsyncClient subscriber if successful, else None
   */
  val subscriber: Option[MqttAsyncClient] = allCatch.opt {
    val sub = new MqttAsyncClient(uri, SUB_ID, new MemoryPersistence())
    sub.setCallback(this)
    sub.connect(new MqttConnectOptions).waitForCompletion
    subTopics.map(topic=> sub.subscribe(topic,qos))
    sub
  }

  /** True if publisher and subsriber are connected to the MQTT broker */
  def ready: Boolean = 
    ((!subscriber.isEmpty && subscriber.head.isConnected) &&
    (!publisher.isEmpty && publisher.head.isConnected))

  /** Publish a string to all publication topics
   *  @param output string to publish
   *  @return true if the output was published to all publication topics
   */
  def publish(output: String): Boolean = publish(output.getBytes)

  /** Publish a byte array to all publication topics
   *  @param output byte array to publish
   *  @return true if the output was published to all publication topics
   */
  def publish(output: Array[Byte]): Boolean = publish(new MqttMessage(output))

  /** Publish a MQTT message to all publication topics
   *  @param output MQTT message to publish
   *  @return true if the output was published to all publication topics
   */
  def publish(output: MqttMessage): Boolean = 
    pubTopics.map(topic => publish(topic, output)).foldLeft(true)(_ && _)

  /** Publish a MQTT message to one topic
   *  @param topic destination for MQTT message
   *  @param output MQTT message to publish
   *  @return true if the output was published to the topic
   */
  def publish(topic: String, output: MqttMessage): Boolean = try {
      publisher.map(pub=>pub.publish(topic, output))
      true
    } catch {
      case t: Throwable => { 
        logger.error("Could not publish to %s".format(topic))
        false
      }
    } 

  /** This method is called when the connection to the server is lost.
   * @param cause the reason behind the loss of connection.
   */
  override def connectionLost(cause: Throwable): Unit = {
    logger.error("Connection to MQTT broker lost.")
  }

  /** Called when delivery for a message has been completed.
   * @param token the delivery token associated with the message.
   */
  override def deliveryComplete(token: IMqttDeliveryToken): Unit =
    logger.info("deliveryComplete: %s" + token.getMessage)

  /** This method is called when a message arrives from the server.
   * @param topic name of the topic on the message was published to
   * @param mm the actual message.
   * @throws Exception if a terminal logger.error has occurred
   */
  override def messageArrived(topic: String, mm: MqttMessage): Unit = try {
    messageArrived(topic, mm.toString)
  } catch {
    case t: Throwable => {
      logger.error("Problem reading message on %s".format(topic))
      logger.error(t.toString)
    }
  }

  /** This method is called when a MqttMessage is successfully read
   * @param topic name of the topic on the message was published to
   * @param message the string representation of the message
   */
  def messageArrived(topic: String, message: String): Unit
}
