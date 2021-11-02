package org.clulab.asist.rules

import org.clulab.asist.BaseTest

class EntityTest extends BaseTest {

  passingTest should "Recognize person and victim" in {
    val doc = extractor.annotate("My guy where are the victims?")
    val mentions = extractor.extractFrom(doc)

    val victim_mention = DesiredMention("Victim", "victims")

    testMention(mentions, victim_mention)
  }

  passingTest should "Recognize rubble, red, blue, green" in {
    val doc = extractor.annotate("Green, do you see any rubble here? This is red, one victim in sight. Can you confirm blue?")
    val mentions = extractor.extractFrom(doc)

    val red_mention = DesiredMention("Red", "red")
    val green_mention = DesiredMention("Green", "Green")
    val blue_mention = DesiredMention("Blue", "blue")
    val rubble_mention = DesiredMention("Rubble", "rubble")

    testMention(mentions, red_mention)
    testMention(mentions, green_mention)
    testMention(mentions, blue_mention)
    testMention(mentions, rubble_mention)
  }




}