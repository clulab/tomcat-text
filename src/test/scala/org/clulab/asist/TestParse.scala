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
        mentions.size should be (3)
        println("Close events successfully extracted.")   
    }

    "AsistEngine" should "Parse extinguish events properly" in {
        val doc = extractor.annotate("I'm going to put out the fire before leaving")
        val mentions = extractor.extractFrom(doc)
        mentions.size should be (2)
        println("Extinguish events successfully extracted.")   
    }

    "AsistEngine" should "Parse toggle events properly" in {
        val doc = extractor.annotate("He opened the door and walked into the sunset.")
        val mentions = extractor.extractFrom(doc)
        mentions.size should be (3)
        println("Toggle events successfully extracted.")   
    }

    "AsistEngine" should "Parse save events properly" in {
        val doc = extractor.annotate("I'm going to save the villager over there")
        val mentions = extractor.extractFrom(doc)
        mentions.size should be (2)
        println("Save events successfully extracted.")   
    }

    "AsistEngine" should "Parse defeat events properly" in {
        val doc = extractor.annotate("To progress I will kill the zombies")
        val mentions = extractor.extractFrom(doc)
        mentions.size should be (2)
        println("Defeat events successfully extracted.")   
    }

    "AsistEngine" should "Parse search events properly" in {
        val doc = extractor.annotate("I will search inside the building for the villagers")
        val mentions = extractor.extractFrom(doc)
        mentions.size should be (3)
        println("Search events successfully extracted.")   
    }

    "AsistEngine" should "Recognize fire entities" in {
        val doc = extractor.annotate("Inside, the a flame was spreading through the kitchen")
        val mentions = extractor.extractFrom(doc)
        mentions.size should be (1)
        println("Fire entities successfully detected")
    }

    "AsistEngine" should "Recognize infrastructure entities" in {
        val doc = extractor.annotate("The room was up the stairs, behind the first door on the left")
        val mentions = extractor.extractFrom(doc)
        mentions.size should be (4)
        println("Infrastructure entities successfully detected")
    }

    "AsistEngine" should "Recognize switch entities" in {
        val doc = extractor.annotate("The lever is behind the door")
        val mentions = extractor.extractFrom(doc)
        mentions.size should be (3)
        println("Switch entities successfully detected")
    }

    "AsistEngine" should "Recognize foe entities" in {
        val doc = extractor.annotate("Down the road I see a mob who seems like a zombie.")
        val mentions = extractor.extractFrom(doc)
        mentions.size should be (2)
        println("Foe entities successfully detected")
    }

    "AsistEngine" should "Recognize person entities" in {
        val doc = extractor.annotate("There's a guy over there, next to the other person")
        val mentions = extractor.extractFrom(doc)
        mentions.size should be (2)
        println("Person entities successfully detected")
    }
}
