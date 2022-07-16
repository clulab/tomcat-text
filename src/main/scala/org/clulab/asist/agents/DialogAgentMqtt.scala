package org.clulab.asist.agents

import ai.lum.common.ConfigFactory

import buildinfo.BuildInfo
import akka.actor.ActorSystem
import java.time.Clock
import com.typesafe.scalalogging.LazyLogging
import org.clulab.asist.messages._
import org.clulab.utils.{MessageBusClient, MessageBusClientListener}
import scala.collection.mutable.Queue

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

  // each message gets an ID number in the logfile
  var versionInfoCount: Int = 0
  var rollcallResponseCount: Int = 0
  var dialogCount: Int = 0
  var heartbeatCount: Int = 0

  var asrCount:Int = 0
  var chatCount:Int = 0
  var rollcallRequestCount:Int = 0
  var trialStartCount:Int = 0
  var trialStopCount:Int = 0

  // One message read from the bus
  case class BusMessage(
    topic: String = "N/A",
    text: String = "N/A" // may contain newlines
  )

  // enqueue messages from the bus if they're coming in too fast.
  private val queue: Queue[BusMessage] = new Queue

  def checkQueue(): Unit = {
    logger.info("checkQueue:")
    if(processing) 
      logger.info("  Queue is processing")
    else {
      val sz = queue.size
      logger.info("  Queue not processing, size = " + sz)
      if(sz > 0) {
        logger.info("  Restarting queue processing")
        processNextMessage
      } else {
        logger.info("  I'm bored")
      }
    }
  }


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

  // write message to the bus on the message topic.  Do not write topic.
  def publish[A <: AnyRef](a: A): Unit = a match {
    case m: DialogAgentMessage =>
      bus.publish(m.topic, JsonUtils.writeJsonNoNulls(m.copy(topic = "N/A")))
      dialogCount += 1
      logger.info(s"Write ${m.topic} # ${dialogCount}")
    case m: HeartbeatMessage =>
      bus.publish(m.topic, JsonUtils.writeJsonNoNulls(m.copy(topic = "N/A")))
      heartbeatCount += 1
      logger.info(s"Write ${m.topic} # ${heartbeatCount}")
    case m: RollcallResponseMessage =>
      bus.publish(m.topic, JsonUtils.writeJsonNoNulls(m.copy(topic = "N/A")))
      rollcallResponseCount += 1
      logger.info(s"Write ${m.topic} # ${rollcallResponseCount}")
    case m: VersionInfoMessage =>
      bus.publish(m.topic, JsonUtils.writeJsonNoNulls(m.copy(topic = "N/A")))
      versionInfoCount += 1
      logger.info(s"Write ${m.topic} # ${versionInfoCount}")
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
    val message = BusMessage(topic, text)
    queue.enqueue(message)

    logger.info("Message Arrived.  Queue length is now " + queue.length)
  }

  /** process the next message in the queue*/
  def processNextMessage(): Unit = {
    if(queue.isEmpty) processing = false
    else {
      processing = true
      logger.info(s"Procesing message, queue length = ${queue.length}")
      processMessage(queue.dequeue)
    }
  }

  // reset the counts at the start of a trial
  def resetTrafficCounts(): Unit = {
    trialStartCount = 0
    versionInfoCount = 0
    rollcallRequestCount = 0
    rollcallResponseCount = 0
    asrCount = 0
    chatCount = 0
    dialogCount = 0
    heartbeatCount = 0
    trialStopCount = 0
  }

  // report the counts at the end of a trial
  def reportTrafficCounts(): Unit= {
    logger.info("Message Bus traffic for this trial:")
    logger.info(s"trialStart:       ${trialStartCount}")
    logger.info(s"versionInfo:      ${versionInfoCount}")
    logger.info(s"rollcallRequest:  ${rollcallRequestCount}")
    logger.info(s"rollcallResponse: ${rollcallResponseCount}")
    logger.info(s"asr:              ${asrCount}")
    logger.info(s"chat:             ${chatCount}")
    logger.info(s"dialog:           ${dialogCount}")
    logger.info(s"heartbeat:        ${heartbeatCount}")
    logger.info(s"trialStop:        ${trialStopCount}")
  }


  def processMessage(
    message: BusMessage
  ): Unit = {

    val topic = message.topic
    val text = message.text

    topic match {
      case AsrMessage.topic =>
        asrCount += 1
        logger.info(s"Read ${topic} # ${asrCount}")
        AsrMessage(text).foreach(asr =>
          publish(DialogAgentMessage(source_type, topic, asr, this))
        )
      case ChatMessage.topic =>
        chatCount += 1
        logger.info(s"Read ${topic} # ${chatCount}")
        ChatMessage(text).foreach(chat =>
          publish(DialogAgentMessage(source_type, topic, chat, this))
        )
      case RollcallRequestMessage.topic =>
        rollcallRequestCount += 1
        logger.info(s"Read ${topic} # ${rollcallRequestCount}")
        RollcallRequestMessage(text).foreach(request =>
          publish(RollcallResponseMessage(uptimeSeconds, request))
        ) 
      case TrialMessage.topic => 
        TrialMessage(text).foreach(trial => 
          if(TrialMessage.isStart(trial)) { // Trial Start
            resetTrafficCounts
            trialStartCount += 1
            logger.info(s"Read trial start # ${trialStartCount}")
            heartbeatProducer.set_trial_info(trial)
            publish(VersionInfoMessage(trial))
          }
          else if(TrialMessage.isStop(trial)) { // Trial Stop
            trialStopCount += 1
            logger.info(s"Read trial stop # ${trialStopCount}")
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
