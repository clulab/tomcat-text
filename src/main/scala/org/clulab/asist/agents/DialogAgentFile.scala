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

  val filenames = LocalFileUtils.getFileNames(inputFilename)
  val metadataFilenames = filenames.filter(_.endsWith(".metadata"))
  val webVttFilenames = filenames.filter(_.endsWith(".vtt"))

  if((metadataFilenames.isEmpty) && (webVttFilenames.isEmpty)) {
    logger.error("No input filenames found")
  } else try {
    val printWriter = new PrintWriter(new File(outputFilename))
    logger.info(s"Output will be written to: ${outputFilename}")
    new DialogAgentFileMetadata(metadataFilenames, printWriter, tdacUrl, idc)
    new DialogAgentFileWebVtt(webVttFilenames, printWriter, tdacUrl, idc)
  } catch {
    case NonFatal(t)  =>
      logger.error(s"Problem opening ${outputFilename} for writing.")
      logger.error(t.toString)
  }

  override def doNextJob(): Unit = { 
  }

  override def writeOutput(messages: List[BusMessage]): Unit = { 
  }

}
