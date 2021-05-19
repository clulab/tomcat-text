package org.clulab.asist.attachments

import org.clulab.odin.Attachment

case class Agent(text: String, agentType: AgentType.agent) extends Attachment {}

object AgentType extends Enumeration {
  type agent = Value

  // todo: extend
  val Self, Other, You, Team = Value
}
