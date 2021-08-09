package org.clulab.asist.rules

import org.clulab.asist.BaseTest
// For testing team communications

class TestCommunications extends BaseTest {

 // behavior of "team_communication.yml"


  passingTest should "Parse existential constructions" in {
    val text = "There is a medkit in the library. "

    val mentions = extractor.extractFrom(text)
    val medkit = DesiredMention("MedKit", "medkit")
    val location = DesiredMention("Infrastructure", "library")
    val ex1 = DesiredMention("KnowledgeSharing", "There is a medkit in the library", Map("location" -> Seq(location), "exists" -> Seq(medkit)))

    testMention(mentions, ex1)

  }

  passingTest should "Parse existential constructions 2" in {
    val text =  "There is a victim in here."
    // fixme:{I dont know why this one fails! Help}
    val mentions = extractor.extractFrom(text)
    val deic = DesiredMention("Deictic", "here")
    val victim_men = DesiredMention("Victim", "victim")
    val ex2 = DesiredMention(
      "KnowledgeSharing",
      "There is a victim in here",
      Map("location" -> Seq(deic), "exists" -> Seq(victim_men))
    )

    testMention(mentions, ex2)
  }

  passingTest should "find RoomClear event with `room_clear` rule" in {
    val text = "leave this is why I believe this room is clear our remember to put aside the outside of the room to show that they're cleared"
    val mentions = extractor.extractFrom(text)
    val cleared = DesiredMention("RoomClear", "room is clear", Map("target" -> Seq(DesiredMention(INFRASTRUCTURE, "room"))))

    testMention(mentions, cleared)
  }

  passingTest should "Parse the found victim rule" in {
    val text = "I found a victim in the library. "

    val mentions = extractor.extractFrom(text)
    val victim = DesiredMention("Victim", "victim")
    val location = DesiredMention("Infrastructure", "library")
    val ex1 = DesiredMention("KnowledgeSharing", "found a victim in the library", Map("location" -> Seq(location), "exists" -> Seq(victim)), Set(PAST_TENSE))

    testMention(mentions, ex1)

  }

  passingTest should "Parse role switch events" in {
    val text = "I'm switching to engineer. "

    val mentions = extractor.extractFrom(text)
    val engineer = DesiredMention("Engineer", "engineer")
    val self = DesiredMention("Self","I")
    val ex1 = DesiredMention("RoleSwitch", "switching to engineer", Map("target" -> Seq(engineer)),Set(AGENT_SELF))

    testMention(mentions, ex1)

  }
}