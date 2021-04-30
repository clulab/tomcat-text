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
import org.slf4j.LoggerFactory

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
      case ".yml" => {
        logger.info("processing .yml file '%s' ...".format(filename))
      }
      case ".yaml" =>  {
        logger.info("processing .yaml file '%s' ...".format(filename))
      }
      case _ => {
        logger.error("Can't process file '%s'".format(filename))
        RunYmlReader.usageText.map(line => (logger.error(line)))
      }
    }

  this(inputFilename, outputFilename)

}
