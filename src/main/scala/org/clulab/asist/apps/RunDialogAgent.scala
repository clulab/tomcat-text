package org.clulab.asist.apps

import com.typesafe.scalalogging.LazyLogging
import org.clulab.asist.agents._
import scopt.OParser
import buildinfo.BuildInfo

import java.io.File

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 *  This application will run the DialogAgent on the Message Bus, 
 *  with file input, or interactively.
 *
 */

object RunDialogAgent extends App with LazyLogging {

  case class Arguments(
    // optional flag to exclude Chat messages from File or Mqtt input
    nochat: Boolean = false,

    // which Dialog Agent variant to run
    agent: String = "",

    // Mosquitto broker host for mqtt agent
    host: String = "localhost",

    // Mosquitto broker port for mqtt agent
    port: Int = 1883,

    // input location for file and reprocessor agents
    src: String = "",

    // output location for file and reprocessor agents
    dst: String = "",

    // Optional TA3 file version for reprocessor
    ta3_version: Option[Int] = None
  )

  // set up the parser to use the Dialog Agent command line arguments
  val parser = new scopt.OptionParser[Arguments]("Parsing application") {
    head("University of Arizona Dialog Agent", BuildInfo.version)
    help("help").text("prints usage text")
    version("version").text("prints header text")
    cmd("mqtt")
      .action((_, c) => c.copy(agent = "mqtt"))
      .children(
        opt[String]("host")
          .valueName("<String>")
          .action((x, c) => c.copy(host = x))
          .text("Optional MQTT broker host machine. Defaults to localhost if not set"),
        opt[Int]("port")
          .valueName("<Int>")
          .action((x, c) => c.copy(port = x))
          .text("Optional MQTT broker host port. Defaults to 1883 if not set"),
        opt[Unit]("nochat")
          .action((_, c) => c.copy(nochat = true))
          .text("Optional flag to exclude Minecraft Chat messages")
        )
    cmd("file")
      .action((_, c) => c.copy(agent = "file"))
      .children(
        arg[String]("src")
          .valueName("<String>")
          .action((x, c) => c.copy(src = x))
          .text("Input file or directory"),
        arg[String]("dst")
          .valueName("<String>")
          .action((x, c) => c.copy(dst = x))
          .text("Output file"),
        opt[Unit]("nochat")
          .action((_, c) => c.copy(nochat = true))
          .text("Optional flag to exclude Minecraft Chat messages")
        )
    cmd("stdin")
      .action((_, c) => c.copy(agent = "stdin"))
    cmd("reprocess")
      .action((_, c) => c.copy(agent = "reprocess"))
      .children(
        arg[String]("src")
          .valueName("<String>")
          .action((x, c) => c.copy(src = x))
          .text("Input directory"),
        arg[String]("dst")
          .valueName("<String>")
          .action((x, c) => c.copy(dst = x))
          .text("Output directory"),
        opt[Int]("v")
          .action((x, c) => c.copy(ta3_version = Some(x)))
          .text("Optional TA3 version number for reprocessed files. Existing TA3 version numbers are incremented by 1 if not set")
        )
  }

  // Run the appropriate agent for the given arguments
  def run(arguments: Arguments): Unit = arguments.agent match {
    case "mqtt" =>
      logger.info("Starting MQTT agent...")
      logger.info("  host: " + arguments.host) 
      logger.info("  port: " + arguments.port) 
      if(arguments.nochat) logger.info("Minecraft Chat messages not processed")
      new DialogAgentMqtt(arguments.host, arguments.port, arguments.nochat)
    case "file" =>
      logger.info("Starting File agent...")
      logger.info("  input: " + arguments.src) 
      logger.info("  output file: " + arguments.dst) 
      if(arguments.nochat) logger.info("Minecraft Chat messages not processed")
      new DialogAgentFile(arguments.src, arguments.dst, arguments.nochat)
    case "reprocess" =>
      logger.info("Starting Reprocessor agent...")
      logger.info("  input dir: " + arguments.src) 
      logger.info("  output dir: " + arguments.dst) 
      arguments.ta3_version.foreach(v => 
        logger.info("  ta3 version: " + v)
      )
      new DialogAgentReprocessor(
        arguments.src,
        arguments.dst,
        arguments.ta3_version
      )
    case "stdin" =>
      logger.info("Starting Stdin agent...")
      new DialogAgentStdin
    case _ =>
      logger.error(f"Could not run agent '${arguments.agent}'")
      logger.error("valid agent types are [mqtt, reprocess, stdin, file]")
  }

  // Run the arguments if parsing was successful
  parser.parse(args, Arguments()) match {
    case Some(arguments) => 
      run(arguments)
    case _ =>  // usage will be shown otherwise
  }
}

