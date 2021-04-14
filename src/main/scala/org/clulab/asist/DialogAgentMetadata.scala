/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Updated:  2021 April
 *
 *  The dialog agent will process a metadata file exactly as if it were 
 *  read from the message bus.  The supported Message structures are
 *
 *   ChatMessage
 *   UazAsrMessage
 *   AptimAsrMessage
 *
 *  @param inputFilename a file containing Messages in Json format.
 *  @param outputFilename a file containing DialogAgentMessage in Json format.
 *  @param nMatches An optional maximum number of taxonomy_matches to return
 */
package org.clulab.asist

import scala.io.Source
import java.io.PrintWriter

class DialogAgentMetadata(
    val inputFilename: String = "",
    val outputFilename: String = "", 
    override val nMatches: Int = 0
) extends AgentJson 
    with AgentFile {

  val source_type = "metadata_file"

  processFiles(inputFilename, outputFilename)

  /** Wrangle one metadata file 
   * @param filename a single input file
   * @param output Printwriter to the output file
   * @return true if the operation succeeded
   */
  override def processFile(filename: String, output: PrintWriter): Unit = {
    val bufferedSource = Source.fromFile(filename)
    for (line <- bufferedSource.getLines) {
      outputJson(source_type, filename, line).map(a =>
        output.write("%s\n".format(a))
      )
    }
    bufferedSource.close
  } 
}