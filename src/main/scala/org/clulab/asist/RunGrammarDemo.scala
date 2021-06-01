/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  updated:  2021 May
 *
 *  This application will run the YML to Markdown parser.
 *
 */
package org.clulab.asist

object RunMarkdownDemo extends App {
  
  // splash page if args are not understood
  val usageText = List(
    "",
    "usage:",
    "",
    "  RunRuleDemo outputfile",
    "",
    ""
  )

  // Keep in global scope
  val app = run(args.toList)

  /** Run the RuleDemo per user args.
   * @param argList A flat list of running mode then n key-value pairs
   * @returns A RuleDemo running in the mode with the args
   */
  def run(argList: List[String]): Option[Any] = argList match {
    case (outfile::l) => Some(new GrammarDemo(outfile))
    case _ => {
      usageText.map(println)
      None
    }
  }
}
