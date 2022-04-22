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
 * Publishes a Heartbeat to the message bus on a regular interval
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

  // The heartbeats are a copy of this HeartbeatMessage with 
  // current timestamps
  private var base: HeartbeatMessage = HeartbeatMessage()

  // Start the beat on a fixed interval. 
  system.scheduler.scheduleWithFixedDelay(
    startSeconds seconds, 
    beatSeconds seconds
  ) {
    new Runnable {
      def run() = beat
    }
  }

  /** redefine the base heartbeat with information from each trial
   *@param trial The current Testbed trial
   */
  def set_trial_info(trial: TrialMessage): Unit = {
    base = HeartbeatMessage(trial)
  }

  // publish the heartbeat message 
  private def beat: Unit = {
      val currentHeartbeat = HeartbeatMessage(
        base,
        Clock.systemUTC.instant.toString
      )
      val json = JsonUtils.writeJson(currentHeartbeat)
      agent.writeOutput(topic, json)
  }

  logger.info(s"Heartbeat publication topic: ${topic}")
  logger.info(s"Heartbeat interval seconds: ${beatSeconds}")

}
