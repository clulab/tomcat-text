package org.clulab.asist

import java.util.Date
import org.clulab.asist.AsistEngine
import org.clulab.odin.Mention
import org.clulab.processors.Processor
import org.clulab.processors.fastnlp.FastNLPProcessor
import org.scalatest.{FlatSpec, Matchers}

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class TestParse extends FlatSpec with Matchers {
  val extractor = new AsistEngine()

  def getMentionMap(mentions: Vector[Mention]): mutable.Map[String, Int] = {
    val mention_map = mutable.Map[String, Int]()
    for (m <- mentions) {
      if (mention_map contains m.label) {
        mention_map (m.label) += 1
      } else {
        mention_map (m.label) = 1
      }
    }
    mention_map
  }

  "AsistEngine" should "Parse close events properly" in {
    val doc = extractor.annotate("I'm closing the door")
    val mentions = extractor.extractFrom(doc)
    mentions.size should be(3)
    mentions(0).label should be("Infrastructure")
    mentions(1).label should be("Switch")
    mentions(2).label should be("Close")
  }

  it should "Parse craft events properly" in {
    val doc = extractor.annotate("After crafting this sword, I push the button")
    val mentions = extractor.extractFrom(doc)
    mentions.size should be(1)
    mentions(0).label should be("Craft")
  }

  it should "Parse sight events properly" in {
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

  it should "Parse toggle events properly" in {
    val doc =
      extractor.annotate("He opened the door and walked into the sunset.")
    val mentions = extractor.extractFrom(doc)
    mentions.size should be(4)
    mentions(0).label should be("Toggle")
    mentions(1).label should be("Infrastructure")
    mentions(2).label should be("Switch")
    mentions(3).label should be("Deictic")
  }

  it should "Parse save events properly" in {
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

  it should "Parse search events properly" in {
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
    val mention_map = getMentionMap(mentions)
    mentions.size should be(3)
    mention_map("Foe") should be(2)
    mention_map("Deictic") should be(1)
  }

  it should "Recognize person entities" in {
    val doc =
      extractor.annotate("There's a guy over there, next to the other person")
    val mentions = extractor.extractFrom(doc)
    val mention_map = getMentionMap(mentions)
    mentions.size should be(5)
    mention_map("Sight") should be(1)
    mention_map("Victim") should be(2)
    mention_map("Deictic") should be(2)
  }

  it should "Recognize commitments" in {
    val doc = extractor.annotate("I will rescue the victim in here")
    val mentions = extractor.extractFrom(doc)
    val mention_map = getMentionMap(mentions)
    mentions.size should be(5)
    mention_map("Save") should be(1)
    mention_map("Commit") should be(1)
    mention_map("Victim") should be(1)
    mention_map("Deictic") should be(2)
  }

  it should "Recognize questions" in {
    val doc = extractor.annotate("What's that over there?")
    val mentions = extractor.extractFrom(doc)
    val mention_map = getMentionMap(mentions)
    mentions.size should be(2)
    mention_map("Question") should be(1)
    mention_map("Deictic") should be(1)
  }

  it should "Recognize agreements" in {
    val doc = extractor.annotate("Ok, sounds good")
    val mentions = extractor.extractFrom(doc)
    val mention_map = getMentionMap(mentions)
    mentions.size should be(1)
    mention_map("Agreement") should be(1)
  }

  it should "Recognize disagreements" in {
    val doc = extractor.annotate("No, I'm headed over here")
    val mentions = extractor.extractFrom(doc)
    val mention_map = getMentionMap(mentions)
    mentions.size should be(2)
    mention_map("Disagreement") should be(1)
    mention_map("Deictic") should be (1)
  }
}
