package org.clulab.asist.agents

import akka.actor.ActorSystem
import buildinfo.BuildInfo
import com.typesafe.scalalogging.LazyLogging
import java.time.Clock
import org.clulab.asist.messages._
import org.clulab.utils.{MessageBusClient, MessageBusClientListener}

import scala.collection.mutable.Queue
import scala.concurrent.ExecutionContext
import scala.util.control.NonFatal

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
  val queue: Queue[BusMessage] = new Queue 

  // Testbed heartbeat
  val heartbeatProducer = new HeartbeatProducer(this)

  val source_type = "message_bus"
  val trial_start = config.getString("Trial.msg.sub_type.trial_start")
  val trial_stop = config.getString("Trial.msg.sub_type.trial_stop")

  // communication with Message Bus
  val bus = new MessageBusClient(
    host,
    port,
    subscriptions,
    publications,
    this
  )

  // create the IDC worker if required
  val idcWorker: Option[IdcWorker] =
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
  def enqueue(job: BusMessage): Unit = queue.enqueue(job)

  /** Take the next job off the queue.  Do this after processing the job */
  def dequeue: Unit = if(!queue.isEmpty)queue.dequeue 

  /** Do the next thing in the processing queue */
  override def iteration(): Unit = startJob

  /** Manage a processing failure */
  override def handleError(): Unit = finishJob

  /** Write to the Message Bus
   * @param topic:  The message bus topic on which to publish the message
   * @param json:  A JSON message structure
   */
  def publish(
    topic: String,
    json: String
  ): Unit = bus.publish(
    topic,
    JsonUtils.removeNullFields(json) // do not publish nulls
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
    val noJobRunning = queue.isEmpty

    // Place the new messsage behind any others in the processing queue
    enqueue(BusMessage(topic, text))

    // start new async job if none are running
    if(noJobRunning) startJob
  }

  // Begin the next job in the queue or do nothing if queue is empty
  def startJob: Unit = if(!queue.isEmpty) {
    processInput(queue.head)
  }  // else all jobs are done.

  /* Direct bus messages to their handlers by topic */
  def processInput(
    input: BusMessage
  ): Unit = input.topic match {
    case `topicSubTrial` => processTrialMessage(input)
    case `topicSubChat` => processDialogAgentMessage(input)
    case `topicSubAsr` => processDialogAgentMessage(input)
    case _ =>
  }

  /** send VersionInfo if we receive a TrialMessage with subtype "start", 
   * @param input: Message bus traffic with topic and text
   */
  def processTrialMessage(input: BusMessage): Unit = try {
    val trialMessage = JsonUtils.readJson[TrialMessage](input.text)
    trialMessage.msg.sub_type match {

      // trial start message, reset the TDAC and start heartbeat
      case `trial_start` =>
        idcWorker.foreach(_.reset)
        val currentTimestamp: String = Clock.systemUTC.instant.toString
        val versionInfo: VersionInfo = VersionInfo(
          config,
          trialMessage,
          currentTimestamp
        )
        val outputJson: String = JsonUtils.writeJson(versionInfo)
        val output: BusMessage = BusMessage(
          topicPubVersionInfo,
          outputJson
        )
        tdacClient match {
          case Some(tc: TdacClient) =>
            tc.resetServer(List(output))
          case None =>  // no TDAC
            writeOutput(List(output))
            finishJob
        }
        heartbeatProducer.start(trialMessage)

      // trial stop message, stop heartbeat
      case `trial_stop` =>
        heartbeatProducer.stop
        finishJob

      // other trial messages 
      case _ => finishJob
    }
  } catch {
    case NonFatal(t) =>
      reportError(input, t.toString)
      finishJob
  }


  /** Send DialogAgentMessage for any subscribed topic except "trial" 
   * @param input: Incoming traffic on Message Bus
   */
  def processDialogAgentMessage(input: BusMessage): Unit = try {
    val message: DialogAgentMessage = getDialogAgentMessage(
      source_type,
      input.topic,
      input.topic,
      JsonUtils.readJson[Metadata](input.text)
    )
    idcWorker.foreach(_.enqueue(input.topic, message.data.extractions))
    tdacClient match {
      case Some(tc: TdacClient) =>
        val metadataJValue = JsonUtils.parseJValue(input.text)
        metadataJValue match {
          case Some(jvalue) =>
            tc.runClassification(
              topicPubDialogAgent,
              input.text,
              message.data,
              jvalue
            )
          case None => // unable to parse JSON, move on
            finishJob
        }
      case None =>  // no TDAC
        val outputJson = JsonUtils.writeJson(message)
        publish(topicPubDialogAgent, outputJson)
        finishJob
    }
  } catch {
    case NonFatal(t) =>
      reportError(input, t.toString)
      finishJob
  }

  /** When finished, remove the queue head and start the next job.  */
  def finishJob: Unit = if(!queue.isEmpty) {
    dequeue
    startJob
  }

  /** Report an error in parsing a message
   *  @param input The message that led to the problem
   *  @param t The problem
   */
  def reportError(input: BusMessage, report: String): Unit = {
    val preamble = "Could not parse input text"
    logger.error(s"${preamble} from topic ${input.topic}: ${input.text}")
    logger.error(report)
  }
}
