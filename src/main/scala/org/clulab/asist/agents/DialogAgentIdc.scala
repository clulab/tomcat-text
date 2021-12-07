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
 * Authors:  Joseph Astier, Adarsh Pyarelal, and you
 *
 * This class reads input from the message bus on subscribed topics
 *
 * An optional TAMU Dialog Act Classifier server connection is supported
 *
 * Input and output are in json format.
 *
 * @param host MQTT host to connect to.
 * @param port MQTT network port to connect to.
 * @param tdacUrlOpt TDAC server URL and port, optional
 */

class DialogAgentIdc(
  override val host: String = "",
  override val port: String = "",
  override val tdacUrlOpt: Option[String] = None
) extends MessageBusAgent(host, port, tdacUrlOpt)
    with LazyLogging {

  logger.info(s"DialogAgentIdc version ${BuildInfo.version}")

  val source_type = "message_bus"

  // specific to IDC processing
  override def processTrialMessage(input: BusMessage): Unit = {
    logger.info("Processing trial message")
    logger.info(input.text)
    finishJob
  }

  // specific to IDC processing
  override def processChatMessage(input: BusMessage): Unit = {
    logger.info("Processing chat message")
    logger.info(input.text)
    finishJob
  }

  // specific to IDC processing
  override def processAsrMessage(input: BusMessage): Unit = {
    logger.info("Processing ASR message")
    logger.info(input.text)
    finishJob
  }
}
