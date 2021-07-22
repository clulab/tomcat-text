package org.clulab.asist.extraction

import com.typesafe.scalalogging.LazyLogging
import org.clulab.asist.attachments.{Negation, Tense}
import org.clulab.asist.extraction.TomcatRuleEngine._
import org.clulab.odin._
import org.clulab.utils.MentionUtils._

class TomcatActions() extends Actions with LazyLogging {

  def passThrough(
      mentions: Seq[Mention],
      state: State = new State()
  ): Seq[Mention] = {
    mentions
  }

  def globalAction(mentions: Seq[Mention], state: State = new State()): Seq[Mention] = {
    val notSubsumed = mostSpecificOnly(mentions, state)
    val withAttachments = addAttachments(notSubsumed, state)
    keepLongest(withAttachments, state)
  }


/** Keeps the longest mention for each group of overlapping mentions **/
  def keepLongest(mentions: Seq[Mention], state: State = new State()): Seq[Mention] = {
    val mns: Iterable[Mention] = for {
    // find mentions of the same label and sentence overlap
      (k, v) <- mentions.groupBy(m => (m.sentence, m.label, m.tags.get.contains("CC")))
      m <- v
      // for overlapping mentions starting at the same token, keep only the longest
      longest = v.filter(vm => vm.tokenInterval.overlaps(m.tokenInterval)).maxBy(m => m.end - m.start)
    } yield longest
    mns.toVector.distinct
  }

  def noRepeats(mentions: Seq[Mention], state: State = new State()): Seq[Mention] = {
    for {
      m <- mentions
      sameSpan = state.mentionsFor(m.sentence, m.tokenInterval)
      compatibleLabel = sameSpan.filter(_.labels.contains(m.label))
      if compatibleLabel.isEmpty
    } yield m
  }

  def mostSpecificOnly(mentions: Seq[Mention], state: State = new State()): Seq[Mention] = {
    var localState = new State()
    for(m <- mentions.sortBy(-_.labels.length)) {
      val sameSpan = localState.mentionsFor(m.sentence, m.tokenInterval).filter(_.tokenInterval == m.tokenInterval)
      val compatibleLabel = sameSpan.filter(_.labels.contains(m.label))
      if (compatibleLabel.isEmpty) {
        localState = localState.updated(Seq(m))
      }
    }
    // the almost equivalent of `allMentions` but not filtering for _.keep
    localState.lookUpTable.values.toStream.flatten.distinct.toVector
  }

  def addAttachments(mentions: Seq[Mention], state: State): Seq[Mention] = {
    mentions.map(addAttachments(_, state))
  }

  def addAttachments(mention: Mention, state: State): Mention = {
    // negation
    val negation = findNegation(mention)
    // tense
    val tense = findTense(mention, state)

    // add them all
    val attachments = negation ++ tense
    withMoreAttachments(mention, attachments.toSeq)
  }

  def findNegation(mention: Mention): Option[Negation] = {
    mention match {
      case event: EventMention =>
        val trigger = event.trigger
        if (hasVerb(trigger)) {
          // most precise: look for negation coming from a token in the trigger
          negationFrom(trigger)
        } else {
          // backoff: look for negation in the span
          negationFrom(mention)
        }
      case _ => // backoff: look for negation in the span
        negationFrom(mention)
    }
  }

  def negationFrom(mention: Mention): Option[Negation] = {
    if (outgoingNeg(mention) || prevTokenNot(mention)) Some(Negation())
    else None
  }

  def prevTokenNot(mention: Mention): Boolean = {
    if (mention.start == 0) false
    else {
      mention.sentenceObj.words(mention.start - 1) == "not"
    }
  }

  def outgoingNeg(mention: Mention): Boolean = {
    mention
      .sentenceObj.dependencies.get // should be safe bc sentence has been parsed
      // outgoing dependencies
      .outgoingEdges.slice(mention.start, mention.end)
      // we don't care which token in the mention the outgoing is coming from, so flatten
      .flatten
      // get the dependency names only bc it doesn't matter where we land
      .map(_._2)
      // check for negation dependency
      .contains("neg")
  }

  def hasVerb(mention: TextBoundMention): Boolean = {
    mention
      // the tags for the mention or an empty sequence
      .tags.getOrElse(Seq.empty)
      // whether or not there is a tag in that sequence that starts with V
      .exists(tag => tag.startsWith("V"))
  }

  def findTense(mention: Mention, state: State): Option[Tense] = {
    mention match {
      case event: EventMention =>
        val trigger = event.trigger
        if (hasVerb(trigger)) {
          // most precise: look for negation coming from a token in the trigger
          tenseFrom(trigger, state)
        } else {
          // backoff: look for negation in the span
          tenseFrom(mention, state)
        }
      case _ => // backoff: look for negation in the span
        tenseFrom(mention, state)
    }
  }

  def tenseFrom(mention: Mention, state: State): Option[Tense] = {
    if (state.hasMentionsFor(mention.sentence, mention.tokenInterval, label = "PastTense")) {
      Some(Tense(value = Tense.PAST))
    } else if (state.hasMentionsFor(mention.sentence, mention.tokenInterval, label = "FutureTense")) {
      Some(Tense(value = Tense.FUTURE))
    } else None
  }

  def requireSubjectVerbInversion(mentions: Seq[Mention], state: State = new State()): Seq[Mention] = {
    // trigger should be before all the arguments
    // todo: revisit when the no_agent branch merged
    // FIXME!!
    for {
      mention <- mentions
      if hasSubjectVerbInversionOrNotApplicable(mention)
    } yield mention
  }

  def hasSubjectVerbInversionOrNotApplicable(mention: Mention): Boolean = {
    mention match {
      case em: EventMention => hasSubjectVerbInversion(em)
      case _ => true
    }
  }

  def hasSubjectVerbInversion(mention: EventMention): Boolean = {
    val triggerStart = mention.trigger.start
    // first token index of all the arguments
    val leftMostArg = mention.arguments
      // get the mentions from all arguments, flatten to a Seq[Mention]
      .flatMap{ case (argName, argMentions) => argMentions }
      // get the first token index of each mention
      .map(m => m.start)
      // find the smallest (leftmost) index
      .min
    val action = mention.arguments("topic").head // should only be one
    val missedSubjs = action.sentenceObj.dependencies.get
      // get the outgoing dep edges coming from the trigger of the action
      .outgoingEdges(triggerStart)
      // we're only interested in nsubj
      .filter(tup => tup._2 == "nsubj")
      // get the landing token (i.e., the subject of that action's token index)
      .map(_._1)
    // get the leftmost (or a dummy big number if there are none)
    val leftMostMissedSubj = if (missedSubjs.isEmpty) 1000 else missedSubjs.min

    // check that trigger is to left of all args and any missed subjects
    (triggerStart < leftMostArg) && (triggerStart < leftMostMissedSubj)
  }

  def mkVictim(mentions: Seq[Mention], state: State = new State()): Seq[Mention] = {
    mentions.map(addArgLabel(_, TARGET_ARG, VICTIM, Some(ENTITY)))
  }

  def addArgLabel(mention: Mention, argName: String, newLabel: String, typeConstraint: Option[String]): Mention = {
    val newArgs = mention.arguments.toSeq.map { case (name, argMentions) =>
      name match {
        case n if n == argName =>
          val afterAdding = argMentions.map(m => copyWithNewLabel(m, newLabel, typeConstraint))
          (name, afterAdding)
        case _ => (name, argMentions)
      }
    }
    copyWithNewArgs(mention, newArgs.toMap)
  }

  def copyWithNewArgs(m: Mention, newArgs: Map[String, Seq[Mention]]): Mention = {
    m match {
      case tb: TextBoundMention => ???
      case rm: RelationMention => rm.copy(arguments = newArgs)
      case em: EventMention => em.copy(arguments = newArgs)
    }
  }

  def copyWithNewLabel(m: Mention, label: String, typeConstraint: Option[String]): Mention = {
    if (
      // If the label is already there, don't add
      m.labels.contains(label) ||
        // OR, if the constraint isn't satisfied
        !typeConstraint.forall(c => m.labels.contains(c))
    ) return m

    m match {
      case tb: TextBoundMention => tb.copy(labels = Seq(label) ++ tb.labels)
      case rm: RelationMention => rm.copy(labels = Seq(label) ++ rm.labels)
      case em: EventMention => em.copy(labels = Seq(label) ++ em.labels)
    }
  }

}

object TomcatActions {
  def apply() = new TomcatActions()
}

