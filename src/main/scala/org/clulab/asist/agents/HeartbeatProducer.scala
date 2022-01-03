package org.clulab.asist.agents

import akka.actor.ActorSystem
import com.typesafe.config.Config
import com.typesafe.scalalogging.LazyLogging
import java.time.Clock
import org.clulab.asist.messages._

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext
import scala.language.postfixOps


/**
 * Authors:  Joseph Astier, Adarsh Pyarelal
 *
 * Publishes a Heartbeat to the message bus on a regular interval. 
 *
 * @param agent A DialogAgentMqtt connected to the Message Bus 
 */

class HeartbeatProducer(agent: DialogAgentMqtt) extends LazyLogging {

  private val config: Config = agent.config
  private val topic: String = config.getString("Heartbeat.topic")
  private val startSeconds: Long = 0
  private val beatSeconds: Long = config.getInt("Heartbeat.beat_seconds")

  // Actor concurrency system
  private implicit val ec:ExecutionContext = ExecutionContext.global
  private implicit val system: ActorSystem = ActorSystem("HeartbeatProducer")
  import system.dispatcher  // from var now in scope

  // An optional instance of a HeartbeatMessage with all fields initialized 
  // except the timestamps.  If defined, this gets used as a template for
  // the creation of heartbeat messages.  Setting it to None will stop
  // the publication of heartbeat messages
  private var templateMessage: Option[HeartbeatMessage] = None

  // Start the beat on a fixed interval. The beat is always running,
  // but the heartbeat message is published only if the template is defined.
  system.scheduler.scheduleWithFixedDelay(
    startSeconds seconds, 
    beatSeconds seconds
  ) {
    new Runnable {
      def run() = publishHeartbeat
    }
  }

  /** Define the template to start publishing heartbeats
   *@param tm The current Testbed trial
   */
  def start(trialMessage: TrialMessage): Unit = {
    templateMessage = Some(HeartbeatMessage(config, trialMessage))
  }

  /** Undefine the template to stop publishing heartbeats */
  def stop: Unit = {
    templateMessage = None
  }

  /** If the template is defined, pulish a copy with correct timestamps */
  private def publishHeartbeat: Unit = templateMessage.foreach {
    hm: HeartbeatMessage =>
      val timestamp = Clock.systemUTC.instant.toString
      val currentHeartbeat = HeartbeatMessage(
        hm.header.copy(timestamp = timestamp),
        hm.msg.copy(timestamp = timestamp),
        hm.data
      )
      val json = JsonUtils.writeJson(currentHeartbeat)
      agent.publish(topic, json)
  }
}
