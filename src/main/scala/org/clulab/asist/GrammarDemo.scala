package org.clulab.asist

import java.nio.charset.StandardCharsets.UTF_8

import org.clulab.odin.Actions
import org.clulab.odin.impl._
import org.clulab.utils.FileUtils
import org.slf4j.LoggerFactory

/**
 * Authors:  Joseph Astier, Adarsh Pyarelal
 *
 * Updated:  2021 May
 *
 * Use the ODIN infrastructure to list the Extractors, and write them
 * to the output file in Markdown language format.
 *
 * @param outputDir The results of all file processing are written to this Dir
 */
class GrammarDemo (val outputDir: String, masterPath: String){

  private lazy val logger = LoggerFactory.getLogger(this.getClass())

  private val asistEngine = new AsistEngine
  private val extractorEngine = asistEngine.engine
  private val actions: Actions = asistEngine.loadableAttributes.actions
  private val ruleReader = new RuleReader(actions, UTF_8)
  private val masterFile: String = FileUtils.getTextFromResource(masterPath)

  logger.info("Number of extractors = %s\n".format(extractorEngine.extractors.length))

  // Open the print stream and write the extractors as markdown text.
  ruleReader.exportExtractionSchemas(masterFile, s"${outputDir}/extraction_schemas.md")
  ruleReader.exportRuleSchemas(masterFile, s"${outputDir}/rule_schemas.md")

}
