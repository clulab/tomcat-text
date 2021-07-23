package org.clulab.asist.attachments

import org.clulab.odin.Attachment

import scala.util.matching.Regex

case class MarkerId(value: String) extends Attachment {}

object MarkerId {
  val ONE = "1"
  val TWO = "2"
  val THREE = "3"

  val onePattern: Regex = "(1|[oO]ne)".r
  val twoPattern: Regex = "(2|[tT]wo|[tT]oo?)".r
  val threePattern: Regex = "(3|[tT]hree)".r

  def apply(text: String): MarkerId = {
    text match {
      case onePattern(_) => new MarkerId(MarkerId.ONE)
      case twoPattern(_) => new MarkerId(MarkerId.TWO)
      case threePattern(_) => new MarkerId(MarkerId.THREE)
    }
  }

}
