//  DialogAgent
//
//  Author:  Joseph Astier, Adarsh Pyarelal
//
//  updated:  2021 January
//
//  This class will start the DialogAgent with default arguments
//
// -h hostname  // default is "localhost"
//
// -p port      // default is "1883"
//
package org.clulab.asist

object  RunDialogAgent extends App {

  /** watch these MQTT topics for incoming messages */
  val TOPIC_INPUT_OBS = "observations/chat"
  val TOPIC_INPUT_ASR = "agent/asr"

  /** publish message analysis to this MQTT topic */
  val TOPIC_OUTPUT = "agent/tomcat_chatbot"

  /** Command line args */
  case class Args(
    val h:String = "localhost", // host
    val p:String = "1883" // port
  )
  
  /** parse the command line args and populate an Args struct */
  val a: Args = parseArgs(new Args, args.toList)

  /** Run a DialogAgent using the defaults and command line args */
  val agent = new DialogAgent(
    host = a.h, 
    port = a.p,
    topicSubObs = TOPIC_INPUT_OBS,
    topicSubAsr = TOPIC_INPUT_ASR,
    topicPub = TOPIC_OUTPUT
  )

  /** Read user args, use defaults if not found 
   *  @param ret Args struct that is updated with command line args
   *  @param args List of command-line arguments
   *  @return Args struct containing the command line args
   */
  def parseArgs(ret: Args, args: List[String]): Args = args match {
    case "-h" :: host :: tail => parseArgs(Args(host, ret.p), tail)
    case "-p" :: port :: tail => parseArgs(Args(ret.h, port), tail)
    case _ :: tail => parseArgs(ret, tail)
    case _ => ret
  }
}

