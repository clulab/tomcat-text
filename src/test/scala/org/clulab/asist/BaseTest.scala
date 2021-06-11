package org.clulab.asist

import org.clulab.odin.Mention
import org.scalatest.{FlatSpec, Matchers}

import scala.collection.mutable

class BaseTest extends FlatSpec with Matchers {

  val extractor = BaseTest.extractor

  val failingTest = ignore
  val passingTest = it
  val tempFailingTest = ignore

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
    val found = ms.map(DesiredMention.fromMention(_))
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

  case class DesiredMention(label: String, text: String, arguments: Map[String, Seq[DesiredMention]] = Map.empty) {
    override def toString: String = {
      s"TestMention(label=$label, $text, arguments=${arguments.map(_.toString)})"
    }
  }
  object DesiredMention {
    def fromMention(m: Mention): DesiredMention = {
      DesiredMention(m.label, m.text, m.arguments.map(arg =>
          (
            arg._1,
            arg._2.map(one_mention => DesiredMention.fromMention(one_mention))
          )
        )
      )

    }
  }

}

object BaseTest {
  val extractor = new AsistEngine()
}
