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

object  RunDialogAgent extends App {

  /** Show the usage hints */
  def usage: Unit = List(
    "",
    "To use %s on the message bus:".format(args(0)),
    "%s mqtt [host] [port]".format(args(0)),
    "",
    "To use %s on a file or directory (first level only):".format(args(0)),
    "%s file [inputFileOrDir] [outputFile]".format(args(0)),
    ""
  ).map(println)


  /** Create an agent based on the user args */
  val agent: Option[DialogAgent] = args match {
    case Array("mqtt") => 
      Some(new DialogAgentMqtt)
    case Array("mqtt", host: String, port: String) => 
      Some(new DialogAgentMqtt(host, port))
    case Array("file", inputFile: String, outputFile: String) => 
      Some(new DialogAgentFile(inputFile, outputFile))
    case Array("test", inputFile: String, outputFile: String) => 
      Some(new DialogAgentTest(inputFile, outputFile))
    case _ => {
      usage
      None
    }
  }
}
