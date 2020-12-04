//  DialogAgent
//
//  Author:  Joseph Astier, Adarsh Pyarelal
//  Date:  2020 November
//
//  Test object for the DialogAgent class
//
package org.clulab.asist

import java.io.{File, PrintWriter}
import java.util.Properties

import edu.stanford.nlp.pipeline.StanfordCoreNLP
import org.clulab.odin.Mention
import spray.json.DefaultJsonProtocol._
import spray.json._

import scala.collection.immutable
import scala.io.Source
import scala.util.parsing.json.JSON


object DialogAgentTest extends App {

  // Test data, this should contain all cases, ideally from an actual mission
  val testData = Array(
    "Researcher: Are you ready to enter Minecraft?",
    "Subject: Yes, let's go.",
  )

  // Build an extractor for our tokens
  val pipeline = new StanfordCoreNLP(new Properties() {
    setProperty("annotators","tokenize, ssplit, pos, lemma, ner, parse, dcoref")
  })
  val json_tax_map = JsonParser(
    Source.fromResource("taxonomy_map.json").mkString
  ).convertTo[immutable.Map[String, Array[immutable.Map[String, String]]]]
  val extractor = new Extractor(pipeline, new AsistEngine(), json_tax_map)

  // configure the MQTT broker URI
  val host = "127.0.0.1"
  val port = 1883
  
  // this DialogAget is the test article
  val agent = new DialogAgent(host, port, extractor)

  // Use hard-coded values already in agent for this test
  val relay_source_topic = agent.relaySrc
  val relay_destination_topic = agent.relayDst

  // Send a message on the relay source topic, and see if it is published
  // on the relay destination topic.
  if (agent.start()) {
    testData.foreach(test => agent.publish(relay_source_topic, test))
  } else
    println("Could not start Dialog Relayer.")
}

