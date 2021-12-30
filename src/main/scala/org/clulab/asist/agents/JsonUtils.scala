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
  def writeJson[A <: AnyRef](a: A)(implicit formats: Formats): String 
    = write(a)

  /** Remove fields with null values from JSON string 
   * @param input A JSON string that might have null values
   * @return The same JSON string with all null-value fields removed
   */
  def denullify (input: String): Unit = {
    val foo: Option[JValue] = parseJValue(input)
    foo.foreach(f => removeNulls(f))
  }

  def removeNulls(input: JValue): Unit = {
    val fields: Map[String, String] = Extraction.flatten(input)

    logger.info("Fields:")
    fields.keys.foreach{ key => 
      val value: String = fields(key)
        logger.info(s"Key = ${key}, value = ${value}")
    }
  }

/*
def removeNulls(jsObject: JsObject): JsValue = {
  JsObject(jsObject.fields.collect {
    case (s, j: JsObject) =>
      (s, removeNulls(j))
    case other if (other._2 != JsNull) =>
      other
  })
}
*/


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

