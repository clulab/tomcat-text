/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Updated:  2021 April
 *
 *  An interactive Dialog Agent that will return extractions for text entered
 *  on the command line 
 *
 *  @param nMatches Number of taxonomy_matches to return (up to 5)
 */
package org.clulab.asist

import java.util.Scanner
import org.json4s.jackson.Serialization.write

class DialogAgentStdin (
  override val nMatches: Int = 0
) extends DialogAgent { 

  println("\nDialog Agent stdin extractor running.")
  println("Enter plaintext for extraction, [CTRL-D] to exit.")

  print("\n> ")

  // console input
  val input = new Scanner(System.in)

  // Read keyboard input until user hits [CTRL-D]
  while (input.hasNextLine){
    val (extractions, extracted_doc) = extractor.runExtraction(input.nextLine)
    extractions.map(extraction).map(f => println(write(f)))
    print("\n> ")
  }
}
