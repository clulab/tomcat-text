package org.clulab.asist.rules

import org.clulab.asist.BaseTest

class TestActions extends BaseTest {

  val CLOSE = "Close"
  val INFRASTRUCTURE = "Infrastructure"

  behavior of "AsistEngine"

   // Target: close
   passingTest should "Parse close events properly" in {

    val doc = extractor.annotate("I'm closing the door")
    val mentions = extractor.extractFrom(doc)

    val self_mention = DesiredMention("Self", "I")
    val door_mention = DesiredMention(INFRASTRUCTURE, "door")
    val close_mention = DesiredMention(CLOSE, "closing the door",
      Map("target" -> Seq(door_mention)))

    testMention(mentions, self_mention)
    testMention(mentions, door_mention)
    testMention(mentions, close_mention)
  }

  passingTest should "Parse toggle events properly" in {
    val doc =
      extractor.annotate("Open the door.")
    val mentions = extractor.extractFrom(doc)

    val switch_mention = DesiredMention("Switch", "door")
    val open_mention = DesiredMention("Toggle", "Open the door",
      Map("target" -> Seq(switch_mention)))

    testMention(mentions, switch_mention)
    testMention(mentions, open_mention)
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

  passingTest should "Parse search lvo events properly" in {
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

  passingTest should "Parse move events properly" in {
    val doc = extractor.annotate("Let's run over.")
    val mentions = extractor.extractFrom(doc)

    val move_mention = DesiredMention("Move", "run")

    testMention(mentions, move_mention)
  }

  tempFailingTest should "Parse move into events properly" in {
    val doc = extractor.annotate("I'm moving into the building.")
    val mentions = extractor.extractFrom(doc)

    val self_mention = DesiredMention("Self", "I")
    val infra_mention = DesiredMention(INFRASTRUCTURE, "building")
    val move_mention = DesiredMention("Move", "I'm moving into the building",
                        Map("person" -> Seq(self_mention),
                        "target" -> Seq(infra_mention)))

    testMention(mentions, self_mention)
    testMention(mentions, infra_mention)
    testMention(mentions, move_mention)
  }

  tempFailingTest should "Parse clear events properly" in {
    val doc = extractor.annotate("I'm clearing this rebel.")
    val mentions = extractor.extractFrom(doc)

    val self_mention = DesiredMention("Self", "I")
    val rubble_mention = DesiredMention("Rubble", "rebel")
    val clear_mention = DesiredMention("Clear", "I'm clearing this rebel",
      Map("agent" -> Seq(self_mention),
        "target" -> Seq(rubble_mention)))

    testMention(mentions, self_mention)
    testMention(mentions, rubble_mention)
    testMention(mentions, clear_mention)
  }

  tempFailingTest should "Parse clear locations events properly" in {
    val doc = extractor.annotate("I'm clearing this room.")
    val mentions = extractor.extractFrom(doc)

    val self_mention = DesiredMention("Self", "I")
    val infra_mention = DesiredMention(INFRASTRUCTURE, "room")
    val clear_mention = DesiredMention("Clear", "I'm clearing this room",
      Map("agent" -> Seq(self_mention),
        "target" -> Seq(infra_mention)))

    testMention(mentions, self_mention)
    testMention(mentions, infra_mention)
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

    val self_mention = DesiredMention("Self", "I")
    val searcher_mention = DesiredMention("Searcher", "searcher")
    val roleswitch_mention = DesiredMention("RoleSwitch", "I am changing to searcher",
      Map("agent" -> Seq(self_mention),
          "target" -> Seq(searcher_mention)))

    testMention(mentions, self_mention)
    testMention(mentions, searcher_mention)
    testMention(mentions, roleswitch_mention)
  }

  passingTest should "Parse Block events properly" in {
    val doc = extractor.annotate("There is rubble blocking this door.")
    val mentions = extractor.extractFrom(doc)

    val rubble_mention = DesiredMention("Rubble", "rubble")
    val switch_mention = DesiredMention("Switch", "door")
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
    val switch_mention = DesiredMention("Switch", "door")
    val block_mention = DesiredMention("Block", "rubble in the way of this door",
      Map("source" -> Seq(rubble_mention),
        "target" -> Seq(switch_mention)))

    testMention(mentions, rubble_mention)
    testMention(mentions, switch_mention)
    testMention(mentions, block_mention)
  }

  passingTest should "Parse location report events properly" in {
    val doc = extractor.annotate("I am in the hallway.")
    val mentions = extractor.extractFrom(doc)

    val self_mention = DesiredMention("Self", "I")
    val infra_mention = DesiredMention(INFRASTRUCTURE, "hallway")
    val report_loc_mention = DesiredMention("ReportLocation", "I am in the hallway",
      Map("person" -> Seq(self_mention),
        "location" -> Seq(infra_mention)))

    testMention(mentions, self_mention)
    testMention(mentions, infra_mention)
    testMention(mentions, report_loc_mention)
  }
}
