/**
 * Authors:  Joseph Astier, Adarsh Pyarelal
 *
 * Updated:  2021 April
 *
 * Process a file or the first level of a directory of files.
 *
 * @param inputFilename A file or directory of files to process.
 * @param outputFilename The results of all file processing are written here
 */
package org.clulab.asist

import java.io.File
import java.io.PrintWriter
import java.util.Collection
import org.slf4j.LoggerFactory
import scala.io.Source
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor

class YmlReader(
  val inputFilename: String = "",
  val outputFilename: String = "",
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
    val string = source.mkString
    val yaml = new Yaml(new Constructor(classOf[Collection[Any]]))
    yaml.load(string).asInstanceOf[Collection[Any]]
    yaml
  }

  def processYaml(yaml: Yaml, output: PrintWriter): Unit = {
    output.write(yaml.toString)
  }


  this(inputFilename, outputFilename)
}
