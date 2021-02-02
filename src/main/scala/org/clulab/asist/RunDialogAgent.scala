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

  /** let the user know that the DialogAgent is run in MQTT or file mode */
  val usageText = List(
    "Usage",
    "mqtt <host> <portnum>",
    "or",
    "file <input filename> <output filename>",
  )


  /** display the usage hints */
  def usage: Option[DialogAgent] = {
    usageText.map(println)
    None
  }


  /** Read user args, use defaults if not found 
   *  @param args either "file infile outfile" or "mqtt host portnum"
   */
  def buildAgent(): Option[DialogAgent] = 
    if(args.length !=3) usage else args(0) match {
    case "mqtt" => Some(new DialogAgentMqtt(
      args(1), 
      args(2),
      topicSubObs = TOPIC_INPUT_OBS,
      topicSubAsr = TOPIC_INPUT_ASR,
      topicPub = TOPIC_OUTPUT
    ))
    case "file" => Some(new DialogAgentFile(args(1), args(2)))
    case _ => usage
  }
}

