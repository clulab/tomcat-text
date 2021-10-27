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
  private implicit val ec:ExecutionContext = ExecutionContext.global
  private implicit val system: ActorSystem = ActorSystem("HeartbeatProducer")
  import system.dispatcher  // from var now in scope

  // An optional instance of a HeartbeatMessage that when defined will 
  // be published with current timestamps. Setting this to None will stop
  // the publication of heartbeat messages
  private var heartbeatMessageMaybe: Option[HeartbeatMessage] = None

  // Start the beat on a fixed delay interval. The heartbeat message is 
  // published only if defined.
  private val topicHeartbeat: String = agent.topicPubHeartbeat
  private val startSeconds: Long = 0
  private val beatSeconds: Long = agent.config.getInt("DialogAgent.heartbeatSeconds")
  system.scheduler.scheduleWithFixedDelay(
    startSeconds seconds, 
    beatSeconds seconds
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
  def stop: Unit = {
    // setting this to None stops the publication of HeartbeatMessages
    heartbeatMessageMaybe = None
  }

  /** Will send a HeartbeatMessage if one is defined */
  private def heartbeat: Unit = 
    heartbeatMessageMaybe.foreach(publishHeartbeatMessage(_))

  /** Return a string representing the current time */
  private def now: String = Clock.systemUTC.instant.toString

  /** publish the HeartbeatMessage with current time to the Message Bus
   *  @param hm A heartbeat message with Testbed Parameters
   */
  private def publishHeartbeatMessage(hm: HeartbeatMessage): Unit = 
    agent.publish(topicHeartbeat, agent.writeJson(copyHeartbeatMessage(hm, now)))

  /** return a copy of the heartbeat message with replaced timestamps
   *  @param hm The HeartbeatMessage to copy
   *  @param timestamp the new timestamp to use
   */
  private def copyHeartbeatMessage(
    hm: HeartbeatMessage, 
    timestamp: String
  ): HeartbeatMessage = HeartbeatMessage(
      hm.header.copy(timestamp = timestamp),
      hm.msg.copy(timestamp = timestamp),
      hm.data
    )

  /** Create a HeartbeatMessage with Testbed parameters
   *  @param tm Trial start message from the Testbed
   */
  private def createHeartbeatMessage(
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
