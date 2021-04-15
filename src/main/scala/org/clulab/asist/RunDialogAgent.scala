/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  updated:  2021 April
 *
 *  This application will run the DialogAgent on an input file, on the
 *  message bus, or interactively depending on user inputs.
 *
 *  The arguments are expected as an Array of string vaue with the element
 *  at index 0 being the run mode, and the remainder as key-value pairs:  
 *  
 *    Array("mode","key1","value1","key2","value2", ...)
 */
package org.clulab.asist

object RunDialogAgent extends App {
  
  // splash page if args are not understood
  val hints = List(
    "",
    "usage:",
    "",
    "  RunDialogAgent {mqtt host port [-m taxonomy_matches]}",
    "                 {stdin [-m taxonomy_matches]}",
    "                 {file inputfile outputfile [-m taxonomy_matches]}",
    "",
    " -m : maximum number of taxonomy matches, up to 5.  Defaults to 0.",
    " inputfile : types are .vtt      - WebVTT format",
    "                       .metadata - same json as on Message Bus",
    "                       .json     - same as on Message Bus",
    ""
  )
  
  // a dialog agent kept in global scope
  val agent = run(args.toList)

  /** Find a String value in the argument list
   * @param l A flat list of key value pairs
   * @param key A key to search for in the list
   * @returns the string value for the key, else None
   */
  def stringArg(l: List[String], key: String): Option[String] = l match {
    case (k::v::_) => if (k == key) Some(v) else stringArg(l.tail, key)
    case _ => None
  }

  /** Find an Int integer value in the argument list
   * @param l A flat list of key value pairs
   * @param key A key to search for in the list
   * @returns the integer value for the key, else None
   */
  def intArg(l: List[String], key: String): Option[Int] = l match {
    case (k::v::_) => if (k == key) { 
      try Some(v.toInt)
      catch {
        case e: Exception => None
      }
    } else intArg(l.tail,key)
    case _ => None
  }

  /** Run the Dialog Agent per user args.
   * @param argList A flat list of running mode then n key-value pairs
   * @returns A DialogAgent running in the mode with the args
   */
  def run(argList: List[String]): Option[DialogAgent] = {
    argList match {
      case ("mqtt"::host::port::l) => {
        val m: Int = intArg(l, "-m").getOrElse(0)
        Some(new DialogAgentMqtt(host, port, m))
      }
      case ("file"::infile::outfile::l) => {
        val m: Int = intArg(l, "-m").getOrElse(0)
        runFile(infile, outfile, m)
      }
      case ("stdin"::l) => {
        val m: Int = intArg(l, "-m").getOrElse(0)
        Some(new DialogAgentStdin(m))
      }
      case _ => {
        hints.map(println)
        None
      }
    }
  }

  /**  Run the Dialog Agent from file input
   * @param infile  Either WebVTT or Message Bus Json format
   * @param outfile Write analysis here
   * @param m Maximum number of taxonomy matches
   * @returns A DialogAgent running in the mode with the args
   */
  def runFile(infile: String, outfile: String, m: Int): Option[DialogAgent] = 
    infile.substring(infile.lastIndexOf(".")) match {
      case ".vtt" =>  
        Some(new DialogAgentWebVtt(infile, outfile, m))
      case ".json" | ".metadata" => 
        Some(new DialogAgentMetadata(infile, outfile, m))
      case _ => {
        hints.map(println)
        None
      }
    }
}
