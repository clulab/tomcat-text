package org.clulab.asist.agents

import java.time.Clock

import org.clulab.asist.messages._
import org.clulab.utils.{MessageBusClient, MessageBusClientListener}
import org.json4s.jackson.Serialization.{read, write}

import scala.collection.mutable.Queue
import scala.util.control.NonFatal

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


case class BusMessage (
  topic: String,
  line: String
)


class DialogAgentMqtt(
  val host: String = "",
  val port: String = "",
  override val args: DialogAgentArgs = new DialogAgentArgs
) extends DialogAgent 
    with DacUser
    with MessageBusClientListener { 

  val source_type = "message_bus"

  val queue: Queue[BusMessage] = new Queue 

  // this handles the message bus operations.  
  val bus = new MessageBusClient(
    host, 
    port, 
    subscriptions,
    publications,
    this
  )


  def iteration(s: RunState): Unit = {
  }

  def writeLine(s: RunState, line: String): RunState = {
    s
  }

  /* async callback after DAC reset */
  def publishVersionInfo(
    json: String,
  ): Unit = bus.publish(topicPubVersionInfo, json)

  /* async callback after Dac classification */
  def publishMessage(
    json: String,
  ): Unit = bus.publish(topicPubDialogAgent, json)

  /** Receive a message from the message bus
   *  @param topic:  The message bus topic where the message was published
   *  @param json:  A metadata text string
   */
  def messageArrived(
    topic: String,
    json: String
  ): Unit = {
    json.split("\n").map{
      line => queue.enqueue(BusMessage(topic, line))
    }
    iteration
  }


  // service the next input in line.
  def iteration: Unit = {
    // if the queue only contains the element we just added, there is no
    // processing thread running.  Start one now.
    if(queue.length == 1) {
      processInput(queue.head)
    }

    // otherwise allow the returning processing thread to start the next.
  }


  def processInput(input: BusMessage): Unit = input.topic match {
    case `topicSubTrial` => trialMessageArrived(input)
    case _ => dialogAgentMessageArrived (input)
  }

  def dialogAgentMessageArrived(input: BusMessage): Unit = try {
    val message = getDialogAgentMessage(
      source_type,
      input.topic,
      input.topic,
      read[Metadata](input.line)
    )
    // publish it
  } catch {
    case NonFatal(t) => logger.error("Could not parse {}", input.line)
  } 

  // send VersionInfo if we receive a TrialMessage with subtype "start", 
  def trialMessageArrived(input: BusMessage): Unit = try {
    val tm = read[TrialMessage](input.line)
    if(tm.msg.sub_type == "start") {
      val timestamp = Clock.systemUTC.instant.toString
      val versionInfo = VersionInfo(this, timestamp)

      // publish it
    }
  } catch {
    case NonFatal(t) => logger.error("Could not parse {}", input.line)
  }

}
