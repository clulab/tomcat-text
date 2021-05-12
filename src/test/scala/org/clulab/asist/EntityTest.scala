package org.clulab.asist


class EntityTest extends BaseTest {

  passingTest should "Recognize person and victim" in {
    val doc = extractor.annotate("Look at this dude. My guy where are the victims?")
    val mentions = extractor.extractFrom(doc)

    val person1_mention = DesiredMention("Person", "dude")
    val person2_mention = DesiredMention("Person", "guy")
    val victim_mention = DesiredMention("Victim", "victims")

    testMention(mentions, person1_mention)
    testMention(mentions, person2_mention)
    testMention(mentions, victim_mention)
  }




}