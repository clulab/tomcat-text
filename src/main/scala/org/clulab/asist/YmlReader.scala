/**
 * Authors:  Joseph Astier, Adarsh Pyarelal
 *
 * Updated:  2021 May
 *
 * Read .yml or .yaml rules and generate .md output
 *
 * Snake YAML implementation based on: 
 *    https://www.baeldung.com/java-snake-yaml
 *
 * Additional documentation from:
 *    https://bitbucket.org/asomov/snakeyaml/wiki/Documentation
 *
 * @param inputFilename A file or directory of files to process.
 * @param outputFilename The results of all file processing are written here
 */
package org.clulab.asist

//import shapeless.syntax.typeable._
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

// for now 
class Vars {
  val name: String = ""
  val label: String = ""
}

// for now 
class Rule {
  val _import: String = ""
  val vars: LinkedHashMap[String, String] = new LinkedHashMap
}

class YmlReader(
  val inputFilename: String = "",
  val outputFilename: String = ""
) extends FileHandler {

  private lazy val logger = LoggerFactory.getLogger(this.getClass())

  /** process one input file
   * @param filename a single input file
   * @param output Printwriter to the output file
   */
  override def processFile(filename: String, output: PrintWriter): Unit = 
    filename.substring(filename.lastIndexOf(".")) match {
      case ".yml" | ".yaml" => {
        logger.info("processing '%s' ...".format(filename))
        val file: File = new File(filename)
        if(file.exists) 
          processYaml(file)
        else 
          logger.error("File not found '%s'".format(filename))

      }
      case _ => {
        logger.error("Can't process this type of file '%s'".format(filename))
        RunYmlReader.usageText.map(line => (logger.error(line)))
      }
    }


  def processYaml(file: File): Unit = {
    val inputStream = new FileInputStream(file)
    val yaml = new Yaml
    val javaMap = yaml
      .load(inputStream)
      .asInstanceOf[java.util.LinkedHashMap[String, ArrayList[Any]]]

    val map = javaMap.asScala
    val keys = map.keySet
    System.out.println("number of keys found: %d".format(keys.size))

    val keyIter = keys.iterator

    while(keyIter.hasNext) {
      System.out.println("KEY: %s".format(keyIter.next))
    }

    showRules(map.getOrElse("rules", new ArrayList[Any]))

  }

  def showRules(rules: ArrayList[Any]): Unit = {
    System.out.println ("RULES:")
    System.out.println("number of rules found: %d".format(rules.size))
    val ruleIter = rules.iterator
    while(ruleIter.hasNext) showRule(ruleIter.next)
  }


  def showRule(rule: Any): Unit = {
    System.out.println ("RULE:")
    System.out.println (rule.toString)
    System.out.println
    /*
    val map: LinkedHashMap[String, ArrayList[Any]] = 
      yaml.load(rule).asInstanceOf[LinkedHashMap[String, ArrayList[Any]]]
    val keys = map.keySet
    System.out.println("number of keys found: %d".format(keys.size))

    val keyIter = keys.iterator

    while(keyIter.hasNext) {
      System.out.println("RULE KEY: %s".format(keyIter.next))
    }
    */
  }

  this(inputFilename, outputFilename)
}
