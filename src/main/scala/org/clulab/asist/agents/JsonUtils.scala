package org.clulab.asist.agents

import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write}
import org.json4s.JField
import spray.json.DefaultJsonProtocol._
import spray.json.JsonParser
import scala.util.control.NonFatal
import com.typesafe.scalalogging.LazyLogging



object JsonUtils extends LazyLogging{

  /** Translate a structure to single-line JSON text
   *  @param a The structure to be translated
   */
  def writeJson[A <: AnyRef](a: A)(implicit formats: Formats): String = write(a)

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

  /** Deserialize a string into a case class 
   *  @param s JSON string to deserialize
   *  @return A case class defined by the JSON string 
   */
  def readJson[A <: Any](s: String)(implicit m: Manifest[A]): A = read[A](s)

}

