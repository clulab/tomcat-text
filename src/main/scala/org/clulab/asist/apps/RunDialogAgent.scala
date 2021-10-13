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
    "  mqtt <host> <port> [--tdac <host> <port>]",
    "  stdin",
    "  file <inputfile> <outputfile>",
    "  reprocess <inputdir> <outputdir> [-v ta3_version_number] [--tdac <host> <port>]}",
    "",
    "-v         : Set the TA3 version number of reprocessed metadata files.",
    "             If not set, existing TA3 version numbers are incremented by 1",
    "inputfile  : supported file extensions are .vtt and .metadata",
    "              (also handles directories containing files with those extensions)",
    "outputfile : Processed file input will be written here.",
    "inputdir   : A directory of .metadata files to be reprocessed by the DialogAgent",
    "outputdir  : A directory where reprocessed .metadata files will be saved.",
    "--tdac     : Internet address of the TAMU dialogue act classifier.",
    ""
  )

  // a dialog agent kept in global scope
  val agent = run(args.toList)

  /** Find the TA3 Version number arg in the arg list
   * @param argList A flat list of keys and values
   * @return The value if the key is found, else None
   */
  def ta3Version(arglist: List[String]): Option[Int] = arglist match {
    case "-v"::version::l =>
      try Some(version.toInt)
      catch {
        case e: Exception => None
      }
    case head::tail =>
      ta3Version(tail)
    case _ => None
  }

  /** Find the TDAC server and port values in the arg list
   * @param argList A flat list of keys and values
   * @return The server and port values if the key is found, else (None, None)
   */
  def tdacUrl(
    arglist: List[String]
  ): Tuple2[Option[String], Option[String]] = arglist match {
    case "--tdac"::tdacHost::tdacPort::l =>
      (Some(tdacHost), Some(tdacPort))
    case head::tail =>
      tdacUrl(tail)
    case _ => 
      (None, None)
  }

  /** Run the Dialog Agent per user args.
   * @param argList A flat list of running mode then n key-value pairs
   * @return A DialogAgent running in the mode with the args
   */
  def run(argList: List[String]): Option[DialogAgent] = argList match {
    case ("mqtt"::host::port::l) => 
      val tdac = tdacUrl(l)
      Some(new DialogAgentMqtt(host, port, tdac._1, tdac._2))
    case ("file"::infile::outfile::l) =>
      Some(new DialogAgentFile(infile, outfile))
    case ("stdin"::l) =>
      Some(new DialogAgentStdin)
    case ("reprocess"::indir::outdir::l) =>
      val ta3  = ta3Version(l)
      val tdac = tdacUrl(l)
      Some(new DialogAgentReprocessor(indir, outdir, ta3, tdac._1, tdac._2))
    case _ =>
      usageText.foreach(println)
      None
  }
}
