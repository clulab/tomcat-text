/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Updated:  2021 March
 *
 *  An interactive Dialog Agent that will return extractions for text entered
 *  on the command line while running in sbt
 */
package org.clulab.asist

import java.util.Scanner

class DialogAgentStdin extends DialogAgent with DialogAgentJson{

  println
  println("Dialog Agent stdin extractor running.")
  println("Enter plaintext for extraction, [CTRL-C] to exit.")

  def prompt: Unit = print("\n> ")

  val input = new Scanner(System.in)

  prompt

  while (input.hasNextLine){
    val (extractions, extracted_doc) = extractor.runExtraction(input.nextLine)
    extractions.map(extraction).foreach(f => println(toJson(f)))
    prompt
  }
}

