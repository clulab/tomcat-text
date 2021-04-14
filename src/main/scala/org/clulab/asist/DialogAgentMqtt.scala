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

  override val source_type = "message_bus"

  val bus = new AgentMqtt(host, port, topics, "agent/dialog", this)

  /** Receive messages and publish analysis 
   *  @param topic:  The message bus topic where the message was published
   *  @param json:  A json representation of a message struct
   */
  def messageArrived(topic: String, text: String): Unit = 
    toMetadataMessage(text).map(md =>
      participantId(topic, md).map(id =>  
        toOutputJson(topic, md.msg, id, md.data.text).map(json =>
          bus.publish(json)
        )
      )
    )
}
