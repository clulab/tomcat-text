/**
 * Authors:  Joseph Astier, Adarsh Pyarelal
 * Updated:  2021 January
 *
 * Json translator for message bus structures
 */
package org.clulab.asist

import org.json4s._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write}
import scala.util.control.Exception._

trait DialogAgentJson {

  /** Used so Json serializer can recognize case classes */
  implicit val formats = Serialization.formats(NoTypeHints)

  /** Serialize a DialogAgentMessage to Json */
  def toJson(da: DialogAgentMessage): String = write(da)

  /** Deserialize a DialogAgentMessage from Json */
  def toDialogAgentMessage(json: String): Option[DialogAgentMessage] = 
    allCatch.opt(read[DialogAgentMessage](json))

  /** Deserialize a ObsMessage from Json */
  def toObsMessage(json: String): Option[ObsMessage] = 
    allCatch.opt(read[ObsMessage](json))

  /** Deserialize a AsrMessage from Json */
  def toAsrMessage(json: String): Option[AsrMessage] = 
    allCatch.opt(read[AsrMessage](json))
}
