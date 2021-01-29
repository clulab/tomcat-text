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
import scala.util.control.Exception._

class TestDialogAgent (
  val host: String = "localhost",
  val port: String = "1883",
  val pubTopic: String,
  val subTopic: String,
  val testCase: String,
  val expectedResult: String) extends MqttAgent(
    host, 
    port,
    "dialog_agent_test",
    List(pubTopic),
    List(subTopic)) {

  /** Used so Json serializer can recognize case classes */
  implicit val formats = Serialization.formats(NoTypeHints)

  override def info(str: String): Unit = Info("TestDialogAgent", str)
  override def error(str: String): Unit = Error("TestDialogAgent", str)

  /** Compare a message with its expected result 
   *  @param actualResult A string to be compared with an expected result
   */ 
  def test(actualResult: String): Unit = try {
    val actual = read[DialogAgentMessage](actualResult)
    val expected = read[DialogAgentMessage](expectedResult)
    val ret = MessageTestDialogAgent(actual, expected)
    if (ret) println("PASS") else println("FAIL")
  } catch {
    case t: Throwable => {
      error("Problem interpreting test results ")
      error(t.toString)
    }
  }

  // Send the test case and then wait around for a reply.
  override def start: Unit = {
    subscriber.map(sub => sub.subscribe(subTopic, qos))
    publish(testCase)
  }

  /** This method is called when a MqttMessage is successfully read
   * @param topic name of the topic on the message was published to
   * @param message the string representation of the message
   */
  override def messageArrived(topic: String, message: String): Unit = 
    topic match {
      case `subTopic` => test(message)
      case _ => println("Received %s ob topic %s", message, topic)
    }
}
