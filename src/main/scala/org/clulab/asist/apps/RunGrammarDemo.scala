package org.clulab.asist.apps
import org.clulab.asist.GrammarDemo

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 *  Updated:  2021 June
 *
 *  This application will run the YML to Markdown parser.
 *
 */
object RunGrammarDemo extends App {
  
  // splash page if args are not understood
  val usageText = List(
    "",
    "usage:",
    "",
    "  RunGrammarDemo <outputdir> <masterfile>",
    "",
    ""
  )

  // Keep in global scope
  val app = run(args.toList)

  /** Run the RuleDemo per user args.
   * @param argList A flat list of running mode then n key-value pairs
   * @return A RuleDemo running in the mode with the args
   */
  def run(argList: List[String]): Option[Any] = argList match {
    case List(outfile, masterPath) => Some(new GrammarDemo(outfile, masterPath))
    case _ =>
      usageText.foreach(println)
      None
  }
}
