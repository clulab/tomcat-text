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
 * @param nMatches  maximum number of taxonomy_matches to return (up to 5)
 */
package org.clulab.asist

import org.json4s._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write}
import scala.util.control.Exception._

class DialogAgentMqtt(
  val host: String = "",
  val port: String = "",
  override val nMatches: Int = 0
) extends DialogAgent { 

  val source_type = "message_bus"

  // this handles the message bus operations.  
  val busDriver = new AgentMqtt(
    host, 
    port, 
    inputTopics,
    outputTopic, 
    this
  )

  /** Receive a message from the message bus
   *  @param topic:  The message bus topic where the message was published
   *  @param json:  A metadata text string
   */
  def messageArrived(
    topic: String,
    json: String
  ): Unit = json.split("\n").map(line => 
    allCatch.opt(read[Metadata](line)).map(metadata => 
      busDriver.publish(  // to Message Bus
        write( // to json
          toDialogAgentMessage( // to struct
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
