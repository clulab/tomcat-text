package org.clulab.asist

import scala.collection.mutable.ArraySeq

class TestParse extends BaseTest {

  val CLOSE = "Close"
  val INFRASTRUCTURE = "Infrastructure"

  behavior of "AsistEngine"

   failingTest should "Parse close events properly" in {

    val doc = extractor.annotate("I'm closing the door")
    val mentions = extractor.extractFrom(doc)

    val door = DesiredMention(INFRASTRUCTURE, "door")
    val close = DesiredMention(CLOSE, "closing the door", Map("theme" -> Seq(door)))

    val found = getMentionsWithLabel(mentions, CLOSE)
    found should have size(1)

    testMention(found.head, close)

//    mentions.size should be(3)
//    mentions(0).label should be("Infrastructure")
//    mentions(1).label should be("Switch")
//    mentions(2).label should be("Close")
  }

  failingTest should "Parse craft events properly" in {
    val doc = extractor.annotate("After crafting this sword, I push the button")
    val mentions = extractor.extractFrom(doc)
    mentions.size should be(1)
    mentions(0).label should be("Craft")
  }

  failingTest should "Parse sight events properly" in {
    val doc = extractor.annotate("I think I see a victim over there")
    val mentions = extractor.extractFrom(doc)
    mentions.size should be(3)
    mentions(0).label should be("Deictic")
    mentions(1).label should be("Sight")
    mentions(2).label should be("Victim")
  }

  failingTest should "Parse extinguish events properly" in {
    val doc = extractor.annotate("I'm going to put out the fire before leaving")
    val mentions = extractor.extractFrom(doc)
    mentions.size should be(2)
    mentions(0).label should be("Fire")
    mentions(1).label should be("Extinguish")
  }

  failingTest should "Parse toggle events properly" in {
    val doc =
      extractor.annotate("He opened the door and walked into the sunset.")
    val mentions = extractor.extractFrom(doc)
    mentions.size should be(4)
    mentions(0).label should be("Toggle")
    mentions(1).label should be("Infrastructure")
    mentions(2).label should be("Switch")
    mentions(3).label should be("Deictic")
  }

  passingTest should "Parse save events properly" in {
    val doc = extractor.annotate("I'm going to save the villager over there")
    val mentions = extractor.extractFrom(doc)

    val self_mention = DesiredMention("Self", "I")
    val victim_mention = DesiredMention("Victim", "villager")
    val save_mention = DesiredMention("Save", "save the villager",
                        Map("target" -> Seq(victim_mention)))
    val deictic_mention = DesiredMention("Deictic", "there")

    testMention(mentions, self_mention)
    testMention(mentions, save_mention)
    testMention(mentions, deictic_mention)
  }

  failingTest should "Parse defeat events properly" in {
    val doc = extractor.annotate("To progress I'm going to kill the zombies")
    val mentions = extractor.extractFrom(doc)
    mentions.size should be(2)
    mentions(0).label should be("Foe")
    mentions(1).label should be("Defeat")
  }

  passingTest should "Parse search events properly" in {
    val doc =
      extractor.annotate("I will search for the villagers inside the building")
    val mentions = extractor.extractFrom(doc)

    val self_mention = DesiredMention("Self", "I")
    val victim_mention = DesiredMention("Victim", "villagers")
    val search_mention = DesiredMention("Search", "I will search for the villagers",
                                        Map("person" -> Seq(self_mention),
                                          "target" -> Seq(victim_mention)))
    val deictic_mention = DesiredMention("Deictic", "inside")
    val infrastructure_mention = DesiredMention("Infrastructure", "building")

    testMention(mentions, search_mention)
//    testMention(mentions, deictic_mention) // todo: we should determine some structure for locations
    testMention(mentions, infrastructure_mention)
  }

  failingTest should "Recognize fire entities" in {
    val doc =
      extractor.annotate("Inside, a flame was spreading through the kitchen")
    val mentions = extractor.extractFrom(doc)
    mentions.size should be(1)
    mentions(0).label should be("Fire")
  }

  passingTest should "Recognize infrastructure entities" in {
    val doc = extractor.annotate(
      "The room was up the stairs, behind the first door on the left"
    )
    val mentions = extractor.extractFrom(doc)

    val room_mention = DesiredMention("Infrastructure", "room")
    val door_switch_mention = DesiredMention("Switch", "door")
    val door_infra_mention = DesiredMention("Infrastructure", "door")

    testMention(mentions, room_mention)
    testMention(mentions, door_switch_mention)
    testMention(mentions, door_infra_mention)
  }

  passingTest should "Recognize switch entities" in {
    val doc = extractor.annotate("The lever is behind the door")
    val mentions = extractor.extractFrom(doc)

    val lever_mention = DesiredMention("Switch", "lever")
    val door_switch_mention = DesiredMention("Switch", "door")
    val door_infra_mention = DesiredMention("Infrastructure", "door")

    testMention(mentions, lever_mention)
    testMention(mentions, door_switch_mention)
    testMention(mentions, door_infra_mention)
  }

  failingTest should "Recognize foe entities" in {
    val doc = extractor.annotate(
      "Down the road there is a mob or a zombie."
    )
    val mentions = extractor.extractFrom(doc)
    val mention_map = getMentionCounter(mentions)
    mentions.size should be(3)
    mention_map("Foe") should be(2)
    mention_map("Deictic") should be(1)
  }

  passingTest should "Recognize person entities" in {
    val doc =
      extractor.annotate("There's a guy over there, next to the other person")
    val mentions = extractor.extractFrom(doc)

    val guy_victim = DesiredMention("Person", "guy")
    val there_deictic = DesiredMention("Deictic", "there")
    val person_victim = DesiredMention("Person", "person")
    val sight_mention = DesiredMention("Sight", "'s a guy",
                          Map("target" -> Seq(guy_victim)))

    testMention(mentions, guy_victim)
    testMention(mentions, there_deictic)
    testMention(mentions, person_victim)
    testMention(mentions, sight_mention)
  }

  passingTest should "Recognize commitments" in {
    val doc = extractor.annotate("I will rescue the victim in here")
    val mentions = extractor.extractFrom(doc)

    val self_mention = DesiredMention("Self", "I")
    val victim_mention = DesiredMention("Victim", "victim")
    val deictic_mention = DesiredMention("Deictic", "here")
    val save_mention = DesiredMention("Save", "rescue the victim",
      Map("target"-> Seq(victim_mention)))
    val commitment_mention = DesiredMention("MakeCommitment", "I will rescue the victim",
                                            Map("person" -> Seq(self_mention),
                                                "target" -> Seq(save_mention)))
    testMention(mentions, self_mention)
    testMention(mentions, victim_mention)
    testMention(mentions, deictic_mention)
    testMention(mentions, save_mention)
    testMention(mentions, commitment_mention)
  }

  passingTest should "Recognize agreements" in {
    val doc = extractor.annotate("Yes, sounds good")
    val mentions = extractor.extractFrom(doc)

    val agree_mention = DesiredMention("Agreement", "Yes")

    testMention(mentions, agree_mention)
  }

  passingTest should "Recognize disagreements" in {
    val doc = extractor.annotate("No, I'm going over here")
    val mentions = extractor.extractFrom(doc)

    val disagree_mention = DesiredMention("Disagreement", "No")
    val self_mention = DesiredMention("Self", "I")
    val deictic_mention = DesiredMention("Deictic", "here")
    val move_mention = DesiredMention("Move", "I'm going over here",
      Map("person" -> Seq(self_mention),
          "target" -> Seq(deictic_mention)))

    testMention(mentions, disagree_mention)
    testMention(mentions, self_mention)
    testMention(mentions, deictic_mention)
    testMention(mentions, move_mention)
  }
}
