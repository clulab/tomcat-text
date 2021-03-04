/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Updated:  2021 February
 *
 *  A web_vtt DialogAgent that will process files and directories. 
 *  Directories are traversed one level deep.
 *
 *  inputFilename  - A web_vtt file
 *
 *  outputFilename - The concatenated results of processing the input files.
 */
package org.clulab.asist

import com.crowdscriber.caption.common.Vocabulary.SubtitleBlock
import com.crowdscriber.caption.vttdissector.VttDissector
import java.io.{FileInputStream, File, PrintWriter}
import org.slf4j.LoggerFactory
import scala.util.{Failure, Success}


class DialogAgentWebVtt(
    val inputFilename: String = "",
    val outputFilename: String = ""
) extends DialogAgent with DialogAgentJson {


  private val logger = LoggerFactory.getLogger(this.getClass())

  // List all the files to be processed.
  val allFiles: List[String] = {
    val f = new File(inputFilename)
    if(f.isDirectory) f.listFiles.toList.map(_.getAbsolutePath)
    else List(f.getAbsolutePath)
  }


  /** Manage one web_vtt file 
   * @param filename a single input file
   * @param output Printwriter to the output file
   * @return true if the operation succeeded
   */
  def processFile(filename: String, output: PrintWriter): Boolean = try {
    logger.info("Reading input file %s".format(filename))
    val stream = new FileInputStream(new File(filename))
    parseInputStream(stream, filename, output)
    stream.close
    logger.info("Closed input file %s".format(filename))
    true
  } catch {
    case t: Throwable => {
      logger.error("Problem reading input file '%s'".format(filename))
      logger.error(t.toString)
      false
    }
  }


  /** Run a VttDissector on the contents of one file
   * @param stream The contents of an input file
   * @param filename The name of the input file
   * @param output Printwriter to the output file
   * @return true if the operation succeeded
   */
  def parseInputStream(
      stream: FileInputStream, 
      filename: String, 
      output: PrintWriter): Boolean = VttDissector(stream) match {
    case Success(blocks) => {
      val results = blocks.map(block => {
        val dialogAgentMessage = parse(block.lines.toList, filename)
        dialogAgentMessage.map(a => output.write("%s\n".format(toJson(a))))
      })
      !results.contains(false)
    }
    case Failure(f) => {
      logger.error("VttDissector could not parse input")
      logger.error(f.toString)
      false
    }
  }


  /** process one web_vtt block
   * @param lines The line sequence from a single SubtitleBlock instance
   * @param filename The name of the input file where the block was read
   * @return A DialogAgentMessage based on the inputs
   */
  def parse(
      lines: List[String],
      filename: String): Option[DialogAgentMessage] = lines match {
    case head::tail => {
      // if a colon exists in the first line, text to left is participant id
      val foo = head.split(':')
      if(foo.length == 1) {
        val text = lines.mkString(" ")
        Some(toDialogAgentMessage("file", filename, null, null, text))
      } else {
        val text = (foo(1)::tail).mkString(" ")
        Some(toDialogAgentMessage("file", filename, null, foo(0), text))
      }
    }
    case _ => None
  }


  // open the output stream and process the files
  try {
    val output = new PrintWriter(new File(outputFilename))
    val results = allFiles.map(processFile(_, output))
    output.close
    if(results.contains(false)) logger.error("Problems were encountered during this run.")
    else logger.info("All operations completed successfully.")
  } catch {
    case t: Throwable => {
      logger.error("Problem writing to %s".format(outputFilename))
      logger.error(t.toString)
      None
    }
  }
}
