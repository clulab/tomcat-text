package org.clulab.asist

import java.util.Date

import com.typesafe.scalalogging.LazyLogging
import org.clulab.odin.{Actions, EventMention, Mention, State, TextBoundMention}
import org.clulab.odin.impl.Taxonomy
import org.clulab.utils.FileUtils
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor

import scala.collection.mutable.ArrayBuffer

class StubActions(
    val timeintervals: (ArrayBuffer[Int], ArrayBuffer[Int], ArrayBuffer[Int])
) extends Actions
    with LazyLogging {
  def passThrough(
      mentions: Seq[Mention],
      state: State = new State()
  ): Seq[Mention] = {
    mentions
  }

/** Keeps the longest mention for each group of overlapping mentions **/
  def keepLongest(mentions: Seq[Mention], state: State = new State()): Seq[Mention] = {
    val mns: Iterable[Mention] = for {
    // find mentions of the same label and sentence overlap
      (k, v) <- mentions.groupBy(m => (m.sentence, m.label))
      m <- v
      // for overlapping mentions starting at the same token, keep only the longest
      longest = v.filter(_.tokenInterval.overlaps(m.tokenInterval)).maxBy(m => m.end - m.start)
    } yield longest
    mns.toVector.distinct
  }

  def removeResearcher(
      mentions: Seq[Mention],
      state: State = new State()
  ): Seq[Mention] = {
    // If there are no timeintervals, return all events found
    if (timeintervals._1.size == 0) {
      return mentions
    }
    val to_be_returned = new ArrayBuffer[Mention]
    for (men <- mentions) {
      val startOffset = men match {
        case cur: EventMention     => cur.trigger.startOffset
        case cur: TextBoundMention => cur.startOffset
      }

      var cur = timeintervals._1(0)
      var i = 0
      while (cur < startOffset) {
        i += 1
        cur = timeintervals._1(i)
      }
      if (!(timeintervals._2 contains i)) {
        to_be_returned.append(men)
        if (timeintervals._3 contains timeintervals._1(i - 1)) {
          // This checks if the previous utterance was a researcher question
          //println(men.words.mkString)
          // TODO create new event mention
        }
      }
    }
    to_be_returned
  }
}

object StubActions {

  def apply(
      timeintervals: (ArrayBuffer[Int], ArrayBuffer[Int], ArrayBuffer[Int])
  ) =
    new StubActions(
      timeintervals: (ArrayBuffer[Int], ArrayBuffer[Int], ArrayBuffer[Int])
    )
}

