package org.clulab.asist.text

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
      "Where is it?"
    val mentions = extractor.extractFromText(text)
    val victim = DesiredMention("Victim", "victim")
    val one = DesiredMention("Entity", "one")
    val others = DesiredMention("Entity", "others")
    val they = DesiredMention("Entity", "they")
    val it = DesiredMention("Entity", "it")
    val desired1 = DesiredMention("LocationQuestion", "Where is the other victim", Map("topic" -> Seq(victim)))
    val desired2 = DesiredMention("LocationQuestion", "Where is the other one", Map("topic" -> Seq(one)))
    val desired3 = DesiredMention("LocationQuestion", "Where are the others", Map("topic" -> Seq(others)))
    val desired4 = DesiredMention("LocationQuestion", "Where are they", Map("topic" -> Seq(they)))
    val desired5 = DesiredMention("LocationQuestion", "Where is it", Map("topic" -> Seq(it)))
    testMention(mentions, desired1)
    testMention(mentions, desired2)
    testMention(mentions, desired3)
    testMention(mentions, desired4)
    testMention(mentions, desired5)

  }

  it should "find location questions with `location_moving_question` rule" in {
    val text = "Where did they go?"
    val mentions = extractor.extractFromText(text)
    val they = DesiredMention("Entity", "they")
    val move = DesiredMention("Move", "they go", Map("person" -> Seq(they)))
    val desired1 = DesiredMention("LocationQuestion", "Where did they go", Map("topic" -> Seq(move)))
    testMention(mentions, desired1)
  }

}
