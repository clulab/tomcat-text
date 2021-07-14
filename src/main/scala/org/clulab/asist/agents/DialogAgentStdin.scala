package org.clulab.asist.agents

import java.util.Scanner

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 *  Updated:  2021 June
 *
 *  An interactive Dialog Agent that will return extractions for text entered
 *  on the command line
 *
 *  @param nMatches Number of taxonomy_matches to return (up to 5)
 */

class DialogAgentStdin (
  override val args: DialogAgentArgs = new DialogAgentArgs
) extends DialogAgent { 

  println("\nDialog Agent stdin extractor running.")
  println("Enter plaintext for extraction, [CTRL-D] to exit.")

  print("\n> ")

  // console input
  val input = new Scanner(System.in)

  // Read keyboard input until user hits [CTRL-D]
  while (input.hasNextLine){
    val extractions = extractor.extractFrom(input.nextLine, keepText = true)
    extractions.map(extraction).map(f => println(writeJson(f)))
    print("\n> ")
  }
}
