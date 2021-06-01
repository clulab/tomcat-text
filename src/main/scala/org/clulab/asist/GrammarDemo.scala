/**
 * Authors:  Joseph Astier, Adarsh Pyarelal
 *
 * Updated:  2021 May
 *
 * Use the ODIN infrastructure to list the Extractors, and write them
 * to the output file in Markdown language format.
 *
 * @param outputFile The results of all file processing are written here
 */
package org.clulab.asist

import java.io.{File, PrintWriter}
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets.UTF_8
import org.clulab.odin.Actions
import org.clulab.odin.impl._
import org.clulab.odin.impl.{Extractor => OdinExtractor}
import org.slf4j.LoggerFactory
import scala.util.Sorting


class GrammarDemo (val outputFile: String){ 

  private lazy val logger = LoggerFactory.getLogger(this.getClass())

  val asistEngine = new AsistEngine

  val extractorEngine = asistEngine.engine

  val extractors = sortByName(extractorEngine.extractors)

  logger.info("Number of extractors = %s\n".format(extractors.size))
    
  // Open the print stream and write the extractors as markdown text.
  Option(new PrintWriter(new File(outputFile))) match {
    case Some(output: PrintWriter) => {
      output.write("# ODIN Extraction Schemas\n\n")
      val strings = extractors.map(extractorToString)
      strings.foreach(string => output.write(string))
      output.close
    }
    case _ => logger.error("Could not open file '%s' for writing".format(outputFile))
  }

  def sortByName(extractors:  Seq[OdinExtractor]): List[OdinExtractor] = {
    object NameOrdering extends Ordering[OdinExtractor] {
      def compare(a:OdinExtractor , b:OdinExtractor ) = a.name compare b.name
    }
    val extractorArray = extractors.toArray
    Sorting.quickSort(extractorArray)(NameOrdering)
    extractorArray.toList
  }


  def extractorToString(extractor: OdinExtractor): String = {
    val preamble = List(
      "## %s".format(extractor.name),
      "Attribute  |  Type  |  Value",
      "-----  |  -----  |  ----",
    )
    val lines = extractor match {
      case x: CrossSentenceExtractor => crossSentenceExtractor(x)
      case x: TokenExtractor => tokenExtractor(x)
      case x: GraphExtractor => graphExtractor(x)
      case _ => List()
   }
    val list = List(preamble, lines).flatten
    list.mkString("\n")
  }


  def crossSentenceExtractor(
    x: CrossSentenceExtractor
  ): List[String] = List(
    "type | Extractor | CrossSentenceExtractor",
    "labels | Seq[String] | [%s]".format(x.labels.mkString(", ")),
    "priority | Prority | %s".format(x.priority),
    "keep | Boolean | %s".format(x.keep),
    "action | Action | %s".format(x.action.toString),
    "leftWindow | Int | %s".format(x.leftWindow),
    "rightWindow | Int | %s".format(x.rightWindow),
    "anchorPattern | TokenExtractor | %s".format(x.anchorPattern),
    "neighborPattern | TokenExtractor | %s".format(x.neighborPattern),
    "anchorRole | String | %s".format(x.anchorRole),
    "neighborRole | String | %s".format(x.neighborRole),
    ""
  )


  def tokenExtractor(x: TokenExtractor): List[String] = List(
    "type | Extractor | TokenExtractor",
    "labels | Seq[String] | [%s]".format(x.labels.mkString(", ")),
    "priority | Prority | %s".format(x.priority),
    "keep | Boolean | %s".format(x.keep),
    "action | Action | %s".format(x.action.toString),
    "pattern | TokenPattern | %s".format(x.pattern.toString),
    ""
  )


  def graphExtractor(
    x: GraphExtractor
  ): List[String] = {
    val taxonomy: String = x.config.taxonomy match {
      case Some(t: Taxonomy) => "Some"
      case _ => "None"
    }
    val table = List(
      "type | Extractor | GraphExtractor",
      "labels | Seq[String] | [%s]".format(x.labels.mkString(", ")),
      "priority | Prority | %s".format(x.priority),
      "keep | Boolean | %s".format(x.keep),
      "action | Action | %s".format(x.action.toString),
      "pattern | GraphPattern | %s".format(x.pattern.toString),
      "taxonomy | Option[Taxonomy] | %s".format(taxonomy),
      "graph | String | %s".format(x.config.graph),
      "resources | OdinResourceManager | %s".format(x.config.resources),
      ""
    )
    val configSummary = mapSummary(x.config.variables)
    List(table, configSummary).flatten
  }


  // write the map entries within a dropdown summary
  def mapSummary(map: Map[String, String]): List[String] = {
    val preamble = List (
      "<details>",
      "<summary>Config variables</summary>",
      "",
      "Atribute | Type | Value",
      "----|----|----",
    )
    val keys = map.keys
    val body = keys.map(key => {
      "%s | String | %s".format(key, map(key))
    }).toList

    val postamble = List (
      "",
      "",
      "</details>",
      "",
      ""
    )

    List(preamble, body, postamble).flatten
  }
}
