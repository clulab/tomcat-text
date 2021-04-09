/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Updated:  2021 March
 *
 *  An abstract MQTT agent
 *
 *  Based on the Eclipse Paho MQTT API: www.eclipse.org/paho/files/javadoc
 */
package org.clulab.asist

import org.eclipse.paho.client.mqttv3._
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence
import org.slf4j.LoggerFactory
import scala.util.control.Exception._


class DialogAgentMqtt(
    val host: String = "localhost",
    val port: String = "1883",
    val nMatches: Option[Int] = None
  ) extends DialogAgentFoo with DialogAgentJson {

  val id = "dialog_agent",
    // subscription from message bus
  val topicInputChat: String = "minecraft/chat"
  val topicInputUazAsr: String = "agent/asr/final"
  val topicInputAptimaAsr: String = "status/asistdataingester/userspeech"

  // publication to message bus
  val topicOutput: String = "agent/dialog"

  private lazy val logger = LoggerFactory.getLogger(this.getClass())

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
    sub.subscribe(topicInputChat, qos)
    sub.subscribe(topicInputUazAsr, qos)
    sub.subscribe(topicInputAptimaAsr, qos)
    sub
  }

  /** Test connection to the broker, can't run without it */
  if ((subscriber.isDefined && subscriber.head.isConnected) &&
      (publisher.isDefined && publisher.head.isConnected))) {
    logger.info("Connected to MQTT broker at %s".format(uri))
    logger.info("Subscribed to: %s".format(inputTopics.mkString(", ")))
    logger.info("Publishing on: %s".format(outputTopics.mkString(", ")))
    logger.info("Running.")
  } else {
    logger.error("Could not connect to MQTT broker at %s".format(uri))
    System.exit(1)
  }

   /** Convert a json-serialized ChatMessage to a DialogAgent message
   *  and publish to the message bus.
   *  @param msg:  input text from the Minecraft chat textfield
   */
  def processChat(msg: ChatMessage): Unit =
    publish(toDialogAgentMessage(msg, "message_bus", DialogAgentMqttSettings.topicInputChat))


  /** Convert a json-serialized UazAsrMessage to a DialogAgent message
   *  and publish to the message bus if the 'is_final' flag is set.
   *  @param msg: Input from the Minecraft microphone
   */
  def processUazAsr(msg: UazAsrMessage): Unit =
    publish(toDialogAgentMessage(msg, "message_bus", DialogAgentMqttSettings.topicInputUazAsr))


  /** Convert a json-serialized AptimaAsrMessage to a DialogAgent message
   *  and publish to the message bus
   *  @param msg: Input from the Minecraft microphone
   */
  def processAptimaAsr(msg: AptimaAsrMessage): Unit =
    publish(toDialogAgentMessage(msg, "message_bus", DialogAgentMqttSettings.topicInputAptimaAsr))



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
    outputTopics.map(topic => publish(topic, output)).foldLeft(true)(_ && _)

  /** Publish a MQTT message to one topic
   *  @param topic Destination for MQTT message
   *  @param output MQTT message to publish
   *  @return true if the operation succeeded
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

  /** Called when the connection to the server is lost.
   * @param cause The reason behind the loss of connection.
   */
  override def connectionLost(cause: Throwable): Unit = {
    logger.error("Connection to MQTT broker lost.")
  }

  /** Called when delivery for a message has been completed.
   * @param token the delivery token associated with the message.
   */
  override def deliveryComplete(token: IMqttDeliveryToken): Unit =
    logger.debug("deliveryComplete: %s" + token.getMessage)

  /** This method is called when a message arrives on the message bus
   * @param topic The message topic
   * @param mm The message
   */
  override def messageArrived(topic: String, mm: MqttMessage): Unit = try {
    messageArrived(topic, mm.toString)
  } catch {
    case t: Throwable => {
      logger.error("Problem reading message on %s".format(topic))
      logger.error(t.toString)
    }
  }


  /** Publish analysis of messages received on subscription topics
   *  @param topic:  The message bus topic where the input appeared
   *  @param json:  A json representation of a case class data struct
   */
  override def messageArrived(topic: String, json: String): Unit = {
    topic match {
      case DialogAgentMqttSettings.topicInputChat =>
        toChatMessage(json).map(a => processChat(a))
      case DialogAgentMqttSettings.topicInputUazAsr =>
        toUazAsrMessage(json).map(a => processUazAsr(a))
      case DialogAgentMqttSettings.topicInputAptimaAsr =>
        toAptimaAsrMessage(json).map(a => processAptimaAsr(a))
      case _ =>
    }
  }

}
