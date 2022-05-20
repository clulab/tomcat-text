package org.clulab.asist.extraction

import com.typesafe.scalalogging.LazyLogging
import org.clulab.asist.attachments.{Agent, MarkerId, Negation, Tense, VictimType}
import org.clulab.asist.extraction.TomcatRuleEngine._
import org.clulab.odin._
import org.clulab.struct.Interval
import org.clulab.utils.DisplayUtils
import org.clulab.utils.MentionUtils._

class TomcatActions() extends Actions with LazyLogging {

  def passThrough(
      mentions: Seq[Mention],
      state: State = new State()
  ): Seq[Mention] = {
    mentions
  }

  /**
   * This function has four steps:
   * 1. Convert any agent argument to an attachment sequence;
   * 2. Exclude the agent attachment sequence that are overlapping with parent mentions
   * 3. Add the excluded attachment sequence to a new attachment sequence
   * 4. For each mention found, only use the longest one
   * @param mentions
   * @param state
   * @return sequence of attachments
   */
  def globalAction(mentions: Seq[Mention], state: State = new State()): Seq[Mention] = {
    // convert any agent argument to an attachment
    val agentResolved = convertAgents(mentions, state)
    val excluded = excludeArgEqualsParent(agentResolved, state)
//    val notSubsumed = mostSpecificOnly(agentResolved, state)
    val withAttachments = addAttachments(excluded, state)
    keepLongest(withAttachments, state)
  }

  /**
   * Exclude the overlapping parts of parent mention and the argument mention
   * @param mentions
   * @param state
   * @return sequence mention without the overlapping part
   */
  def excludeArgEqualsParent(mentions: Seq[Mention], state: State = new State()): Seq[Mention] = {
    mentions.filterNot{ parent =>
      val args = parent.arguments
      args.values.flatten.exists(argMention => argIsParent(argMention, parent))
    }
  }

  // Below function is not used anywhere else and very similar to the argIsParent function; probably no use
//  def argsSubsumeParent(argMentions: Seq[Mention], parentMention: Mention): Boolean = {
//    argMentions.exists(m => argIsParent(m, parentMention))
//  }

  /**
   * check if an argument is a parent mention and the span of the parent mention is equal to the argument mention
   * @param argMention
   * @param parentMention
   * @return true/false
   */
  def argIsParent(argMention: Mention, parentMention: Mention): Boolean = {
    argMention.sentence == parentMention.sentence &&
      argMention.tokenInterval == parentMention.tokenInterval
  }

  /**
   * Convert multiple agents into attachments
   * @param mentions
   * @param state
   * @return sequence of Odin mention
   */
  def convertAgents(mentions: Seq[Mention], state: State = new State()): Seq[Mention] = {
    mentions.map(convertAgent)
  }//converting multiple agents into attachments


  //converting Agent arg into an attachment, we need this because the argument of an extraction cannot be equal in span to the extraction
  //agents (subjects) blow up the span size of our extractions, which is why we need this. attachments do not figure into the span size
  def convertAgent(mention: Mention): Mention = {
    val nonAgentArgs = mention.arguments.filterKeys(key => key != AGENT_ARG) //we defined what agents are, here we make sure we take out non-agents, the definition is in TomcatRuleEngine.scala
    val agentMentions = mention.arguments.getOrElse(AGENT_ARG, Seq.empty) //find arguments that are marked as agents
    val agents = agentMentions.map(mkAgent).toSet //turn the agent mentions into a set

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

  /**
   * Find the longest span of the agent argument and return the span of the argument mention
   * @param m Mention
   * @param args
   * @return the span of the argument mention
   */
  def mkInterval(m: Mention, args: Map[String, Seq[Mention]]): Interval = {
    val triggerOffsets = m match {
      case em: EventMention => Seq(em.trigger.start, em.trigger.end)
      case _ => Seq.empty
    }
    val argOffsets = args.toSeq.flatMap(_._2)
      .map(_.tokenInterval)
      .flatMap(t => Seq(t.start, t.end))
    // find the longest
    val allOffsets = triggerOffsets ++ argOffsets
    val start = allOffsets.min
    val end = allOffsets.max
    Interval(start, end)
  }

  /**
   * Put all agent information including text, label, and token span into attachment
   * @param m Mention
   * @return Attachment
   */
  def mkAgent(m: Mention): Attachment = Agent(m.text, m.label, m.labels, m.tokenInterval)

/** Keeps the longest mention for each group of overlapping mentions **/
  def keepLongest(mentions: Seq[Mention], state: State = new State()): Seq[Mention] = {
    def argLabelsBonus(args: Map[String, Seq[Mention]]): Double = {
      val argMentions = args.values.flatten.toSeq
      // 0.1 * number of args
      val numArgsBonus = argMentions.length * 0.1
      // 0.01 * number of labels for each
      val argSpecificityBonus = argMentions.flatMap(_.labels).size * 0.05
      numArgsBonus + argSpecificityBonus
    }
    val mns: Iterable[Mention] = for {
    // find mentions of the same label and sentence overlap
      (k, v) <- mentions.groupBy(m => (m.sentence, m.label, m.tags.get.contains("CC")))
      m <- v
      // for overlapping mentions starting at the same token, keep only the longest
      longest = v.filter(vm => vm.tokenInterval.overlaps(m.tokenInterval))
        .maxBy(m => (m.end - m.start) + argLabelsBonus(m.arguments))
    } yield longest
    mns.toVector.distinct
  }
  


  def addAttachments(mentions: Seq[Mention], state: State): Seq[Mention] = {
    mentions.map(addAttachments(_, state))
  }

  /**
   * Add negation, tense, victim type, and marker ID into attachments
   * @param mention
   * @param state
   * @return attachments with new added information
   */
  def addAttachments(mention: Mention, state: State): Mention = {
    // negation
    val negation = findNegation(mention, state)
    // tense
    val tense = findTense(mention, state)
    // victimType and markerId are for MarkerMeaning mentions
    val victimType = findVictim(mention)
    val markerId = findMarkerId(mention)

    // add them all
    val attachments = negation ++ tense ++ victimType ++ markerId
    withMoreAttachments(mention, attachments.toSeq)
  }

  /**
   * Find negation:
   * 1. For all event mentions:
   *    a. if verb in a mention, look for negation coming from a token in the trigger
   *    b. else, look for negation in the span
   * 2. For all non-event mentions, look for negation in the span
   * @param mention
   * @param state
   * @return array of index of negation mention
   */
  def findNegation(mention: Mention, state: State): Option[Negation] = {
    mention match {
      case event: EventMention =>
        val trigger = event.trigger
        if (hasVerb(trigger)) {
          // most precise: look for negation coming from a token in the trigger
          negationFrom(trigger, state)
        } else {
          // backoff: look for negation in the span
          negationFrom(mention, state)
        }
      case _ => // backoff: look for negation in the span
        negationFrom(mention, state)
    }
  }

  /**
   * This function aims to find the negation mention without the VICTIM label
   * It has three steps:
   * 1. Exclude any mention with victim label
   * 2. Get the index of negation mention
   *     (either by the "neg" label (get from the outgoingNeg function)
   *     or by the word "not" (get from the prevTokenNot function))
   * 3. Exclude any negation mention with victim label
   * @param mention
   * @param state
   * @return the index array of negation mention
   */
  def negationFrom(mention: Mention, state: State): Option[Negation] = {
    if (mention.matches(VICTIM)) return None

    val negatedTokens = outgoingNeg(mention) ++ prevTokenNot(mention)
    val nonVictimNegated = negatedTokens.filter(token => !state.hasMentionsFor(mention.sentence, token, VICTIM))
    if (nonVictimNegated.nonEmpty) Some(Negation())
    else None
  }

  /**
   * Find mention occurring right after the word "not"; if found, add the index of the mention start into an array
   * @param mention
   * @return an array of index of the mention start where the mention occurs after "not"
   */
  def prevTokenNot(mention: Mention): Array[Int] = {
    if (mention.start == 0) Array.empty
    else if (mention.sentenceObj.words(mention.start - 1) == "not") Array(mention.start)
    else Array.empty
  }

  /**
   * ??? Find the negation dependency and add the index of negation token into an array
   * @param mention
   * @return an array consists of the index of the negation token
   */
  def outgoingNeg(mention: Mention): Array[Int] = {
    mention
      .sentenceObj.dependencies.get // should be safe bc sentence has been parsed
      // outgoing dependencies
      .outgoingEdges.slice(mention.start, mention.end)
      // we don't care which token in the mention the outgoing is coming from, so flatten
      .flatten
      // check for negation dependency
      .filter(_._2 == "neg")
      .map(_._1)
  }

  /**
   * Check whether the tag of a mention starts with V; if true, it is a verb; if false, not a verb
   * @param mention
   * @return true / false
   */
  def hasVerb(mention: TextBoundMention): Boolean = {
    mention
      // the tags for the mention or an empty sequence
      .tags.getOrElse(Seq.empty)
      // whether or not there is a tag in that sequence that starts with V
      .exists(tag => tag.startsWith("V"))
  }

  /**
   * Find tense:
   * 1. For all event mentions:
   *    a. if verb in a mention, look for tense coming from a token in the trigger
   *    b. else, look for tense in the span
   * 2. For all non-event mentions, look for tense in the span
   * @param mention
   * @param state
   * @return array of index of tense mention
   */
  def findTense(mention: Mention, state: State): Option[Tense] = {
    mention match {
      case event: EventMention =>
        val trigger = event.trigger
        if (hasVerb(trigger)) {
          // most precise: look for tense coming from a token in the trigger
          tenseFrom(trigger, state)
        } else {
          // backoff: look for tense in the span
          tenseFrom(mention, state)
        }
      case _ => // backoff: look for tense in the span
        tenseFrom(mention, state)
    }
  }

  /**
   * Find mentions with label "PastTense" or "FutureTense"
   * @param mention
   * @param state
   * @return the array of the tense mention index
   */

  def tenseFrom(mention: Mention, state: State): Option[Tense] = {
    if (state.hasMentionsFor(mention.sentence, mention.tokenInterval, label = "PastTense")) {
      Some(Tense(value = Tense.PAST))
    } else if (state.hasMentionsFor(mention.sentence, mention.tokenInterval, label = "FutureTense")) {
      Some(Tense(value = Tense.FUTURE))
    } else None
  }

  /**
   * For all event mentions, find the marker ID and convert to number format.
   * @param mention
   * @return the marker ID
   */
  def findMarkerId(mention: Mention): Option[MarkerId] = {
    mention.label match {
      case MARKER_MEANING =>
        mention match {
          case event: EventMention =>
            val lemmaText = event.trigger.lemmas.get.mkString(" ")
            Some(MarkerId(lemmaText))
          case _ =>
            logger.warn(s"MarkerMeaning mention found that wasn't an EventMention, no attachment created. (${mention.text})")
            None
        }
      case _ => None
    }
  }
// ??????????????????? The below series of functions are all related and they seem to be broken. ???????????????????
  def preventSubjectVerbInversion(mentions: Seq[Mention], state: State = new State()): Seq[Mention] = {
    for {
      mention <- mentions
      if !hasSubjectVerbInversionOrNotApplicable(mention)
    } yield mention
  }

  def requireSubjectVerbInversion(mentions: Seq[Mention], state: State = new State()): Seq[Mention] = {
    // trigger should be before all the arguments
    // todo: revisit when the no_agent branch merged
    // FIXME!!
    // comment by Remo: The hasSubjectVerbInversion method is broken, I cannot figure out why. However, it may be better to leave it.
    for {
      mention <- mentions
      if hasSubjectVerbInversionOrNotApplicable(mention)
    } yield mention
  }

  // For event mentions, check if the SubjectVerbInversion happens. For non-event mentions, return true.
  def hasSubjectVerbInversionOrNotApplicable(mention: Mention): Boolean = {
    mention match {
      case em: EventMention => hasSubjectVerbInversion(em)
      case _ => true
    }
  }

  def hasSubjectVerbInversion(mention: EventMention): Boolean = {
    // this method is used to determine whether SubjectVerbInversion is happening, useful for binary questions
    val trigger = mention.trigger //finding the triggers in the mention object
    val triggerStart = trigger.start //finding the starting point of the trigger
    val triggerIsVB = trigger.tags.exists(_.startsWith("V")) //finding whether the trigger is a verb

    // first token index of all the arguments
    val leftMostArg = mention.arguments
      // get the mentions from all arguments, flatten to a Seq[Mention]
      .flatMap{ case (argName, argMentions) => argMentions }
      // get the first token index of each mention
      .map(m => m.start)
      // find the smallest (leftmost) index
      .min

    val action = mention.arguments("topic").head // should only be one
    val actionAgents = action.attachments.collect{ case a: Agent => a.span.start }
    val leftMostAgentAttachment = if (actionAgents.isEmpty) 1000 else actionAgents.min

    val leftMostSubj = if (triggerIsVB) { //this actually doesn't work right now, but it's better that way, because we want to match things like: "Dude, can you help me?"
      val outgoing = mention.sentenceObj //this is the sentence object of the trigger
        .dependencies.get
        .outgoingEdges
        .slice(triggerStart, trigger.end) //taking out the trigger itself
      outgoing.flatten
        .filter(_._2.startsWith("nsubj")) // only subjects
        .map(_._1) // get the dsts
        .min  // leftmost
    } else 1000

    val leftMostAll = Seq(leftMostArg, leftMostAgentAttachment, leftMostSubj).min
    // check that trigger is to left of all args and any missed subjects
    triggerStart < leftMostAll
  }


  def mkVictim(mentions: Seq[Mention], state: State = new State()): Seq[Mention] = {
    mentions.map(mkVictimArg(_, Some(ENTITY)))
  }

  /**
   * 1. Check if the mention label is "target":
   *    if yes, copy the labels of the mention with certain conditions;
   *    else, keep the argument mention
   *  2. Copy new arguments of the mention
   * @param mention
   * @param typeConstraint Some(Entity)
   * @return nested array of the mention and the label
   */
  def mkVictimArg(mention: Mention, typeConstraint: Option[String]): Mention = {
    val newArgs = mention.arguments.toSeq.map { case (name, argMentions) =>
      name match {
        case n if n == TARGET_ARG =>
          val afterAdding = argMentions.map {m =>
            copyWithNewLabel(m, VICTIM, typeConstraint)
          }
          (name, afterAdding)
        case _ => (name, argMentions)
      }
    }
    copyWithNewArgs(mention, newArgs.toMap)
  }

// ??? 
  def findVictim(mention: Mention): Option[VictimType] = {
    mention.label match {
      case MARKER_MEANING => mkVictimType(mention.arguments("meaning").head)
      case _ => None
    }
  }

  /**
   * Find the victim types listed below
   * @param mention
   * @return mentions with listed victim labels or none
   */
  def mkVictimType(mention: Mention): Option[VictimType] = {
    mention.label match {
      case CRITICAL_VICTIM => Some(VictimType(VictimType.CRITICAL))
      case NO_VICTIM => Some(VictimType(VictimType.NONE))
      case REGULAR_VICTIM => Some(VictimType(VictimType.REGULAR))
      case VICTIM => Some(VictimType(VictimType.REGULAR))
      case _ => None
    }
  }

// ?? unknown
  def copyWithNewArgs(m: Mention, newArgs: Map[String, Seq[Mention]]): Mention = {
    m match {
      case tb: TextBoundMention => ???
      case rm: RelationMention => rm.copy(arguments = newArgs)
      case em: EventMention => em.copy(arguments = newArgs)
    }
  }

  /**
   * This function copies the labels of the mention with conditions:
   * 1. It returns mentions that already have victim labels or do not contain entity labels.
   * 2. ??? Check if the mention matches certain conditions.
   * @param m mention
   * @param label (VICTIM)
   * @param typeConstraint Some(ENTITY)
   * @return
   */
  def copyWithNewLabel(m: Mention, label: String, typeConstraint: Option[String]): Mention = {
    if (
      // If the label is already there, don't add
      m.labels.contains(label) ||
        // OR, if the constraint isn't satisfied
        !typeConstraint.forall(c => m.labels.contains(c))
    ) return m

    // ?? Not sure why match occurs after the if condition
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

