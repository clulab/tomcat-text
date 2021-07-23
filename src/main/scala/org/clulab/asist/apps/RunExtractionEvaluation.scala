package org.clulab.asist.apps

import ai.lum.common.ConfigFactory
import org.clulab.asist.`export`.ExtractionEvaluation
import org.clulab.asist.agents.DialogAgentReprocessor
import org.clulab.asist.messages.DialogAgentMessage
import org.clulab.odin.Mention
import org.clulab.utils.LocalFileUtils
import org.slf4j.LoggerFactory

import scala.io.Source

object RunExtractionEvaluation extends App {

  private lazy val logger = LoggerFactory.getLogger(this.getClass())
  val config = ConfigFactory.load()
  val inputDir = config.getString("DialogAgent.inputDir")
  val agent = new DialogAgentReprocessor(inputDir, "/dev/null")
  val files = LocalFileUtils.getFileNames(inputDir)
  val mentions = for {
    filename <- files.par
    mention <- getMentionsFromFile(filename)
  } yield mention

  val outputDir = config.getString("export.ruleAnnotationDir")
  ExtractionEvaluation.exportExtractionAnnotationSheets(mentions.seq, outputDir)


  def getMentionsFromFile(filename: String): Seq[Mention] = {
    logger.info(s"Loading mentions from file: $filename")
    val bufferedSource = Source.fromFile(filename)
    val lines = bufferedSource.getLines()
    val mentions = for {
      line <- lines
      msg = DialogAgentMessage.readDialogAgentMessage(line)
      txt = msg.data.text
      mention <- agent.extractMentions(txt)
    } yield mention

    mentions.toVector
  }

}
