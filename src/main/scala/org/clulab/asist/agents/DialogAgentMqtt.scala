package org.clulab.asist.agents

import akka.actor.ActorSystem
import buildinfo.BuildInfo
import com.typesafe.scalalogging.LazyLogging
import java.time.Clock
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
      topicSubChat,
      topicSubAsr,
      topicSubTrial
    ),
    publications = List(
      topicPubDialogAgent,
      topicPubVersionInfo,
      topicPubHeartbeat
    ),
    this
  )

  // Testbed heartbeat (this will start immediately)
  private val heartbeatProducer = new HeartbeatProducer(this)

  /** Publish a list of bus messages
   * @param output A list of objects to be published to the Message Bus
   */
  def writeOutput(
    output: List[BusMessage]
  ): Unit = output match {
    case head::tail =>
      bus.publish(
        head.topic, 
        JsonUtils.noNulls(head.text) // do not publish nulls
      )
      writeOutput(tail)
    case _ => 
  }

  /** Convenience method to write topic and text to the Message Bus
   * @param topic:  The message bus topic on which to publish the message
   * @param json:  A JSON message structure
   */
  def writeOutput (topic: String, json: String): Unit = 
    writeOutput(List(BusMessage(topic, json)))

  /** Receive a message from the message bus
   * @param topic:  The message bus topic where the message was published
   * @param text:  A metadata text string, possibly multi-line
   */
  def messageArrived(
    topic: String,
    text: String 
  ): Unit = {

    // if the queue is empty, there is no aynchronous job in
    // progress and it is safe to start a new one.
    val busy: Boolean = !queue.isEmpty
    
    val job: BusMessage = BusMessage(topic, text)

    // Add message to the processing queue
    queue.enqueue(job)

    // If the queue was not busy, we must start the job directly
    // because it will be deleted when it finishes
    if(!busy) doJob(job)
  }

  /** process the next message in the queue*/
  def doNextJob(): Unit = {
    queue.dequeue  // delete the queue head, we just finished it
    if(!queue.isEmpty) doJob(queue.head)  
  }

  private def doJob(message: BusMessage): Unit = message.topic match {
    case `topicSubTrial` => processTrialMessage(message)
    case `topicSubChat` => processChatMessage(message)
    case `topicSubAsr` => processAsrMessage(message)
  }

  /** send VersionInfo if we receive a TrialMessage with subtype "start", 
   * @param input: Incoming traffic from Message Bus
   */
  private def processTrialMessage(
    input: BusMessage
  ): Unit = TrialMessage(input.text) match {
    case Some(trial) => 
      if(TrialMessage.isStart(trial)) { // Trial Start
        val versionInfo: VersionInfo = VersionInfo(trial)
        val outputJson: String = JsonUtils.writeJson(versionInfo)
        val output: BusMessage = BusMessage(
          topicPubVersionInfo,
          outputJson
        )
        heartbeatProducer.set_trial_info(trial)
        writeOutput(List(output))
        doNextJob
      }
    case _ => 
    doNextJob
  }

  /** process a UAZ ASR message
   * @param input: Incoming traffic from Message Bus
   */
  private def processAsrMessage(input: BusMessage): Unit = 
    publishDialogAgentMessage(
      input,
      AsrMessage(input.text)
        .map(DialogAgentMessage(source_type, input.topic, _, this))
    )

  /** process a Minecraft Chat message
   * @param input: Incoming traffic from Message Bus
   */
  private def processChatMessage(input: BusMessage): Unit = 
    publishDialogAgentMessage(
      input,
      ChatMessage(input.text)
        .map(DialogAgentMessage(source_type, input.topic, _, this))
    )

  /** post-processing steps 
   * @param topic: where the originating message was subscribed
   * @param message: A completed Dialog Agent Message
   */
  private def publishDialogAgentMessage(
    input: BusMessage,
    messageMaybe: Option[DialogAgentMessage]
  ): Unit = {
    messageMaybe match {
      case Some(message) =>
        writeOutput(topicPubDialogAgent, JsonUtils.writeJson(message))
      case None => // no message
    }
    doNextJob
  }

  logger.info(s"DialogAgentMqtt version ${BuildInfo.version} running.")
}
