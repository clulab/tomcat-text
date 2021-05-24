package org.clulab.asist.text

import org.clulab.asist.BaseTest
// For testing team communications

class TestCommunications extends BaseTest {

 // behavior of "team_communication.yml"


  passingTest should "Parse existential constructions" in {
    val text = "There is a medkit in the library. "

    val mentions = extractor.extractFromText(text)
    val medkit = DesiredMention("MedKit", "medkit")
    val location = DesiredMention("Infrastructure", "library")
    val ex1 = DesiredMention("KnowledgeSharing", "There is a medkit in the library", Map("location" -> Seq(location), "exists" -> Seq(medkit)))

    testMention(mentions, ex1)

  }

  passingTest should "Parse existential constructions 2" in {
    val text =  "There is a victim in here."

    val mentions = extractor.extractFromText(text)
    val here = DesiredMention("Deictic", "here")
    val victim = DesiredMention("Victim", "victim")
    val ex2 = DesiredMention("KnowledgeSharing", "There is a victim in here", Map("exists" -> Seq(victim), "location" -> Seq(here)))

  testMention(mentions, ex2)
}
}