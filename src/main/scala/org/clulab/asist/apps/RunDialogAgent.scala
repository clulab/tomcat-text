package org.clulab.asist.apps

import com.typesafe.scalalogging.LazyLogging
import org.clulab.asist.agents._
import scopt.OParser

import scala.annotation.tailrec
import java.io.File




/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 *  This application will run the DialogAgent on the Message Bus, 
 *  file input, or interactively.
 *
 */

object RunDialogAgent extends App {

  case class Arguments(
    // optional flag to exclude Minecraft Chat messages from File or Mqtt input
    nochat: Boolean = false,

    // which Dialog Agent variant to run
    agent: String = "",

    // Mosquitto broker host for mqtt agent
    host: String = "",

    // Mosquitto broker port for mqtt agent
    port: String = "",

    // input location for file and reprocessor agents
    src: String = "",

    // output location for file and reprocessor agents
    dst: String = ""
  )

  val parser = new scopt.OptionParser[Arguments]("Parsing application") {

    head("scopt", "4.0.1")

    opt[String]("agent")
      .required()
      .valueName("<type>")
      .action((x, c) =>c.copy(agent = x))
      .text("One of [mqtt, reprocess, stdin, file]")

    opt[String]("host")
      .valueName("<name>")
      .action((x, c) =>c.copy(host = x))
      .text("Message Bus host machine name (mqtt agent)")

    opt[String]("port")
      .valueName("<number>")
      .action((x, c) =>c.copy(port = x))
      .text("Message Bus host machine port (mqtt agent)")

    opt[String]("src")
      .valueName("<filename or directory>")
      .action((x, c) =>c.copy(src = x))
      .text("input location (file and reprocessor agents)")

    opt[String]("dst")
      .valueName("<directory>")
      .action((x, c) =>c.copy(dst = x))
      .text("output location (file and reprocessor agents)")

    opt[Unit]("nochat")
      .action((_, c) => c.copy(nochat = true))
      .text("Optional flag to exclude Minecraft Chat messages (mqtt and file agents)")
  }

  def run(arguments: Arguments): Unit = arguments.agent.toLowerCase match {
    case "mqtt" =>
      println("MQTT agent:")
      println("host:  " + arguments.host) 
      println("port:  " + arguments.port) 
      if(arguments.nochat) println("Minecraft Chat messages not processed")
    case "file" =>
      println("File agent:")
      println("src:  " + arguments.src) 
      println("dst:  " + arguments.dst) 
      if(arguments.nochat) println("Minecraft Chat messages not processed")
    case "reprocess" =>
      println("Reprocess agent:")
      println("src:  " + arguments.src) 
      println("dst:  " + arguments.dst) 
    case "stdin" =>
      println("Stdin agent:")
    case _ =>
      println(f"Could not run agent '${arguments.agent}', valid agent types are [mqtt, reprocess, stdin, file]")

  }

  parser.parse(args, Arguments()) match {
    case Some(arguments) => run(arguments)
    case _ =>  // usage will be shown
  }
}


class Foo{

  
  // splash page if args are not understood
  val usageText = List(
    "",
    "usage:",
    "",
    "  mqtt <host> <port> [--nochat]",
    "  stdin",
    "  file <inputfile> <outputfile> [--nochat]",
    "  reprocess <inputdir> <outputdir> [-v ta3_version_number]}",
    "",
    "-v         : Set the TA3 version number of reprocessed metadata files.",
    "             If not set, existing TA3 version numbers are incremented by 1",
    "--nochat   : Exclude Minecraft Chat messages from processing",
    "inputfile  : supported file extensions are .vtt and .metadata",
    "              (also handles directories containing files with those extensions)",
    "outputfile : Processed file input will be written here.",
    "inputdir   : A directory of .metadata files to be reprocessed by the DialogAgent",
    "outputdir  : A directory where reprocessed .metadata files will be saved.",
    ""
  )

  /** Find the TA3 Version number arg in the arg list
   * @param argList A flat list of keys and values
   * @return The value if the key is found, else None
   */
  /*
  @tailrec
  def ta3Version(arglist: List[String]): Option[Int] = arglist match {
    case "-v"::version::l =>
      try Some(version.toInt)
      catch {
        case e: Exception => None
      }
    case head::l =>
      ta3Version(l)
    case _ => None
  }
  */

  /** Check if the 'nochat' flag is set
   * @param argList A flat list of keys and values
   * @return true if the key is found
   */
  /*
  @tailrec
  def nochat(arglist: List[String]): Boolean = arglist match {
    case "--nochat"::l => true
    case head::l => nochat(l)
    case _ => false
  }
  */

  /** Create a Dialog Agent per user args.
   * @param argList A flat list of running mode then n key-value pairs
   * @return A DialogAgent running in the mode with the args
   */
  /*
  args.toList match {
    case ("mqtt"::host::port::l) => 
      new DialogAgentMqtt(host, port, nochat(l))
    case ("file"::infile::outfile::l) =>
      new DialogAgentFile(infile, outfile, nochat(l))
    case ("stdin"::l) =>
      new DialogAgentStdin
    case ("reprocess"::indir::outdir::l) =>
      new DialogAgentReprocessor(indir, outdir, ta3Version(l))
    case _ =>

      usageText.foreach(println)
  }
  */
}
