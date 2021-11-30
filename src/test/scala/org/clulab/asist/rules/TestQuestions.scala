package org.clulab.asist.rules

import org.clulab.asist.BaseTest

class TestQuestions extends BaseTest {

  behavior of "questions.yml"

  it should "find location questions with `location_question` rule" in {
    // also tests: pro-form-etc, them_token_capture
    val text =
      "Where is the other victim? " +
      "Where is the other one? " +
      "Where are the others? " +
      "Where are they? " +
      "Where is it? " +
      "Where in the back room is it?"
    val mentions = extractor.extractFrom(text)
    val victim = DesiredMention("Victim", "victim")
    val one = DesiredMention("Entity", "one")
    val others = DesiredMention("Entity", "others")
    val they = DesiredMention("Entity", "they")
    val it = DesiredMention("Entity", "it")
    val backroom = DesiredMention("Room", "back room")
    val desired1 = DesiredMention("LocationQuestion", "Where is the other victim", Map("topic" -> Seq(victim)))
    val desired2 = DesiredMention("LocationQuestion", "Where is the other one", Map("topic" -> Seq(one)))
    val desired3 = DesiredMention("LocationQuestion", "Where are the others", Map("topic" -> Seq(others)))
    val desired4 = DesiredMention("LocationQuestion", "Where are they", Map("topic" -> Seq(they)))
    val desired5 = DesiredMention("LocationQuestion", "Where is it", Map("topic" -> Seq(it)))
    val desired6 = DesiredMention(
      "LocationQuestion",
      "Where in the back room is it",
      Map("topic" -> Seq(it), "location" -> Seq(backroom))
    )
    testMention(mentions, desired1)
    testMention(mentions, desired2)
    testMention(mentions, desired3)
    testMention(mentions, desired4)
    testMention(mentions, desired5)
    testMention(mentions, desired6)
  }

  it should "find location questions with `location_moving_question` rule" in {
    val text = "Where did they go?"
    val mentions = extractor.extractFrom(text)
    val move = DesiredMention("Move", "go", Map.empty, Set(PAST_TENSE, AGENT_ENTITY))
    val desired1 = DesiredMention("LocationQuestion", "Where did they go", Map("topic" -> Seq(move)), Set(PAST_TENSE))
    testMention(mentions, desired1)
  } //fixme: we need to make an exception to the obligatory move argument for this, perhaps a movement rule that is not being kept

  it should "handle information gathering questions" in {
    // information_gathering_question_that
    // information_gathering_question_clarification
    val text =
      "What's that over there? " +
      "What is the plan? " +
      "What is that?"
    val mentions = extractor.extractFrom(text)

    val that = DesiredMention("DemPron", "that")
    val there = DesiredMention("Deictic", "there")
    val plan = DesiredMention("PlanLanguage", "plan")
    //val q1 = DesiredMention("Question", "What's that over there", Map("topic" -> Seq(that), "location" -> Seq(there)))
    val q2 = DesiredMention("Question", "What is the plan", Map("topic" -> Seq(plan)))
    val q3 = DesiredMention("Question", "What is that", Map("topic" -> Seq(that)))
    //testMention(mentions, q1)
    testMention(mentions, q2)
    testMention(mentions, q3)
  }

  it should "handle contracted information gathering questions" in {
    val text = "What's that over there?"
    val mentions = extractor.extractFrom(text)
    val that = DesiredMention("DemPron", "that")
    val there = DesiredMention("Deictic", "there")
    val quest1 = DesiredMention("Question", "What's that over there", Map("topic" -> Seq(that), "location" ->Seq(there)))

    testMention(mentions,quest1)
  }

  it should "handle Yes_No questions" in {
    // information_gathering_question_that
    // information_gathering_question_clarification
    val text =
    "Do you see any rubble? " +
      "Can you save this guy? "
    val mentions = extractor.extractFrom(text)
    val rubble = DesiredMention("Rubble", "rubble")
    val person = DesiredMention("Victim", "guy")
    val see = DesiredMention("Sight", "see any rubble", Map("target" -> Seq(rubble)), Set(AGENT_YOU))
    val save = DesiredMention("Save", "save this guy", Map("target" -> Seq(person)), Set(AGENT_YOU))
    val q1 = DesiredMention("YesNoQuestion", "Do you see any rubble", Map("topic" -> Seq(see)), Set(AGENT_YOU))
    val q2 = DesiredMention("YesNoQuestion", "Can you save this guy", Map("topic" -> Seq(save)), Set(AGENT_YOU))
    testMention(mentions, q1)
    testMention(mentions, q2)
  }


//  it should "handle verb phrase topics"

}
