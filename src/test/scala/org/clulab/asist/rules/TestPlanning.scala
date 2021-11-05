package org.clulab.asist.rules

import org.clulab.asist.BaseTest

class TestPlanning extends BaseTest {

  behavior of "AsistEngine"

  passingTest should "Capture planning events" in {
    val text =  "I'm going to save the victim."
    val mentions = extractor.extractFrom(text)


    val victim_men = DesiredMention("Victim", "victim")
    val save_men = DesiredMention("Save", "save the victim", Map("target" -> Seq(victim_men)), Set(FUTURE_TENSE,AGENT_SELF))
    val plan_men = DesiredMention("DeliberatePlan", "going to save the victim", Map("target" -> Seq(save_men)),Set(FUTURE_TENSE,AGENT_SELF))


    testMention(mentions, plan_men)
  }
}
