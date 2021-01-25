//  DialogAgent
//
//  Author:  Joseph Astier, Adarsh Pyarelal
//  Date:  2020 November
//
//  
//
//  This class will start the DialogAgent with command line key-value pairs.
//
// -h hostname[String]      // default is "localhost"
//
// -p port[Int]         // default is 1883
//
// -i inputFile[String]   // default is none
//
// -o outputFile[String]   // default is none
//
package org.clulab.asist


object  RunDialogAgent extends App {

  case class Args(
    val h:String = "localhost", // host
    val p:String = "1883", // port
    val i:String = "", // input file
    val o:String = "" // output file
  )
  
  val a = parseArgs(new Args, args.toList)
  val agent = new DialogAgent(a.h, a.p)
//  val agent = new DialogAnalysis(a.i, a.o)

  /** Read user args, use defaults if not found */
  def parseArgs(a: Args, user: List[String]): Args = user match {

    case "-h" :: host :: tail => parseArgs (Args(host, a.p, a.i, a.o),tail)
    case "-p" :: port :: tail => parseArgs (Args(a.h, port, a.i, a.o),tail)
    case "-i" :: infile :: tail => parseArgs (Args(a.h, a.p, infile, a.o),tail)
    case "-o" :: outfile :: tail => parseArgs (Args(a.h, a.p, a.i, outfile),tail)
    case _ :: tail => parseArgs(a, tail)
    case _ => a
  }
}
