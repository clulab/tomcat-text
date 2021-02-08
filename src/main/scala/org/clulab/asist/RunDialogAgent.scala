/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  updated:  2021 February
 *
 *  This object will run the DialogAgent in either message bus or 
 *  file mode depending on user inputs
 */
package org.clulab.asist

import java.io.File

object  RunDialogAgent extends App {

  /** display the usage hints */
  def usage: Unit = List(
    "",
    "To use %s on the message bus:".format(args(0)),
    "%s mqtt [host] [port number]".format(args(0)),
    "",
    "To use %s on a file:".format(args(0)),
    "%s file [input_filename] [output_filename]".format(args(0)),
    "",
    "To use %s on a directory:".format(args(0)),
    "%s dir [input_dirname] [output_filename]".format(args(0)),
    ""
  ).map(println)

  /**  Find all the normal filenames in a directory */
  def filenamesFromDir(dirname: String): List[String] = List(new File(dirname))
    .filter(_.isDirectory)
    .map(_.listFiles)
    .toList
    .flatten
    .filter(_.isFile)
    .map(_.getAbsolutePath)


  /** Create an agent based on the user args */
  val agent: Option[DialogAgent] = args match {
    case Array("mqtt", h: String, p: String) => 
      Some(new DialogAgentMqtt(host = h, port = p))
    case Array("file", i: String, o: String) => Some(new DialogAgentFile(
      inputFilenames = List(i), 
      outputFilename = o)
    )
    case Array("dir", d: String, o: String) => Some(new DialogAgentFile(
      inputFilenames = filenamesFromDir(d),
      outputFilename = o)
    )
    case _ => {
      usage
      None
    }
  }
}
