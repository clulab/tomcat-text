//  DialogAgent
//
//  Author:  Joseph Astier, Adarsh Pyarelal
//  Date:  2020 November
//
package org.clulab.asist


// this object allows a test of the DialogAgent
object DialogAgentTest extends App {

  val testData = Array(
    "Researcher: Are you ready to begin your Minecraft mission?",
    "Subject: Yes, let's go.",
  )

  val host = "127.0.0.1"
  val port = 1883
  val agent = new DialogAgent(host, port)

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

