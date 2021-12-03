package org.clulab.asist.agents

import ai.lum.common.ConfigFactory
import org.clulab.utils.{MessageBusClient, MessageBusClientListener}
import org.clulab.asist.messages._
import com.typesafe.config.Config
import com.typesafe.scalalogging.LazyLogging
import buildinfo.BuildInfo


/**
 * Authors:  Joseph Astier, Adarsh Pyarelal
 *
 * This class reads input from the message bus on subscribed topics
 *
 * Input and output are expected in json format.
 *
 * @param mqttHost MQTT network host to connect to.
 * @param mqttPort MQTT network port to connect to.
 */

class DialogAgentMqttCoordinator(
  val host: String = "",
  val port: String = "",
  val tdacUrlOpt: Option[String] = None
) extends LazyLogging {

  logger.info(s"DialogAgentMqttCoordinator version ${BuildInfo.version}")

  val agents: List[MqttAgentBase] = List(
    new MqttAgentTrial(host, port),
    new MqttAgentChat(host, port),
    new MqttAgentSpeech(host, port),
    new MqttAgentIdc(host, port)
  )
}

abstract class MqttAgentBase(
  val mqttHost: String = "",
  val mqttPort: String = ""
) extends LazyLogging with MessageBusClientListener {

  val config: Config = ConfigFactory.load()

  // A single Message Bus message
  case class BusMessage (
    topic: String,
    text: String // may contain newlines
  )

  def subTopic: String = ""
  def pubTopic: String = ""

  logger.info(s"Subscribed to ${subTopic}")
  logger.info(s"Publishing on ${pubTopic}")
}

/** support production of VersionInfo messages */
class MqttAgentHeartbeat(
  override val mqttHost: String = "",
  override val mqttPort: String = ""
) extends MqttAgentBase {

  override def subTopic = "TrialSubTopic"
  override def pubTopic = config.getString("DialogAgent.heartbeatTopic")

  /** Receive a message from the message bus
   * @param topic:  The message bus topic where the message was published
   * @param text:  A metadata text string, possibly multi-line
   */
  def messageArrived(
    topic: String,
    text: String
  ): Unit = {
    logger.info("messageArrived")
  }
}

/** support production of VersionInfo messages */
class MqttAgentTrial(
  override val mqttHost: String = "",
  override val mqttPort: String = ""
) extends MqttAgentBase {

  override def subTopic = "TrialSubTopic"
  override def pubTopic = config.getString("DialogAgent.versionInfoTopic")

  /** Receive a message from the message bus
   * @param topic:  The message bus topic where the message was published
   * @param text:  A metadata text string, possibly multi-line
   */
  def messageArrived(
    topic: String,
    text: String
  ): Unit = {
    logger.info("messageArrived")
  }
}

class MqttAgentChat(
  override val mqttHost: String = "",
  override val mqttPort: String = ""
) extends MqttAgentBase {

  override def subTopic = "ChatSubTopic"
  override def pubTopic = config.getString("DialogAgent.outputTopic")

  /** Receive a message from the message bus
   * @param topic:  The message bus topic where the message was published
   * @param text:  A metadata text string, possibly multi-line
   */
  def messageArrived(
    topic: String,
    text: String
  ): Unit = {
    logger.info("messageArrived")
  }
}

class MqttAgentSpeech(
  override val mqttHost: String = "",
  override val mqttPort: String = ""
) extends MqttAgentBase {

  override def subTopic = "SpeechSubTopic"
  override def pubTopic = config.getString("DialogAgent.outputTopic")

  /** Receive a message from the message bus
   * @param topic:  The message bus topic where the message was published
   * @param text:  A metadata text string, possibly multi-line
   */
  def messageArrived(
    topic: String,
    text: String
  ): Unit = {
    logger.info("messageArrived")
  }
}


class MqttAgentIdc(
  override val mqttHost: String = "",
  override val mqttPort: String = ""
) extends MqttAgentBase {

  override def subTopic = "IdcSubTopic"
  override def pubTopic = config.getString("DialogAgent.outputTopic")

  /** Receive a message from the message bus
   * @param topic:  The message bus topic where the message was published
   * @param text:  A metadata text string, possibly multi-line
   */
  def messageArrived(
    topic: String,
    text: String
  ): Unit = {
    logger.info("messageArrived")
  }
}


