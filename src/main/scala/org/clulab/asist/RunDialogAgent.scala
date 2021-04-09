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
  
  val hints = List(
    "Running the ToMCAT-text Dialog Agent:",
    "",
    "  RunDialogAgent {--mqtt [-h host] [-p port ] [-t taxonomy_matches]}",
    "                 {--stdin [-t taxonomy_matches]}",
    "                 {--web_vtt [-i infile] [-o outfile] [-t taxonomy_matches]}",
    "",
    " -h : MQTT host to connect to. Defaults to localhost.",
    " -p : MQTT network port to connect to. Defaults to 1883.",
    " -t : maximum number of taxonomy matches.  No limit if not set.",
    " -i : WebVTT input filename, mandatory",
    " -o : output filename, defaults to web_vtt_output.json"
  )
  

  // a dialog agent kept in global scope
  val agent = run(args.toList)

  /**
   * @param l A list of keys and values as (key value key value ...)
   * @param key A key to search for in the list
   * @returns the value for the key, if found, else None
   */
  def value(l: List[String], key: String): Option[String] = l match {
    case (k::v::rest) => if (k == key) Some(v) else value(rest, key)
    case _ => None
  }


  def intOption(l: List[String], key: String): Option[Int] = {
    val foo = value(l, "-t")
    if(foo.isEmpty) None
    else try {
      Some(foo.head.toInt)
    } catch {
      case e: Exception => None
    }
  }

  /**
   * @param l A list of keys and values as (key value key value ...)
   * @returns A DialogAgent running in the user-chose mode
   */
  def run(argList: List[String]): Option[DialogAgent] = {
    val t: Option[Int] = intOption(argList, "-t")
    argList match {
      // Run on the Message Bus
      case ("--mqtt"::l) => {
        val h = value(l, "-h").getOrElse("localhost")
        val p = value(l, "-p").getOrElse("1883")
        Some(new DialogAgentMqtt(h, p, t))
      }
      // Run using file input
      case ("--web_vtt"::l) => {
        val i = value(l, "-i")  // mandatory arg
        val o = value(l, "-o").getOrElse("web_vtt_output.json")
        if(i.isDefined) 
          Some(new DialogAgentWebVtt(i.head, o, t))
        else {
          hints.map(println)
          None
        }
      }
      // Run on the command line
      case ("--stdin"::l) => Some(new DialogAgentStdin(t))
      // Run the help page
      case _ => {
        hints.map(println)
        None
      }
    }
  }
}
