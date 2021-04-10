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
    " -t : maximum number of taxonomy matches.  Defaults to 5.",
    " -i : WebVTT input filename, mandatory",
    " -o : output filename, defaults to web_vtt_output.json"
  )
  

  // a dialog agent kept in global scope
  val agent = run(args.toList)

  /**
   * @param l A list of keys and values as (key value key value ...)
   * @param key A key to search for in the list
   * @returns the string value for the key, if found, else None
   */
  def stringArg(l: List[String], key: String): Option[String] = l match {
    case (k::v::rest) => if (k == key) Some(v) else stringArg(rest, key)
    case _ => None
  }

  /**
   * @param l A list of keys and values as (key value key value ...)
   * @param key A key to search for in the list
   * @returns the integer value for the key, if found, else None
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
   * @param argList A list of keys and values as (key value key value ...)
   * @returns A DialogAgent running in the user-chose mode
   */
  def run(argList: List[String]): Option[DialogAgent] = {
    val t: Option[Int] = intArg(argList.tail, "-t")
    argList match {
      // Run on the Message Bus
      case ("--mqtt"::l) => {
        val h = stringArg(l, "-h").getOrElse("localhost")
        val p = stringArg(l, "-p").getOrElse("1883")
        Some(new DialogAgentMqtt(h, p, t))
      }
      // Run using file input
      case ("--web_vtt"::l) => {
        val i = stringArg(l, "-i")  // mandatory arg
        val o = stringArg(l, "-o").getOrElse("web_vtt_output.json")
        if(i.isDefined) 
          Some(new DialogAgentWebVtt(i.head, o, t))
        else {
          hints.map(println)
          None
        }
      }
      // Run interactively from the command line
      case ("--stdin"::l) => Some(new DialogAgentStdin(t))
      // Show the help page
      case _ => {
        hints.map(println)
        None
      }
    }
  }
}
