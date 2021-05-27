/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  updated:  2021 May
 *
 *  This application will run the YML to Markdown parser.
 *
 */
package org.clulab.asist

object RunRuleDemo extends App {
  
  // splash page if args are not understood
  val usageText = List(
    "",
    "usage:",
    "",
    "  RunRuleDemo odin inputfile outputfile",
    "",
    "  or",
    "",
    "  RunRuleDemo tomcat inputfile outputfile",
    "",
    "inputfile : supported file extensions are .yml and .yaml (also handles directories containing files with those extensions)",
    ""
  )

  // Keep in global scope
  val app = run(args.toList)

  /** Run the RuleDemo per user args.
   * @param argList A flat list of running mode then n key-value pairs
   * @returns A RuleDemo running in the mode with the args
   */
  def run(argList: List[String]): Option[RuleDemo] = argList match {
    case ("odin"::infile::outfile::l) => {
      Some(new OdinRuleDemo(infile, outfile))
    }
    case ("yml"::infile::outfile::l) => {
      Some(new YmlRuleDemo(infile, outfile))
    }
    case _ => {
      usageText.map(println)
      None
    }
  }
}
