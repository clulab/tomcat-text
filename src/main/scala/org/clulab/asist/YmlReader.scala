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
import org.slf4j.LoggerFactory
import scala.io.Source
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor
import org.clulab.utils.{Configured, FileUtils}


// for now 
class Rule{
  val name: String = ""
  val label: String = ""
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
    val map: LinkedHashMap[String, Any] = 
      yaml.load(document).asInstanceOf[LinkedHashMap[String, Any]]

    val keys = map.keySet
    System.out.println("number of keys found: %d".format(keys.size))

    val keyIter = keys.iterator

    while(keyIter.hasNext) {
      System.out.println("KEY: %s".format(keyIter.next))
    }

    yaml
  }


  def processYaml(yaml: Yaml, output: PrintWriter): Unit = {
  }


  this(inputFilename, outputFilename)

}
