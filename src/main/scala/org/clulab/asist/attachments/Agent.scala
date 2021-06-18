package org.clulab.asist.attachments

import org.clulab.odin.Attachment

case class Agent(text: String, agentType: String) extends Attachment {}

object Agent{
  // agentType values for use when creating an Agent Attachment
  val SELF = "Self"
  val YOU = "You"
  val TEAM = "Team"
  val OTHER = "Other"
}