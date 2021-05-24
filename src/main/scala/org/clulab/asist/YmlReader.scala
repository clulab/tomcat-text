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
 * Additional reading:
 *    https://bitbucket.org/asomov/snakeyaml/wiki/Documentation
 *    https://www.javadoc.io/doc/org.yaml/snakeyaml/1.19/org/yaml/snakeyaml/Yaml.html
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
        val source = scala.io.Source.fromFile(filename)
        val lines = source.getLines
        val str = lines.mkString("\n")
        processAllYaml(str)
        source.close
      }
      case _ => {
        logger.error("Can't process this type of file '%s'".format(filename))
        RunYmlReader.usageText.map(line => (logger.error(line)))
      }
    }


  // String input from one YAML file.
  def processAllYaml(str: String): Unit = {
    val yaml = new Yaml
    val javaIterable = yaml.loadAll(str)
    val javaIterator = javaIterable.iterator
    while(javaIterator.hasNext) processYamlObject(javaIterator.next)
  }


  // a comlete YAML document that was read first pass as a java Object
  def processYamlObject(javaObj: Any): Unit = {
    val javaMap = javaObj.asInstanceOf[java.util.Map[String, Any]]
    processYamlMap(javaMap)
  }

  // This is the top-level YAML map for a given document object
  def processYamlMap(javaMap: java.util.Map[String, Any]): Unit = {
    System.out.println("YAML DOCUMENT:")
    val map = javaMap.asScala
    val keys = map.keySet

    val keyIter = keys.iterator
    while(keyIter.hasNext) {
      System.out.println("KEY: %s".format(keyIter.next))
    }

    // show the rules if there are rules to be shown
    map.get("rules").foreach(a => processYamlRules(a))
  }


  // YAML rules are an ArrayList of maps
  def processYamlRules(obj: Any): Unit = {
    val rules = obj.asInstanceOf[java.util.ArrayList[java.util.Map[String, Any]]]
    System.out.println("RULES FOUND: %d".format(rules.size))
    val iterator = rules.iterator
    while(iterator.hasNext) processYamlRule(iterator.next)
  }


  // Each YAML rule is a map.   Presumably of arbitrary depth.
  def processYamlRule(rule: java.util.Map[String, Any]): Unit = {
    System.out.println("RULE:")
    val map = rule.asScala
    val keys = map.keySet

    val keyIter = keys.iterator

    while(keyIter.hasNext) {
      val key = keyIter.next 
      val value = map.getOrElse(key, "(empty)")
      System.out.println("  %s  %s".format(key,value))
    }
  }

  this(inputFilename, outputFilename)
}
