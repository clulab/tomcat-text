package org.clulab.asist.attachments

import org.clulab.odin.Attachment

case class Negation(
  `type`: String = "Negation",
  value: Boolean = true
) extends Attachment 
