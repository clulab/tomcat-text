package org.clulab.asist

import org.json4s._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write}

import scala.util.control.Exception._

/**
 * Authors:  Joseph Astier, Adarsh Pyarelal
 *
 * Updated:  2021 June
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
  override val nMatches: Int = 0
) extends DialogAgent { 

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
        bus.publish(publishVersionInfo, write(versionInfo))
      }
    })
  )

  /** Receive a message from the message bus
   *  @param topic:  The message bus topic where the message was published
   *  @param json:  A metadata text string
   */
  def messageArrived(
    topic: String,
    json: String
  ): Unit = if(topic == subscribeTrial) trialMessageArrived(json) else {
    json.split("\n").map(line => 
      allCatch.opt(read[Metadata](line)).map(metadata => 
        bus.publish(  // to Message Bus
          publishDialogAgent,
          write( // to json
            dialogAgentMessage( // to struct
              source_type,
              topic,
              topic,
              metadata
            )
          )
        )
      )
    )
  } 
}
