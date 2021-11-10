package org.clulab.asist.rules

import org.clulab.asist.BaseTest
// For testing team communications

class TestCommunications extends BaseTest {

 // behavior of "team_communication.yml"


  passingTest should "Parse existential constructions" in {
    val text = "There is a medkit in the library. "

    val mentions = extractor.extractFrom(text)
    val medkit = DesiredMention("MedKit", "medkit")
    val location = DesiredMention("Library", "library")
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
    val cleared = DesiredMention("RoomClear", "room is clear", Map("target" -> Seq(DesiredMention("Room", "room"))))

    testMention(mentions, cleared)
  }

  passingTest should "Parse the found victim rule" in {
    val text = "I found a victim in the library. "

    val mentions = extractor.extractFrom(text)
    val victim = DesiredMention("Victim", "victim")
    val location = DesiredMention("Library", "library")
    val ex1 = DesiredMention("KnowledgeSharing", "found a victim in the library", Map("location" -> Seq(location), "exists" -> Seq(victim)), Set(PAST_TENSE))

    testMention(mentions, ex1)

  }

  passingTest should "Parse role switch events" in {
    val text = "I'm switching to engineer. "

    val mentions = extractor.extractFrom(text)
    val engineer = DesiredMention("Engineer", "engineer")
    val ex1 = DesiredMention("RoleSwitch", "switching to engineer", Map("target" -> Seq(engineer)),Set(AGENT_SELF))

    testMention(mentions, ex1)

  }

  passingTest should "communicate open doors" in {
    val text = """
                 |I'm going to leave the door closed.
                 |Close the door.
                 |Shut the door when it's clear.
                 |I opened the door.
                 |the door was left open.
                 |I left the room.
                 |""".stripMargin

    val mentions = extractor.extractFrom(text)
    // sentence 1
    var desired = DesiredMention(
      "DoorClosed", "door closed",
      Map("status" -> Seq(DesiredMention("Close", "closed", attachments = Set(PAST_TENSE)))),
      Set(PAST_TENSE)) // isn't really, but a parser mistake tags `closed` as VBD
    testMention(mentions.filter(_.sentence==0), desired)
    // sentence 2
    desired = DesiredMention("DoorClosed", "Close the door")
    testMention(mentions.filter(_.sentence==1), desired, shallow = true)
    // sentence 3
    desired = DesiredMention("DoorClosed", "Shut the door")
    testMention(mentions.filter(_.sentence==2), desired, shallow = true)
    // sentence 4
    desired = DesiredMention("DoorOpen", "opened the door", attachments = Set(PAST_TENSE))
    testMention(mentions.filter(_.sentence==3), desired, shallow = true)
    // sentence 5
    desired = DesiredMention("DoorOpen", "door was left open", attachments = Set(PAST_TENSE))
    testMention(mentions.filter(_.sentence==4), desired, shallow = true)
    // sentence 6
    mentions.toArray.filter(m => m.sentence==5 && Seq("DoorOpen", "DoorClosed").contains(m.label)) shouldBe empty


  }

  passingTest should "parse instructions" in {
    val text = "Come help me here!"
    val mentions = extractor.extractFrom(text)
    val action = DesiredMention("GenericAction", "help")
    val inst = DesiredMention("Instruction", "Come help", Map("topic" -> Seq(action)))

    testMention(mentions, inst)
  }

  passingTest should "parse instructions with a topic" in {
    val text = "Help him save the victim!"
    val mentions = extractor.extractFrom(text)
    val victim = DesiredMention("Victim","victim")
    val save = DesiredMention("Save", "save the victim", Map("target" -> Seq(victim)),Set(AGENT_OTHER))
    val inst = DesiredMention("Instruction", "Help him save the victim", Map("topic" -> Seq(save)))

    testMention(mentions, inst)
  }


  passingTest should "parse simple help request" in {
    val text = "I need help."
    val mentions = extractor.extractFrom(text)
    val helpReq = DesiredMention("HelpRequest", "need help",Map.empty,Set(AGENT_SELF))

    testMention(mentions, helpReq)
  }

  passingTest should "parse complex help request" in {
    val text = "Can you help me here?"
    val mentions = extractor.extractFrom(text)
    val helpReq = DesiredMention("HelpRequest", "you help me here",
      Map("helper" -> Seq(DesiredMention("You", "you")),
        "location" -> Seq(DesiredMention("Deictic", "here"))
      ),
      Set(AGENT_SELF))

    testMention(mentions, helpReq)
  }

  passingTest should "parse tool broken" in {
    val text = "The hammer is broken."
    val mentions = extractor.extractFrom(text)
    val toolBroken = DesiredMention("ToolBroken", "hammer is broken",
      Map("tool" -> Seq(DesiredMention("Hammer", "hammer"))))

    testMention(mentions, toolBroken)
  }




}