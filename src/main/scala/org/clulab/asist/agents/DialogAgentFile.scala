package org.clulab.asist.agents

import com.crowdscriber.caption.vttdissector.VttDissector
import com.typesafe.scalalogging.LazyLogging
import java.io.{File, FileInputStream, PrintWriter}
import java.time.Clock
import org.clulab.asist.messages._
import org.clulab.asist.apps.RunDialogAgent
import org.clulab.utils.LocalFileUtils
import org.json4s.jackson.Serialization.{read, write}

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
 * @param inputFilename A file or directory of files to process.
 * @param outputFilename The results of all file processing are written here
 * @param nMatches  maximum number of taxonomy_matches to return (up to 5)
 */

class DialogAgentFile(
  val inputFilename: String = "",
  val outputFilename: String = ""
) extends DialogAgent with LazyLogging {

  logger.info(s"DialogAgentFile version ${dialogAgentVersion}")

  // screen input filenames for supported types
  val supported = List(".vtt", ".metadata")
  val filenames = LocalFileUtils
    .getFileNames(inputFilename)
    .filter(f => f.contains("."))
    .filter(f => supported.contains(f.substring(f.lastIndexOf("."))))
  if(filenames.isEmpty) 
    logger.error("No valid input files found")
  else openFileWriter(outputFilename).foreach{fileWriter =>
    logger.info(s"Using input files: ${filenames.mkString(", ")}")
    filenames.foreach(file => processFile(file, fileWriter))
    logger.info("All operations completed successfully.")
    fileWriter.close 
  }

  /** Create a file writer for a given filename
   *  @param filename a single input file
   *  @return A file writer for the filename, or None if an error occurs.
   */
  def openFileWriter(
    filename: String
  ): Option[PrintWriter] = try {
    val output = new PrintWriter(new File(filename))
    logger.info(s"Using output file ${filename}")
    Some(output)
  } catch {
    case NonFatal(t)  =>
      logger.error(s"Problem opening ${filename} for writing.")
      logger.error(t.toString)
      None
  } 

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
        processMetadataFile(filename, output)
        logger.info(s"finished processing '${filename}'")
      case _ => usage(filename)
    }
  } else usage(filename)


  /** Manage one metadata file
   * @param filename a single input file
   * @param output Printwriter to the output file
   * @return true if the operation succeeded
   */
  def processMetadataFile(
    filename: String,
    output: PrintWriter
  ): Unit = {
    val bufferedSource = Source.fromFile(filename)
    val lines = bufferedSource.getLines
    while(lines.hasNext) {
      processMetadataLine(filename, lines.next, output)
    }
    bufferedSource.close
  }

  def processMetadataLine(
    filename: String, 
    line: String, 
    output: PrintWriter
  ): Unit = {
    val source_type = "message_bus" // file metadata originates there

    allCatch.opt(read[MetadataLookahead](line)).map{lookahead =>
      if(topicSubTrial == lookahead.topic) {
        allCatch.opt(read[TrialMessage](line)).map{trialMessage => 
          if(trialMessage.msg.sub_type == "start") {
            val timestamp = Clock.systemUTC.instant.toString
            val versionInfo = VersionInfo(this, trialMessage, timestamp)
            val json = write(versionInfo)
            output.write(s"${json}\n")
          }
        }
      }
      else if(subscriptions.contains(lookahead.topic)) {
        allCatch.opt(read[Metadata](line)).map{metadata =>
          val message = getDialogAgentMessage(
            source_type,
            filename,
            lookahead.topic,
            metadata
          )
          val json = write(message)
          output.write(s"${json}\n")
        }
      }
    }
  }

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
        ).map{message => // struct
            val json = writeJson(message) 
            output.write(s"${json}\n") // to file
        }
      )
      case Failure(f) => 
        logger.error("VttDissector could not parse '%s'".format(filename))
        logger.error(f.toString)
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
          Some(getDialogAgentMessage(source_type, filename, null, text))
        } else {
          val text = (foo(1)::tail).mkString(" ")
          Some(getDialogAgentMessage(source_type, filename, foo(0), text))
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
  }
}
