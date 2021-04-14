/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Updated:  2021 February
 *
 *  A web_vtt DialogAgent that will process files and directories. 
 *  Directories are traversed one level deep.
 *
 *  @param inputFilename a web_vtt file or directory of files
 *  @param outputFilename Write results of parsing here
 *  @param nMatches An optional maximum number of taxonomy_matches to return
 */
package org.clulab.asist

import com.crowdscriber.caption.common.Vocabulary.SubtitleBlock
import com.crowdscriber.caption.vttdissector.VttDissector
import java.io.{FileInputStream, File, PrintWriter}
import org.slf4j.LoggerFactory
import scala.util.{Failure, Success}

class DialogAgentWebVtt(
    val inputFilename: String = "",
    val outputFilename: String = "", 
    override val nMatches: Int = 0
) extends DialogAgentJson 
    with AgentFile {

  private lazy val logger = LoggerFactory.getLogger(this.getClass())

  val source_type = "web_vtt_file"

  processFiles(inputFilename, outputFilename)

  /** Manage one web_vtt file 
   * @param filename a single input file
   * @param output Printwriter to the output file
   */
  override def processFile(filename: String, output: PrintWriter): Unit = {
    val stream = new FileInputStream(new File(filename))
    parseInputStream(stream, filename, output)
    stream.close
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
      output: PrintWriter): Unit = VttDissector(stream) match {
    case Success(blocks) =>
      blocks.map(block => 
        toOutputJson(block.lines.toList, filename).map(a =>
          output.write("%s\n".format(a))
        )
      )
    case Failure(f) => {
      logger.error("VttDissector could not parse input")
      logger.error(f.toString)
    }
  }

  /** process one web_vtt block
   * @param lines The line sequence from a single SubtitleBlock instance
   * @param filename The name of the input file where the block was read
   * @return A DialogAgentMessage based on the inputs
   */
  def toOutputJson(
      lines: List[String],
      filename: String): Option[String] = lines match {
    case head::tail => {
      // if a colon exists in the first line, text to left is participant id
      val foo = head.split(':')
      val msg = new CommonMsg
      if(foo.length == 1) {
        val text = lines.mkString(" ")
        Some(toOutputJson(source_type, filename, msg, null, text))
      } else {
        val text = (foo(1)::tail).mkString(" ")
        Some(toOutputJson(source_type, filename, msg, foo(0), text))
      }
    }
    case _ => None
  }
}
