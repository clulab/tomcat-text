package org.clulab.asist.extraction

import com.typesafe.scalalogging.LazyLogging
import org.clulab.asist.extraction.TomcatRuleEngine._
import org.clulab.odin._
import org.clulab.asist.attachments.Agent
import org.clulab.struct.Interval

import scala.collection.mutable.ArrayBuffer

class TomcatActions() extends Actions with LazyLogging {

  def passThrough(
      mentions: Seq[Mention],
      state: State = new State()
  ): Seq[Mention] = {
    mentions
  }

  def globalAction(mentions: Seq[Mention], state: State = new State()): Seq[Mention] = {
    // convert any agent argument to an attachment
    val agentResolved = convertAgents(mentions, state)
    val notSubsumed = mostSpecificOnly(agentResolved, state)
    keepLongest(notSubsumed, state)
  }

  def convertAgents(mentions: Seq[Mention], state: State = new State()): Seq[Mention] = {
    mentions.map(convertAgent)
  }

  def convertAgent(mention: Mention): Mention = {
    val nonAgentArgs = mention.arguments.filterKeys(key => key != AGENT_ARG)
    val agentMentions = mention.arguments.getOrElse(AGENT_ARG, Seq.empty)
    val agents = agentMentions.map(mkAgent).toSet

    // make a copy of the mention with the agent attachments
    val copy = mention match {
      case tb: TextBoundMention => mention
      case rm: RelationMention =>
        val newSpan = mkInterval(mention, nonAgentArgs)
        rm.copy(arguments = nonAgentArgs, attachments = agents, tokenInterval = newSpan)
      case em: EventMention =>
        val newSpan = mkInterval(mention, nonAgentArgs)
        em.copy(arguments = nonAgentArgs, attachments = agents, tokenInterval = newSpan)
      case _ => ???
    }

    copy
  }

  def mkInterval(m: Mention, args: Map[String, Seq[Mention]]): Interval = {
    val triggerOffsets = m match {
      case em: EventMention => Seq(em.trigger.start, em.trigger.end)
      case _ => Seq.empty
    }
    val argOffsets = args.toSeq.flatMap(_._2)
      .map(_.tokenInterval)
      .flatMap(t => Seq(t.start, t.end))
    val allOffsets = triggerOffsets ++ argOffsets
    val start = allOffsets.min
    val end = allOffsets.max
    Interval(start, end)
  }

  def mkAgent(m: Mention): Attachment = Agent(m.text, m.label, m.labels, m.tokenInterval)

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

  def requireSubjectVerbInversion(mentions: Seq[Mention], state: State = new State()): Seq[Mention] = {
    // trigger should be before all the arguments
    // todo: revisit when the no_agent branch merged
    // FIXME!!
    for {
      mention <- mentions
      triggerStart = mentionStart(mention)
      leftMostArg = mention.arguments.flatMap(xx => xx._2).map(m => m.start).min // first token index of all the arguments
      action = mention.arguments("topic").head // should only be one
      missedSubjs = action.sentenceObj.dependencies.get
        // get the outgoing dep edges coming from the trigger of the action
        .outgoingEdges(mentionStart(action))
        // we're only interested in nsubj
        .filter(tup => tup._2 == "nsubj")
        // get the landing token (i.e., the subject of that action's token index)
        .map(_._1)
      // get the leftmost (or a dummy big number if there are none)
      leftMostMissedSubj = if (missedSubjs.isEmpty) 1000 else missedSubjs.min
      // check that trigger is to left of all args and any missed subjects
      if triggerStart < leftMostArg && triggerStart < leftMostMissedSubj
    } yield mention
  }

  def mentionStart (mention: Mention): Int = mention match {
    case m: EventMention => m.trigger.start
    case m: TextBoundMention => m.start
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

