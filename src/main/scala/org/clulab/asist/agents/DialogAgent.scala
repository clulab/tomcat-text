package org.clulab.asist.agents

import java.time.Clock
import com.typesafe.scalalogging.LazyLogging
import org.clulab.asist.extraction.TomcatRuleEngine
import org.clulab.asist.messages._
import org.clulab.odin.Mention
import org.clulab.processors.Document

import scala.util.control.NonFatal

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 *  Create extractions from text for the ToMCAT project.
 *
 *  https://github.com/clulab/tomcat-text/blob/master/README.md
 *
 */

class DialogAgent (
  val ruleEngine: TomcatRuleEngine = new TomcatRuleEngine
) extends LazyLogging {

  // Used to compute agent uptime
  val runtimeStart:Long = Clock.systemUTC.millis

  // Run the rule engine to get its lazy init out of the way
  def startEngine(){
    logger.info("Initializing Extractor (this may take a few seconds) ...")
    ruleEngine.extractFrom("I see a green victim")
    logger.info("Extractor initialized.")
  }

  /**
   * @return How long the agent has been running in seconds
   */
  def uptimeSeconds(): Double = {
    val uptimeMillis: Long = Clock.systemUTC.millis - runtimeStart
    uptimeMillis / 1000.0
  }

  /**
   * Extract Odin mentions from text.
   * @param text String text to extract from, can be multiple sentences.
   * @return sequence of Odin Mentions
   */
  def extractMentions(text: String): Seq[Mention] = ruleEngine
    .extractFrom(Option(text).getOrElse(""), keepText = true)
    .sortBy(m => (m.sentence, m.label))

  /**
   * Extract Odin mentions from text.
   * @param doc Document to extract from, can be multiple sentences.
   * @return sequence of Odin Mentions
   */
  def extractMentions(doc: Document): Seq[Mention] = ruleEngine
    .extractFrom(doc)
    .sortBy(m => (m.sentence, m.label))

  /** Return an array of all extractions found in the input text
   *  @param text Text to analyze
   */
  def getExtractions(
    text: String
  ): Seq[DialogAgentMessageUtteranceExtraction] = 
    extractMentions(text).map(getExtraction)

  /** Map all of the mentions into extractions
   *  @param mentions To be mapped into extractions
   */
  def getExtractions(
    mentions: Seq[Mention]
  ): Seq[DialogAgentMessageUtteranceExtraction] = 
    mentions.map(getExtraction)

  /** Create a DialogAgent extraction from Extractor data 
   *  @param mention Contains text to analyze
   */
  def getExtraction(
    mention: Mention
  ): DialogAgentMessageUtteranceExtraction = {
    val originalArgs = mention.arguments.toArray
    val extractionArguments = for {
      (role, ms) <- originalArgs
      converted = ms.map(getExtraction)
    } yield (role, converted)
    DialogAgentMessageUtteranceExtraction(
      mention.labels,
      mention.words.mkString(" "),
      extractionArguments.toMap,
      mention.attachments,
      mention.startOffset,
      mention.endOffset,
      mention.foundBy,
    )
  }
}
