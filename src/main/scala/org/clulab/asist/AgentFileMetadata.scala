/**
 * Authors:  Joseph Astier, Adarsh Pyarelal
 *
 * Updated:  2021 April
 *
 * Handle parsing of .metadata files into DialogAgentMessage json
 *
 */
package org.clulab.asist

import java.io.PrintWriter
import org.json4s._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write}
import scala.io.Source
import scala.util.control.Exception._

object AgentFileMetadata {
  val source_type = "message_bus"

  // Used so Json serializers can recognize case classes
  implicit val formats = Serialization.formats(NoTypeHints)

  /** Wrangle one metadata file
   * @param filename a single input file
   * @param agent Agent to perform analysis
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
          allCatch.opt(read[Metadata](line)).map(metadata =>
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
