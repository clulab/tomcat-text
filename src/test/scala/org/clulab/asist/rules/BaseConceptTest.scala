package org.clulab.asist

class BaseConceptTest extends BaseTest {

  passingTest should "Recognize role entities" in {
    val doc = extractor.annotate("searcher search specialist. I'm a medic. He's an engineer.")
    val mentions = extractor.extractFrom(doc)

    val searcher1_mention = DesiredMention("Searcher", "searcher")
    val searcher2_mention = DesiredMention("Searcher", "search specialist")
    val medic_mention = DesiredMention("Medic", "medic")
    val engineer_mention = DesiredMention("Engineer", "engineer")

    testMention(mentions, searcher1_mention)
    testMention(mentions, searcher2_mention)
    testMention(mentions, medic_mention)
    testMention(mentions, engineer_mention)
  }

  passingTest should "Recognize call-sign entities" in {
    val doc = extractor.annotate("Hello alpha. Yes Delta. I'm Bravo.")
    val mentions = extractor.extractFrom(doc)

    val alpha_mention = DesiredMention("Alpha", "alpha")
    val delta_mention = DesiredMention("Delta", "Delta")
    val bravo_mention = DesiredMention("Bravo", "Bravo")

    testMention(mentions, alpha_mention)
    testMention(mentions, delta_mention)
    testMention(mentions, bravo_mention)
  }


}
