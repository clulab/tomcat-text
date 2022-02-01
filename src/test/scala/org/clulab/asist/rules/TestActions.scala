package org.clulab.asist.rules

import org.clulab.asist.BaseTest

class TestActions extends BaseTest {

  val CLOSE = "Close"

  behavior of "AsistEngine"

   // Target: close
   passingTest should "Parse close events properly" in {

    val doc = extractor.annotate("I'm closing the door")
    val mentions = extractor.extractFrom(doc)

    val door_mention = DesiredMention(INFRASTRUCTURE, "door")
    val close_mention = DesiredMention(CLOSE, "closing the door",
      Map("target" -> Seq(door_mention)))

    testMention(mentions, door_mention)
    testMention(mentions, close_mention)
  }

  passingTest should "Parse toggle events properly" in {
    val doc =
      extractor.annotate("Open the door.")
    val mentions = extractor.extractFrom(doc)

    val door_mention = DesiredMention(INFRASTRUCTURE, "door")
    val open_mention = DesiredMention("Open", "Open the door",
      Map("target" -> Seq(door_mention)))

    testMention(mentions, door_mention)
    testMention(mentions, open_mention)
  }

  passingTest should "Parse save events properly" in {
    val doc = extractor.annotate("I'm going to save the villager over there")
    val mentions = extractor.extractFrom(doc)
    val deictic_mention = DesiredMention("Deictic", "there")
    val victim_mention = DesiredMention("Victim", "villager")
    val save_mention = DesiredMention("Save", "save the villager over there",
      Map("target" -> Seq(victim_mention),
        "location" -> Seq(deictic_mention)),
      Set(AGENT_SELF, FUTURE_TENSE)
    )

    testMention(mentions, save_mention)
  }


  passingTest should "Parse search lvo events properly" in {
    val doc =
      extractor.annotate("I search for the villagers inside the building")
    val mentions = extractor.extractFrom(doc)
    val infrastructure_mention = DesiredMention("Infrastructure", "building")
    val victim_mention = DesiredMention("Victim", "villagers")
    val search_mention = DesiredMention("Search", "search for the villagers",
      Map("target" -> Seq(victim_mention)),
      Set(AGENT_SELF)
    )
    val deictic_mention = DesiredMention("Deictic", "inside")

    testMention(mentions, search_mention)
    //    testMention(mentions, deictic_mention) // todo: we should determine some structure for locations
    testMention(mentions, infrastructure_mention)
  }

  passingTest should "Parse move events properly" in {
    val doc = extractor.annotate("run to the office.")
    val mentions = extractor.extractFrom(doc)
    val location= DesiredMention("Office", "office")
    val move_mention = DesiredMention("MoveTo", "run to the office", Map("target" -> Seq(location)))

    testMention(mentions, move_mention)
  }

  passingTest should "Parse move into events properly" in {
    val doc = extractor.annotate("I'm moving into the building.")
    val mentions = extractor.extractFrom(doc)

    val infra_mention = DesiredMention(INFRASTRUCTURE, "building")
    val move_mention = DesiredMention(
      "Enter", "moving into the building",
      Map("target" -> Seq(infra_mention)),
      Set(AGENT_SELF)
    )

    testMention(mentions, infra_mention)
    testMention(mentions, move_mention)
  }

  passingTest should "Parse clear events properly" in {
    val doc = extractor.annotate("I'm clearing this rebel.")
    val mentions = extractor.extractFrom(doc)

    val rubble_mention = DesiredMention("Rubble", "rebel")
    val clear_mention = DesiredMention(
      "Clear", "clearing this rebel",
      Map("target" -> Seq(rubble_mention)),
      Set(AGENT_SELF)
    )

    testMention(mentions, rubble_mention)
    testMention(mentions, clear_mention)
  }

  passingTest should "Parse clear locations events properly" in {
    val doc = extractor.annotate("I'm clearing this room.")
    val mentions = extractor.extractFrom(doc)

    val infra_mention = DesiredMention("Room", "room")
    val clear_mention = DesiredMention(
      "Clear", "clearing this room",
      Map("target" -> Seq(infra_mention)),
      Set(AGENT_SELF)
    )

    testMention(mentions, clear_mention)
  }

  passingTest should "Parse role switch events properly" in {
    val doc = extractor.annotate("Going to switch to medic.")
    val mentions = extractor.extractFrom(doc)

    val medic_mention = DesiredMention("Medic", "medic")
    val switch_mention = DesiredMention("RoleSwitch", "switch to medic",
      Map("target" -> Seq(medic_mention)))

    testMention(mentions, medic_mention)
    testMention(mentions, switch_mention)
  }

  passingTest should "Parse role change events properly" in {
    val doc = extractor.annotate("I am changing to searcher.")
    val mentions = extractor.extractFrom(doc)

    val searcher_mention = DesiredMention("Searcher", "searcher")
    val roleswitch_mention = DesiredMention("RoleSwitch", "changing to searcher",
      Map("target" -> Seq(searcher_mention)),
      Set(AGENT_SELF)
    )

    testMention(mentions, searcher_mention)
    testMention(mentions, roleswitch_mention)
  }

  passingTest should "Parse Block events properly" in {
    val doc = extractor.annotate("There is rubble blocking this door.")
    val mentions = extractor.extractFrom(doc)

    val rubble_mention = DesiredMention("Rubble", "rubble")
    val switch_mention = DesiredMention(INFRASTRUCTURE, "door")
    val block_mention = DesiredMention("Block", "rubble blocking this door",
      Map("source" -> Seq(rubble_mention),
        "target" -> Seq(switch_mention)))

    testMention(mentions, rubble_mention)
    testMention(mentions, switch_mention)
    testMention(mentions, block_mention)
  }

  passingTest should "Parse Block in the way events properly" in {
    val doc = extractor.annotate("There is rubble in the way of this door.")
    val mentions = extractor.extractFrom(doc)

    val rubble_mention = DesiredMention("Rubble", "rubble")
    val door_mention = DesiredMention(INFRASTRUCTURE, "door")
    val block_mention = DesiredMention("Block", "rubble in the way of this door",
      Map("source" -> Seq(rubble_mention),
        "target" -> Seq(door_mention)))

    testMention(mentions, rubble_mention)
    testMention(mentions, door_mention)
    testMention(mentions, block_mention)
  }

  passingTest should "Parse location report events properly" in {
    val doc = extractor.annotate("I am in the hallway.")
    val mentions = extractor.extractFrom(doc)

    val self_mention = DesiredMention("Self", "I")
    val infra_mention = DesiredMention(INFRASTRUCTURE, "hallway")
    val report_loc_mention = DesiredMention(
      "ReportLocation", "am in the hallway",
      Map("location" -> Seq(infra_mention)),
      Set(AGENT_SELF)
    )

    testMention(mentions, infra_mention)
    testMention(mentions, report_loc_mention)
  }
}
