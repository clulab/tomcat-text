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
    override val nMatches: Option[Int] = None
) extends DialogAgent 
    with DialogAgentJson {

  // message bus topics
  val topicInputChat: String = "minecraft/chat"
  val topicInputUazAsr: String = "agent/asr/final"
  val topicInputAptimaAsr: String = "status/asistdataingester/userspeech"
  val topicOutput: String = "agent/dialog"
  
  // message bus handler
  val bus = new AgentMqtt(
    host,
    port,
    inputTopics = List(
      topicInputChat, 
      topicInputUazAsr,
      topicInputAptimaAsr
    ),
    topicOutput,
    this
  )

  /** publish the Dialog Agent analysis of message data 
   *  @param topic: The message bus topic where the message was published
   *  @param msg: CommonMsg struct from the message data
   *  @param participant_id: Subject who is speaking
   *  @param text: Spoken text that will be processed
   */
  def publish(
    topic: String, 
    msg: CommonMsg, 
    participant_id: String, 
    text: String): Unit = bus.publish(
      toJson(
        toDialogAgentMessage(
          "message_bus",
          topic,
          msg,
          participant_id,
          text
        )
      )
    )

  /** Receive messages and publish analysis 
   *  @param topic:  The message bus topic where the message was published
   *  @param json:  A json representation of a message struct
   */
  def messageArrived(topic: String, json: String): Unit = topic match {
    case `topicInputChat` => toChatMessage(json).map(a =>
      publish(topic, a.msg, a.data.sender, a.data.text)
    )
    case `topicInputUazAsr` => toUazAsrMessage(json).map(a =>
      publish(topic,a.msg, a.data.participant_id, a.data.text)
    )
    case `topicInputAptimaAsr` => toAptimaAsrMessage(json).map(a =>
      publish(topic, a.msg, a.data.playername, a.data.text)
    )
    case _ => 
  }
}
