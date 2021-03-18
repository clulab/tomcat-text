package org.clulab.asist.text

import org.clulab.asist.BaseTest

class TestDialogueA extends BaseTest {

  // TODO: move to BaseTest when we're done
  val DELTA = "Delta"
  val LOCATION = "Location"
  val MODIFIER = "Modifier"
  val VICTIM = "Victim"

  behavior of "DialogueA1"

  val text =
    """
      |Let me start like this. We'll see how things go Delta you can start in grab
      |to donate and get the right side of the map going up into 9 and 10 and brother
      |and myself outside will go to 345 working our way to the center than at 6
      |and 7 and then we'll see how they're doing. We'll see how many how high value
      |targets we find cuz we'll need to surround us up towards the end and I'll
      |convene her cup and Tackle us.""".stripMargin

  val mentions = extractor.extractFromText(text)

  passingTest should "extract delta" in {
    val delta = DesiredMention(DELTA, "Delta")
    testMention(mentions, delta)
  }

  failingTest should "extract long location" in {
    val location1 = DesiredMention(LOCATION, "right side of the map going up into 9 and 10")
    testMention(mentions, location1)
  }

  failingTest should "extract numbered rooms" in {
    val location345 = DesiredMention(LOCATION, "345")
    testMention(mentions, location345)

    val location67 = DesiredMention(LOCATION, "6 and 7")
    testMention(mentions, location67) // TODO: ??
  }

  failingTest should "extract center" in {
    val locationCenter = DesiredMention(LOCATION, "center")
    testMention(mentions, locationCenter)
  }

  failingTest should "extract targets with modifiers" in {
    // high value targets Victim("targets", mod="high value")
    val modifier = Arg("mod", Seq(DesiredMention(MODIFIER, "high value")))
    val targets = DesiredMention(VICTIM, "targets", Seq(modifier))
    testMention(mentions, targets)
  }

  behavior of "other text"

  passingTest should "do something" in {
    ()
  }

}
