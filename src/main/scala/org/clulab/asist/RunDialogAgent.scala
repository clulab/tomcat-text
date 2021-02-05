/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  updated:  2021 February
 *
 *  This object will run the DialogAgent in either MQTT or file mode
 *  depending on user inputs
 */
package org.clulab.asist

object  RunDialogAgent extends App {

  /** display the usage hints */
  def usage: Unit = List(
    "Usage:",
    "  mqtt <host> <port number>",
    "or",
    "  file <input filename> <expected_results filename>",
  ).map(println)


  /** Create an agent based on the user args */
  val agent: Option[DialogAgent] = args match {
    case Array("mqtt", h: String, p: String) => 
      Some(new DialogAgentMqtt(host = h, port = p))
    case Array("file", i: String, o: String) => 
      Some(new DialogAgentFile(inputFilename = i, outputFilename = o))
    case _ => {
      usage
      None
    }
  }
}
