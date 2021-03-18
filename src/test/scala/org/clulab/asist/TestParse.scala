package org.clulab.asist

class TestParse extends BaseTest {

  val CLOSE = "Close"
  val INFRASTRUCTURE = "Infrastructure"

  behavior of "AsistEngine"

   failingTest should "Parse close events properly" in {

    val doc = extractor.annotate("I'm closing the door")
    val mentions = extractor.extractFrom(doc)

    val door = DesiredMention(INFRASTRUCTURE, "door")
    val doorArg = Arg("theme", Seq(door))
    val close = DesiredMention(CLOSE, "closing the door", Seq(doorArg))

    val found = getMentionsWithLabel(mentions, CLOSE)
    found should have size(1)

    testMention(found.head, close)

//    mentions.size should be(3)
//    mentions(0).label should be("Infrastructure")
//    mentions(1).label should be("Switch")
//    mentions(2).label should be("Close")
  }

  it should "Parse craft events properly" in {
    val doc = extractor.annotate("After crafting this sword, I push the button")
    val mentions = extractor.extractFrom(doc)
    mentions.size should be(1)
    mentions(0).label should be("Craft")
  }

  ignore should "Parse sight events properly" in {
    val doc = extractor.annotate("I think I see a victim over there")
    val mentions = extractor.extractFrom(doc)
    mentions.size should be(3)
    mentions(0).label should be("Deictic")
    mentions(1).label should be("Sight")
    mentions(2).label should be("Victim")
  }

  ignore should "Parse extinguish events properly" in {
    val doc = extractor.annotate("I'm going to put out the fire before leaving")
    val mentions = extractor.extractFrom(doc)
    mentions.size should be(2)
    mentions(0).label should be("Fire")
    mentions(1).label should be("Extinguish")
  }

  ignore should "Parse toggle events properly" in {
    val doc =
      extractor.annotate("He opened the door and walked into the sunset.")
    val mentions = extractor.extractFrom(doc)
    mentions.size should be(4)
    mentions(0).label should be("Toggle")
    mentions(1).label should be("Infrastructure")
    mentions(2).label should be("Switch")
    mentions(3).label should be("Deictic")
  }

  ignore should "Parse save events properly" in {
    val doc = extractor.annotate("I'm going to save the villager over there")
    val mentions = extractor.extractFrom(doc)
    mentions.size should be(3)
    mentions(0).label should be("Save")
    mentions(1).label should be("Deictic")
    mentions(2).label should be("Victim")
  }

  it should "Parse defeat events properly" in {
    val doc = extractor.annotate("To progress I'm going to kill the zombies")
    val mentions = extractor.extractFrom(doc)
    mentions.size should be(2)
    mentions(0).label should be("Foe")
    mentions(1).label should be("Defeat")
  }

  ignore should "Parse search events properly" in {
    val doc =
      extractor.annotate("I will search for the villagers inside the building")
    val mentions = extractor.extractFrom(doc)
    mentions.size should be(4)
    mentions(0).label should be("Search")
    mentions(1).label should be("Victim")
    mentions(2).label should be("Infrastructure")
    mentions(3).label should be("Deictic")
  }

  ignore should "Recognize fire entities" in {
    val doc =
      extractor.annotate("Inside, a flame was spreading through the kitchen")
    val mentions = extractor.extractFrom(doc)
    mentions.size should be(1)
    mentions(0).label should be("Fire")
  }

  it should "Recognize infrastructure entities" in {
    val doc = extractor.annotate(
      "The room was up the stairs, behind the first door on the left"
    )
    val mentions = extractor.extractFrom(doc)
    mentions.size should be(3)
    mentions(0).label should be("Infrastructure")
    mentions(1).label should be("Switch")
    mentions(2).label should be("Infrastructure")
  }

  it should "Recognize switch entities" in {
    val doc = extractor.annotate("The lever is behind the door")
    val mentions = extractor.extractFrom(doc)
    mentions.size should be(3)
    mentions(0).label should be("Switch")
    mentions(1).label should be("Infrastructure")
    mentions(2).label should be("Switch")
  }

  it should "Recognize foe entities" in {
    val doc = extractor.annotate(
      "Down the road there is a mob or a zombie."
    )
    val mentions = extractor.extractFrom(doc)
    val mention_map = getMentionCounter(mentions)
    mentions.size should be(3)
    mention_map("Foe") should be(2)
    mention_map("Deictic") should be(1)
  }

  it should "Recognize person entities" in {
    val doc =
      extractor.annotate("There's a guy over there, next to the other person")
    val mentions = extractor.extractFrom(doc)
    val mention_map = getMentionCounter(mentions)
    mentions.size should be(5)
    mention_map("Sight") should be(1)
    mention_map("Victim") should be(2)
    mention_map("Deictic") should be(2)
  }

  ignore should "Recognize commitments" in {
    val doc = extractor.annotate("I will rescue the victim in here")
    val mentions = extractor.extractFrom(doc)
    val mention_map = getMentionCounter(mentions)
    mentions.size should be(5)
    mention_map("Save") should be(1)
    mention_map("Commit") should be(1)
    mention_map("Victim") should be(1)
    mention_map("Deictic") should be(2)
  }

  it should "Recognize questions" in {
    val doc = extractor.annotate("What's that over there?")
    val mentions = extractor.extractFrom(doc)
    val mention_map = getMentionCounter(mentions)
    mentions.size should be(2)
    mention_map("Question") should be(1)
    mention_map("Deictic") should be(1)
  }

  it should "Recognize agreements" in {
    val doc = extractor.annotate("Ok, sounds good")
    val mentions = extractor.extractFrom(doc)
    val mention_map = getMentionCounter(mentions)
    mentions.size should be(1)
    mention_map("Agreement") should be(1)
  }

  it should "Recognize disagreements" in {
    val doc = extractor.annotate("No, I'm headed over here")
    val mentions = extractor.extractFrom(doc)
    val mention_map = getMentionCounter(mentions)
    mentions.size should be(2)
    mention_map("Disagreement") should be(1)
    mention_map("Deictic") should be (1)
  }
}
