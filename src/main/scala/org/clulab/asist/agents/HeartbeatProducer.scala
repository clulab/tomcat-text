package org.clulab.asist.agents

import akka.actor.ActorSystem
import com.typesafe.scalalogging.LazyLogging
import java.time.Clock
import org.clulab.asist.messages._

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import scala.language.postfixOps

/**
 * Authors:  Joseph Astier, Adarsh Pyarelal
 *
 * This class maintains will produce a HeartbeatMessage on a fixed delay,
 * that can be sent to the Message Bus through the owning agent.
 *
 * The heartbeat method is called on a fixed delay, always.  It will only 
 * produce a message if 'heartbeatMessageMaybe' contains a value.
 *
 * @param agent A DialogAgentMqtt connected to the Message Bus 
 */

class HeartbeatProducer(agent: DialogAgentMqtt) extends LazyLogging {

  // Actor concurrency system
  implicit val ec:ExecutionContext = ExecutionContext.global
  implicit val system: ActorSystem = ActorSystem("HeartbeatProducer")
  import system.dispatcher  // from var now in scope

  // An optional instance of a HeartbeatMessage that when defined will 
  // be published with current timestamps
  var heartbeatMessageMaybe: Option[HeartbeatMessage] = None

  // Start the beat on a fixed delay interval. The heartbeat method will be
  // called on the beat interval, always.  Whether or not a heartbeat message
  // is published depends on whether it is defined.
  val topicHeartbeat: String = agent.topicPubHeartbeat
  val startDelaySeconds: Long = 0
  val beatIntervalSeconds: Long = agent.config.getInt("DialogAgent.heartbeatSeconds")
  system.scheduler.scheduleWithFixedDelay(
    startDelaySeconds seconds, 
    beatIntervalSeconds seconds
  ) {
    new Runnable {
      def run() = heartbeat
    }
  }

  /** Start producing heartbeat messages
   *@param trialMessage Base the HeartbeatMessages on this data
   */
  def start(trialMessage: TrialMessage): Unit = {
    // setting this to a value starts the publication of HeartbeatMessages
    heartbeatMessageMaybe = Some(createHeartbeatMessage(trialMessage))
    // send a HeartbeatMessage immediately 
    heartbeat
  }

  /** Stop producing heartbeat messages */
  def stop(): Unit = {
    // setting this to None stops the publication of HeartbeatMessages
    heartbeatMessageMaybe = None
  }

  /** Will send a HeartbeatMessage if one is defined */
  def heartbeat(): Unit = {
    heartbeatMessageMaybe.foreach(publishHeartbeatMessage(_))
  }

  /** publish a HeartbeatMessage on the Message Bus
   *  @param hm A heartbeat message with Testbed Parameters
   */
  def publishHeartbeatMessage(hm: HeartbeatMessage): Unit = {
    logger.info("publishHeartbeatMessage")
    // We send the same HeartbeatMessage every time, all that changes
    // is the timestamps
    val timestamp = Clock.systemUTC.instant.toString
    val currentHeartbeatMessage = HeartbeatMessage(
      hm.header.copy(timestamp = timestamp),
      hm.msg.copy(timestamp = timestamp),
      hm.data
    )

    agent.publish(topicHeartbeat, agent.writeJson(currentHeartbeatMessage))
  }

  /** Create a HeartbeatMessage with Testbed parameters
   *  @param tm Trial start message from the Testbed
   */
  def createHeartbeatMessage(
    tm: TrialMessage
  ): HeartbeatMessage = HeartbeatMessage(
    CommonHeader(
      timestamp = "",
      message_type = "status",
      version = agent.dialogAgentVersion
    ),
    HeartbeatMessageMsg(
      timestamp = "",
      version = tm.msg.version,
      trial_id = tm.msg.trial_id,
      experiment_id = tm.msg.experiment_id
    ),
    new HeartbeatMessageData
  )
}
