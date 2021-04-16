/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Updated:  2021 April
 *
 *  This trait is to group file processing functionality in one place.
 *
 */
package org.clulab.asist

import com.crowdscriber.caption.common.Vocabulary.SubtitleBlock
import com.crowdscriber.caption.vttdissector.VttDissector
import java.io.{FileInputStream, File, PrintWriter}
import org.json4s._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write}
import org.slf4j.LoggerFactory
import scala.io.Source
import scala.util.control.Exception._
import scala.util.{Failure, Success}
import java.io.{FileInputStream, File, PrintWriter}
import org.json4s._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write}
import org.slf4j.LoggerFactory
import scala.io.Source
import scala.util.control.Exception._
import scala.util.{Failure, Success}



object AgentFileMetadata {
  val source_type = "message_bus"

  private lazy val logger = LoggerFactory.getLogger(this.getClass())

  // Used so Json serializers can recognize case classes
  implicit val formats = Serialization.formats(NoTypeHints)

  /** Wrangle one metadata file
   * @param filename a single input file
   * @param output Printwriter to the output file
   * @return true if the operation succeeded
   */

  def apply(filename: String, agent: DialogAgent, output: PrintWriter): Unit = {
    val bufferedSource = Source.fromFile(filename)
    val lines = bufferedSource.getLines
    while(lines.hasNext) {
      val line = lines.next
      allCatch.opt(read[MetadataLookahead](line)).map(lookahead =>
        if(agent.inputTopics.contains(lookahead.topic))
          allCatch.opt(read[MetadataMessage](line)).map(metadata =>
            (lookahead.topic match {
            case agent.topicChat => Some(metadata.data.sender)
            case agent.topicUazAsr => Some(metadata.data.participant_id)
            case agent.topicAptimaAsr => Some(metadata.data.playername)
            case _ => None
          }).map(participant_id =>
            output.write( // to file
              write( // to json
                agent.toDialogAgentMessage( // to struct
                  source_type,
                  filename,
                  metadata.msg,
                  participant_id,
                  metadata.data.text
                )
              )
            )
          )
        )
      )
    }
    bufferedSource.close
  }
}
