package org.clulab.asist.rules

import org.clulab.asist.BaseTest

class CommitmentTest extends BaseTest {

  passingTest should "Recognize commitment in" in {
    val doc = extractor.annotate("I can save this guy.")
    val mentions = extractor.extractFrom(doc)

    val person_mention = DesiredMention("Victim", "guy")
    val action_mention = DesiredMention("Save", "save this guy",
      Map("target" -> Seq(person_mention)),
      Set(AGENT_SELF)
    )
    val commit1_mention = DesiredMention("MakeCommitment", "can save this guy",
      Map("topic" -> Seq(action_mention)),
      Set(AGENT_SELF)
    )

    testMention(mentions, action_mention)
    testMention(mentions, commit1_mention)
  }

  passingTest should "Recognize commitments" in {
    val doc = extractor.annotate("I will rescue the victim in here")
    val mentions = extractor.extractFrom(doc)

    val victim_mention = DesiredMention("Victim", "victim")
    val deictic_mention = DesiredMention("Deictic", "here")
    val save_mention = DesiredMention("Save", "rescue the victim in here",
      Map("target" -> Seq(victim_mention),
          "location" -> Seq(deictic_mention)),
      Set(AGENT_SELF, FUTURE_TENSE)
    )
    val commitment_mention = DesiredMention("DeliberatePlan", "will rescue the victim in here",
      Map("topic" -> Seq(save_mention)),
      Set(AGENT_SELF, FUTURE_TENSE)
    )
    testMention(mentions, victim_mention)
    testMention(mentions, save_mention)
    testMention(mentions, commitment_mention)
  }
}