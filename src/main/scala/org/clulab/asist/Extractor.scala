package org.clulab.asist

import java.text.SimpleDateFormat

import edu.stanford.nlp.coref.CorefCoreAnnotations
import edu.stanford.nlp.coref.data.CorefChain
import edu.stanford.nlp.pipeline.{Annotation, StanfordCoreNLP}
import org.clulab.odin.{EventMention, Mention, TextBoundMention}
import org.json4s.jackson.JsonMethods.{compact, parse, render}
import org.json4s.JsonDSL._

import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import scala.collection.{immutable, mutable}
import scala.collection.mutable.{ArrayBuffer, Map}
import scala.io.Source
import scala.util.parsing.json.JSON

class Extractor(
    processor: StanfordCoreNLP,
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
      transcript: String,
      transcription_id: String
  ): (ArrayBuffer[Array[Any]], org.clulab.processors.Document) = {
    val all_events = new ArrayBuffer[Array[Any]]
    val corenlp_doc = new Annotation(transcript)
    processor.annotate(corenlp_doc)
    val coref_chains =
      corenlp_doc.get(classOf[CorefCoreAnnotations.CorefChainAnnotation])

    val doc = ieEngine.annotate(transcript)
    val mentions = ieEngine
      .extractFrom(doc)
      .sortBy(m => (m.sentence, m.getClass.getSimpleName))
    for (m <- mentions) {
      if (m.arguments contains "target") {
        all_events += Array(
          m,
          transcription_id
        ) // TODO remove this once anaphor matching is robust
        val target = m.arguments("target").head
        for (i <- target.tokenInterval) {
          val sent = m.document.sentences(target.sentence)
        }
        if (use_coref_resolution && target.label == "Anaphor") {
          insideCorefChain(coref_chains.asScala, target, m) match {
            case Some(coref_head) =>
              // Now check if coref_head has label other than anaphor
              getCorefHeadLabel(coref_head, mentions) match {
                case Some(head_label) =>
                  if (event_pair_map contains m.label) {
                    if (event_pair_map(m.label) contains head_label) {
                      //all_events += Array(m, transcription_id)
                    }
                  }
                case _ =>
              }
            case _ =>
          }

        }
      } else {
        all_events += Array(m, transcription_id)
      }
    }
    (all_events, doc)
  }

  def extractMentions(
      file_name: String,
      experiment_id: String = "NULL"
  ): ArrayBuffer[String] = {
    val output_array = new ArrayBuffer[String]()
    val transcript = new Transcript(file_name)
    val raw_text = transcript.getCleanDoc
    val time_lists = transcript.getTimeMap

    ieEngine.timeintervals = (time_lists._1, time_lists._3, time_lists._4)
    ieEngine.reload()

    var doc: org.clulab.processors.Document = null
    val all_events = new ArrayBuffer[Array[Any]]
    val (extractions, extracted_doc) = runExtraction(raw_text, "")
    doc = extracted_doc
    extractions.foreach(event_array => all_events.append(event_array))

    val trial_id = "NULL"
    val time_format = new SimpleDateFormat("HH:mm:ss.SSS")
    var text: String = ""; var startOffset = 0; var endOffset = 0
    for (extraction <- all_events) {
      val mention = extraction(0).asInstanceOf[Mention]
      val argument_labels =
        for (key <- mention.arguments.keys)
          yield mention.arguments.get(key).get(0).label
      mention match {
        case cur: EventMention =>
          text = cur.trigger.text
          startOffset = cur.trigger.startOffset
          endOffset = cur.trigger.endOffset
        case cur: TextBoundMention =>
          text = cur.text
          startOffset = cur.startOffset
          endOffset = cur.endOffset
      }
      val timestamp = getTimeStamp(time_lists._1, time_lists._2, startOffset)
      val taxonomy_matches =
        tax_map(mention.label).map(x => (x("term") -> x("score"))).toSeq
      val json =
        (
          ("header" ->
            ("timestamp" -> time_format.format(timestamp)) ~
            ("message_type" -> "event") ~
            ("version" -> 0.1)) ~
            ("msg" ->
              ("source" -> "DialogueActionExtractor") ~
              ("experiment_id" -> experiment_id.toString) ~
              ("filename" -> file_name) ~
              ("timestamp" -> time_format.format(timestamp)) ~
              ("sub_type" -> "Event:dialogue_action") ~
              ("version" -> "0.1")) ~
            ("data" ->
              ("Label" -> mention.label) ~
              ("Span" -> mention.words.mkString(" ")) ~
              ("Arguments" -> argument_labels.mkString(" ")) ~
              ("Text" -> doc.sentences(mention.sentence).getSentenceText) ~
              ("timestamp" -> time_format.format(timestamp)) ~
              ("startOffset" -> startOffset) ~
              ("endOffset" -> endOffset) ~
              ("TaxonomyMatches" -> taxonomy_matches))
        )
      output_array.add(compact(render(json)))
    }
    output_array
  }
}
