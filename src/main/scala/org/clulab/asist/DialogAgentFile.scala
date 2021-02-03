/**
 *  Author:  Joseph Astier, Adarsh Pyarelal
 *  Updated:  2021 January
 *
 *  A file-compatible DialogAgent that will take over the roles
 *  of ExtractDirSearch and ExtractInfoSearch
 *
 */
package org.clulab.asist

import java.time.Clock
import org.json4s._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write}
import org.json4s.Xml.{toJson, toXml}
import org.slf4j.LoggerFactory
import scala.util.control.Exception._
import org.clulab.odin.{EventMention, Mention, TextBoundMention}


/** coordinator class for all things chatbot */
class DialogAgentFile(
  val inputFilename: String = "", 
  val outputFilename: String = ""
  ) extends DialogAgent {

  private val logger = LoggerFactory.getLogger(this.getClass())

  logger.info("In startup")

  /** Create the text analysis pipeline */
  logger.info("Creating text processor (this may take a few seconds) ...")

  /** Translate an AsrMessage to a DialogAgentMessage  */
  def toDialogAgentMessage(
    a: AsrMessage,
    topic: String): DialogAgentMessage =
      toDialogAgentMessage(a, topic, inputFilename)

  /** Translate an ObsMessage to a DialogAgentMessage  */
  def toDialogAgentMessage(
    a: ObsMessage,
    topic: String): DialogAgentMessage =
      toDialogAgentMessage(a, topic, inputFilename)


  /** read the input file */
  def readInput(input: String): Unit =  {
  }

  /** process each line of the input file */
  def processInput(input: String): Unit =  {
    logger.info(input)
    /*
    topic match {
      case `topicSubObs` => allCatch.opt {
        read[ObsMessage](input)
      }.map(a => publish(toDialogAgentMessage(a, topic)))
      case `topicSubAsr` => allCatch.opt {
        read[AsrMessage](input)
      }.map(a => publish(toDialogAgentMessage(a, topic)))
      case _ =>
    }  */
  }
}
