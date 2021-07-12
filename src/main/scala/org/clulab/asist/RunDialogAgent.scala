package org.clulab.asist

import org.clulab.asist.agents._

import scala.annotation.tailrec

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 *  Updated:  2021 June
 *
 *  This application will run the DialogAgent on an input file, on the
 *  message bus, or interactively depending on user inputs.
 *
 *  The arguments are expected as an Array of string vaue with the element
 *  at index 0 being the run mode, and the remainder as key-value pairs:  
 *  
 *    Array("mode","key1","value1","key2","value2", ...)
 */

object RunDialogAgent extends App {
  
  // splash page if args are not understood
  val usageText = List(
    "",
    "usage:",
    "",
    "  RunDialogAgent {mqtt host port [-m taxonomy_matches]}",
    "                 {stdin [-m taxonomy_matches]}",
    "                 {file inputfile outputfile [-m taxonomy_matches]}",
    "                 {reprocess inputdir outputdir [-m taxonomy_matches]}",
    "",
    "       -m : maximum number of taxonomy matches, up to 5.  Defaults to 0.",
    "inputfile : supported file extensions are .vtt and .metadata (also handles directories containing files with those extensions)",
    "inputdir : A directory of .metadata files to be reprocessed by the DialogAgent",
    "outputdir : A directory where reprocessed .metadata files will be saved, using the same filenames",
    ""
  )

  // a dialog agent kept in global scope
  val agent = run(args.toList)

  /** Find a String value in the argument list
   * @param l A flat list of key value pairs
   * @param key A key to search for in the list
   * @return the string value for the key, else None
   */
  @tailrec
  def stringArg(l: List[String], key: String): Option[String] = l match {
    case (k::v::_) => if (k == key) Some(v) else stringArg(l.tail, key)
    case _ => None
  }

  /** Find an Int integer value in the argument list
   * @param l A flat list of key value pairs
   * @param key A key to search for in the list
   * @return the integer value for the key, else None
   */
  @tailrec
  def intArg(l: List[String], key: String): Option[Int] = l match {
    case (k::v::_) =>
      if (k == key) {
        try Some(v.toInt)
        catch {
          case e: Exception => None
        }
      } else intArg(l.tail, key)
    case _ => None
  }

  /** Run the Dialog Agent per user args.
   * @param argList A flat list of running mode then n key-value pairs
   * @returns A DialogAgent running in the mode with the args
   */
  def run(argList: List[String]): Option[DialogAgent] = {
    argList match {
      case ("mqtt"::host::port::l) =>
        val matches: Int = intArg(l, "-m").getOrElse(0)
        Some(new DialogAgentMqtt(host, port, matches))

      case ("file"::infile::outfile::l) =>
        val matches: Int = intArg(l, "-m").getOrElse(0)
        Some(new DialogAgentFile(infile, outfile, matches))

      case ("stdin"::l) =>
        val matches: Int = intArg(l, "-m").getOrElse(0)
        Some(new DialogAgentStdin(matches))

      case ("reprocess"::indir::outdir::l) =>
        val matches: Int = intArg(l, "-m").getOrElse(0)
        Some(new DialogAgentReprocessor(indir, outdir, matches))

      case ("tamu"::l) =>
        val matches: Int = intArg(l, "-m").getOrElse(0)
        Some(new DialogAgentTamu(matches))

      case _ =>
        usageText.foreach(println)
        None
    }
  }
}
