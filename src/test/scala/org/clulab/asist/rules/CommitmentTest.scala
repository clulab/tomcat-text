package org.clulab.asist.rules

import org.clulab.asist.BaseTest

class CommitmentTest extends BaseTest {

  passingTest should "Recognize commitment in" in {
    val doc = extractor.annotate("I can save this guy.")
    val mentions = extractor.extractFrom(doc)

    val self_mention = DesiredMention("Self", "I")
    val person_mention = DesiredMention("Victim", "guy")
    val action_mention = DesiredMention("Save", "save this guy",
      Map("target" -> Seq(person_mention)))
    val commit1_mention = DesiredMention("MakeCommitment", "I can save this guy",
      Map("agent" -> Seq(self_mention),
        "target" -> Seq(action_mention))
    )

    testMention(mentions, self_mention)
    testMention(mentions, action_mention)
    testMention(mentions, commit1_mention)
  }

  passingTest should "Recognize commitments" in {
    val doc = extractor.annotate("I will rescue the victim in here")
    val mentions = extractor.extractFrom(doc)

    val self_mention = DesiredMention("Self", "I")
    val victim_mention = DesiredMention("Victim", "victim")
    val deictic_mention = DesiredMention("Deictic", "here")
    val save_mention = DesiredMention("Save", "rescue the victim in here",
      Map("target" -> Seq(victim_mention),
          "location" ->Seq(deictic_mention)))
    val commitment_mention = DesiredMention("MakeCommitment", "I will rescue the victim in here",
      Map("agent" -> Seq(self_mention),
        "target" -> Seq(save_mention)))
    testMention(mentions, self_mention)
    testMention(mentions, victim_mention)
    testMention(mentions, deictic_mention)
    testMention(mentions, save_mention)
    testMention(mentions, commitment_mention)
  }
}