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
  
  def usage: Option[DialogAgent] = {
    List(
      "Running the ToMCAT-text Dialog Agent:",
      "",
      "  RunDialogAgent {mqtt [-h host] [-p port ] [-t taxonomy matches]}",
      "                 {stdin [-t taxonomy matches]}",
      "                 {web_vtt [-i infile] [-o outfile] [-t taxonomy matches]}",
      "",
      " -h : MQTT host to connect to. Default is localhost.",
      " -p : MQTT network port to connect to. Default is 1883.",
      " -t : maximum number of taxonomy matches.  Default is no limit.",
      " -i : WebVTT input filename, mandatory",
      " -o : output filename, defaults to web_vtt_output.json"
    ).map(println)
    None
  }

  val agent: Option[DialogAgent] = run(args.toList)

  /** Run an agent based on the user args */
  def run(argList: List[String]): Option[DialogAgent] = argList match {
    case ("mqtt"::l) => runMqtt(l, "localhost", "1883", None)
    case ("stdin"::l) => runStdin(l, None)
    case ("web_vtt"::l) => runWebVtt(l, None, None, None)
    case _ => usage
  }

  // run interactively from the command prompt
  def runStdin(
    argList: List[String],
    nMatches: Option[String]
  ):Option[DialogAgent] = argList match {
    case ("-t"::l) => runStdin(l.tail, Some(l.head))
    case _ => Some(new DialogAgentStdin(nMatches))
  }


  // run on the Message Bus
  def runMqtt(
    argList: List[String],
    host: String,
    port: String,
    nMatches: Option[String]
  ): Option[DialogAgent] = argList match {
    case ("-h"::l) => runMqtt(l.tail, l.head, port, nMatches)
    case ("-p"::l) => runMqtt(l.tail, host, l.head, nMatches)
    case ("-t"::l) => runMqtt(l.tail, host, port, Some(l.head))
    case List() => Some(new DialogAgentMqtt(host, port, nMatches))
    case _ => usage
  }


  // run with file input
  def runWebVtt(    
    argList: List[String],
    infile: Option[String],
    outfile: Option[String],
    nMatches: Option[String]

  ): Option[DialogAgent] = argList match {
    case ("-i"::l) => runWebVtt(l.tail, Some(l.head), outfile, nMatches)
    case ("-o"::l) => runWebVtt(l.tail, infile, Some(l.head), nMatches)
    case ("-t"::l) => runWebVtt(l.tail, infile, outfile, Some(l.head))
    case List() => {
      if(infile.isDefined && outfile.isDefined) 
        Some(new DialogAgentWebVtt(infile.head, outfile.head))
      else usage
    }
    case _ => usage
  }
}
