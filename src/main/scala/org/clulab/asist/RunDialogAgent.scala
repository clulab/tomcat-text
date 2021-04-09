/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  updated:  2021 April
 *
 *  This object will run the DialogAgent on an input file, on the 
 *  message bus, or interactively depending on user inputs
 */
package org.clulab.asist

object  RunDialogAgent extends App {
  
  val appName ="DialogAgent"

  val hintsStdin = List(
    "'%s stdin' runs the Dialog Agent interactively. ".format(appName)
  )

  val hintsMqtt = List(
    "'%s mqtt' runs the Dialog Agent on the Message Bus".format(appName),
    "  -h   Host machine where the MQTT broker is running.",
    "       Optional, default is 'localhost'.",
    "  -p   Port on the host machine for connection to the broker.",
    "       Optional, default is '1883'.",
    "  -n   Number of extractions to perform.",
    "       Optional, if not specified all extractions are performed."
  )

  val hintsWebVtt = List(
    "'%s web_vtt' runs the Dialog Agent on an input file in WebVTT format.".format(appName),
    "  -i   Input filename.  Mandatory argument.",
    "  -o   Output filename.  Destination for processed input."
  )

  /** Show the usage hints */
  val allHints: List[String] = List(
    hintsMqtt, 
    List(" "), 
    hintsStdin,
    List(" "),
    hintsWebVtt
  ).flatten

  // inform the user that you need a little more information
  def usage(hints: List[String]): Option[DialogAgent] = {
    hints.map(println)
    None
  }

  val agent: Option[DialogAgent] = getAgent(args.toList)

  /** Create an agent based on the user args */
  def getAgent(l: List[String]): Option[DialogAgent] = l.head match {
    case ("mqtt") => parseMqtt(l.tail, "localhost", "1883", None)
    case ("stdin") => parseStdin(l.tail)
    case ("web_vtt") => parseWebVtt(l.tail, None, None)
    case _ => usage(allHints)
  }


  def parseStdin(
    argList: List[String]
  ):Option[DialogAgent] = argList match {
    case ("--help"::l) => usage(hintsStdin)
    case _ => Some(new DialogAgentStdin)
  }


  // the Mqtt agent can run on no args at all
  def parseMqtt(
    argList: List[String],
    host: String,
    port: String,
    nMatches: Option[String]
  ): Option[DialogAgent] = argList match {
    case ("--help"::l) => usage(hintsMqtt)
    case ("-h"::l) => parseMqtt(l.tail, l.head, port, nMatches)
    case ("-p"::l) => parseMqtt(l.tail, host, l.head, nMatches)
    case ("-n"::l) => parseMqtt(l.tail, host, port, Some(l.head))
    case List() => Some(new DialogAgentMqtt(host, port, nMatches))
    case _ => usage(hintsMqtt)
  }


  // the WebVtt agent must have input and output args to run.
  def parseWebVtt(    
    argList: List[String],
    infile: Option[String],
    outfile: Option[String]
  ): Option[DialogAgent] = argList match {
    case ("--help"::l) => usage(hintsWebVtt)
    case ("-i"::l) => parseWebVtt(l.tail, Some(l.head), outfile)
    case ("-o"::l) => parseWebVtt(l.tail, infile, Some(l.head))
    case List() => {
      if(infile.isDefined && outfile.isDefined) 
        Some(new DialogAgentWebVtt(infile.head, outfile.head))
      else usage(hintsWebVtt)
    }
    case _ => usage(hintsWebVtt)
  }
}
