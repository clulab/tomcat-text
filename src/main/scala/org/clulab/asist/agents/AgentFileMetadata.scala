package org.clulab.asist

import java.io.PrintWriter

import org.clulab.asist.agents.DialogAgent
import org.clulab.asist.messages._
import org.json4s._
import org.json4s.jackson.Serialization.{read, write}

import scala.io.Source
import scala.util.control.Exception._

/**
 * Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 * Updated:  2021 June
 *
 * Handle parsing of .metadata files into DialogAgentMessage json
 *
 */

object AgentFileMetadata {
  val source_type = "message_bus"

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
        if(agent.topicSubTrial == lookahead.topic) {
          allCatch.opt(read[TrialMessage](line)).map(trialMessage => {
            if(trialMessage.msg.sub_type == "start") {
              output.write(write(AgentVersionInfo(agent)))
            }
          })
        }
        else if(agent.subscriptions.contains(lookahead.topic))
          allCatch.opt(read[Metadata](line)).map(metadata =>
            output.write( // to file
              write( // to json
                agent.dialogAgentMessage( // to struct
                  source_type,
                  filename,
                  lookahead.topic,
                  metadata
                )
              )
            )
          )
      )
    }
    bufferedSource.close
  }
}
