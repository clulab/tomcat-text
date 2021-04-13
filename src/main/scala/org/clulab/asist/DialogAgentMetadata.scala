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
import java.io.{File, PrintWriter}
import org.slf4j.LoggerFactory
import spray.json.DefaultJsonProtocol._
import spray.json.JsonParser

class DialogAgentMetadata(
    val inputFilename: String = "",
    val outputFilename: String = "", 
    override val nMatches: Int = 0
) extends DialogAgent 
    with DialogAgentJson 
    with AgentFile {

  val topicChat: String = "minecraft/chat"
  val topicUazAsr: String = "agent/asr/final"
  val topicAptimaAsr: String = "status/asistdataingester/userspeech"

  val source_type = "metadata_file"

  private lazy val logger = LoggerFactory.getLogger(this.getClass())

  logger.info("Using input file '%s'".format(inputFilename))
  logger.info("Using output file '%s'".format(outputFilename))

  /** Wrangle one metadata file 
   * @param filename a single input file
   * @param output Printwriter to the output file
   * @return true if the operation succeeded
   */
  def processFile(filename: String, output: PrintWriter): Boolean = try {
    logger.info("Reading input file %s".format(filename))
    val bufferedSource = Source.fromFile(filename)
    bufferedSource.getLines.foreach(line => processLine(line, output))
    bufferedSource.close
    logger.info("Closed input file %s".format(filename))
    true
  } catch {
    case t: Throwable => {
      logger.error("Problem reading input file '%s'".format(filename))
      logger.error(t.toString)
      false
    }
  }

  def processLine(json: String, output: PrintWriter): Unit = 
    toMetadataMessage(json).foreach(metadata => 
      toMessage(metadata).foreach(message =>
        output.write("%s\n".format(toJson(message)))
      )   
    )


  /** The participant_id field is different in each type */
  def participant_id(md: MetadataMessage): Option[String] = md.topic match {
    case `topicChat` => Some(md.data.sender)
    case `topicUazAsr` => Some(md.data.participant_id)
    case `topicAptimaAsr` => Some(md.data.playername)
    case _ => None
  }


  /** publish the Dialog Agent analysis of message data
   *  @param topic: The message bus topic where the message was published
   *  @param json:  A json representation of a message struct
   */
  def toMessage(md: MetadataMessage): Option[DialogAgentMessage] = 
    participant_id(md).map(id => toDialogAgentMessage( 
      source_type,
      md.topic,
      md.msg,
      id,
      md.data.text
    )
  )
}
