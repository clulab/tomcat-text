package org.clulab.asist.agents

import com.typesafe.scalalogging.LazyLogging
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization.{read, write}

import scala.util.control.NonFatal

/**
 * General JSON utilities for use by any Dialog Agent
 */

object JsonUtils extends LazyLogging{

  // the "N/A" value with quotes indicates a missing value
  val notSet = "\"N/A\""

  /** Translate a structure to single-line JSON text
   *  @param a The structure to be translated
   */
  def writeJson[A <: AnyRef](a: A)(implicit formats: Formats): String 
    = write(a)

  /** Deserialize a string into a case class 
   *  @param s JSON string to deserialize
   *  @return A case class defined by the JSON string 
   */
  def readJson[A <: Any](s: String)(implicit m: Manifest[A]): A 
    = read[A](s)

  /** Remove fields with null values from JSON string 
   * @param input A JSON string that might have null values
   * @return The input string with all null-value fields removed
   */
  def removeNullFields (input: String): String = {
    val jvalue:JValue = parseJValue(input).getOrElse(JNothing)
    val map = Extraction.flatten(jvalue).filter{
      case (k: String, v: String) => (v != notSet)
    }
    writeJson(Extraction.unflatten(map))
  } 

  /** Parse a string into a JValue
   * @param line Hopefully JSON but could be anything the user tries to run
   * @return A JSON value parsed from the line or None
   */
  def parseJValue(line: String): Option[JValue] = try {
    Some(parse(s""" ${line} """))
  } catch {
    case NonFatal(t) =>
      logger.error(s"parseJValue: Could not parse: ${line}\n")
      logger.error(t.toString)
      None
  }
}
