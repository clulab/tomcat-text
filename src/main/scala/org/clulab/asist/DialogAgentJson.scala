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
  def toJson(a: DialogAgentMessage): String = write(a)

  /** Serialize a ObsMessage to Json */
  def toJson(a: ObsMessage): String = write(a)

  /** Serialize a AsrMessage to Json */
  def toJson(a: AsrMessage): String = write(a)
 
  /** Deserialize a DialogAgentMessage from Json */
  def toDialogAgentMessage(s: String): Option[DialogAgentMessage] = 
    allCatch.opt(read[DialogAgentMessage](s))

  /** Deserialize a ObsMessage from Json */
  def toObsMessage(s: String): Option[ObsMessage] = 
    allCatch.opt(read[ObsMessage](s))

  /** Deserialize a AsrMessage from Json */
  def toAsrMessage(s: String): Option[AsrMessage] = 
    allCatch.opt(read[AsrMessage](s))
}

