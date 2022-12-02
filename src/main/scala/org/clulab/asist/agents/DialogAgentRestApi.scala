package org.clulab.asist.agents

import buildinfo.BuildInfo
import org.clulab.asist.extraction.TomcatRuleEngine


// Process HTTP requests containing text spans
// Generate HTTP responses with extractions

class DialogAgentRestApi (
  override val ruleEngine: TomcatRuleEngine = new TomcatRuleEngine
) extends DialogAgent(ruleEngine) {

  logger.info(s"DialogAgentRestApi version ${BuildInfo.version} running.")

  // get rule engine lazy init out of the way
  startEngine()

  // REST API main loop

  println("Exiting Dialog Agent REST API")
}

