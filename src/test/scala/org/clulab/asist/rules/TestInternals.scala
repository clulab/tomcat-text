package org.clulab.asist.rules

import org.clulab.asist.BaseTest

class TestInternals extends BaseTest {

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
}
