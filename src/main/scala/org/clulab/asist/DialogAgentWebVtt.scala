/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Updated:  2021 February
 *
 *  A .vtt parser that will convert to json format
 *
 *  inputFilename  - Either a file or directory containing .vtt files.
 *
 *  outputFilename - All results of file processing are written here.
 */
package org.clulab.asist

import java.io.{FileInputStream, File, PrintWriter}
import org.slf4j.LoggerFactory
import scala.io.{BufferedSource, Source}
import scala.util.{Failure, Success, Try}
import com.crowdscriber.caption.common.Vocabulary.SubtitleBlock
import com.crowdscriber.caption.vttdissector.VttDissector
import org.htmlcleaner.HtmlCleaner
import java.time.Clock


class DialogAgentWebVtt(
    val inputFilename: String = "",
    val outputFilename: String = "output_events.json"
) extends DialogAgent 
    with DialogAgentJson {

  val experiment_id = "Experiment ID arg is needed!"
  val trial_id = "Trial ID arg is needed!"

  private val logger = LoggerFactory.getLogger(this.getClass())

  val file = new File(inputFilename)
  val stream = new FileInputStream(file)

  // subs is of type SubtitleBlock
  val subs = VttDissector(stream)
  subs match {
    case Success(s) => {
      s.foreach(sub => println(toJson(toVttJsonMessage(sub))))
      stream.close
    }
    case Failure(f) => println("failure")
  }

  //TODO How do we handle multiple lines?  Concatenate?  
  //TODO Break the first line on ':', if it exists then participant_id is before it
  //TODO Run this on an entire .vtt file and just print the SubtitleBlocks to see them

  def toVttJsonMessage(sub: SubtitleBlock): VttJsonMessage = {
    val timestamp = Clock.systemUTC.instant.toString
    val version = "0.1"
    val lines: Seq[String] = sub.lines
    val start: Int = sub.start
    val end: Int = sub.end
    VttJsonMessage(
      VttJsonMessageHeader(
        timestamp = timestamp,
        version = version
      ),
      VttJsonMessageMsg(
        timestamp = timestamp,
        experiment_id = experiment_id,
        trial_id = trial_id,
        message_type = "observation",
        version = version,
        source = "dialog_agent_vtt_parser"
      ),
      VttJsonMessageData(
        asr_system = "Zoom",
        source_filename = file.getPath,
        participant_id = null,  // "firstname lastname"
        caption_start = start.toString,  // milliseconds
        caption_end = end.toString,  // milliseconds
        text = null // "We are about to start the experiment."
      )
    )
  }
}
