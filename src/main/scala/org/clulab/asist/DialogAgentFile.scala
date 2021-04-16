/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Updated:  2021 April
 *
 *  This trait is to group file processing functionality in one place.
 *
 */
package org.clulab.asist

import java.io.{FileInputStream, File, PrintWriter}
import org.slf4j.LoggerFactory

class DialogAgentFile
 extends DialogAgent {

  private lazy val logger = LoggerFactory.getLogger(this.getClass())

  def getFiles(filename: String): List[String] = {
    val f = new File(filename)
    if(f.isDirectory) f.listFiles.toList.map(_.getPath)
    else List(f.getPath)
  }

  def processFiles(
    inputFilename: String, 
    outputFilename: String
  ): Unit = {
    logger.info("Using input file '%s'".format(inputFilename))
    logger.info("Using output file '%s'".format(outputFilename))
    try {
      val output = new PrintWriter(new File(outputFilename))
      getFiles(inputFilename).sorted.map(filename => {
        logger.info("processing %s".format(filename))
        processFile(filename, output)
        logger.info("finished processing %s".format(filename))
      })
      output.close
      logger.info("All operations completed successfully.")
    } catch {
      case t: Throwable => {
        logger.error("Problem writing to %s".format(outputFilename))
        logger.error(t.toString)
      }
    }
  }

  /** Manage one file
   * @param filename a single input file
   * @param output Printwriter to the output file
   */
  def processFile(filename: String, output: PrintWriter): Unit = 
    filename.substring(infile.lastIndexOf(".")) match {
      case ".vtt" => processWebVttFile(filename, output)
      case ".metadata" => processMetadataFile(fileame, output)
      case _ => Logger.error("Can't process unknown file type %s".format(filename))
    }


  /** Manage one web_vtt file
   * @param filename a single input file
   * @param output Printwriter to the output file
   */
  def processWebVttFile(filename: String, output: PrintWriter): Unit = {
    val stream = new FileInputStream(new File(filename))
    VttDissector(stream) match {
      case Success(blocks) =>
        blocks.map(block =>
          toOutputJson(block.lines.toList, filename).map(json =>
            output.write("%s\n".format(json))
          )
        )
      case Failure(f) => {
        logger.error("VttDissector could not parse input")
        logger.error(f.toString)
      }
    }
    stream.close
  }



  /** Wrangle one metadata file
   * @param filename a single input file
   * @param output Printwriter to the output file
   * @return true if the operation succeeded
   */
  def processMetadataFile(filename: String, output: PrintWriter): Unit = {
    val source_type = "message_bus"
    val bufferedSource = Source.fromFile(filename)
    val lines = bufferedSource.getLines
    while(lines.hasNext) {
      val line = lines.next
      allCatch.opt(read[MetadataLookahead](line)).map(lookahead =>
        if(inputTopics.contains(lookahead.topic))
          allCatch.opt(read[MetadataMessage](line)).map(metadata =>
            (lookahead.topic match {
            case `topicChat` => Some(metadata.data.sender)
            case `topicUazAsr` => Some(metadata.data.participant_id)
            case `topicAptimaAsr` => Some(metadata.data.playername)
            case _ => None
          }).map(participant_id =>
            output.write( // to file
              write( // to json
                toDialogAgentMessage( // to struct
                  source_type,
                  filename,
                  metadata.msg,
                  participant_id,
                  metadata.data.text
                )
              )
            )
          )
        )
      )
    }
    bufferedSource.close
  }

}
