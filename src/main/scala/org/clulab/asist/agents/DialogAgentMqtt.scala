package org.clulab.asist.agents

import akka.actor.ActorSystem
import buildinfo.BuildInfo
import com.typesafe.scalalogging.LazyLogging
import org.clulab.asist.messages._
import org.clulab.utils.{MessageBusClient, MessageBusClientListener}

import scala.collection.mutable.Queue
import scala.concurrent.ExecutionContext

/**
 * Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 * This class is part of the ASIST Testbed.  Messages are queued
 * and processed in FIFO order.
 *
 * No null JSON value are published
 *
 * @param host MQTT host to connect to.
 * @param port MQTT network port to connect to.
 */

class DialogAgentMqtt(
  val host: String = "",
  val port: String = "",
) extends DialogAgent
    with LazyLogging
    with MessageBusClientListener { 

  // Actor concurrency system
  implicit val ec:ExecutionContext = ExecutionContext.global
  implicit val system: ActorSystem = ActorSystem("MessageBusAgent")

  // enqueue messages from the bus if they're coming in too fast.
  private val queue: Queue[BusMessage] = new Queue 

  // CommonMsg source type for all MQTT publishing
  private val source_type = "message_bus"

  // communication with the MQTT Message Bus
  private val bus = new MessageBusClient(
    host,
    port,
    subscriptions = List(
      topicSubAsr,
      topicSubChat,
      topicSubRollcallRequest,
      topicSubTrial
    ).sorted,
    publications = List(
      topicPubDialogAgent,
      topicPubHeartbeat,
      topicPubRollcallResponse,
      topicPubVersionInfo
    ).sorted,
    this
  )

  // Heartbeat message publication on a fixed interval
  private val heartbeatProducer = new HeartbeatProducer(bus)

  /** Receive a message from the message bus
   * @param topic:  The message bus topic where the message was published
   * @param text:  A metadata text string, possibly multi-line
   */
  def messageArrived(
    topic: String,
    text: String 
  ): Unit = {
    queue.enqueue(BusMessage(topic, text))
    // Restart processing if the queue was empty
    if(queue.length == 1) processMessage(queue.head)
  }

  /** process the next message in the queue*/
  private def processNextMessage(): Unit = {
    queue.dequeue
    if(!queue.isEmpty) {
      processMessage(queue.head)  
    }
  }

  // Publish messages.  Do not include topic
  private def processMessage(
    message: BusMessage
  ): Unit = {
    message.topic match {
      case `topicSubAsr` => 
        AsrMessage(message.text).foreach(asr => {
          bus.publish (
            topicPubDialogAgent, 
            JsonUtils.writeJsonNoNulls(
              DialogAgentMessage(source_type, message.topic, asr, this)
                .copy(topic = "N/A")
            )
          )
        })
      case `topicSubChat` => 
        ChatMessage(message.text).foreach(chat => {
          bus.publish (
            topicPubDialogAgent, 
            JsonUtils.writeJsonNoNulls(
              DialogAgentMessage(source_type, message.topic, chat, this)
                .copy(topic = "N/A")
            )
          )
        })
      case `topicSubRollcallRequest` => 
        RollcallRequestMessage(message.text).foreach(request => {
          bus.publish(
            topicPubRollcallResponse,
            JsonUtils.writeJsonNoNulls(
              RollcallResponseMessage(uptimeMillis, request)
                .copy(topic = "N/A")
            )
          )
        })
      case `topicSubTrial` => 
        TrialMessage(message.text).foreach(trial => {
          if(TrialMessage.isStart(trial)) { // Trial Start
            heartbeatProducer.set_trial_info(trial)
            bus.publish(
              topicPubVersionInfo, 
              JsonUtils.writeJsonNoNulls(
                VersionInfo(trial)
                  .copy(topic = "N/A")
              )
            )
          }
          else if(TrialMessage.isStop(trial)) { // Trial Stop
            // heartbeats lose the trial_id 
            val new_msg: CommonMsg = trial.msg.copy(trial_id = "N/A")
            heartbeatProducer.set_trial_info(trial.copy(msg = new_msg))
          }
        })
      case _ => logger.error("Unknown topic read: " + message.topic)
    }
    processNextMessage
  }

  logger.info(s"DialogAgentMqtt version ${BuildInfo.version} running.")
}
