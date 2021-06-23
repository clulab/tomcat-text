package org.clulab.asist.rules

import org.clulab.asist.BaseTest
// For testing team communications

class TestCommunications extends BaseTest {

 // behavior of "team_communication.yml"


  passingTest should "Parse existential constructions" in {
    val text = "There is a medkit in the library. "

    val mentions = extractor.extractFrom(text)
    val medkit = DesiredMention("MedKit", "medkit")
    val location = DesiredMention("Infrastructure", "library")
    val ex1 = DesiredMention("KnowledgeSharing", "There is a medkit in the library", Map("location" -> Seq(location), "exists" -> Seq(medkit)))

    testMention(mentions, ex1)

  }

  failingTest should "Parse existential constructions 2" in {
    val text =  "There is a victim in here."
    // fixme:{I dont know why this one fails! Help}
    val mentions = extractor.extractFrom(text)
    val deic = DesiredMention("Deictic", "here")
    val victim_men = DesiredMention("Victim", "victim")
    val ex2 = DesiredMention("KnowledgeSharing", "There is a victim in here", Map("location" -> Seq(deic), "exists" -> Seq(victim_men)))

    testMention(mentions, ex2)
  }

}