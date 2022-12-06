package org.clulab.asist.agents

import buildinfo.BuildInfo
import java.util.Scanner
import org.clulab.asist.extraction.TomcatRuleEngine


/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 *  An interactive Dialog Agent that will return extractions for 
 *  text entered on the command line
 *
 */

class DialogAgentConsole (
  override val ruleEngine: TomcatRuleEngine = new TomcatRuleEngine
) extends DialogAgent(ruleEngine) { 

  // get rule engine lazy init out of the way
  startEngine()

  val v = BuildInfo.version
  println(s"\nDialog Agent text extractor version ${v}")
  println("Enter plaintext for extraction, two blank lines to exit.")

  // Console input
  val input:Scanner = new Scanner(System.in)

  var blankLines = 0

  // scan input until user enters two consecutive blank lines
  while(blankLines < 2) {
    print("\n> ")
    val text: String = input.nextLine
    if(text.isEmpty) {
      blankLines += 1
    }
    else {
      val extractions = ruleEngine.extractFrom(text, keepText = true)
      extractions.map(getExtraction).map(f => 
        println(JsonUtils.writeJson(f))
      )
      blankLines = 0
    }
  }

  println("\nExiting program...")
}
