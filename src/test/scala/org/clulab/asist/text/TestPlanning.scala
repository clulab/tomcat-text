package org.clulab.asist.text

import org.clulab.asist.BaseTest

class TestPlanning extends BaseTest {

  behavior of "AsistEngine"

   // Target: close
   passingTest should "Parse planning tokens properly" in {

    val doc = extractor.annotate("Before that, we should continue.")
    val mentions = extractor.extractFrom(doc)

    val planning_mention = DesiredMention("Planning", "Before")
    val team_mention = DesiredMention("Team", "we")
    val precedence_mention = DesiredMention("Precedence", "Before")

    testMention(mentions, planning_mention)
    testMention(mentions, team_mention)
    testMention(mentions, precedence_mention)
  }
}
