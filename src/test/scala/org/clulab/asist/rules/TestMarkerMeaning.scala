package org.clulab.asist.rules

import org.clulab.asist.BaseTest
import org.clulab.asist.attachments.VictimType

class TestMarkerMeaning extends BaseTest {

  val MM = "MarkerMeaning"
  val NO_VICTIM: String = "NoVictim"
  val REG_VICTIM: String = "RegularVictim"
  val CRIT_VICTIM: String = "CriticalVictim"
  def victimType(s: String) = s"VictimType($s)"
  def markerId(s: String): String = s"MarkerId($s)"

  behavior of "marker_meanings.yml"

  it should "find marker meanings with `marker_meaning` rule" in {
    val text = "but let's let standardized that for the three of us and just understand that one is no victim two is regular victim and three is for critical victim"
    val mentions = extractor.extractFrom(text)
    val one = DesiredMention(
      MM,
      "one is no victim",
      Map("meaning" -> Seq(DesiredMention(NO_VICTIM, "no victim"))),
      Set(victimType(VictimType.NONE), markerId("1"))
    )
    val two = DesiredMention(
      MM,
      "two is regular victim",
      Map("meaning" -> Seq(DesiredMention(REG_VICTIM, "regular victim"))),
      Set(victimType(VictimType.REGULAR), markerId("2"))
    )
    val three = DesiredMention(
      MM,
      "three is for critical victim",
      Map("meaning" -> Seq(DesiredMention(CRIT_VICTIM, "critical victim"))),
      Set(victimType(VictimType.CRITICAL), markerId("3"))
    )

    testMention(mentions, one)
    testMention(mentions, two)
    testMention(mentions, three)

  }

  it should "find marker meanings with `marker_meaning_token` rule" in {
    val text = "I'm taking the same same tactic as last time I started with the Searchers looks like our numbers mean the same thing as well regular victim has one no victim or clear drum its 2 and I'm critical victims are three"
    val mentions = extractor.extractFrom(text)
    val one = DesiredMention(
      MM,
      "regular victim has one",
      Map("meaning" -> Seq(DesiredMention(REG_VICTIM, "regular victim"))),
      Set(victimType(VictimType.REGULAR), markerId("1"))
    )
    val three = DesiredMention(
      MM,
      "critical victims are three",
      Map("meaning" -> Seq(DesiredMention(CRIT_VICTIM, "critical victims"))),
      Set(victimType(VictimType.CRITICAL), markerId("3"))
    )

    testMention(mentions, one)
    testMention(mentions, three)

  }

  it should "find marker meanings with `marker_meaning_token_flipped` rule" in {
    val text = "my screen shows number one says no victim here and number two is regular victim here"
    val mentions = extractor.extractFrom(text)
    val one = DesiredMention(
      MM,
      "one says no victim",
      Map("meaning" -> Seq(DesiredMention(NO_VICTIM, "no victim"))),
      Set(victimType(VictimType.NONE), markerId("1"))
    )
    val two = DesiredMention(
      MM,
      "two is regular victim",
      Map("meaning" -> Seq(DesiredMention(REG_VICTIM, "regular victim"))),
      Set(victimType(VictimType.REGULAR), markerId("2"))
    )

    testMention(mentions, one)
    testMention(mentions, two)

  }

  it should "find marker meanings with `marker_meaning_two_misparsed` rule" in {
    val text = "one online one indicates a regular victim to indicates no victim and then three and debate indicate the critical victim"
    val mentions = extractor.extractFrom(text)
    val one = DesiredMention(
      MM,
      "one online one indicates a regular victim",
      Map("meaning" -> Seq(DesiredMention(REG_VICTIM, "regular victim"))),
      Set(victimType(VictimType.REGULAR), markerId("1"))
    )
    // this is the part that _actually_ tests the rule, the others are for good measure
    val two = DesiredMention(
      MM,
      "to indicates no victim",
      Map("meaning" -> Seq(DesiredMention(NO_VICTIM, "no victim"))),
      Set(victimType(VictimType.NONE), markerId("2"))
    )
    val three = DesiredMention(
      MM,
      "three and debate indicate the critical victim",
      Map("meaning" -> Seq(DesiredMention(CRIT_VICTIM, "critical victim"))),
      Set(victimType(VictimType.CRITICAL), markerId("3"))
    )

    testMention(mentions, one)
    testMention(mentions, two)
    testMention(mentions, three)

  }
}
