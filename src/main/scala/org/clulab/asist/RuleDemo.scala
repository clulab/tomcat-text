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

import java.io.File
import java.io.InputStream
import java.io.FileInputStream
import java.io.PrintWriter

import org.clulab.asist.AsistEngine._

import java.util.Collection
import java.util.LinkedHashMap
import java.util.ArrayList
import org.slf4j.LoggerFactory
import scala.io.Source
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor
import org.clulab.utils.{Configured, FileUtils}

import scala.collection.JavaConverters._

import java.io._
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets.UTF_8

import scala.io.{ Codec, Source }
import scala.reflect.ClassTag
import org.clulab.processors.Document
import org.clulab.odin.{Actions, ExtractorEngine}
import org.clulab.odin.impl.RuleReader

import java.net.URL
import java.util.{ Collection, Map => JMap }
import java.nio.charset.Charset

import org.apache.commons.text.StrSubstitutor

import scala.collection.JavaConverters._
import scala.io.{ Codec, Source }

import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.{ Constructor, ConstructorException }
import org.clulab.odin._
import org.clulab.odin.impl._
import org.clulab.odin.impl.RuleReader._

abstract class RuleDemo(
  val inputFilename: String = "",
  val outputFilename: String = ""
) extends FileHandler {

  private lazy val logger = LoggerFactory.getLogger(this.getClass())

  val charset: Charset = UTF_8
  val actions: Actions = new Actions
  val reader = new RuleReaderDemo(actions, charset)

  // 
  override def preamble(output: PrintWriter): Unit = {
    output.write("\n# PREAMBLE\n")
  }

  // override to write to the very end of the output file
  override def postamble(output: PrintWriter): Unit = {
    output.write("\n# POSTAMBLE\n")
  }


  /** process one input file
   * @param filename a single input file
   * @param output Printwriter to the output file
   */
  override def processFile(filename: String, output: PrintWriter): Unit = {
    filename.substring(filename.lastIndexOf(".")) match {
      case ".yml" | ".yaml" => {
        val source = scala.io.Source.fromFile(filename)
        val lines = source.getLines
        val str = lines.mkString("\n")
        process(filename, str, output)
        source.close
      }
      case _ => {
        logger.error("Can't process this type of file '%s'".format(filename))
        RunRuleDemo.usageText.map(line => (logger.error(line)))
      }
    }
    output.close
  }


  def process(filename: String, str: String, output: PrintWriter): Unit


  this(inputFilename, outputFilename)
}
