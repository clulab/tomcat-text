package org.clulab.asist

import com.typesafe.scalalogging.LazyLogging
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
    "                 {reprocess inputdir outputdir [-m taxonomy_matches] [-v ta3_version_number]}",
    "",
    "       -m : maximum number of taxonomy matches, up to 5.  Defaults to 0.",
    "       -v : Set the TA3 version number of reprocessed metadata files.",
    "            If not set, existing TA3 version numbers are incremented by 1",
    "inputfile : supported file extensions are .vtt and .metadata",
    "            (also handles directories containing files with those extensions)",
    "inputdir  : A directory of .metadata files to be reprocessed by the DialogAgent",
    "outputdir : A directory where reprocessed .metadata files will be saved.",
    ""
  )

  // a dialog agent kept in global scope
  val agent = run(args.toList)

  /** Find a String value in the argument list
   * @param l A flat list of arguments
   * @param key A key to search for in the list
   * @return the string value for the key, else None
   */
  @tailrec
  def stringArg(l: List[String], key: String): Option[String] = l match {
    case (k::v::_) => if (k == key) Some(v) else stringArg(l.tail, key)
    case _ => None
  }

  /** Find an Int integer value in the argument list
   * @param l A flat list of arguments
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

  /** Compose the arguments used by the Dialog Agent
   * @param l A flat list of arguments
   * @return An Args struct populated per the arg list
   */
  def readArgs(l: List[String]): DialogAgentArgs = DialogAgentArgs(
    nMatches = intArg(l, "-m").getOrElse(0),
    ta3Version = intArg(l, "-v"),
    withClassifications = l.contains("--with-classifications")
  )

  /** Run the Dialog Agent per user args.
   * @param argList A flat list of running mode then n key-value pairs
   * @return A DialogAgent running in the mode with the args
   */
  def run(argList: List[String]): Option[DialogAgent] = {
    argList match {
      case ("mqtt"::host::port::l) =>
        Some(new DialogAgentMqtt(host, port, readArgs(l)))
      case ("file"::infile::outfile::l) =>
        Some(new DialogAgentFile(infile, outfile, readArgs(l)))
      case ("stdin"::l) =>
        Some(new DialogAgentStdin(readArgs(l)))
      case ("reprocess"::indir::outdir::l) =>
        Some(new DialogAgentReprocessor(indir, outdir, readArgs(l)))
      case _ =>
        usageText.foreach(println)
        None
    }
  }
}
