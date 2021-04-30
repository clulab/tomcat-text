/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  updated:  2021 April
 *
 *  This application will run the YML to Markdown parser.
 *
 */
package org.clulab.asist

object RunYmlReader extends App {
  
  // splash page if args are not understood
  val usageText = List(
    "",
    "usage:",
    "",
    "  RunYmlReader inputfile outputfile",
    "",
    "inputfile : supported file extensions are .yml and .yaml (also handles directories containing files with those extensions)",
    ""
  )

  // a yml reader kept in global scope
  val reader = run(args.toList)

  /** Run the YmlReader per user args.
   * @param argList A flat list of running mode then n key-value pairs
   * @returns A YmlReader running in the mode with the args
   */
  def run(argList: List[String]): Option[YmlReader] = argList match {
    case (infile::outfile::l) => {
      Some(new YmlReader(infile, outfile))
    }
    case _ => {
      usageText.map(println)
      None
    }
  }
}
