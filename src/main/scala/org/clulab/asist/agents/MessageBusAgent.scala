package org.clulab.asist.agents

import ai.lum.common.ConfigFactory
import akka.actor.ActorSystem
import akka.http.scaladsl._
import akka.http.scaladsl.model._
import buildinfo.BuildInfo
import com.typesafe.config.Config
import com.typesafe.scalalogging.LazyLogging
import java.time.Clock
import org.clulab.asist.messages._
import org.clulab.utils.{MessageBusClient, MessageBusClientListener}
//import org.json4s.{Extraction,_}
//import org.json4s.jackson.JsonMethods._
//import org.json4s.jackson.Serialization.{read, write}
import scala.collection.mutable.Queue
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._
import scala.language.postfixOps
import scala.util.control.NonFatal
import scala.util.{Failure, Success}


/**
 * Authors:  Joseph Astier, Adarsh Pyarelal
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

abstract class MessageBusAgent(
  val host: String = "",
  val port: String = "",
  val tdacUrlOpt: Option[String] = None
) extends TdacAgent(tdacUrlOpt)
    with LazyLogging
    with MessageBusClientListener { 

  // Actor concurrency system
  implicit val ec:ExecutionContext = ExecutionContext.global
  implicit val system: ActorSystem = ActorSystem("MessageBusAgent")

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

  // Begin the next job in the queue or do nothing if queue is empty
  def startJob: Unit = if(!queue.isEmpty) {
    processInput(queue.head)
  }  // else all jobs are done.

  /* Direct bus messages to their handlers by topic */
  def processInput(
    input: BusMessage
  ): Unit = input.topic match {
    case `topicSubTrial` => processTrialMessage(input)
    case `topicSubChat` => processChatMessage(input)
    case `topicSubAsr` => processAsrMessage(input)
    case _ =>
  }

  // user definitions
  def processTrialMessage(input: BusMessage): Unit
  def processChatMessage(input: BusMessage): Unit
  def processAsrMessage(input: BusMessage): Unit

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
