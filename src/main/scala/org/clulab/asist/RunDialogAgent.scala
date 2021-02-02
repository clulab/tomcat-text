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


  /** display the usage hints */
  def usage: Unit = List(
    "Usage",
    "mqtt <host> <port number>",
    "or",
    "file <input filename> <output filename>",
  ).map(println)


  /** Keep an instance of the agent in scope */
  val agent = buildAgent


  /** Build the DialogAgent to work with an MQTT broker 
   *  @param host the address of the MQTT broker
   *  @param port the address of the MQTT broker
   *  @return a new DialogAgentMqtt instance
   */ 
  def buildAgentMqtt(host: String, port: String): DialogAgentMqtt = {

    /** watch these MQTT topics for incoming messages */
    val TOPIC_INPUT_OBS = "observations/chat"
    val TOPIC_INPUT_ASR = "agent/asr"

    /** publish message analysis to this MQTT topic */
    val TOPIC_OUTPUT = "agent/tomcat_chatbot"

    new DialogAgentMqtt(
      host,
      port, 
      topicSubObs = TOPIC_INPUT_OBS,
      topicSubAsr = TOPIC_INPUT_ASR,
      topicPub = TOPIC_OUTPUT
    )
  }


  /** Build the DialogAgent to work with an MQTT broker 
   *  @param infile File to be read for processing
   *  @param outfile processed results are written here
   *  @return a new DialogAgentMqtt instance
   */ 
  def buildAgentFile(infile: String, outfile: String): DialogAgentFile =
    new DialogAgentFile(infile, outfile)



  /** Read user args, use defaults if not found 
   *  @param args (implicit) either "file infile outfile" or "mqtt host portnum"
   */
  def buildAgent: Option[DialogAgent] = if(args.length !=3) {
    usage 
    None
  } else args(0) match {
    case "mqtt" => Some(buildAgentMqtt(args(1), args(2))) 
    case "file" => Some(buildAgentFile(args(1), args(2)))
    case _ => {
      usage
      None
    }
  }
}

