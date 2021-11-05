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

  passingTest should "Capture conditional planning events" in {
    val text =  "If we remove the rubble, we can save the victim."
    val mentions = extractor.extractFrom(text)


    val victim_men = DesiredMention("Victim", "victim")
    val rubble_men = DesiredMention("Rubble", "rubble")
    val clear_men = DesiredMention("Clear", "remove the rubble", Map("target" -> Seq(rubble_men)), Set(AGENT_TEAM))
    val save_men = DesiredMention("Save", "save the victim", Map("target" -> Seq(victim_men)), Set(AGENT_TEAM))
    val commitment_men = DesiredMention("MakeCommitment", "can save the victim",Map("topic" -> Seq(save_men)),Set(AGENT_TEAM)
    )
    val plan_men = DesiredMention("ContingentPlan", "If we remove the rubble, we can save the victim",
      Map("condition" -> Seq(clear_men), "solution" -> Seq(commitment_men)))


    testMention(mentions, plan_men)
  }
}
