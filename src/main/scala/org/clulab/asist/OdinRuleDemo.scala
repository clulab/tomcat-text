/**
 * Authors:  Joseph Astier, Adarsh Pyarelal
 *
 * Updated:  2021 May
 *
 * Use the ODIN infrastructure to capture rules from .yml grammar files
 *
 * @param inputFilename A file or directory of files to process.
 * @param outputFilename The results of all file processing are written here
 */
package org.clulab.asist

import java.io.PrintWriter
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets.UTF_8
import org.clulab.odin.Actions
import org.clulab.odin.impl.RuleReader
import org.slf4j.LoggerFactory
import scala.util.Sorting


class OdinRuleDemo (
  val inputFile: String,
  val outputFile: String
) extends RuleDemo {

  private lazy val logger = LoggerFactory.getLogger(this.getClass())

  val charset: Charset = UTF_8
  val actions: Actions = new Actions
  val reader = new org.clulab.odin.impl.RuleReader(actions, charset)

//  if(reader == null) logger.error("Null reader")
//  else logger.info("Non-null reader")

  //
  override def preamble(output: PrintWriter): Unit = {
    output.write("# ODIN Grammar \n")
    output.write("--- \n")
  }

  // override to write to the very end of the output file
  override def postamble(output: PrintWriter): Unit = {
    output.write("\n# POSTAMBLE\n")
  }

  // String input from one YAML file.
  override def process(
    filename: String, 
    str: String, 
    output: PrintWriter
  ): Unit = {
    val foo = "**File:** %s\n".format(filename)
    logger.info(foo)
    output.write(foo)

//    Option(reader.read(str)).foreach(extractors: Vector[org.clulab.odin.impl.Extractor] => {

    Option(str).foreach(safeStr => {
      logger.info("Safe str")
      Option(reader).foreach(safeReader => {
        logger.info("Safe reader")
        Option(safeReader.read(safeStr)).foreach(extractors => {
          logger.info("Number of extractors = %s\n".format(extractors.length))
          // extractors.foreach(e => output.write(e.toString))
        })
      })
    })

//    if (filename.endsWith("master.yml")) 
//      val rules = reader.demoReadMasterFile(str)
//      showRules(filename, rules, output)
//    else reader.demoReadSimpleFile(str)

  }

  def sortByName(rules:  Seq[RuleReader.Rule]): Seq[RuleReader.Rule] = {
    object NameOrdering extends Ordering[RuleReader.Rule] {
      def compare(a: RuleReader.Rule, b: RuleReader.Rule) = a.name compare b.name
    }
    val ruleArray = rules.toArray
    Sorting.quickSort(ruleArray)(NameOrdering)
    ruleArray.toList
  }

  def showRules(filename: String, rules:  Seq[RuleReader.Rule] , output: PrintWriter): Unit = {
    val sortedRules = sortByName(rules)
    sortedRules.foreach(rule => showRule(filename, rule, output))
  }

  def showRule(
    filename: String,
    rule: RuleReader.Rule,
    output: PrintWriter
  ): Unit = {
    output.write("## %s:\n".format(rule.name))
    output.write("Key  |  Type  |  Value\n")
    output.write("-----  |  -----  |  ----\n")
    output.write("name | String | %s\n".format(rule.name))
    output.write("labels | Seq[String] | %s\n".format(rule.labels.mkString(", ")))
    output.write("ruleType | String | %s\n".format(rule.ruleType))
    output.write("unit | String | %s\n".format(rule.unit))
    output.write("priority | String | %s\n".format(rule.priority))
    output.write("keep | String | %s\n".format(rule.keep))
    output.write("action | String | %s\n".format(rule.action))
//    output.write(" pattern | String | %s\n".format(rule.pattern))
//    output.write(" config | OdinConfig | %s\n".format(rule.config))
  }

  start(inputFile, outputFile)
}
