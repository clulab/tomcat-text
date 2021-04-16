/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Updated:  2021 April
 *
 *  This trait is to group file processing functionality in one place.
 *
 */
package org.clulab.asist

import com.crowdscriber.caption.common.Vocabulary.SubtitleBlock
import com.crowdscriber.caption.vttdissector.VttDissector
import java.io.{FileInputStream, File, PrintWriter}
import org.json4s._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write}
import org.slf4j.LoggerFactory
import scala.io.Source
import scala.util.control.Exception._
import scala.util.{Failure, Success}


class DialogAgentFile(
    val inputFilename: String = "",
    val outputFilename: String = "",
    override val nMatches: Int = 0
  ) extends DialogAgent {

  private lazy val logger = LoggerFactory.getLogger(this.getClass())


  /** Manage one file
   * @param filename a single input file
   * @param output Printwriter to the output file
   */
  def processFile(filename: String, output: PrintWriter): Unit = 
    filename.substring(filename.lastIndexOf(".")) match {
      case ".vtt" => {
        logger.info("processing WebVTT file '%s' ...".format(filename))
        AgentFileWebVtt(filename, this, output)
        logger.info("finished processing %s".format(filename))
      }
      case ".metadata" =>  {
        logger.info("processing Metadata file: '%s' ...".format(filename))
        AgentFileMetadata(filename, this, output)
        logger.info("finished processing '%s'".format(filename))
      }
      case _ => {
        logger.error("Can't process file '%s'".format(filename))
        RunDialogAgent.usageText.map(line => (logger.error(line)))
      }
    }


  def processFiles(filenames: List[String]): Unit = {
    logger.info("Using output file '%s'".format(outputFilename))
    try {
      val output = new PrintWriter(new File(outputFilename))
      filenames.map(processFile(_, output))
      output.close
      logger.info("All operations completed successfully.")
    } catch {
      case t: Throwable => {
        logger.error("Problem writing to '%s'".format(outputFilename))
      logger.error(t.toString)
      }
    }
  }

  val input = new File(inputFilename)

  if(input.exists) {
    if(input.isDirectory) {
      logger.info("Using input directory '%s'".format(inputFilename))
      processFiles(input.listFiles.toList.map(_.getPath).sorted)
    }
    else {
      logger.info("Using input file '%s'".format(inputFilename))
      processFiles(List(input.getPath))
    }
  } 
  else {
    logger.error("File not found '%s'".format(inputFilename))
  }
}
