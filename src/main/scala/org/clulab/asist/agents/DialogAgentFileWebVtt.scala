package org.clulab.asist.agents

import buildinfo.BuildInfo
import com.crowdscriber.caption.vttdissector.VttDissector
import com.typesafe.config.Config
import com.typesafe.scalalogging.LazyLogging
import java.io.{File, FileInputStream, PrintWriter}
import org.clulab.asist.messages._
import org.clulab.utils.LocalFileUtils

import scala.io.Source
import scala.util.control.Exception._
import scala.util.control.NonFatal
import scala.util.{Failure, Success}

/**
 * Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 * Process a file or the first level of a directory of files
 *
 * A file is processed by finding metadata elements published on any of the 
 * DialogAgent subscribed topics and creating new DialogAgentMessages with 
 * that data.
 *
 * This class does some preliminary checking of input parameters and
 * then creates a DialogAgentFileSafe instance.
 *
 * @param inputFilename A file or directory of files to process.
 * @param outputFilename The results of all file processing are written here
 * @param tdacUrl Network host and port of the TDAC server
 * @param idc the IDC Worker will be run if true
 */
class DialogAgentFileWebVtt(
  val webVttFilenames: List[String],
  val printWriter: PrintWriter,
  tdacUrl: Option[String] = None,
  val idc: Boolean = false
) extends TdacAgent(tdacUrl) with LazyLogging {

  logger.info(s"DialogAgentFileWebVtt version ${BuildInfo.version}")

  // create the IDC worker if required
  private val idcWorker: Option[IdcWorker] =
    if(idc) Some(new IdcWorker(this)) else None

  val allJobs: List[BusMessage] = webVttFilenames.map (filename =>
    Source.fromFile(new File(filename))
    .getLines
    .toList
    .map(BusMessage(filename, _))
  ).flatten

  val jobsIter: Iterator[BusMessage] = allJobs.iterator

  // file metadata originates here
  val source_type: String = "message_bus"

  // start processing
  doNextJob


  def processWebVttFile(
    filename: String
  ): Unit = try {
    VttDissector(new FileInputStream(new File(filename))) match {
      case Success(blocks) => 
        blocks.map{block => 
        logger.info(s"processing '${filename}' ...")
        processWebVttElement(
          block.lines.toList,
          filename
        ).map{message => 
          val json = JsonUtils.writeJson(message) 
          printWriter.write(s"${json}\n")
          logger.info(s"finished processing '${filename}'")
        }
      }
      case Failure(f) => 
        logger.error("VttDissector could not parse '%s'".format(filename))
        logger.error(f.toString)
    }
  } catch {
    case NonFatal(t) =>
      logger.error(s"Could not process '${filename}'")
      logger.error(t.toString)
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
      case head::tail => 
        // if a colon is in the first line, text to left is participant id
        val foo = head.split(':')
        if(foo.length == 1) Some(
          DialogAgentMessage(
            source_type, 
            filename,
            None,
            lines.mkString(" "),
            this
          )
        )
        else Some(
          DialogAgentMessage(
            source_type, 
            filename,
            Some(foo(0)),
            (foo(1)::tail).mkString(" "),
            this
          )
        )
      case _ => None  // no lines
    }
  }

  override def doNextJob(): Unit = {
  }

  override def writeOutput(messages: List[BusMessage]): Unit = {
  }

}
