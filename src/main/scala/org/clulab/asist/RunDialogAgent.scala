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
    "                 {--mqtt [-h host] [-p port ] [-m taxonomy_matches]}",
    "                 {--stdin [-m taxonomy_matches]}",
    "                 {--web_vtt [-i infile] [-o outfile] [-m taxonomy_matches]}",
    "",
    " -h : MQTT host to connect to. Defaults to localhost.",
    " -p : MQTT network port to connect to. Defaults to 1883.",
    " -m : maximum number of taxonomy matches, up to 5.  Defaults to 0.",
    " -i : input filename, mandatory. WebVTT format.",
    " -o : output filename, defaults to web_vtt_output.json",
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
    case (k::v::rest) => if (k == key) Some(v) else stringArg(rest, key)
    case _ => None
  }

  /** Find an Int integer value in the argument list
   * @param l A flat list of key value pairs
   * @param key A key to search for in the list
   * @returns the integer value for the key, else None
   */
  def intArg(l: List[String], key: String): Option[Int] = l match {
    case (k::v::rest) => if (k == key) { 
      try Some(v.toInt)
      catch {
        case e: Exception => None
      }
    } else intArg(rest,key)
    case _ => None
  }

  /**
   * @param argList A flat list of running mode then n key-value pairs
   * @returns A DialogAgent running in the mode with the args
   */
  def run(argList: List[String]): Option[DialogAgent] = {
    argList match {
      // Run on the Message Bus, old arg set
      case ("mqtt"::host::port::l) => {
        val m: Int = intArg(argList.tail, "-m").getOrElse(0)
        Some(new DialogAgentMqtt(host, port, t))
      }
      // Run on the Message Bus
      case ("--mqtt"::l) => {
        val h = stringArg(l, "-h").getOrElse("localhost")
        val p = stringArg(l, "-p").getOrElse("1883")
        val m: Int = intArg(argList.tail, "-m").getOrElse(0)
        Some(new DialogAgentMqtt(h, p, t))
      }
      // Run using file input
      case ("--web_vtt"::l) => {
        val i = stringArg(l, "-i")  // mandatory arg
        val o = stringArg(l, "-o").getOrElse("web_vtt_output.json")
        val m: Int = intArg(argList.tail, "-m").getOrElse(0)
        if(i.isDefined) 
          Some(new DialogAgentWebVtt(i.head, o, t))
        else {
          hints.map(println)
          None
        }
      }
      // Run interactively from the command line
      case ("--stdin"::l) => {
        val m: Int = intArg(argList.tail, "-m").getOrElse(0)
        Some(new DialogAgentStdin(t))
      }
      // Show the help page
      case _ => {
        hints.map(println)
        None
      }
    }
  }
}
