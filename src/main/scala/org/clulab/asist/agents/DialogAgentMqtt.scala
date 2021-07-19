package org.clulab.asist.agents

import java.time.Clock

import org.clulab.asist.messages._
import org.clulab.utils.{MessageBusClient, MessageBusClientListener}
import org.json4s.jackson.Serialization.{read, write}

import scala.util.control.Exception._

/**
 * Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 * Updated:  2021 July
 *
 * This class reads input from the message bus on subscribed topics,
 * performs analysis on the input, and then publishes the analysis to
 * the output topic.
 *
 * Input and output are in json format.
 *
 * @param host MQTT host to connect to.
 * @param port MQTT network port to connect to.
 * @param nMatches  maximum number of taxonomy_matches to return (up to 5)
 */

class DialogAgentMqtt(
  val host: String = "",
  val port: String = "",
  override val args: DialogAgentArgs = new DialogAgentArgs
) extends DialogAgent 
  with MessageBusClientListener { 

  val qMan: QueueManager = new QueueManager(this)

  val source_type = "message_bus"

  // this handles the message bus operations.  
  val bus = new MessageBusClient(
    host, 
    port, 
    subscriptions,
    publications,
    this
  )

  // send VersionInfo if we receive a TrialMessage with subtype "start", 
  def trialMessageArrived(json: String): Unit = json.split("\n").map(line =>
    allCatch.opt(read[TrialMessage](line)).map(trialMessage => {
      if(trialMessage.msg.sub_type == "start") {
        val timestamp = Clock.systemUTC.instant.toString
        bus.publish(topicPubVersionInfo, write(VersionInfo(this, timestamp)))
      }
    })
  )

  /* async callback after classification */
  def sendMessage(
    message: DialogAgentMessage,
  ): Unit = bus.publish(topicPubDialogAgent,writeJson(message))

  /** Receive a message from the message bus
   *  @param topic:  The message bus topic where the message was published
   *  @param json:  A metadata text string
   */
  def messageArrived(
    topic: String,
    json: String
  ): Unit = topic match {
    case `topicSubTrial` => trialMessageArrived(json)
    case _ => json.split("\n").map(line => 
      allCatch.opt(read[Metadata](line)).map{metadata => 
        val message = getDialogAgentMessage(
          source_type,
          topic,
          topic,
          metadata
        )
        qMan.enqueue(sendMessage, message)
      }
    )
  } 
}
