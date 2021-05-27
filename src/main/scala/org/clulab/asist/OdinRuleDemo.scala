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


import java.io._
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.io.PrintWriter
import java.net.URL
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets.UTF_8
import java.util.ArrayList
import java.util.Collection
import java.util.{ Collection, Map => JMap }
import java.util.LinkedHashMap
import org.apache.commons.text.StrSubstitutor
import org.clulab.asist.AsistEngine._
import org.clulab.odin._
import org.clulab.odin.{Actions, ExtractorEngine}
import org.clulab.odin.impl._
import org.clulab.odin.impl.RuleReader
import org.clulab.odin.impl.RuleReader._
import org.clulab.processors.Document
import org.clulab.utils.{Configured, FileUtils}
import org.slf4j.LoggerFactory
import org.yaml.snakeyaml.constructor.Constructor
import org.yaml.snakeyaml.constructor.{ Constructor, ConstructorException }
import org.yaml.snakeyaml.Yaml
import scala.collection.JavaConverters._
import scala.io.{ Codec, Source }
import scala.io.Source
import scala.reflect.ClassTag
import scala.util.Sorting


class OdinRuleDemo (
  override val inputFilename: String = "",
  override val outputFilename: String = ""
) extends RuleDemo (inputFilename,outputFilename) {

  private lazy val logger = LoggerFactory.getLogger(this.getClass())



  // String input from one YAML file.
  override def process(
    filename: String, 
    str: String, 
    output: PrintWriter
  ): Unit = {
    logger.info("Processing %s\n".format(filename))

//    if (filename.endsWith("master.yml")) 
      val rules = reader.demoReadMasterFile(str)
      showRules(rules, output)
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

  def showRules(rules:  Seq[RuleReader.Rule] , output: PrintWriter): Unit = {
    val sortedRules = sortByName(rules)
    sortedRules.foreach(rule => showRule(rule, output))
  }

  def formatLabels(labels: Seq[String]): String = {
    val foo = labels.map(str => List("\"",str,"\"").mkString)
    foo.mkString(", ")
  }

  def showRule(rule: RuleReader.Rule, output: PrintWriter): Unit = {
    output.write("## %s:\n".format(rule.name))
    output.write("Key  |  Type  |  Value\n")
    output.write("-----  |  -----  |  ----\n")
    output.write("name | String | %s\n".format(rule.name))
    output.write("labels | Seq[String] | %s\n".format(formatLabels(rule.labels)))
    output.write("ruleType | String | %s\n".format(rule.ruleType))
    output.write("unit | String | %s\n".format(rule.unit))
    output.write("priority | String | %s\n".format(rule.priority))
    output.write("keep | String | %s\n".format(rule.keep))
    output.write("action | String | %s\n".format(rule.action))
//    output.write(" pattern | String | %s\n".format(rule.pattern))
//    output.write(" config | OdinConfig | %s\n".format(rule.config))
  }


  this(inputFilename, outputFilename)
}
