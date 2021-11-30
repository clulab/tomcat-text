package org.clulab.asist.attachments

import org.clulab.odin.Attachment

case class Tense(value: String) extends Attachment {}

object Tense {
  val FUTURE = "future"
  val PAST = "past"
  val PRESENT = "present"
}
