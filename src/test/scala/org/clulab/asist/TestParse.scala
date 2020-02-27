package org.clulab.asist

import org.clulab.asist.AsistEngine
import org.clulab.processors.Processor
import org.clulab.processors.fastnlp.FastNLPProcessor
import org.scalatest.{FlatSpec, Matchers}

class TestParse extends FlatSpec with Matchers {
    val extractor = new AsistEngine() 
    
    "AsistEngine" should "Parse close events properly" in {
        val doc = extractor.annotate("I will close the door")
        val mentions = extractor.extractFrom(doc)
        mentions(0).label should be ("Infrastructure")
        mentions(1).label should be ("Switch")
        mentions(2).label should be ("Close")
    }

    it should "Parse craft events properly" in {
        val doc = extractor.annotate("After crafting this sword, I push the button")
        val mentions = extractor.extractFrom(doc)
        mentions(0).label should be ("Craft")
    }

    it should "Parse sight events properly" in {
        val doc = extractor.annotate("I think I see something over there")
        val mentions = extractor.extractFrom(doc)
        mentions(0).label should be ("Sight")
    }
    
    it should "Parse extinguish events properly" in {
        val doc = extractor.annotate("I'm going to put out the fire before leaving")
        val mentions = extractor.extractFrom(doc)
        mentions(0).label should be ("Fire")
        mentions(1).label should be ("Extinguish")
    }

    it should "Parse toggle events properly" in {
        val doc = extractor.annotate("He opened the door and walked into the sunset.")
        val mentions = extractor.extractFrom(doc)
        mentions(0).label should be ("Infrastructure")
        mentions(1).label should be ("Switch")
        mentions(2).label should be ("Toggle")
    }

    it should "Parse save events properly" in {
        val doc = extractor.annotate("I'm going to save the villager over there")
        val mentions = extractor.extractFrom(doc)
        mentions(0).label should be ("Neutral")
        mentions(1).label should be ("Save")
    }

    it should "Parse defeat events properly" in {
        val doc = extractor.annotate("To progress I will kill the zombies")
        val mentions = extractor.extractFrom(doc)
        mentions(0).label should be ("Foe")
        mentions(1).label should be ("Defeat")
    }

    it should "Parse search events properly" in {
        val doc = extractor.annotate("I will search for the villagers inside the building")
        val mentions = extractor.extractFrom(doc)
        mentions(0).label should be ("Search")
        mentions(1).label should be ("Neutral")
        mentions(2).label should be ("Infrastructure")
    }

    it should "Recognize fire entities" in {
        val doc = extractor.annotate("Inside, a flame was spreading through the kitchen")
        val mentions = extractor.extractFrom(doc)
        mentions(0).label should be ("Fire") 
    }

    it should "Recognize infrastructure entities" in {
        val doc = extractor.annotate("The room was up the stairs, behind the first door on the left")
        val mentions = extractor.extractFrom(doc)
        mentions(0).label should be ("Infrastructure")
        mentions(1).label should be ("Switch")
        mentions(2).label should be ("Ordinal")
        mentions(3).label should be ("Infrastructure")
    }

    it should "Recognize switch entities" in {
        val doc = extractor.annotate("The lever is behind the door")
        val mentions = extractor.extractFrom(doc)
        mentions(0).label should be ("Switch")
        mentions(1).label should be ("Infrastructure")
        mentions(2).label should be ("Switch")
    }

    it should "Recognize foe entities" in {
        val doc = extractor.annotate("Down the road there is a mob who looks like a zombie.")
        val mentions = extractor.extractFrom(doc)
        mentions(0).label should be ("Foe")
        mentions(1).label should be ("Foe")
    }

    it should "Recognize person entities" in {
        val doc = extractor.annotate("There's a guy over there, next to the other person")
        val mentions = extractor.extractFrom(doc)
        mentions(0).label should be ("Neutral")
        mentions(1).label should be ("Neutral")
    }
}
