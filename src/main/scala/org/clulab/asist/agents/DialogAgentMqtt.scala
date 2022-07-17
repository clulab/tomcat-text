package org.clulab.asist.agents

import ai.lum.common.ConfigFactory

import buildinfo.BuildInfo
import akka.actor.ActorSystem
import java.time.Clock
import com.typesafe.scalalogging.LazyLogging
import org.clulab.asist.messages._
import org.clulab.utils.{MessageBusClient, MessageBusClientListener}
import scala.collection.mutable.Queue
import scala.collection.mutable.MutableList

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext
import scala.language.postfixOps


/**
 * Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 * This class is part of the ASIST Testbed.  Messages are 
 * processed in FIFO order.
 *
 * No null JSON value are published
 *
 * @param host MQTT host to connect to.
 * @param port MQTT network port to connect to.
 */

class DialogAgentMqtt(
  val host: String = "localhost",
  val port: Int = 1883,
  val nochat: Boolean = false
) extends DialogAgent
    with LazyLogging
    with MessageBusClientListener { 

  // Actor concurrency system
  private implicit val ec:ExecutionContext = ExecutionContext.global
  private implicit val system: ActorSystem = ActorSystem("MqttQueue")
  import system.dispatcher  // from system now in scope

  // true if there are queued messages being processed
  private var processing: Boolean = false

  // CommonMsg source type for all MQTT publishing
  private val source_type: String = "message_bus"

  // count messages coming and going
  val subscribedTraffic: MutableList[String] = MutableList.empty
  val publishedTraffic: MutableList[String] = MutableList.empty

  // One message read from the bus
  case class BusMessage(
    topic: String = "N/A",
    text: String = "N/A" // may contain newlines
  )

  // enqueue messages from the bus if they're coming in too fast.
  private val queue: Queue[BusMessage] = new Queue

  // if the message processing queue has stalled, restart it
  def checkQueue(): Unit = 
    if((queue.size > 0) && (!processing)) processNextMessage

  // Message Bus subscriptions
  val chatMaybe: List[String] = 
    PartialFunction.condOpt(ChatMessage.topic){case x if !nochat => x}.toList

  val subscriptions: List[String] = List(
    AsrMessage.topic,
    RollcallRequestMessage.topic,
    TrialMessage.topic
  ) ++ chatMaybe

  // Message Bus publications
  val publications: List[String] = List(
    DialogAgentMessage.topic,
    HeartbeatMessage.topic,
    RollcallResponseMessage.topic,
    VersionInfoMessage.topic
  )

  // communication with the MQTT Message Bus
  private val bus: MessageBusClient = new MessageBusClient(
    host,
    port,
    subscriptions.sorted,
    publications.sorted,
    this
  )

  def publish(topic: String, json: String): Unit = {
    bus.publish(topic, json)
    logger.info(s"Write ${topic}")
    publishedTraffic += topic
  }

  // write message to the bus.  Do not write topic.
  def publish[A <: AnyRef](a: A): Unit = a match {
    case m: DialogAgentMessage =>
      publish(m.topic, JsonUtils.writeJsonNoNulls(m.copy(topic = "N/A")))
    case m: HeartbeatMessage =>
      publish(m.topic, JsonUtils.writeJsonNoNulls(m.copy(topic = "N/A")))
    case m: RollcallResponseMessage =>
      publish(m.topic, JsonUtils.writeJsonNoNulls(m.copy(topic = "N/A")))
    case m: VersionInfoMessage =>
      publish(m.topic, JsonUtils.writeJsonNoNulls(m.copy(topic = "N/A")))
    case _ =>
  }

  // send early heartbeat advising of initialization
  publish(HeartbeatMessage("info", false, "Initializing."))

  // get rule engine lazy init out of the way
  startEngine()

  // Heartbeat message publication on a fixed interval
  private val heartbeatProducer = new HeartbeatProducer(this)

  /** Receive a message from the message bus
   * @param topic:  The message bus topic where the message was published
   * @param text:  A metadata text string, possibly multi-line
   */
  def messageArrived(
    topic: String,
    text: String 
  ): Unit = {

    // Add message to the processing queue
    queue.enqueue(BusMessage(topic, text))
    val sz = queue.length

    // only announce the arrival if the line is getting long
    if(queue.length > 3)
      logger.info("Message Arrived.  Queue length = " + sz)
  }

  /** process the next message in the queue*/
  def processNextMessage(): Unit = {
    if(queue.isEmpty) processing = false
    else {
      processing = true
      val sz = queue.length

      // Let the user know if the processing line is getting long
      if(sz > 3) 
        logger.info(s"Procesing message, queue length = ${sz}")

      processMessage(queue.dequeue)
    }
  }

  // report the traffic for this log and then clear it
  def reportTraffic(log: MutableList[String]): Unit = {
    val topics = log.toSet.toList.sorted
    topics.foreach(topic => {
      val sz = log.filter(a => a == topic).size
      logger.info(s" ${sz}\t  ${topic}")
    })
    log.clear
  }

  // report the counts at the end of a trial
  def reportTrafficCounts(): Unit= {
    logger.info("TRIAL STOPPED:")
    logger.info("Messages read:")
    reportTraffic(subscribedTraffic)
    logger.info("Messages written:")
    reportTraffic(publishedTraffic)
  }


  def processMessage(
    message: BusMessage
  ): Unit = {
    val topic = message.topic
    val text = message.text

    logger.info(s"Read ${topic}")

    topic match {
      case AsrMessage.topic =>
        subscribedTraffic += topic
        AsrMessage(text).foreach(asr =>
          publish(DialogAgentMessage(source_type, topic, asr, this))
        )
      case ChatMessage.topic =>
        subscribedTraffic += topic
        ChatMessage(text).foreach(chat =>
          publish(DialogAgentMessage(source_type, topic, chat, this))
        )
      case RollcallRequestMessage.topic =>
        subscribedTraffic += topic
        RollcallRequestMessage(text).foreach(request =>
          publish(RollcallResponseMessage(uptimeSeconds, request))
        ) 
      case TrialMessage.topic => 
        TrialMessage(text).foreach(trial => 
          if(TrialMessage.isStart(trial)) { // Trial Start
            subscribedTraffic.clear
            publishedTraffic.clear
            subscribedTraffic += topic
            heartbeatProducer.set_trial_info(trial)
            publish(VersionInfoMessage(trial))
          }
          else if(TrialMessage.isStop(trial)) { // Trial Stop
            subscribedTraffic += topic
            // heartbeats stop producing the trial_id 
            val new_msg: CommonMsg = trial.msg.copy(trial_id = "N/A")
            heartbeatProducer.set_trial_info(trial.copy(msg = new_msg))
            reportTrafficCounts
          }
        )
      case _ => 
    }

    processNextMessage
  }

  logger.info(s"DialogAgentMqtt version ${BuildInfo.version} running.")

  // Check on the queue every second
  val start: Long = 0
  val frequency: Long = 1
  system.scheduler.scheduleWithFixedDelay(
    start seconds, 
    frequency seconds
  ) {
    new Runnable {
      def run() = checkQueue
    }
  }
}
