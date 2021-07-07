package org.clulab.asist

import java.io.File
import java.nio.charset.StandardCharsets.UTF_8

import org.clulab.asist.extraction.TomcatRuleEngine
import org.clulab.odin.Actions
import org.clulab.odin.impl._
import org.clulab.utils.{FileUtils, LocalFileUtils}
import org.clulab.utils.Closer._
import org.slf4j.LoggerFactory

import org.json4s._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write}

/**
 * Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 * Updated:  2021 June
 *
 * Use the ODIN infrastructure to list the Extractors, and write them
 * to the output file in Markdown language format.
 *
 * @param outputDir The results of all file processing are written to this Dir
 */
class GrammarDemo (val outputDir: String, masterPath: String){

  private lazy val logger = LoggerFactory.getLogger(this.getClass())

  private val asistEngine = new TomcatRuleEngine
  private val extractorEngine = asistEngine.engine
  private val actions: Actions = asistEngine.loadableAttributes.actions
  private val ruleReader = new RuleReader(actions, UTF_8)
  private val masterFile: String = FileUtils.getTextFromResource(masterPath)

  logger.info("Number of extractors = %s\n".format(extractorEngine.extractors.length))

  // Open the print stream and write the extractors as markdown text.
  ruleReader.exportExtractionSchemas(masterFile, s"${outputDir}/extraction_schemas.md", minimal = true)
  val extractionSchemaObjects = ruleReader.extractionSchemaObjects(masterFile)
  val ser = write(extractionSchemaObjects)

  // Write the extraction schemas in a machine readable form (a JSON file)
  FileUtils.printWriterFromFile(new File(s"${outputDir}/extraction_schemas.json")).autoClose { pw =>
      pw.println(ser)
  }

  // Write the rule schemas to markdown
  ruleReader.exportRuleSchemas(masterFile, s"${outputDir}/rule_schemas.md")
}
