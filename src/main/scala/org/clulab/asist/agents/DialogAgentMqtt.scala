package org.clulab.asist.agents

import ai.lum.common.ConfigFactory
import com.typesafe.config.Config
import akka.actor.ActorSystem
import akka.http.scaladsl._
import akka.http.scaladsl.model._
import com.typesafe.scalalogging.LazyLogging
import java.time.Clock
import org.clulab.asist.messages._
import org.clulab.utils.{MessageBusClient, MessageBusClientListener}
import org.json4s.{Extraction,_}
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization.{read, write}
import scala.collection.mutable.Queue
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._
import scala.language.postfixOps
import scala.util.control.NonFatal
import scala.util.{Failure, Success}

/**
 * Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 * This class reads input from the message bus on subscribed topics,
 * performs analysis on the input, and then publishes the analysis to
 * the output topic.
 *
 * If the tdacUrlOpt argument is set, the TAMU Dialog Act Classifier will be 
 * called for each DialogAgentMessage published.
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
  val tdacUrlOpt: Option[String] = None
) extends TdacAgent(tdacUrlOpt)
    with LazyLogging
    with MessageBusClientListener { 

  // A single Message Bus message
  case class BusMessage (
    topic: String,
    text: String // may contain newlines
  )

  logger.info(s"DialogAgentMqtt version ${dialogAgentVersion}")

  // Actor concurrency system
  implicit val ec:ExecutionContext = ExecutionContext.global
  implicit val system: ActorSystem = ActorSystem("DialogAgentMqtt")

  // Testbed heartbeat
  val heartbeatProducer = new HeartbeatProducer(this)

  val source_type = "message_bus"

  // enqueue messages from the bus if they're coming in too fast.
  val queue: Queue[BusMessage] = new Queue 

  logger.info("Initializing Message Bus connection...")

  // communication with Message Bus
  val bus = new MessageBusClient(
    host,
    port,
    subscriptions,
    publications,
    this
  )

  tdacInit

  /** Lines to be written to the MessageBus
   * @param rs The runState sent with the orignal message to the TDAC client
   * @return The run state with the lineWrites var incremented by 1
   */
  override def writeOutput(
    rs: RunState
  ): RunState = rs.outputLines match {
    case line::tail =>
      publish(rs.outputTopic, line)
      val rs1 = RSM.addLineWrite(rs)
      val rs2 = RSM.setOutputLines(rs1, tail)
      writeOutput(rs2)
    case _ => 
      dequeue 
      rs
  }

  /** Add a Message Bus job to the queue 
   *  @param job Job to add
   */
  def enqueue(job: BusMessage): Unit = queue.enqueue(job)

  /** Take the next job off the queue.  Do this after processing the job */
  def dequeue: Unit = if(!queue.isEmpty)queue.dequeue 

  /** States sent by the TDAC server, if in use.
   * @param message A DialogAgentMessage with the dialog_act_label value set
   */
  override def iteration(rs: RunState): Unit = startJob

  override def handleError(rs: RunState): Unit = finishJob

  /** Write to the Message Bus
   * @param topic:  The message bus topic on which to publish the message
   * @param text:  A JSON message structure
   */
  def publish(
    topic: String,
    text: String
  ): Unit = {
    bus.publish(topic, text)
  }

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

  /* Use the head of the queue as the next job. */
  def startJob: Unit = if(!queue.isEmpty) queue.head.topic match {
    case `topicSubTrial` => processTrialMessage(queue.head)
    case _ => processDialogAgentMessage (queue.head)
  }  // else all jobs are done.

  /** When finished, remove the queue head and start the next job.  */
  def finishJob: Unit = if(!queue.isEmpty) {
    dequeue
    startJob
  }

  /** send VersionInfo if we receive a TrialMessage with subtype "start", 
   * @param input: Message bus traffic with topic and text
   */
  def processTrialMessage(input: BusMessage): Unit = try {
    val trialMessage = read[TrialMessage](input.text)
    trialMessage.msg.sub_type match {

      // trial start message, reset the TDAC and start heartbeat
      case "start" =>
        heartbeatProducer.start(trialMessage)
        val currentTimestamp = Clock.systemUTC.instant.toString
        val versionInfo = VersionInfo(this, trialMessage, currentTimestamp)
        val outputJson = write(versionInfo)
        tdacClient match {
          case Some(tc: TdacClient) =>
            val rs1 = RSM.setInputTopic(new RunState, input.topic)
            val rs2 = RSM.setInputText(rs1, input.text)
            val rs3 = RSM.setOutputTopic(rs2, topicPubVersionInfo)
            val rs4 = RSM.setOutputLine(rs3, outputJson)
            tc.resetServer(rs4)
          case None =>  // no TDAC
            publish(topicPubVersionInfo, outputJson)
            finishJob 
        }

      // trial stop message, stop heartbeat
      case "stop" =>
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
    val message = getDialogAgentMessage(
      source_type,
      input.topic,
      input.topic,
      read[Metadata](input.text)
    )
    tdacClient match {
      case Some(tc: TdacClient) =>
        val metadataJValue = parse(input.text)
        val rs1 = RSM.setInputTopic(new RunState, input.topic)
        val rs2 = RSM.setInputText(rs1, input.text)
        val rs3 = RSM.setOutputTopic(rs2, topicPubDialogAgent)
        tc.runClassification(rs3, message.data, metadataJValue)
      case None =>  // no TDAC
        val outputJson = write(message)
        publish(topicPubDialogAgent, outputJson)
        finishJob
    }
  } catch {
    case NonFatal(t) => 
      reportError(input, t.toString)
      finishJob
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
