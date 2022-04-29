package org.clulab.asist.agents

import buildinfo.BuildInfo
import java.time.Clock
import com.typesafe.scalalogging.LazyLogging
import org.clulab.asist.messages._
import org.clulab.utils.{MessageBusClient, MessageBusClientListener}

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
  val host: String = "",
  val port: String = "",
) extends DialogAgent
    with LazyLogging
    with MessageBusClientListener { 

  // CommonMsg source type for all MQTT publishing
  private val source_type: String = "message_bus"

  // communication with the MQTT Message Bus
  private val bus: MessageBusClient = new MessageBusClient(
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

  // each incoming message gets an ID number in the logfile
  var count: Int = 0

  /** Receive a message from the message bus
   * @param topic:  The message bus topic where the message was published
   * @param text:  A metadata text string, possibly multi-line
   */
  def messageArrived(
    topic: String,
    text: String 
  ): Unit = {
    count += 1

    logger.info(s"Read # ${count}: read ${topic}")
    topic match {
      case `topicSubAsr` => 
        AsrMessage(text).foreach(asr =>
          bus.publish (
            topicPubDialogAgent, 
            JsonUtils.writeJsonNoNulls(
              DialogAgentMessage(source_type, topic, asr, this)
                .copy(topic = "N/A")  // do not publish topic
            )
          )
        )
      case `topicSubChat` => 
        ChatMessage(text).foreach(chat =>
          bus.publish (
            topicPubDialogAgent, 
            JsonUtils.writeJsonNoNulls(
              DialogAgentMessage(source_type, topic, chat, this)
                .copy(topic = "N/A")
            )
          )
        )
      case `topicSubRollcallRequest` => 
        RollcallRequestMessage(text).foreach(request =>
          bus.publish(
            topicPubRollcallResponse,
            JsonUtils.writeJsonNoNulls(
              RollcallResponseMessage(uptimeSeconds, request)
                .copy(topic = "N/A")
            )
          )
        )
      case `topicSubTrial` => 
        TrialMessage(text).foreach(trial => 
          if(TrialMessage.isStart(trial)) { // Trial Start
            logger.info("trial start")
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
            logger.info("trial stop")
            // heartbeats stop producing the trial_id 
            val new_msg: CommonMsg = trial.msg.copy(trial_id = "N/A")
            heartbeatProducer.set_trial_info(trial.copy(msg = new_msg))
          }
        )
      case _ => 
    }
  }

  logger.info(s"DialogAgentMqtt version ${BuildInfo.version} running.")
}
