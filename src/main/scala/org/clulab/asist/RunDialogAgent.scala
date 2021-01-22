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

object  RunDialogAgent extends App {
  
  val userArgs = parseArgs(("localhost", 1883), args.toList)
  val host = userArgs._1
  val port = userArgs._2
  val agent = new DialogAgent(host, port)

  /** Read user args for host and port, use defaults if not found */
  def parseArgs(
      ca: Tuple2[String, Int], 
      args: List[String]): Tuple2[String, Int]= args match {

    case "-h" :: arg :: tail => arg(0) match {
      case '-' => parseArgs(ca, args.tail)
      case _ => parseArgs((arg, ca._2), tail)
    }
    case "-p" :: arg :: tail => arg(0) match {
      case '-' => parseArgs(ca, args.tail)
      case _ => try {
        parseArgs((ca._1, arg.toInt), tail)
      } catch {
        case e: NumberFormatException => parseArgs(ca, tail)
      }
    }
    case _ => ca
  }
}
