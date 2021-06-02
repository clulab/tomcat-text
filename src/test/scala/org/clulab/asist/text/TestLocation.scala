package org.clulab.asist.text

import org.clulab.asist.BaseTest

class TestLocation extends BaseTest {

  behavior of "AsistEngine"

  failingTest should "Parse defeat events properly" in {
    val doc = extractor.annotate("To progress I'm going to kill the zombies")
    val mentions = extractor.extractFrom(doc)

    val self_mention = DesiredMention("Self", "I")
    val foe_mention = DesiredMention("Foe", "zombies")
    val defeat_mention = DesiredMention("Defeat", "I'm going to kill the zombies",
      Map("agent" -> Seq(self_mention),
        "target" -> Seq(foe_mention)))

    testMention(mentions, self_mention)
    testMention(mentions, foe_mention)
    testMention(mentions, defeat_mention)
  }
  /*
   // Target: location
   passingTest should "Parse location tokens properly" in {

    val doc = extractor.annotate("Go north, then turn east, continue west before moving south")
    val mentions = extractor.extractFrom(doc)

    val planning_mention = DesiredMention("Planning", "Before")
    val team_mention = DesiredMention("Team", "we")
    val precedence_mention = DesiredMention("Precedence", "Before")

    testMention(mentions, planning_mention)
    testMention(mentions, team_mention)
    testMention(mentions, precedence_mention)
  }
  */
}
