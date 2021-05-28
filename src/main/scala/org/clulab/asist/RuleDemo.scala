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
import org.slf4j.LoggerFactory

abstract class RuleDemo extends FileHandler {

  private lazy val logger = LoggerFactory.getLogger(this.getClass())

  /** process one input file
   * @param filename a single input file
   * @param output Printwriter to the output file
   */
  override def processFile(filename: String, output: PrintWriter): Unit = {
    filename.substring(filename.lastIndexOf(".")) match {
      case ".yml" | ".yaml" => {
        logger.info("processFile, filename = %s\n".format(filename))
        Option(scala.io.Source.fromFile(filename)).foreach(source => {
          val lines = source.getLines
          val str = lines.mkString("\n")
//          logger.info("processFile, str = %s\n".format(str))
          process(filename, str, output)
          source.close
        })
      }
      case _ => {
        logger.error("Can't process this type of file '%s'".format(filename))
        RunRuleDemo.usageText.map(line => (logger.error(line)))
      }
    }
  }


  def process(filename: String, str: String, output: PrintWriter): Unit
}
