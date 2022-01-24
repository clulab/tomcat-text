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
  import system.dispatcher  // from system now in scope

  // An optional instance of a HeartbeatMessage with all fields initialized 
  // except the timestamps.  If defined, this gets used as a base for
  // the creation of heartbeat messages.  Setting it to None will stop
  // the publication of heartbeat messages
  private var base: Option[HeartbeatMessage] = None

  // Start the beat on a fixed interval. The beat is always running,
  // but the heartbeat message is only published if the base is defined.
  system.scheduler.scheduleWithFixedDelay(
    startSeconds seconds, 
    beatSeconds seconds
  ) {
    new Runnable {
      def run() = beat
    }
  }

  /** Define the base to start publishing heartbeats
   *@param trial The current Testbed trial
   */
  def start(trial: TrialMessage): Unit = {
    base = Some(HeartbeatMessage(trial))
  }

  /** Undefine the base to stop publishing heartbeats */
  def stop: Unit = {
    base = None
  }

  // publish the heartbeat message if defined
  private def beat: Unit = base.foreach {
    hm: HeartbeatMessage =>
      val currentHeartbeat = HeartbeatMessage(
        hm,
        Clock.systemUTC.instant.toString
      )
      val json = JsonUtils.writeJson(currentHeartbeat)
      agent.writeOutput(topic, json)
  }

  logger.info(s"Heartbeat publication topic: ${topic}")
  logger.info(s"Heartbeat interval seconds: ${beatSeconds}")
}
