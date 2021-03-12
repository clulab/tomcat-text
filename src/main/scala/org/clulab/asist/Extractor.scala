package org.clulab.asist

import edu.stanford.nlp.coref.CorefCoreAnnotations
import edu.stanford.nlp.coref.data.CorefChain
import edu.stanford.nlp.pipeline.{Annotation, StanfordCoreNLP}
import org.clulab.odin.{EventMention, Mention, TextBoundMention}

import scala.collection.JavaConversions._
import scala.collection.{immutable, mutable}
import scala.collection.mutable.{ArrayBuffer, Map}
import scala.io.Source
import scala.util.parsing.json.JSON

class Extractor(
    ieEngine: AsistEngine,
    tax_map: immutable.Map[String, Array[immutable.Map[String, String]]]
) {
  val use_coref_resolution = false
  val event_pair_file_name = "event_pairs.tsv"

  // load tsv of acceptable event-action pairs
  val event_pair_iter = Source.fromResource(event_pair_file_name).getLines
  val event_pair_map = new mutable.HashMap[String, Set[String]]
  for (line <- event_pair_iter) {
    val split_line = line.split('\t').map(_.trim)
    if (event_pair_map contains split_line(0)) {
      event_pair_map(split_line(0)).add(split_line(1))
    } else {
      event_pair_map.put(split_line(0), Set(split_line(1)))
    }
  }

  def getTimeStamp(
      char_indices: ArrayBuffer[Int],
      date_values: ArrayBuffer[java.util.Date],
      index: Int
  ): java.util.Date = {
    var cur = char_indices(0)
    var i = 0
    while (cur < index) {
      i += 1
      cur = char_indices(i)
    }
    date_values(i)
  }

  def getCorefHeadLabel(
      coref_head: CorefChain.CorefMention,
      mentions: Vector[Mention]
  ): Option[String] = {
    val sent_id = coref_head.sentNum - 1
    for (mention <- mentions) {
      if (
        sent_id == mention.sentence &
          coref_head.startIndex <= mention.start & mention.end <= coref_head.endIndex
      ) {
        return Option(mention.label)
      }
    }
    None
  }

  def insideCorefChain(
      chains: Map[Integer, CorefChain],
      target: Mention,
      mention: Mention
  ): Option[CorefChain.CorefMention] = {
    val token_idx = target.tokenInterval(0) + 1
    val sentence_id = target.sentence
    // Check each chain for membership
    for (cc <- chains.values) {
      for (mention <- cc.getMentionsInTextualOrder) {
        if (
          sentence_id == (mention.sentNum - 1) &
            token_idx >= mention.startIndex & token_idx <= mention.endIndex
        ) {
          return Option(
            cc.getMentionsInTextualOrder.head: CorefChain.CorefMention
          )
        }
      }
    }
    None
  }

  def getMetaData(filepath: String): (String, String) = {
    val in_file = Source.fromFile(filepath)
    var exp_id = "Null"; var trial_id = "Null"
    try {
      val lines = in_file.getLines
      lines.next
      val first_line = lines.next
      val parsed_json = JSON.parseFull(first_line).getOrElse("")
    } finally {
      in_file.close
    }
    (exp_id, trial_id)
  }

  def runExtraction(
      transcript: String
  ): (Seq[Mention], org.clulab.processors.Document) = {
    val all_events = new ArrayBuffer[Mention]

    val doc = ieEngine.annotate(transcript)
    val mentions = ieEngine
      .extractFrom(doc)
      .sortBy(m => (m.sentence, m.getClass.getSimpleName))
    for (m <- mentions) {
      all_events += m
    }
    (all_events, doc)
  }
}
