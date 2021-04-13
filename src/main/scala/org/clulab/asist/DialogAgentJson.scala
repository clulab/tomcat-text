/**
 * Authors:  Joseph Astier, Adarsh Pyarelal
 *
 * Updated:  2021 March
 *
 * Json serialization and deserialization of messages
 */
package org.clulab.asist

import org.json4s._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write}
import scala.util.control.Exception._

trait DialogAgentJson {

  // Used so Json serializer can recognize case classes
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

  /** Serialize an AptimaAsrMessage to a Json string.
   *  @param data the AptimaAsrMessage struct
   *  @return The struct represented as a json string
   */
  def toJson(data: AptimaAsrMessage): String = write(data)

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

  /** Deserialize an AptimaAsrMessage from a Json string
   *  @param json a json representation of a AptimaAsrMessage struct
   *  @return An AptimaAsrMessage struct if the read succeeded, else None
   */
  def toAptimaAsrMessage(json: String): Option[AptimaAsrMessage] = 
    allCatch.opt(read[AptimaAsrMessage](json))

  /** Deserialize Metadata from a Json string
   *  @param json a json representation of a Metadata struct
   *  @return A Metadata struct if the read succeeded, else None
   */
  def toMetadataMessage(json: String): Option[MetadataMessage] = 
    allCatch.opt(read[MetadataMessage](json))
}
