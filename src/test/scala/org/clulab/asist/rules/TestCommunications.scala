package org.clulab.asist.rules

import org.clulab.asist.BaseTest
// For testing team communications

class TestCommunications extends BaseTest {

 // behavior of "team_communication.yml"



  passingTest should "parse help command" in {
    val text = "Come help me here!"
    val mentions = extractor.extractFrom(text)
    val helpee = DesiredMention("Self", "me")
    val location = DesiredMention("Deictic", "here")
    val inst = DesiredMention("HelpCommand", "help me here", Map("helpee" -> Seq(helpee), "location" ->Seq(location)))

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
    val text = "The tool is broken."
    val mentions = extractor.extractFrom(text)
    val toolBroken = DesiredMention("ToolBroken", "tool is broken",
      Map("tool" -> Seq(DesiredMention("Tool", "tool"))))

    testMention(mentions, toolBroken)
  }

  passingTest should "parse help offers" in {
    val text = "I can help you."
    val mentions = extractor.extractFrom(text)
    val you = DesiredMention("You","you")
    val save = DesiredMention("HelpOffer", "help you", Map("helpee" -> Seq(you)),Set(AGENT_SELF))

    testMention(mentions,save)
  }



}