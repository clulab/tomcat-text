package org.clulab.asist

import org.clulab.odin.Mention
import org.scalatest.{FlatSpec, Matchers}

import scala.collection.mutable

class BaseTest extends FlatSpec with Matchers {
  val extractor = new AsistEngine()

  val failingTest = ignore
  val passingTest = it

  // Returns a count of how many times each **type** of event occurs (i.e., by label)
  def getMentionCounter(mentions: Vector[Mention]): mutable.Map[String, Int] = {
    val mention_map = mutable.Map[String, Int]()
    for (m <- mentions) {
      if (mention_map contains m.label) {
        mention_map(m.label) += 1
      } else {
        mention_map(m.label) = 1
      }
    }
    mention_map
  }

  // ------------------------------------------------------
  //                    Test Event Contents
  // ------------------------------------------------------

  def getMentionsWithLabel(ms: Seq[Mention], label: String): Seq[Mention] = {
    ms.filter(_.label matches label)
  }

  def testMention(ms: Seq[Mention], desiredMention: DesiredMention): Unit = {
    val matchesText = ms.filter(_.text == desiredMention.text)
    if (matchesText.isEmpty) {
      throw new RuntimeException(s"Desired text «${desiredMention.text}≫ not found in " +
        s"Mentions: \n\t${ms.map(_.text).mkString("\n\t")}")
    }
    else if (matchesText.length > 1) {
      throw new RuntimeException(s"More than one Mention has desired text «${desiredMention.text}≫ in " +
        s"matching Mentions: \n\t${matchesText.map(_.text).mkString("\n\t")}")
    }
    else {
      testMention(matchesText.head, desiredMention)
    }
  }

  /** Borrowed in spirit from Lum's OdinsonTest
   *
   * @param m              found Mention you want to test
   * @param desiredMention what you wanted to find, in the local case class
   */
  def testMention(m: Mention, desiredMention: DesiredMention): Unit = {

    // Test the mention type and  text
    m.labels should contain(desiredMention.label)
    m.text shouldBe desiredMention.text

    val found = DesiredMention.fromMention(m)

    // all desired args should be there, in the right number
    val groupedMatched = found.arguments.groupBy(_.name)
    val groupedDesired = desiredMention.arguments.groupBy(_.name)

    // There should be the same number of roles
    groupedDesired.keySet should have size (groupedMatched.keySet.size)

    // Check for full containment
    for ((desiredRole, desired) <- groupedDesired) {
      // there should be arg(s) of the desired label
      groupedMatched.keySet should contain(desiredRole)
      // should have the same number of arguments of that label
      val matchedForThisRole = groupedMatched(desiredRole).toSeq
      desired should have size matchedForThisRole.length
      for (d <- desired) {
        matchedForThisRole should contain(d)
      }
      // there shouldn't be any found arguments that we didn't want
      val unwantedArgs = groupedMatched.keySet.diff(groupedDesired.keySet)
      unwantedArgs shouldBe size(0)
    }
  }


  case class Arg(name: String, mentions: Seq[DesiredMention]) {
    override def toString: String = {
      s"TestArgument(name=$name, mentions=${mentions.sortBy(m => (m.label, m.text)).map(_.toString)})"
    }
  }
  object Arg {
    def fromArguments(args: Map[String, Seq[Mention]]): Seq[Arg] = {
      for {
        (argName, argMentions) <- args.toSeq
        recastMentions = argMentions.map(DesiredMention.fromMention)
      } yield Arg(argName, recastMentions)
    }
  }


  case class DesiredMention(label: String, text: String, arguments: Seq[Arg] = Seq.empty) {
    override def toString: String = {
      s"TestMention(label=$label, $text, arguments=${arguments.map(_.toString).sorted})"
    }
  }
  object DesiredMention {
    def fromMention(m: Mention): DesiredMention = {
      DesiredMention(m.label, m.text, Arg.fromArguments(m.arguments))
    }
  }

}
