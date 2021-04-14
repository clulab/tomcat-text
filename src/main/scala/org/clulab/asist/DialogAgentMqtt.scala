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
) extends DialogAgentJson { 

  val inputTopics = List(topicChat, topicUazAsr, topicAptimaAsr)
  val outputTopic = "agent/dialog"
  val source_type = "message_bus"

  val bus = new AgentMqtt(
    host, 
    port, 
    inputTopics,
    outputTopic, 
    this
  )

  /** Process one line of input at a time
   *  @param topic:  The message bus topic where the message was published
   *  @param json:  A json representation of a message struct
   *  @return A json representation of the Dialog Agent output
   */
  def processLine(topic: String, line: String): Unit = 
    toMetadataMessage(line).map(md =>
      participantId(topic, md).map(id =>  
        bus.publish(
          toOutputJson(
            source_type,
            topic,
            md.msg,
            id,
            md.data.text
          )
        )
      )
    )

  /** Receive a message from the message bus
   *  @param topic:  The message bus topic where the message was published
   *  @param json:  A json text string
   */
  def messageArrived(topic: String, text: String): Unit = 
    text.split("\n").map(line => processLine(topic, line))
}
