package org.clulab.asist.agents

import buildinfo.BuildInfo
import java.util.Scanner

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 *  An interactive Dialog Agent that will return extractions for text entered
 *  on the command line
 *
 */

class DialogAgentStdin extends DialogAgent { 

  println(s"\nRunning Dialog Agent stdin extractor version ${BuildInfo.version}")
  println("Enter plaintext for extraction, [CTRL-D] to exit.")

  print("\n> ")

  // Console input
  val input = new Scanner(System.in)

  // Read keyboard input until user hits [CTRL-D]
  while (input.hasNextLine){
    val extractions = engine.extractFrom(input.nextLine, keepText = true)
    extractions.map(getExtraction).map(f => println(JsonUtils.writeJson(f)))
    print("\n> ")
  }
}
