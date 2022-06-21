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
    dst: String = "",
  )

  val parser = new scopt.OptionParser[Arguments]("Parsing application") {

    head("scopt", "4.0.1")

    arg[String]("agent")
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
      .valueName("<file or dir>")
      .action((x, c) =>c.copy(src = x))
      .text("input location (file and reprocessor agents)")

    opt[String]("dst")
      .valueName("<dir>")
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
    case Some(arguments) => 
      run(arguments)
    case _ =>  // usage will be shown
  }
}

