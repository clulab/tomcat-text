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
 * @param idc Run the IdcWorker class if true
 */

class DialogAgentMqtt(
  override val host: String = "",
  override val port: String = "",
  override val tdacUrlOpt: Option[String] = None,
  val idc: Boolean = false
) extends MessageBusAgent(host, port, tdacUrlOpt)
    with LazyLogging {

  logger.info(s"DialogAgentMqtt version ${BuildInfo.version}")

  // Testbed heartbeat
  val heartbeatProducer = new HeartbeatProducer(this)

  val source_type = "message_bus"

  // create the IDC worker if required
  val idcWorker: Option[IdcWorker] = 
    if(idc) Some(new IdcWorker(this)) else None

  /** send VersionInfo if we receive a TrialMessage with subtype "start", 
   * @param input: Message bus traffic with topic and text
   */
  override def processTrialMessage(input: BusMessage): Unit = try {
    val trialMessage = read[TrialMessage](input.text)
    trialMessage.msg.sub_type match {

      // trial start message, reset the TDAC and start heartbeat
      case "start" =>
        idcWorker.foreach(_.reset)
        val currentTimestamp = Clock.systemUTC.instant.toString
        val versionInfo = VersionInfo(config, trialMessage, currentTimestamp)
        val outputJson = write(versionInfo)
        val output: BusMessage = BusMessage(topicPubVersionInfo,outputJson)
        tdacClient match {
          case Some(tc: TdacClient) =>
            tc.resetServer(List(output))
          case None =>  // no TDAC
            writeOutput(List(output))
            finishJob 
        }
        heartbeatProducer.start(trialMessage)

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

  override def processChatMessage(input: BusMessage): Unit = 
    processDialogAgentMessage(input)

  override def processAsrMessage(input: BusMessage): Unit = 
    processDialogAgentMessage(input)

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
    idcWorker.foreach(_.enqueue(input.topic, message.data.extractions))
    tdacClient match {
      case Some(tc: TdacClient) =>
        val metadataJValue = parse(input.text)
        tc.runClassification(
          topicPubDialogAgent,
          input.text,
          message.data,
          metadataJValue
        )
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
}
