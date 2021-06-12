package org.clulab.asist.apps

import ai.lum.common.ConfigFactory
import org.clulab.asist.`export`.ExtractionEvaluation
import org.clulab.odin.Mention

object RunExtractionEvaluation extends App {

  val config = ConfigFactory.load()
  val mentions: Seq[Mention] = ??? // todo
  val outputDir = config.getString("export.ruleAnnotationDir")
  ExtractionEvaluation.exportExtractionAnnotationSheets(mentions, outputDir)

}
