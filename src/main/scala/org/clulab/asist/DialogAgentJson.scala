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
   *  @param ex A DialogAgentMessageDataExtraction struct
   *  @return The struct represented as a json string
   */
  def toJson(ex: DialogAgentMessageDataExtraction): String = write(ex)

  /** Serialize a DialogAgentMessage to a Json string 
   *  @param da A DialogAgentMessage struct
   *  @return The struct represented as a json string
   */
  def toJson(da: DialogAgentMessage): String = write(da)

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

  /** Deserialize a AsrMessage from a Json string
   *  @param json a json representation of a AsrMessage struct
   *  @return An AsrMessage struct if the read succeeded, else None
   */
  def toAsrMessage(json: String): Option[AsrMessage] = 
    allCatch.opt(read[AsrMessage](json))
}
