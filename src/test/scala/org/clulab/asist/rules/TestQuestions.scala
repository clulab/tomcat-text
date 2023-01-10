package org.clulab.asist.rules

import org.clulab.asist.BaseTest

class TestQuestions extends BaseTest {

  behavior of "questions.yml"


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



//  it should "handle verb phrase topics"

}
