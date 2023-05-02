package org.clulab.asist.attachments

import org.clulab.odin.Attachment

case class Negation (
  `type`: String = "Negation",
  negated: Boolean = true
) extends Attachment
