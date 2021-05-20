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

import java.io.File
import java.io.InputStream
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
          processYaml(toYaml(file), output)
        else 
          logger.error("File not found '%s'".format(filename))

      }
      case _ => {
        logger.error("Can't process this type of file '%s'".format(filename))
        RunYmlReader.usageText.map(line => (logger.error(line)))
      }
    }


  def toYaml(file: File): Yaml = {
    val source = Source.fromFile(file)
    val document = source.mkString
    System.out.println("YAML source document:")
    System.out.print(document)

    val yaml = new Yaml
    val map: LinkedHashMap[String, ArrayList[Rule]] = 
      yaml.load(document).asInstanceOf[LinkedHashMap[String, ArrayList[Rule]]]

    val keys = map.keySet
    System.out.println("number of keys found: %d".format(keys.size))

    val keyIter = keys.iterator

    while(keyIter.hasNext) {
      System.out.println("KEY: %s".format(keyIter.next))
    }

    val rules: ArrayList[Rule] = 
      Option(map.get("rules")).getOrElse(new ArrayList[Rule])

    System.out.println("number of rules found: %d".format(rules.size))
    val ruleIter = rules.iterator
    while(ruleIter.hasNext) {
      val rule = ruleIter.next.asInstanceOf[Rule]
      System.out.println("RULE:")
      System.out.println("  %s".format(rule.getClass.getCanonicalName))
      System.out.println
      showRule(rule)
    }
    
    yaml
  }

  def showRule(rule: Any): Unit = {
//    System.out.println(" import: %s".format(rule.get("_import")))
    System.out.println(" vars:")
//    System.out.println("   name: %s".format(rule.vars.name))
//    System.out.println("   label: %s".format(rule.vars.label))
  }



  def processYaml(yaml: Yaml, output: PrintWriter): Unit = {
  }


  this(inputFilename, outputFilename)

}
