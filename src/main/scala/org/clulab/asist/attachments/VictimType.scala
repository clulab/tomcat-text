package org.clulab.asist.attachments

import org.clulab.odin.Attachment

case class VictimType(value: String) extends Attachment {}

object VictimType {
  val CRITICAL = "critical"
  val REGULAR = "regular"
  val NONE = "none"
}