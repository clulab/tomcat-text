package org.clulab.asist.attachments

import org.clulab.odin.Attachment
import org.slf4j.LoggerFactory

import scala.util.matching.Regex

case class MarkerId(value: String) extends Attachment {}

object MarkerId {
  private lazy val logger = LoggerFactory.getLogger(this.getClass())

  val ONE = "1"
  val TWO = "2"
  val THREE = "3"

  val onePattern: Regex = "(1|[oO]ne)".r
  val twoPattern: Regex = "(2|[tT]wo|[tT]oo?)".r
  val threePattern: Regex = "(3|[tT]hree)".r

  def apply(text: String): MarkerId = {
    text.toLowerCase match {
      case onePattern(_) => new MarkerId(MarkerId.ONE)
      case twoPattern(_) => new MarkerId(MarkerId.TWO)
      case threePattern(_) => new MarkerId(MarkerId.THREE)
      case _ =>
        logger.info(s"Unexpected markerId text: $text")
        new MarkerId(s"UNK($text)")
    }
  }

}
