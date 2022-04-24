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
 * This class reads interacts with the Message Bus
 *
 * Input and output are in json format.
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

  private val source_type = "message_bus"

  // communication with Message Bus
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

  // Testbed heartbeat (this will start immediately)
  private val heartbeatProducer = new HeartbeatProducer(this)

  /** Publish a list of bus messages
   * @param output A list of objects to be published to the Message Bus
   */
  def publish(
    output: List[BusMessage]
  ): Unit = output match {
    case head::tail =>
      bus.publish(
        head.topic, 
        JsonUtils.noNulls(head.text) // do not publish nulls
      )
      publish(tail)
    case _ => 
  }

  /** Convenience method to write topic and text to the Message Bus
   * @param topic:  The message bus topic on which to publish the message
   * @param json:  A JSON message structure
   */
  def publish (topic: String, json: String): Unit = 
    publish(List(BusMessage(topic, json)))

  /** Receive a message from the message bus
   * @param topic:  The message bus topic where the message was published
   * @param text:  A metadata text string, possibly multi-line
   */
  def messageArrived(
    topic: String,
    text: String 
  ): Unit = {

    val job: BusMessage = BusMessage(topic, text)

    // An empty queue indicates no asynchonous job in process
    val busy: Boolean = !queue.isEmpty

    // Add message to the processing queue
    queue.enqueue(job)

    // If the queue was not busy, we must start the job directly
    // because it will be deleted when it finishes
    if(!busy) processMessage(job)
  }

  /** process the next message in the queue*/
  def processNextMessage(): Unit = {
    queue.dequeue
    if(!queue.isEmpty) {
      processMessage(queue.head)  
    }
  }

  private def processMessage(
    message: BusMessage
  ): Unit = {
    message.topic match {
      case `topicSubAsr` => 
        AsrMessage(message.text).foreach(asr => {
          publish (
            topicPubDialogAgent, 
            JsonUtils.writeJson(
              DialogAgentMessage(source_type, message.topic, asr, this)
            )
          )
        })
      case `topicSubChat` => 
        ChatMessage(message.text).foreach(chat => {
          publish (
            topicPubDialogAgent, 
            JsonUtils.writeJson(
              DialogAgentMessage(source_type, message.topic, chat, this)
            )
          )
        })
      case `topicSubRollcallRequest` => 
        RollcallRequestMessage(message.text).foreach(request => {
          publish(
            topicPubRollcallResponse,
            JsonUtils.writeJson(RollcallResponseMessage(uptimeMillis, request))
          )
        })
      case `topicSubTrial` => 
        TrialMessage(message.text).foreach(trial => {
          if(TrialMessage.isStart(trial)) { // Trial Start
            heartbeatProducer.set_trial_info(trial)
            publish(
              topicPubVersionInfo, 
              JsonUtils.writeJson(VersionInfo(trial))
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
