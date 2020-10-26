package org.clulab.asist

import java.io.{File, PrintWriter}
import java.util.Properties

import edu.stanford.nlp.pipeline.StanfordCoreNLP
import spray.json._
import DefaultJsonProtocol._

import scala.collection.{immutable, mutable}
import scala.io.Source
import scala.util.parsing.json.JSON


object ExtractInfoSearch extends App {

    // SCRIPT START

    println("[CoreNLP] Initializing the CoreNLP pipeline ...")
    val corenlp_properties = new Properties()
    corenlp_properties.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref")
    val pipeline = new StanfordCoreNLP(corenlp_properties)
    println("[CoreNLP] Completed Initialization")

    val taxonomy_map_raw = Source.fromResource("taxonomy_map.json").mkString
    val taxonomy_json = JsonParser(taxonomy_map_raw)
    val tax_map = taxonomy_json.convertTo[immutable.Map[String, Array[immutable.Map[String, String]]]]

    val input_file_name = if (args.length > 0){
        args(0)
    } else {
        throw new Exception("Must include transcript file path: {transcript file} {metadata|None} {annotation|None}")
    }

    val transcript = new Transcript(input_file_name)
    var raw_text = transcript.getCleanDoc
    var time_lists = transcript.getTimeMap

    println("[AsistEngine] Initializing the AsistEngine ...")
    val ieSystem = new AsistEngine((time_lists._1, time_lists._3, time_lists._4))
    var proc = ieSystem.proc

    // Only given transcript & metadata, print extracted events in JSON format
    //TODO get experiment metadata
    val extractor = new Extractor(pipeline, ieSystem, tax_map)
    val output_file = new PrintWriter(new File("output_events.txt"))
    try {
      val extracted_mention_json = extractor.extractMentions(input_file_name)
      for (event_json <- extracted_mention_json){
        output_file.write(event_json + "\n")
      }
    } finally {
      output_file.close()
    }
}
