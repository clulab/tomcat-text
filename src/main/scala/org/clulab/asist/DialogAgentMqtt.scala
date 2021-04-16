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

import org.json4s._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write}
import scala.util.control.Exception._
import org.slf4j.LoggerFactory

class DialogAgentMqtt(
    val host: String = "",
    val port: String = "",
    override val nMatches: Int = 0
) extends DialogAgent { 

  private lazy val logger = LoggerFactory.getLogger(this.getClass())

  val source_type = "message_bus"

  val bus = new AgentMqtt(
    host, 
    port, 
    inputTopics,
    outputTopic, 
    this
  )

  /** Receive a message from the message bus
   *  @param topic:  The message bus topic where the message was published
   *  @param json:  A json text string
   */
  def messageArrived(
    topic: String,
    text: String
  ): Unit = text.split("\n").map(line => 
      allCatch.opt(read[MetadataMessage](line)).map(metadata => {
        logger.info("messageArrived")
        bus.publish(  // to Message Bus
          write( // to json
            toDialogAgentMessage( // to struct
              source_type,
              topic,
              metadata.msg,
              topic match {
                case `topicChat` => metadata.data.sender
                case `topicUazAsr` => metadata.data.participant_id
                case `topicAptimaAsr` => metadata.data.playername
              },
              metadata.data.text
            )
          )
        )
      }
      )
    )
}
