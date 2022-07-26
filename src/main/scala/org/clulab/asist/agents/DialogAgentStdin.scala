package org.clulab.asist.agents

import scala.annotation.tailrec
import buildinfo.BuildInfo
import java.util.Scanner

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 *  An interactive Dialog Agent that will return extractions for 
 *  text entered on the command line
 *
 */

class DialogAgentStdin extends DialogAgent { 

  // get rule engine lazy init out of the way
  startEngine()

  val v = BuildInfo.version
  println(s"\nDialog Agent standard input text extractor version ${v}")
  println("Enter plaintext for extraction, two blank lines to exit.")

  // Console input
  val input:Scanner = new Scanner(System.in)

  readInput(0)
  println("\nExiting program...")

  @tailrec
  private def readInput(blankLines: Int): Unit = if(blankLines < 2) {
    print("\n> ")
    val text: String = input.nextLine
    if(text.isEmpty) {
      readInput(blankLines + 1)
    }
    else {
      val extractions = engine.extractFrom(text, keepText = true)
      extractions.map(getExtraction).map(f => 
        println(JsonUtils.writeJson(f))
      )
      readInput(0)
    }
  }
}
