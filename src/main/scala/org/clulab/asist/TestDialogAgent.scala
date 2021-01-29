//  TestDialogAgent
//
//  Author:  Joseph Astier, Adarsh Pyarelal
//  Date:  2021 January
//
//  Test the DialogAgent by comparing its responses on the message bus with 
//  known standards
//
package org.clulab.asist

import java.net.ConnectException
import org.eclipse.paho.client.mqttv3._
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write}
import org.json4s.Xml.{toJson, toXml}
import scala.util.control.Exception._


class TestDialogAgent(
    val host: String = "localhost",
    val port: String = "1883",
    val testTopic: String,
    val testCase: String,
    val expectedResult: String) extends MqttCallback {

  /** Used so Json serializer can recognize case classes */
  implicit val formats = Serialization.formats(NoTypeHints)

  /** readback message analysis to this topic */
  val TOPIC_INPUT = "agent/tomcat_chatbot"

  /** MQTT broker connection identities */
  val SUB_ID = "test_dialog_agent_subscriber"
  val PUB_ID = "test_dialog_agent_publisher"

  /** MQTT broker connection address */
  val uri = "tcp://%s:%s".format(host,port)

  /** MQTT quality of service */
  val qos = 2

  /** Publisher sends our message analysis to the output topic */
  val publisher: Option[MqttClient] = allCatch.opt{
    val pub = new MqttClient(uri, PUB_ID, new MemoryPersistence())
    pub.connect(new MqttConnectOptions)
    pub
  }

  /** Subscriber receives messages on n topics */
  val subscriber: Option[MqttAsyncClient] = allCatch.opt {
    val sub = new MqttAsyncClient(uri, SUB_ID, new MemoryPersistence())
    sub.setCallback(this)
    sub.connect(new MqttConnectOptions).waitForCompletion
    sub.subscribe(TOPIC_INPUT,qos)
    sub
  }

  /** Compare a message with its expected result */
  def test(actualResult: String): Unit = try {
    Info("Read")
    val actual = read[DialogAgentMessage](actualResult)
    val expected = read[DialogAgentMessage](expectedResult)
    val ret = MessageTestDialogAgent(actual, expected)
    if (ret) println("PASS") else println("FAIL")
  } catch {
    case t: Throwable => {
      Error("Problem interpreting test results ")
      Error(t)
    }
  }

  /** Report an issue that resulted in loss of contact with the MQTT broker.*/
  override def connectionLost(t: Throwable): Unit = {
    Error("Connection to MQTT broker lost.")
    Error(t)
  }

  /** Needed for MqttCallback extension but otherwise not used. */
  override def deliveryComplete(token: IMqttDeliveryToken): Unit = {
    Info("deliveryComplete: %s" + token.getMessage)
  }

  // Send the test case and then wait around for a reply.
  publish(testCase)

  /** Publish an AsrMessage */
  def publish(output: AsrMessage): Unit = publish(write(output))

  /** Publish a string */
  def publish(output: String): Unit = publish(output.getBytes)

  /** Publish a byte array */
  def publish(output: Array[Byte]): Unit = publish(new MqttMessage(output))

  /** Publish a MQTT message */
  def publish(output: MqttMessage): Unit = try {
    publisher.map(pub=>pub.publish(testTopic, output))
    Info("Published to %s: %s".format(testTopic, output.toString))
  } catch {
    case t: Throwable => {
      Error("Could not publish to %s".format(testTopic))
      Error(t)
    }
  }

  /** Publish analysis of messages received on subscription topics */
  override def messageArrived(topic: String, msg: MqttMessage): Unit = topic match {
    case TOPIC_INPUT => test(msg.toString)
  }
}
