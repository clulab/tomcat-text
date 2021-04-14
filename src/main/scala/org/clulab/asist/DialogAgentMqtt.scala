/**
 * Authors:  Joseph Astier, Adarsh Pyarelal
 *
 * Updated:  2021 April
 *
 * This class reads input from the message bus on subscribed topics,
 * performs analysis on the input, and then publishes the analysis to
 * the output topic.
 *
 * Input and output are in json format.
 *
 * @param host MQTT host to connect to.
 * @param port MQTT network port to connect to.
 * @param nMatches An optional maximum number of taxonomy_matches to return
 */
package org.clulab.asist

class DialogAgentMqtt(
    val host: String = "",
    val port: String = "",
    override val nMatches: Int = 0
) extends AgentJson { 

  // publish to this topic
  val outputTopic: String = "agent/dialog"

  val source_type = "message_bus"
  
  // message bus handler
  val bus = new AgentMqtt(host, port, topics, outputTopic, this)

  /** Receive messages and publish analysis 
   *  @param topic:  The message bus topic where the message was published
   *  @param json:  A json representation of a message struct
   */
  def messageArrived(topic: String, json: String): Unit = 
    outputJson(source_type, topic, topic, json).map(bus.publish)
}
