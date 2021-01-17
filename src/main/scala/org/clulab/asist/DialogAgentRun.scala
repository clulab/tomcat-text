//  DialogAgent
//
//  Author:  Joseph Astier, Adarsh Pyarelal
//  Date:  2020 November
//
//  
//
//  This class will start the DialogAgent with command line arguments.
//
// -h: String = <hostname>       // default is "localhost"
//
// -p: Int <port_number>         // default is 1883
//
package org.clulab.asist

object  DialogAgentRun extends App {

  // default server and port.
  val cleanArgs = parseArgs(("localhost", 1883), args.toList)
  val agent = new DialogAgent(cleanArgs._1, cleanArgs._2)

  // read user args for host and port.   Use defaults if they are not found.
  def parseArgs(
      ca: Tuple2[String, Int], 
      args: List[String]): Tuple2[String, Int]= args match {

    case "-h" :: arg :: tail => {
      if(arg(0)=='-') parseArgs(ca, args.tail)
      else parseArgs((arg, ca._2), tail)
    }
    case "-p" :: arg :: tail => {
      if(arg(0)=='-') parseArgs(ca, args.tail)
      else try {
        parseArgs((ca._1, arg.toInt), tail)
      } catch {
        case e: NumberFormatException => parseArgs(ca, tail)
      }
    }
    case _ => ca
  }
}

