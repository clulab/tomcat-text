/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Updated:  2021 April
 *
 *  The dialog agent will process a metadata file exactly as if it were 
 *  read from the message bus.  The supported Message structures are
 *
 *   ChatMessage
 *   UazAsrMessage
 *   AptimAsrMessage
 *
 *  @param inputFilename a file containing Messages in Json format.
 *  @param outputFilename a file containing DialogAgentMessage in Json format.
 *  @param nMatches An optional maximum number of taxonomy_matches to return
 */
package org.clulab.asist

import scala.io.Source
import java.io.PrintWriter

import org.json4s._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write}
import scala.util.control.Exception._
import org.slf4j.LoggerFactory


class DialogAgentMetadata(
    val inputFilename: String = "",
    val outputFilename: String = "", 
    override val nMatches: Int = 0
) extends DialogAgent
    with AgentFile {

  val source_type = "message_bus"
    private lazy val logger = LoggerFactory.getLogger(this.getClass())


  /** Wrangle one metadata file 
   * @param filename a single input file
   * @param output Printwriter to the output file
   * @return true if the operation succeeded
   */
  def processFile(filename: String, output: PrintWriter): Unit = {
    val bufferedSource = Source.fromFile(filename)
    val lines = bufferedSource.getLines
    while(lines.hasNext) {
      val line = lines.next
      allCatch.opt(read[MetadataLookahead](line)).map(lookahead => 
        if(inputTopics.contains(lookahead.topic)) 
          allCatch.opt(read[MetadataMessage](line)).map(metadata =>
            (lookahead.topic match {
            case `topicChat` => Some(metadata.data.sender)
            case `topicUazAsr` => Some(metadata.data.participant_id)
            case `topicAptimaAsr` => Some(metadata.data.playername)
            case _ => None
          }).map(participant_id =>
            output.write( // to file
              write( // to json
                toDialogAgentMessage( // to struct
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

  processFiles(inputFilename, outputFilename)
}
