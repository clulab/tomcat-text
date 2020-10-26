package org.clulab.asist

import java.io.{File, PrintWriter}
import java.util.Properties

import edu.stanford.nlp.pipeline.StanfordCoreNLP
import org.clulab.odin.Mention
import spray.json.DefaultJsonProtocol._
import spray.json._

import scala.collection.immutable
import scala.io.Source
import scala.util.parsing.json.JSON


object ExtractDirSearch extends App {

    // SCRIPT START

    println("[CoreNLP] Initializing the CoreNLP pipeline ...")
    val corenlp_properties = new Properties()
    corenlp_properties.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref")
    val pipeline = new StanfordCoreNLP(corenlp_properties)
    println("[CoreNLP] Completed Initialization")

    val taxonomy_json = JsonParser(Source.fromResource("taxonomy_map.json").mkString)
    val tax_map = taxonomy_json.convertTo[immutable.Map[String, Array[immutable.Map[String, String]]]]

    println("[AsistEngine] Initializing the AsistEngine ...")
    val ieSystem = new AsistEngine()
    var proc = ieSystem.proc
    println("[AsistEngine] Completed Initialization ...")

    val extractor = new Extractor(pipeline, ieSystem, tax_map)

    val input_dir_name = if (args.length > 0){
        args(0)
    } else {
        throw new Exception("ERROR: Must include transcript directory path as argument")
    }

    val output_file = new PrintWriter(new File("output_events.txt"))

    //https://alvinalexander.com/scala/how-to-list-subdirectories-under-directory-in-scala/
    def getFilesInDir(dir: File): Array[String] = {
      dir.listFiles
        .filter(_.isFile)
        .map(_.getAbsolutePath)
        .filter(_.endsWith(".vtt"))
    }

    try {
      println("Starting in: " + input_dir_name)
      for (input_file_name <- getFilesInDir(new File(input_dir_name))) {
        println("Extracting from: " + input_file_name + " . . .")
        val extracted_mention_json = extractor.extractMentions(input_file_name)
        for (event_json <- extracted_mention_json) {
          output_file.write(event_json + "\n")
        }
      }
    } finally {
      output_file.close()
    }
}
