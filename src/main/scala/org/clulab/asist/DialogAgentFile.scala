/**
 *  Author:  Joseph Astier, Adarsh Pyarelal
 *  Updated:  2021 January
 *
 *  A file-compatible DialogAgent that will take over the roles
 *  of ExtractDirSearch and ExtractInfoSearch
 *
 *  inputFilename - a .vtt file that has been converted to .json by
 *                  scripts/vtt_to_json_msgs
 *
 *  outputFilename - direct our processing output here.
 */
package org.clulab.asist

import java.io.{File, PrintWriter}
import org.slf4j.LoggerFactory
import scala.io.{BufferedSource, Source}
import scala.util.control.Exception._

class DialogAgentFile(
    val inputFilename: String = "",
    val outputFilename: String = ""
) extends DialogAgent
    with DialogAgentJson {

  private val logger = LoggerFactory.getLogger(this.getClass())

  val inputOpt: Option[BufferedSource] = try {
    Some(Source.fromFile(inputFilename))
  } catch {
    case t: Throwable => {
      logger.error("Problem opening %s for reading".format(inputFilename))
      logger.error(t.toString)
      None
    }
  }

  val outputOpt:Option[PrintWriter] = try {
    Some(new PrintWriter(new File(outputFilename)))
  } catch {
    case t: Throwable => {
      logger.error("Problem opening %s for writing".format(outputFilename))
      logger.error(t.toString)
      None
    }
  }

  (inputOpt, outputOpt) match {
    case (i: Some[BufferedSource], o: Some[PrintWriter]) => {
      val input = i.head
      val output = o.head
      val lines = input.getLines
      while(lines.hasNext) processLine(lines.next, output)
      input.close
      output.close
    }
    case (i: Some[BufferedSource], o: Any) => i.head.close
    case (i: Any, o: Some[PrintWriter]) => o.head.close
    case _ =>
  }

  def processLine(json: String, output: PrintWriter): Unit = {
    toVttJsonMessage(json).foreach(vttJsonMessage => {
      val dialogAgentMessage = toDialogAgentMessage(vttJsonMessage)
      val dialogAgentMessageJson = toJson(dialogAgentMessage)
      output.write("%s\n".format(dialogAgentMessageJson))
    })
  }
}
