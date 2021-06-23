package org.clulab.asist.apps

import ai.lum.common.ConfigFactory
import org.clulab.asist.`export`.ExtractionEvaluation
import org.clulab.asist.agents.DialogAgentReprocess
import org.clulab.asist.messages.DialogAgentMessage
import org.clulab.odin.Mention
import org.clulab.utils.LocalFileUtils

import scala.io.Source

object RunExtractionEvaluation extends App {

  val config = ConfigFactory.load()
  val inputDir = config.getString("DialogAgent.inputDir")
  val agent = new DialogAgentReprocess(inputDir, "/dev/null")
  val files = LocalFileUtils.getFileNames(inputDir)
  val mentions = for {
    filename <- files.par
    mention <- getMentionsFromFile(filename)
  } yield mention

  val outputDir = config.getString("export.ruleAnnotationDir")
  ExtractionEvaluation.exportExtractionAnnotationSheets(mentions.seq, outputDir)


  def getMentionsFromFile(filename: String): Seq[Mention] = {
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
