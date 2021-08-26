package org.clulab.asist.agents

import com.typesafe.scalalogging.LazyLogging
import java.io.PrintWriter

/**
 * Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 * Updated:  2021 August
 *
 * Keep track of ongoing Dialog Agent input processing
 */

case class MetadataLog(
  topic: String,     // topic name
  timestamps: List[String] = List()  // CommonHeader timestamps when seen
)


object MetadataLogger extends LazyLogging {

  // true if at least one of the logs contains the topic
  def containsTopic(
    topic: String,
    logs: List[MetadataLog]
  ): Boolean = (logs.filter(log => log.topic == topic).length > 0)

  def logMetadata(
    topic: String, 
    timestamp: String, 
    logs: List[MetadataLog]
  ): List[MetadataLog] = {
    val logsWithTopic = 
      if (containsTopic(topic, logs)) logs
      else new MetadataLog(topic, List())::logs
    logMetadata(topic, timestamp, logsWithTopic, List())
  }

  def logMetadata(
    topic: String, 
    timestamp: String, 
    input: List[MetadataLog],
    output: List[MetadataLog]
  ): List[MetadataLog] = input match {
    case head::tail =>
      val newHead = if(head.topic == topic) 
          head.copy(timestamps = timestamp::head.timestamps)
        else
          head
      logMetadata(topic, timestamp, tail, newHead::output)
    case _ => output
  }

}

