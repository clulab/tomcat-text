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
 *  file input, or interactively.
 *
 */

object RunDialogAgent extends App {

  case class Arguments(
    // optional flag to exclude Chat messages from File or Mqtt input
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
    dst: String = "",
  )

  val parser = new scopt.OptionParser[Arguments]("Parsing application") {
    head("University of Arizona Dialog Agent", BuildInfo.version)
    help("help").text("prints usage text")
    version("version").text("prints header text")
    cmd("mqtt")
      .action((_, c) =>c.copy(agent = "mqtt"))
      .children(
        arg[String]("host")
          .valueName("<String>")
          .action((x, c) =>c.copy(host = x))
          .text("Message Bus host machine name"),
        arg[String]("port")
          .valueName("<String>")
          .action((x, c) =>c.copy(port = x))
          .text("Message Bus host port name"),
        opt[Unit]("nochat")
          .action((_, c) => c.copy(nochat = true))
          .text("Optional flag to exclude Minecraft Chat messages")
        )
    cmd("file")
      .action((_, c) =>c.copy(agent = "file"))
      .children(
        arg[String]("src")
          .valueName("<String>")
          .action((x, c) =>c.copy(src = x))
          .text("input (directory or file)"),
        arg[String]("dst")
          .valueName("<String>")
          .action((x, c) =>c.copy(dst = x))
          .text("output directory"),
        opt[Unit]("nochat")
          .action((_, c) => c.copy(nochat = true))
          .text("Optional flag to exclude Minecraft Chat messages")
        )
    cmd("stdin")
      .action((_, c) =>c.copy(agent = "stdin"))
    cmd("reprocess")
      .action((_, c) =>c.copy(agent = "reprocess"))
      .children(
        arg[String]("src")
          .valueName("<String>")
          .action((x, c) =>c.copy(src = x))
          .text("input directory"),
        arg[String]("dst")
          .valueName("<String>")
          .action((x, c) =>c.copy(dst = x))
          .text("output directory"),
        )
  }

  def run(arguments: Arguments): Unit = arguments.agent match {
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
      println(f"Could not run agent '${arguments.agent}'")
      println("valid agent types are [mqtt, reprocess, stdin, file]")

  }

  parser.parse(args, Arguments()) match {
    case Some(arguments) => 
      run(arguments)
    case _ =>  // usage will be shown
  }
}

