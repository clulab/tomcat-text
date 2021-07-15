package org.clulab.asist.agents

import com.typesafe.scalalogging.LazyLogging

import org.clulab.asist.{AgentFileMetadata, RunDialogAgent}
import org.clulab.utils.LocalFileUtils
import java.io.{File, PrintWriter}

import scala.util.control.NonFatal

import java.io.{File, FileInputStream, PrintWriter}

import com.crowdscriber.caption.vttdissector.VttDissector
import org.clulab.asist.messages._
import org.json4s.jackson.Serialization.write

import scala.util.{Failure, Success}




/**
 * Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 * Updated:  2021 July
 *
 * Process a file or the first level of a directory of files.
 *
 * @param inputFilename A file or directory of files to process.
 * @param outputFilename The results of all file processing are written here
 * @param nMatches  maximum number of taxonomy_matches to return (up to 5)
 */

class DialogAgentFile(
  val inputFilename: String = "",
  val outputFilename: String = "",
  override val args: DialogAgentArgs = new DialogAgentArgs
) extends DialogAgent with LazyLogging {

  /** process one input file
   * @param filename a single input file
   * @param output Printwriter to the output file
   */
  def processFile(
    filename: String, 
    output: PrintWriter
  ): Unit = if(filename.contains(".")) {
    filename.substring(filename.lastIndexOf(".")) match {
      case ".vtt" =>
        processWebVttFile(filename, output)
        logger.info(s"finished processing ${filename}")
      case ".metadata" =>
        logger.info(s"processing Metadata file: '${filename}' ...")
        AgentFileMetadata(filename, this, output)
        logger.info(s"finished processing '${filename}'")
      case _ => usage(filename)
    }
  } else usage(filename)


  /** Manage one WebVtt file
  *  @param filename input filename
  *  @param output Printwriter to the output file
  */
  def processWebVttFile(
    filename: String, 
    output: PrintWriter
  ): Unit = {
    VttDissector(new FileInputStream(new File(filename))) match {
      case Success(blocks) => blocks.map(block =>
        processWebVttElement(
          block.lines.toList,
          filename
        ).map(dialogAgentMessage =>
          output.write("%s\n".format(write(dialogAgentMessage)))
        )
      )
      case Failure(f) => {
        logger.error("VttDissector could not parse '%s'".format(filename))
        logger.error(f.toString)
      }
    }
  }

  /** process one web_vtt block
  *  @param lines The line sequence from a single SubtitleBlock instance
  *  @param filename The name of the input file where the block was read
  *  @return A DialogAgentMessage based on the inputs
  */
  def processWebVttElement(
    lines: List[String],
    filename: String
  ): Option[DialogAgentMessage] = {
    val source_type = "web_vtt_file"
    lines match {
      case head::tail => {
        // if a colon exists in the first line, text to left is participant id
        val foo = head.split(':')
        if(foo.length == 1) {
          val text = lines.mkString(" ")
          Some(dialogAgentMessage(source_type, filename, null, text))
        } else {
          val text = (foo(1)::tail).mkString(" ")
          Some(dialogAgentMessage(source_type, filename, foo(0), text))
        }
      }
      case _ => None  // FIXME: Is the multiple-line case really a showstopper?
    }
  }


  /** Give the user a hint
   * @param filename a single input file
   */
  def usage(filename: String): Unit = {
    logger.error(s"Can't process file '${filename}'")
    logger.error("Extension must be .vtt or .metadata")
    RunDialogAgent.usageText.foreach(line => (logger.error(line)))
  }

  /** process all of the input files
   * @param inputFilename User input source filename
   * @param outputFileame User input output filename
   */
  def apply(inputFilename: String, outputFilename: String): Unit = {
    logger.info(s"Using input file ${inputFilename}")
    logger.info(s"Using output file ${outputFilename}")
    try {
      val output = new PrintWriter(new File(outputFilename))
      val filenames = LocalFileUtils.getFileNames(inputFilename)
      filenames.map(processFile(_, output))
      output.close
      logger.info("All operations completed successfully.")
    } catch {
      case NonFatal(t)  =>
        logger.error(s"Problem writing to ${inputFilename}")
        logger.error(t.toString)
    }
  }
}
