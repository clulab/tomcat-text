package org.clulab.asist.rules

import org.clulab.asist.BaseTestStudy3

class Puzzle_Marker_TestStudy3 extends BaseTestStudy3 {

  passingTest should "recognize puzzle entities" in {
    val doc = extractor.annotate("meeting in a2. There is severe damage in D2.")
    val mentions = extractor.extractFrom(doc)

    val a2_mention = DesiredMention("A2", "a2")
    val meeting_mention = DesiredMention("Meeting", "meeting in a2", Map("location" -> Seq(a2_mention)))
    val a2_2_mention = DesiredMention("D2", "D2")
    val severe_mention = DesiredMention("SevereDamage", "severe")
    val damage_mention_full = DesiredMention("Damage", "severe damage in D2", Map("type" -> Seq(severe_mention), "location" -> Seq(a2_2_mention)))
    val damage_mention = DesiredMention("Damage", "damage")
    val existential_mention = DesiredMention("KnowledgeSharing", "There is severe damage in D2", Map("exists" -> Seq(damage_mention), "location" -> Seq(a2_2_mention)))

    testMention(mentions, meeting_mention)
    testMention(mentions, existential_mention)
    testMention(mentions, damage_mention_full)
  }

  passingTest should "Recognize players placing markers" in {
    val doc = extractor.annotate("I'm dropping a marker.")
    val mentions = extractor.extractFrom(doc)

    val marker_mention = DesiredMention("MarkerBlock", "marker")
    val place_mention = DesiredMention("PlaceMarker", "dropping a marker", Map("target" -> Seq(marker_mention)))


    testMention(mentions, place_mention)
  }

}