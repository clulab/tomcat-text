package org.clulab.asist

import org.clulab.asist.extraction.TomcatRuleEngine
import org.clulab.odin.Mention
import org.scalatest.{FlatSpec, Matchers}

import scala.collection.mutable

class BaseTest extends FlatSpec with Matchers {

  val extractor = BaseTest.extractor

  val failingTest = ignore
  val passingTest = it
  val tempFailingTest = ignore

  // Mention labels
  val INFRASTRUCTURE = "Infrastructure"

  // AttachmentStrings
  val AGENT_SELF: String = "Agent(Self)"
  val AGENT_YOU: String = "Agent(You)"
  val AGENT_TEAM: String = "Agent(Team)"
  val AGENT_ENTITY: String = "Agent(Entity)"
  val AGENT_OTHER: String = "Agent(Other)"
  val FUTURE_TENSE: String = "Tense(future)"
  val PAST_TENSE: String = "Tense(past)"

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

  def testMention(ms: Seq[Mention], desiredMention: DesiredMention, shallow: Boolean = false): Unit = {
    if (shallow) assert(desiredMention.arguments.isEmpty)
    val found = ms.map(DesiredMention.fromMention(_, shallow))
    found should contain(desiredMention)
  }

  /** Borrowed in spirit from Lum's OdinsonTest
   *
   * @param m              found Mention you want to test
   * @param desiredMention what you wanted to find, in the local case class
   */
  def testMention(m: Mention, desiredMention: DesiredMention): Unit = {
    val found = DesiredMention.fromMention(m)
    found should equal(desiredMention)
  }

  case class DesiredMention(
    label: String,
    text: String,
    arguments: Map[String, Seq[DesiredMention]] = Map.empty,
    attachments: Set[String] = Set.empty
  ) {
    override def toString: String = {
      s"TestMention(label=$label, $text, arguments=${arguments.map(_.toString)}, attachments=${attachments.mkString(", ")})\n"
    }
  }
  object DesiredMention {
    def fromMention(m: Mention, shallow: Boolean = false): DesiredMention = {
      val args = if (shallow) Map.empty[String, Seq[DesiredMention]] else {
        m.arguments.map(arg =>
          (
            arg._1,
            arg._2.map(one_mention => DesiredMention.fromMention(one_mention))
          )
        )
      }
      DesiredMention(m.label, m.text, args,
        m.attachments.map(_.toString)
      )
    }
  }

}

object BaseTest {
  val extractor = new TomcatRuleEngine()
}
