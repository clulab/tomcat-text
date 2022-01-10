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
 * Authors:  Joseph Astier, Adarsh Pyarelal
 *
 * Process a file or the first level of a directory of files
 */
class DialogAgentFileMetadata(
  val inputFilename: String = "",
  val outputFilename: String = "",
  tdacUrl: Option[String] = None,
  val idc: Boolean = false
) extends DialogAgent with LazyLogging {

  logger.info(s"DialogAgentFileMetadata version ${BuildInfo.version}")

  // return a list of tuples with first element filename
  def fileJobs(filename: String): List[BusMessage] = Source
    .fromFile(new File(filename))
    .getLines
    .toList
    .map(BusMessage(filename, _))

  val allJobs: List[BusMessage] = LocalFileUtils
    .getFileNames(inputFilename)
    .filter(_.endsWith(".metadata"))
    .filter(new File(_).exists)
    .map(fileJobs)
    .flatten

  val jobsIter: Iterator[BusMessage] = allJobs.iterator

  val printWriter: Option[PrintWriter] = try {
    val ret = Some(new PrintWriter(new File(outputFilename)))
    logger.info(s"Output will be written to: ${outputFilename}")
    ret
  } catch {
    case NonFatal(t)  =>
      logger.error(s"Problem opening ${outputFilename} for writing.")
      logger.error(t.toString)
      None
  }

  // file metadata originates here
  val source_type: String = "message_bus" 

  // start processing if the output is OK
  if(printWriter.isDefined)
    doNextJob

  def doNextJob(): Unit =
    if(jobsIter.hasNext) {
      logger.info("Doing next job.")
      process(jobsIter.next)
    }
    else {
      logger.info("All operations completed successfully.")
      printWriter.foreach(_.close)
    }

  def process(job: BusMessage): Unit = {
    val filename = job.topic
    val topic = JsonUtils.readJson[Topic](job.text).getOrElse(Topic()).topic
    val text = job.text
    logger.info(s"processing '${topic}")
    topic match {      
      case `topicSubTrial` =>
        processTrialMessage(filename, topic, text)
        /*
      case `topicSubChat` =>
        ChatMessage(text)
          .map(DialogAgentMessage(source_type, filename, _, this))
          .map(writeOutput(_, line))
      case `topicSubAsr` =>
        AsrMessage(text)
          .map(DialogAgentMessage(source_type, filename, _, this))
          .map(writeOutput(_, line))
          */
      case _ => doNextJob
    }
  }

  // we have to somehow get the topic into the output
  def processTrialMessage(
    filename: String,
    topic: String,
    text: String
  ): Unit = TrialMessage(text) match {
    case Some(trial) =>
      if(TrialMessage.isStart(trial)) {
        val versionInfo = VersionInfo(trial)
        val json = JsonUtils.writeJson(versionInfo)
        val jsonNoNulls:String = JsonUtils.noNulls(json) // do not publish nulls
        // we have to somehow get the topic into the output
        val output = BusMessage(topic, jsonNoNulls)
        writeOutput(List(output))
      } 
      else doNextJob
    case None =>
      doNextJob
  }


  def writeOutput(messages: List[BusMessage]): Unit = {
    messages.foreach(writeOutput)
    doNextJob
  }


  def writeOutput(message: BusMessage): Unit = 
    printWriter.foreach(_.write(s"${message.text}\n"))








}
