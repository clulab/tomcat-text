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
class DialogAgentFile(
  val inputFilename: String = "",
  val outputFilename: String = "",
  tdacUrl: Option[String] = None,
  val idc: Boolean = false
) extends TdacAgent(tdacUrl) with LazyLogging {

  private val filenames: List[String] = 
    LocalFileUtils.getFileNames(inputFilename)

  private val printWriter: Option[PrintWriter] = 
    if (outputFilename == "/dev/null")
      None
    else try {
      Some(new PrintWriter(new File(outputFilename)))
    } catch {
        case NonFatal(t)  =>
          logger.error(s"Problem opening ${outputFilename} for writing:")
          logger.error(t.toString)
          None
    }

  // do not process input unless there is a place to write the output
  val outputOK: Boolean =
    (outputFilename == "/dev/null") || printWriter.isDefined

  private val idcWorker: Option[IdcWorker] =
    if(idc) Some(new IdcWorker(this)) else None

  // plain text input
  val textFiles: List[String] = filenames
    .filter(filename =>filename.endsWith(".txt"))

  textFiles.foreach(filename => processFile(filename))

  def processFile(filename: String): Unit = {
    val textLines: List[String] = 
      Source.fromFile(new File(filename)).getLines.toList
    textLines.foreach(line => processLine(filename, line))
  }

  def processLine(filename: String, line: String): Unit = {
    logger.info("processing line: " + line);

    val dm = DialogAgentMessage(
      "file_input",
      filename,
      None,
      line,
      this)

    val json: String = JsonUtils.writeJson(dm)
    val jsonNoNulls: String = 
      JsonUtils.noNulls(topicPubDialogAgent, json) // do not publish nulls
    printWriter.foreach(_.write(s"${jsonNoNulls}\n"))
  }


  def shutdown: Unit = {
    logger.info("Agent is shutting down")
    printWriter.foreach(_.close)
    tdacClient.foreach(_.close)
    idcWorker.foreach(_.close)
  }

  def doNextJob(){
    logger.info("doNextJob")
  }

  def writeOutput(msgs: List[BusMessage]){
    logger.info("writeOutput")
  }
}

