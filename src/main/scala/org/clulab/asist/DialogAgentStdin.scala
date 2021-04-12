/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Updated:  2021 March
 *
 *  An interactive Dialog Agent that will return extractions for text entered
 *  on the command line 
 *
 *  @param nMatches An optional maximum number of taxonomy_matches to return
 */
package org.clulab.asist

import java.util.Scanner

class DialogAgentStdin (
    override val nMatches: Int = 0
) extends DialogAgent 
    with DialogAgentJson {

  println("\nDialog Agent stdin extractor running.")
  println("Enter plaintext for extraction, [CTRL-D] to exit.")

  print("\n> ")

  val input = new Scanner(System.in)

  while (input.hasNextLine){
    val (extractions, extracted_doc) = extractor.runExtraction(input.nextLine)
    extractions.map(extraction).map(f => println(toJson(f)))
    print("\n> ")
  }
}
