package org.clulab.asist.attachments

import org.clulab.odin.{Attachment, Mention, TextBoundMention}
import org.clulab.struct.Interval

case class Agent(text: String, agentType: String, labels: Seq[String], span: Interval) extends Attachment {
  def mentionLike(m: Mention): TextBoundMention = {
    new TextBoundMention(
      labels,
      span,
      m.sentence,
      m.document,
      m.keep,
      m.foundBy,
      Set.empty
    )
  }
}

object Agent{
  // agentType values for use when creating an Agent Attachment
  val SELF = "Self"
  val YOU = "You"
  val TEAM = "Team"
  val OTHER = "Other"
}