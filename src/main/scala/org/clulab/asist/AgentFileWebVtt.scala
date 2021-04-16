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


object AgentFileWebVtt {

  private lazy val logger = LoggerFactory.getLogger(this.getClass())

  // Used so Json serializers can recognize case classes
  implicit val formats = Serialization.formats(NoTypeHints)

  /** Manage one file
   * @param filename a single input file
   * @param output Printwriter to the output file
   */
  def apply(filename: String, agent: DialogAgent, output: PrintWriter): Unit = 
    VttDissector(new FileInputStream(new File(filename))) match {
      case Success(blocks) => blocks.map(block => 
        processWebVttElement(
          block.lines.toList,
          agent,
          filename
        ).map(dialogAgentMessage =>
          output.write("%s\n".format(write(dialogAgentMessage)))
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
  def processWebVttElement(
    lines: List[String],
    agent: DialogAgent,
    filename: String
  ): Option[DialogAgentMessage] = {
    val source_type = "web_vtt_file"
    lines match {
      case head::tail => {
        // if a colon exists in the first line, text to left is participant id
        val foo = head.split(':')
        val msg = new CommonMsg
        if(foo.length == 1) {
          val text = lines.mkString(" ")
          Some(agent.toDialogAgentMessage(source_type, filename, msg, null, text))
        } else {
          val text = (foo(1)::tail).mkString(" ")
          Some(agent.toDialogAgentMessage(source_type, filename, msg, foo(0), text))
        }
      }
      case _ => None
    }
  }
}
