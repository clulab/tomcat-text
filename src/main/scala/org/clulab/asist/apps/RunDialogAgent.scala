package org.clulab.asist.apps

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
    "  mqtt <host> <port> [-nochat]",
    "  stdin",
    "  file <inputfile> <outputfile> [-nochat]",
    "  reprocess <inputdir> <outputdir> [-v ta3_version_number] [-nochat]}",
    "",
    "-v         : Set the TA3 version number of reprocessed metadata files.",
    "             If not set, existing TA3 version numbers are incremented by 1",
    "inputfile  : supported file extensions are .vtt and .metadata",
    "              (also handles directories containing files with those extensions)",
    "outputfile : Processed file input will be written here.",
    "inputdir   : A directory of .metadata files to be reprocessed by the DialogAgent",
    "outputdir  : A directory where reprocessed .metadata files will be saved.",
    ""
  )

  /** Find the TA3 Version number arg in the arg list
   * @param argList A flat list of keys and values
   * @return The value if the key is found, else None
   */
  @tailrec
  def ta3Version(arglist: List[String]): Option[Int] = arglist match {
    case "-v"::version::l =>
      try Some(version.toInt)
      catch {
        case e: Exception => None
      }
    case head::l =>
      ta3Version(l)
    case _ => None
  }

  /** Check if the 'nochat' flag is set
   * @param argList A flat list of keys and values
   * @return true if the key is found
   */
  @tailrec
  def nochat(arglist: List[String]): Boolean = arglist match {
    case "--nochat"::l => true
    case head::l => nochat(l)
    case _ => false
  }

  /** Create a Dialog Agent per user args.
   * @param argList A flat list of running mode then n key-value pairs
   * @return A DialogAgent running in the mode with the args
   */
  args.toList match {
    case ("mqtt"::host::port::l) => 
      new DialogAgentMqtt(host, port, nochat(l))
    case ("file"::infile::outfile::l) =>
      new DialogAgentFile(infile, outfile, nochat(l))
    case ("stdin"::l) =>
      new DialogAgentStdin
    case ("reprocess"::indir::outdir::l) =>
      new DialogAgentReprocessor(indir, outdir, ta3Version(l))
    case _ =>
      usageText.foreach(println)
  }
}
