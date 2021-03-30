/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  updated:  2021 February
 *
 *  This object will run the DialogAgent in either message bus or 
 *  file mode depending on user inputs
 *
 */
package org.clulab.asist

import java.io.File
import org.slf4j.LoggerFactory



object  RunDialogAgent extends App {
  
  val appName ="DialogAgent"

  private lazy val logger = LoggerFactory.getLogger(this.getClass())

  /** Show the usage hints */
  def usage: Unit = List(
    "",
    "To use %s on the message bus:".format(appName),
    "%s mqtt <host> <port>".format(appName),
    "",
    "To use %s on the message bus with default args:".format(appName),
    "%s mqtt".format(appName),
    "or",
    "%s".format(appName),
    "",
    "To use %s for extractions on command line input:".format(appName),
    "%s stdin".format(appName),
    "",
    "To use %s on a web_vtt file or the first level of a directory:".format(appName),
    "%s web_vtt <inputFileOrDir> <outputFile>".format(appName),
    ""
  ).map(println)


  /** Create an agent based on the user args */
  val agent: Option[DialogAgent] = args match {
    case Array() =>
      Some(new DialogAgentMqtt)
    case Array("mqtt") => 
      Some(new DialogAgentMqtt)
    case Array("mqtt", host: String, port: String) => 
      Some(new DialogAgentMqtt(host, port))
    case Array("stdin") => 
      Some(new DialogAgentStdin)
    case Array("web_vtt", inputFile: String, outputFile: String) => {
      DialogAgentWebVtt(inputFile, outputFile)
      None
    }
    case _ => {
      logger.error("Arguments not recognized")
      usage
      None
    }
  }
}
