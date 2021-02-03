//  TestDialogAgent
//
//  Author:  Joseph Astier, Adarsh Pyarelal
//  updated:  2021 January
//
//  Test the DialogAgent by comparing its responses on the message bus with 
//  known standards
//
package org.clulab.asist

import java.net.ConnectException
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write}
import org.json4s.Xml.{toJson, toXml}
import org.slf4j.LoggerFactory
import scala.util.control.Exception._

class TestDialogAgent (
  val host: String = "localhost",
  val port: String = "1883",
  val subTopic: String,
  val pubTopic: String,
  val testCase: String,
  val expectedResult: String) extends MqttAgent(
    host, 
    port,
    "dialog_agent_test",
    List(subTopic),
    List(pubTopic)) {

  private val logger = LoggerFactory.getLogger(this.getClass())

  /** Used so Json serializer can recognize case classes */
  implicit val formats = Serialization.formats(NoTypeHints)



  // Test the MQTT broker connection before proceeding.
  if(ready) {
    logger.info("Connected to MQTT broker at %s".format(uri))
    logger.info("Ready.") // Go

    publish(testCase)
  } else {
    logger.error("Could not connect to MQTT broker at %s".format(uri))
    System.exit(1)  // It is impossible to run without the broker
  }

  /** Compare a message with its expected result 
   *  @param actualResult A string to be compared with an expected result
   */ 
  def test(actualResult: String): Unit = {
    logger.info("Testing...")
    try {
      val actual = read[DialogAgentMessage](actualResult)
      val expected = read[DialogAgentMessage](expectedResult)
      val ret = MessageTestDialogAgent(actual, expected)
      if (ret) println("PASS") else println("FAIL")
    } catch {
      case t: Throwable => {
        logger.error("Problem interpreting test results ")
        logger.error(t.toString)
      }
    }
  }

  /** This method is called when a MqttMessage is successfully read
   * @param topic name of the topic on the message was published to
   * @param message the string representation of the message
   */
  override def messageArrived(topic: String, message: String): Unit = {
    logger.info("Received %s on topic %s".format(message,topic))
    topic match {
      case `subTopic` => test(message)
      case _ => 
    }
  }
}
