package org.clulab.asist.agents

import com.crowdscriber.caption.vttdissector.VttDissector
import com.typesafe.scalalogging.LazyLogging
import java.io.{File, FileInputStream, PrintWriter}
import java.time.Clock
import org.clulab.asist.messages._
import org.clulab.asist.RunDialogAgent
import org.clulab.utils.LocalFileUtils
import org.json4s.jackson.Serialization.{read, write}

import scala.io.Source
import scala.util.control.Exception._
import scala.util.control.NonFatal
import scala.util.{Failure, Success}

/**
 * Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 * Updated:  2021 July
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
  val outputFilename: String = "",
  override val args: DialogAgentArgs = new DialogAgentArgs
) extends DialogAgent(args) with LazyLogging {

  logger.info(s"args.withClassifications = ${args.withClassifications}") 

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
    filenames.map(processFile(_, fileWriter))
    fileWriter.close
    logger.info("All operations completed successfully.")
  } 


  /** async callback after classification
   *  @param message A fully populated case class ready for output
   *  @param output  A PrintWriter connected to an output file
   *  @return 
   */
  def writeMessageToFile(
    message: DialogAgentMessage,
    output: PrintWriter
  ): Unit = {
    logger.info("writeMessageToFile with message, output")
    output.write(s"${writeJson(message)}\n")
  }

  /** async callback after reset
   *  @param message A fully populated case class ready for output
   *  @param output  A PrintWriter connected to an output file
   *  @return 
   */
  def writeVersionInfoToFile(
    message: VersionInfo,
    output: PrintWriter
  ): Unit = {
    logger.info("writeVersionInfoToFile with message, output")
    output.write(s"${writeJson(message)}\n")
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
    logger.info("processMetadataFile")
    val source_type = "message_bus" // file metadata originates there
    val bufferedSource = Source.fromFile(filename)
    val lines = bufferedSource.getLines
    while(lines.hasNext) {
      val line = lines.next
      allCatch.opt(read[MetadataLookahead](line)).map{lookahead =>
        logger.info(s"processMetadataFile with topic = ${lookahead.topic}")
        if(topicSubTrial == lookahead.topic) {
          allCatch.opt(read[TrialMessage](line)).map{trialMessage => 
            if(trialMessage.msg.sub_type == "start") {
              val timestamp = Clock.systemUTC.instant.toString
              val versionInfo = VersionInfo(this, timestamp)
              dqm.enqueueReset(
                this,
                writeVersionInfoToFile,
                versionInfo,
                output
              )
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
            dqm.enqueueClassification(
              this, 
              writeMessageToFile,
              message,
              output
            ) 
          }
        }
      }
    }
    bufferedSource.close
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
      case Success(blocks) => blocks.map{block =>
        processWebVttElement(block.lines.toList, filename)
        .map{message => 
          dqm.enqueueClassification(
            this, 
            writeMessageToFile,
            message,
            output
          )
        }
      }
      case Failure(f) => {
        logger.error("VttDissector could not parse '%s'".format(filename))
        logger.error(f.toString)
      }
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
