//  RunTestDialogAgent
//
package org.clulab.asist

import scala.io.Source
import org.json4s._

import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write}


object  RunTestDialogAgent extends App {
  val host: String = "localhost"
  val port: String = "1883"
  val testFile: String = "src/main/scala/org/clulab/asist/asr_inputs.json"
  val resultFile: String = "src/main/scala/org/clulab/asist/asr_outputs.json"
  val pubTopic: String = "agent/asr"
  val subTopic: String = "agent/tomcat_chatbot" // needs single point of truth
  val testCases: List[String] = readFile(testFile)  // AsrMessages
  val expectedResults: List[String] = readFile(resultFile)  // DialogAgentMessages

  /** Used so Json serializer can recognize case classes */
  implicit val formats = Serialization.formats(NoTypeHints)

  def readFile(filename: String): List[String] = try {
    val source = Source.fromFile(filename)
    val ret = source.getLines.toList
    source.close
    ret
  } catch {
    case t: Throwable => List()
  }

  val test = new TestDialogAgent(
    host, 
    port, 
    pubTopic, 
    subTopic, 
    testCases.head, 
    expectedResults.head
  )
}

