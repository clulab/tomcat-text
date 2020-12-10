//  DialogAgent
//
//  Author:  Joseph Astier, Adarsh Pyarelal
//  Date:  2020 November
//
//  Test object for the DialogAgent class
//
package org.clulab.asist

object DialogAgentTest extends App {

  // test data, that we can hopefully parse
  val messageToRelay = 
    "Which type of victim will you save next Yellow green or whoever comes next"

  // configure the MQTT broker URI
  val host = "127.0.0.1"
  val port = 1883
  
  // this DialogAget is the test article
  val agent = new DialogAgent(host, port)

  // Use hard-coded values already in agent for this test
  val relay_source_topic = agent.relaySrc
  val relay_destination_topic = agent.relayDst

  // Send the message on the relay source topic, and see if it is published
  // on the relay destination topic.
  if (agent.start()) {
    agent.publish(relay_source_topic, messageToRelay)
  } else
    println("Could not start Dialog Relayer.")
}

