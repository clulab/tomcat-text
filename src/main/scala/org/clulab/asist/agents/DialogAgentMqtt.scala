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
 * An optional Tdac Server connection can be used.
 *
 * Input and output are in json format.
 *
 * @param host MQTT host to connect to.
 * @param port MQTT network port to connect to.
 * @param tdacUrlOpt TDAC server URL and port, optional
 */

class DialogAgentMqtt(
  val host: String = "",
  val port: String = "",
  val tdacUrlOpt: Option[String] = None,
  val idc: Boolean = false
) extends TdacAgent(tdacUrlOpt)
    with LazyLogging
    with MessageBusClientListener { 

  // Actor concurrency system
  implicit val ec:ExecutionContext = ExecutionContext.global
  implicit val system: ActorSystem = ActorSystem("MessageBusAgent")

  // enqueue messages from the bus if they're coming in too fast.
  private val queue: Queue[BusMessage] = new Queue 

  // Testbed heartbeat
  private val heartbeatProducer = new HeartbeatProducer(this)

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

  // create the IDC worker if required
  private val idcWorker: Option[IdcWorker] =
    if(idc) Some(new IdcWorker(this)) else None

  tdacInit

  logger.info(s"DialogAgentMqtt version ${BuildInfo.version} running.")

  /** Lines to be written to the MessageBus
   * @param output A list of objects to be published to the Message Bus
   */
  override def writeOutput(
    output: List[BusMessage]
  ): Unit = output match {
    case head::tail =>
      publish(head.topic, head.text)
      writeOutput(tail)
    case _ => 
      dequeue 
  }

  /** Add a Message Bus job to the queue 
   *  @param job Job to add
   */
  private def enqueue(job: BusMessage): Unit = queue.enqueue(job)

  /** Take the next job off the queue.  Do this after processing the job */
  private def dequeue: Unit = if(!queue.isEmpty)queue.dequeue 

  /** Write to the Message Bus
   * @param topic:  The message bus topic on which to publish the message
   * @param json:  A JSON message structure
   */
  def publish(
    topic: String,
    json: String
  ): Unit = bus.publish(
    topic,
    JsonUtils.noNulls(json) // do not publish nulls
  )

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
    val busy = !queue.isEmpty

    // Add message to the processing queue
    enqueue(BusMessage(topic, text))

    // process the message if no other job is running
    if(!busy) doNextJob
  }

  /** process the next message in the queu e*/
  override def doNextJob(): Unit = if(!queue.isEmpty) {
    dequeue
    val message: BusMessage = queue.head
    message.topic match {
      case `topicSubTrial` => processTrialMessage(message)
      case `topicSubChat` => processChatMessage(message)
      case `topicSubAsr` => processAsrMessage(message)
      case _ => doNextJob
    }
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
        idcWorker.foreach(_.reset)
        heartbeatProducer.start(trial)
        writeOutput(List(output))
        tdacClient match {
          case Some(tdac) =>tdac.resetServer
          case _ => doNextJob
        }
      }
      else if(TrialMessage.isStop(trial)) { // Trial Stop
        heartbeatProducer.stop
        doNextJob
      }
      else doNextJob
    case _ => doNextJob
  }

  /** process a UAZ ASR message
   * @param input: Incoming traffic from Message Bus
   */
  private def processAsrMessage(input: BusMessage): Unit = 
    processDialogAgentMessage(
      input,
      AsrMessage(input.text)
        .map(DialogAgentMessage(source_type, input.topic, _, this))
    )

  /** process a Minecraft Chat message
   * @param input: Incoming traffic from Message Bus
   */
  private def processChatMessage(input: BusMessage): Unit = 
    processDialogAgentMessage(
      input,
      ChatMessage(input.text)
        .map(DialogAgentMessage(source_type, input.topic, _, this))
    )

  /** Finishing steps for publishing a DialogAgentMessage
   * @param input: Incoming traffic on Message Bus
   * @param message: A completed Dialog Agent Message
   */
  private def processDialogAgentMessage(
    input: BusMessage,
    messageMaybe: Option[DialogAgentMessage]
  ): Unit = messageMaybe match {
    case Some(message) =>
      // get the IDC worker going if we have one
      idcWorker.foreach(_.enqueue(input.topic, message.data.extractions))
      // send job to TDAC if we use it
      tdacClient match {
        case Some(tc: TdacClient) =>
          val metadataJValue = JsonUtils.parseJValue(input.text) // TODO really?
          metadataJValue match {
            case Some(jvalue) =>
              tc.runClassification(
                topicPubDialogAgent,
                input.text,
                message.data,
                jvalue // TODO can't we use the message text?
  
              )
            case None => // unable to parse JSON, move on
              doNextJob
          }
        case None =>  // no TDAC
          val outputJson = JsonUtils.writeJson(message)
          publish(topicPubDialogAgent, outputJson)
          doNextJob
      }
    case None => // no message
      doNextJob
  }
}
