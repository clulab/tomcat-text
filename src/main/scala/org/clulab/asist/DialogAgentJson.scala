/**
 * Authors:  Joseph Astier, Adarsh Pyarelal
 * Updated:  2021 March
 *
 * Translator for converting Json strings to data structures
 */
package org.clulab.asist

import org.json4s._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write}
import scala.util.control.Exception._

trait DialogAgentJson {

  /** Used so Json serializer can recognize case classes */
  implicit val formats = Serialization.formats(NoTypeHints)

  /** Serialize a DialogAgentMessageDataExtraction to a Json string 
   *  @param data the DialogAgentMessageDataExtraction struct
   *  @return The struct represented as a json string
   */
  def toJson(data: DialogAgentMessageDataExtraction): String = write(data)

  /** Serialize a DialogAgentMessage to a Json string 
   *  @param data the DialogAgentMessage struct
   *  @return The struct represented as a json string
   */
  def toJson(data: DialogAgentMessage): String = write(data)

  /** Serialize an AdaptAsrMessage to a Json string.
   *  @param data the AdaptAsrMessage struct
   *  @return The struct represented as a json string
   */
  def toJson(data: AdaptAsrMessage): String = write(data)

  /** Serialize an UazAsrMessage to a Json string.
   *  @param data the UazAsrMessage struct
   *  @return The struct represented as a json string
   */
  def toJson(data: UazAsrMessage): String = write(data)



  /** Deserialize a DialogAgentMessage from a Json string
   *  @param json a json representation of a DialogAgentMessage struct
   *  @return A DialogAgentMessage struct if the read succeeded, else None
   */
  def toDialogAgentMessage(json: String): Option[DialogAgentMessage] = 
    allCatch.opt(read[DialogAgentMessage](json))

  /** Deserialize a ChatMessage from a Json string
   *  @param json a json representation of a ChatMessage struct
   *  @return A ChatMessage struct if the read succeeded, else None
   */
  def toChatMessage(json: String): Option[ChatMessage] = 
    allCatch.opt(read[ChatMessage](json))

  /** Deserialize a UazAsrMessage from a Json string
   *  @param json a json representation of a UazAsrMessage struct
   *  @return An UazAsrMessage struct if the read succeeded, else None
   */
  def toUazAsrMessage(json: String): Option[UazAsrMessage] = 
    allCatch.opt(read[UazAsrMessage](json))

  /** Deserialize an AdaptAsrMessage from a Json string
   *  @param json a json representation of a AdaptAsrMessage struct
   *  @return An AdaptAsrMessage struct if the read succeeded, else None
   */
  def toAdaptAsrMessage(json: String): Option[AdaptAsrMessage] = 
    allCatch.opt(read[AdaptAsrMessage](json))
}
