package org.clulab.asist

import java.io.{BufferedWriter, File, FileWriter, PrintWriter}
import java.text.SimpleDateFormat
import java.util.Properties

import edu.stanford.nlp.coref.CorefCoreAnnotations
import edu.stanford.nlp.coref.data.CorefChain
import edu.stanford.nlp.pipeline.{Annotation, StanfordCoreNLP}
import org.clulab.odin.{EventMention, Mention, TextBoundMention}
import org.json4s.jackson.JsonMethods._
import spray.json.DefaultJsonProtocol._
import spray.json._

import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import scala.collection.mutable.{ArrayBuffer, Map}
import scala.collection.{immutable, mutable}
import scala.io.Source
import scala.util.control.Breaks._
import scala.util.parsing.json.JSON


object ExtractDirSearch extends App {

    def getProv(event: Any): (Int, Int, Int) = {
        val eventMap = event.asInstanceOf[immutable.Map[String, Any]]
        val prov = eventMap.get("provenance").get.asInstanceOf[String].split(", ")
        return (prov(0).toInt, prov(1).toInt, prov(2).toInt)
    }


    def getUttNum(event: Any): Int = {
        return getProv(event)._1
    }


    def checkEquality(eventAny: Any, mention: Mention): Boolean = {
        val event = eventAny.asInstanceOf[immutable.Map[String, Any]]
        if (!(mention.matches(event.get("type").get.asInstanceOf[String]))) {
            return false
        }
        //println("mention: ("+mention.sentence+","+mention.startOffset+","+mention.endOffset+")")
        val eventProv = getProv(event)
        //println("event:   "+eventProv)
        //if (eventProv._1 != mention.sentence){
        //  return false
        //}
        if (eventProv._2 != 1 + mention.startOffset) {
            return false
        }
        //if (eventProv._3 != 1+mention.endOffset){
        //    return false
        //}
        true
    }


    def compareLabelLists(labels: ArrayBuffer[Any], estimated: Vector[Mention]): (Int, Int, Int) = {
        var true_pos = 0
        var false_neg = 0

        var found_match = false
        for (label <- labels) {
            found_match = false
            breakable {
                for (mention <- estimated) {
                    if (checkEquality(label, mention)) {
                        true_pos += 1
                        found_match = true
                        break
                    }
                }
            }
            if (!(found_match)) {
                false_neg += 1
            }
        }
        val false_pos = estimated.length - true_pos
        // Should probably calculate F1
        (true_pos, false_neg, false_pos)
    }


    def insideCorefChain(chains: Map[Integer, CorefChain], target: Mention,
                         mention: Mention): Option[CorefChain.CorefMention] = {
        val token_idx = target.tokenInterval(0) + 1
        val sentence_id = target.sentence
        // Check each chain for membership
        for (cc <- chains.values) {
            for (mention <- cc.getMentionsInTextualOrder) {
                if (sentence_id == (mention.sentNum - 1) &
                  token_idx >= mention.startIndex & token_idx <= mention.endIndex) {
                    return Option(cc.getMentionsInTextualOrder.head: CorefChain.CorefMention)
                }
            }
        }
        None
    }


    def getCorefHeadLabel(coref_head: CorefChain.CorefMention, mentions: Vector[Mention]): Option[String] = {
        val sent_id = coref_head.sentNum - 1
        for (mention <- mentions) {
            if (sent_id == mention.sentence &
              coref_head.startIndex <= mention.start & mention.end <= coref_head.endIndex) {
                return Option(mention.label)
            }
        }
        None
    }


    def runExtraction(transcript: String, transcription_id: String):
     (ArrayBuffer[Array[Any]], org.clulab.processors.Document) = {
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

        val all_events = new ArrayBuffer[Array[Any]]
        val corenlp_doc = new Annotation(transcript)
        pipeline.annotate(corenlp_doc)
        val coref_chains = corenlp_doc.get(classOf[CorefCoreAnnotations.CorefChainAnnotation])

        val doc = ieSystem.annotate(transcript)
        val mentions = ieSystem.extractFrom(doc).sortBy(m =>
            (m.sentence, m.getClass.getSimpleName))
        for (m <- mentions) {
            if (m.arguments contains "target") {
                all_events += Array(m, transcription_id) // TODO remove this once anaphor matching is robust
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


    def writeExtractionsToFile(all_events: ArrayBuffer[Array[Any]],
                                  output_file_name: String = "output_predictions.tsv") = {
        val out_file = new File(output_file_name)
        val file_buffer = new BufferedWriter(new FileWriter(out_file))
        val eventMap = new mutable.ListMap[String, Int]()
        for (event_array <- all_events) {
            val m = event_array(0).asInstanceOf[Mention]
            val session_id = event_array(1).asInstanceOf[String]
            var key = m.label
            if (m.arguments contains "target") {
                key += "-" + m.arguments("target").head.label
            }
            println(key)
            file_buffer.write(key + '\t')
            println('\t' + m.text)
            file_buffer.write(m.text + '\t')
            for ((k, v) <- m.arguments) {
                file_buffer.write(k + " : ")
                v.foreach(x => print(x.text))
                v.foreach(x => file_buffer.write(x.text))
                file_buffer.write('|')
                print('\n')
            }
            file_buffer.write('\t')
            file_buffer.write(m.document.sentences(m.sentence).getSentenceText + '\n')
            file_buffer.write("\t" + session_id + "\n")
            eventMap.update(key, eventMap.getOrElse(key, 0) + 1)
        }
        file_buffer.close()

        val action_counts = new mutable.ListMap[String, Int]()
        for (event <- all_events){
            val action = event(0).asInstanceOf[Mention]
            if (action_counts.containsKey(action.label)){
                action_counts.update(action.label, action_counts(action.label) + 1)
            } else {
                action_counts.update(action.label, 1)
            }
        }
    }

    def getAnnotations(annotation_file_name: String):
      (ArrayBuffer[ActionAnnotation], mutable.HashMap[String, EntityAnnotation]) = {
        val annotated_transcript = Source.fromFile(annotation_file_name).getLines.mkString

        val annotation_events = new ArrayBuffer[ActionAnnotation]
        val coref_events = new mutable.HashMap[String, EntityAnnotation]()
        var inside_label = false
        var inside_bracket = false
        var after_open_bracket = false
        var prev_was_open = false
        var coref_label = false
        var start_offset = 0
        var end_offset = 0
        var char_counter = 0
        var output_string = ""
        var raw_attributes = ""
        var prev_char = ""
        for (c <- annotated_transcript){
            if (inside_bracket && c == '>'){
                if (after_open_bracket){
                    inside_label = false
                    inside_bracket = false
                    after_open_bracket = false
                    if (coref_label){
                        println("COREF: "+output_string + " : " + start_offset + ", " + end_offset)
                        println("\t"+raw_attributes)
                        val temp = raw_attributes.split(" ")
                        val extract_id = temp(1).slice(4, temp(1).size-2)
                        val extract_type = temp(2).slice(4, temp(2).size-1)
                        coref_events(extract_id) =
                          new EntityAnnotation(output_string, start_offset, end_offset, extract_id)
                        coref_label = false
                    } else {
                        println(output_string + " : " + start_offset + ", " + end_offset)
                        var temp = raw_attributes.split(" ")
                        var extract_type = temp(1).slice(6, temp(1).size-2)
                        var extract_target = "-1"; var extract_id = "-1"; var extract_fact = false
                        if (temp.size > 4){
                            extract_target = temp(2).slice(8, temp(2).size-2)
                            extract_id = temp(3).slice(4, temp(3).size-2)
                            if (temp(4).slice(6, temp(4).size-1) == "no") {
                              extract_fact = false
                            } else {
                              extract_fact = true
                            }
                        } else {
                            extract_id = temp(2).slice(4, temp(2).size-2)
                            if (temp(3).slice(6, temp(3).size-1) == "no") {
                              extract_fact = false
                            } else {
                              extract_fact = true
                            }
                        }
                      println("\t"+extract_type+" "+extract_id+" "+extract_target+" "+extract_fact)

                        annotation_events.append(new ActionAnnotation(output_string, start_offset, end_offset,
                            extract_type, extract_id, target_id = extract_target, factual = extract_fact))
                    }

                    output_string = ""
                    raw_attributes = ""
                } else {
                    inside_bracket = false
                    after_open_bracket = true
                    start_offset = char_counter
                }
            } else if (c == '<'){
                inside_label = true
                inside_bracket = true
                prev_was_open = true
                if (after_open_bracket){
                    end_offset = char_counter
                }
            } else if (inside_label) {
                if (!after_open_bracket && inside_bracket){
                    raw_attributes += c
                }
                if (!inside_bracket) {
                    output_string += c
                    char_counter += 1
                }
                if (prev_was_open){
                    if (c == 'C'){
                        coref_label = true
                    }
                    prev_was_open = false
                }
            } else {
                char_counter += 1
            }
            prev_char = c.toString
        }

        for (cur_action <- annotation_events){
            if (cur_action.target_id != "-1"){
                cur_action.target_span = coref_events.getOrElse(cur_action.target_id, "").toString
            }
        }

        (annotation_events, coref_events)
    }

    def getCleanDoc(zoom_file_name: String): (String) = {
      val zoom_lines = Source.fromFile(zoom_file_name).getLines
      zoom_lines.next
      var output_string = ""; var cur = ""
      val punctuation = Set('?', '!', '.')
      for (line <- zoom_lines){
        cur = line.stripLineEnd
        if (cur.size > 3) {
          if (!cur.charAt(0).isDigit) {//(cur.substring(0, 3) == "Par") {
            if (cur.contains(":")) {
              cur = cur.split(":")(1)
            }
            if (!punctuation(cur.charAt(cur.size-1))) {
                cur += "."
            }
            output_string += cur
          }
        }
      }
      output_string
    }

    def isVictimQuestion(utterance: String): Boolean = {
      val gold_tokens = "Which type of victim will you save next Yellow green or whoever comes next".split(' ').toSet
      var tokens = utterance.split(' ').toSet
      var match_count = 0
      for (tok <- tokens){
        if (gold_tokens contains tok){
          match_count += 1
        }
      }
      (match_count > 4)
    }

    def getTimeMap(zoom_file_name: String): (ArrayBuffer[Int], ArrayBuffer[java.util.Date],
                                              ArrayBuffer[Int], ArrayBuffer[Int]) = {
      // This returns the ending char for each utterance the timestamp of each utterance, and the
      //  index of researcher utterances
      val zoom_lines = Source.fromFile(zoom_file_name).getLines
      val char_values = ArrayBuffer[Int]()
      val date_values = ArrayBuffer[java.util.Date]()
      val researcher_char_values = ArrayBuffer[Int]()
      val researcher_questions = ArrayBuffer[Int]()
      val time_format = new SimpleDateFormat("HH:mm:ss.SSS")
      val punctuation = Set('?', '!', '.')

      zoom_lines.next
      var char_index = 0
      var researcher_flag = false
      var prev = ""; var cur = ""
      for (line <- zoom_lines) {
        cur = line.stripLineEnd
        if (cur.size > 3) {
          if (cur.charAt(0).isDigit) {
            prev = cur
          } else {
            if (cur.contains(":")) {
              // All utterances following a labeled utterance belong to the root label
              //   If a utterance has a ':' char, then its a root utterance
              researcher_flag = (cur.substring(0, 3) != "Par")
              cur = cur.split(":")(1)
            }
            if (!punctuation(cur.charAt(cur.size - 1))) {
              cur += "."
            }
            char_index += cur.size
            prev = prev.split(" --> ")(0) //0 is start time and 1 is end time
            val timestamp = time_format.parse(prev)
            char_values.add(char_index)
            date_values.add(timestamp)
            if (researcher_flag) {
              researcher_char_values.add(char_values.size-1)
              if (isVictimQuestion(cur)){
                researcher_questions.add(char_index)
              }
            }
          }
        }
      }
      (char_values, date_values, researcher_char_values, researcher_questions)
    }

    def getTimeStamp(char_indices: ArrayBuffer[Int], date_values: ArrayBuffer[java.util.Date], index: Int):
                    java.util.Date = {
      var cur = char_indices(0)
      var i = 0
      while(cur < index){
        i += 1
        cur = char_indices(i)
      }
      date_values(i)
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


    // SCRIPT START

    println("[CoreNLP] Initializing the CoreNLP pipeline ...")
    val corenlp_properties = new Properties()
    corenlp_properties.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref")
    val pipeline = new StanfordCoreNLP(corenlp_properties)
    println("[CoreNLP] Completed Initialization")

    val taxonomy_map_raw = Source.fromResource("taxonomy_map.json").mkString
    val taxonomy_json = JsonParser(taxonomy_map_raw)
    val tax_map = taxonomy_json.convertTo[immutable.Map[String, Array[immutable.Map[String, String]]]]

    println("[AsistEngine] Initializing the AsistEngine ...")
    val ieSystem = new AsistEngine()
    var proc = ieSystem.proc
    println("[AsistEngine] Completed Initialization ...")

    val input_dir_name = if (args.length > 0){
        args(0)
    } else {
        throw new Exception("Must include transcript directory path: {transcript file} {metadata|None} {annotation|None")
    }

    val output_file = new PrintWriter(new File("output_events.txt"))

    //https://alvinalexander.com/scala/how-to-list-subdirectories-under-directory-in-scala/
    def getFilesInDir(dir: File): Array[String] = {
      dir.listFiles
        .filter(_.isFile)
        .map(_.getAbsolutePath)
        .filter(_.endsWith(".vtt"))
    }

  println("Starting in: "+input_dir_name)
    for (input_file_name <- getFilesInDir(new File(input_dir_name))){
      println("Extracting from: "+input_file_name+" . . .")
      val raw_text = getCleanDoc(input_file_name)
      val time_lists = getTimeMap(input_file_name)

      ieSystem.timeintervals = (time_lists._1, time_lists._3, time_lists._4)
      ieSystem.reload()

      var doc:org.clulab.processors.Document = null
      val all_events = new ArrayBuffer[Array[Any]]
      val (extractions, extracted_doc) = runExtraction(raw_text, "")
      doc = extracted_doc
      extractions.foreach(event_array =>
          all_events.append(event_array)
      )
      //println("Completed event extraction step")

      val experiment_id = "NULL"; val trial_id = "NULL"
      val time_format = new SimpleDateFormat("HH:mm:ss.SSS")
      var text: String = ""; var startOffset = 0; var endOffset = 0
      for (extraction <- all_events) {
        val mention = extraction(0).asInstanceOf[Mention]
        val argument_labels = for (key <- mention.arguments.keys)
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
        var taxonomy_matches = "{\n"
        var count = 0
        for (term <- tax_map(mention.label)) {
          taxonomy_matches = taxonomy_matches + s"""|         \"${term("term")}\": \"${term("score")}\""""
          count += 1
          if (count != tax_map(mention.label).size) {
            taxonomy_matches = taxonomy_matches + ",\n"
          } else {
            taxonomy_matches = taxonomy_matches + "\n"
          }
        }
        taxonomy_matches = taxonomy_matches + "    }"
        val json_rep_raw =
          s"""
             |{
             | "header": {
             |   "timestamp": "${time_format.format(timestamp)}",
             |   "message_type": "event",
             |   "version": "0.1"
             | },
             | "msg": {
             |   "source": "DialogueActionExtractor",
             |   "experiment_id": "${experiment_id.toString}",
             |   "trial_id": "${trial_id.toString}",
             |   "timestamp": "${time_format.format(timestamp)}",
             |   "sub_type": "Event:dialogue_action",
             |   "version": "0.1"
             | },
             | "data": {
             |     "Label" : "${mention.label}",
             |     "Span" : "${mention.words.mkString(" ")}",
             |     "Arguments" : "${argument_labels.mkString(" ")}",
             |     "Text" : "${doc.sentences(mention.sentence).getSentenceText}",
             |     "timestamp" : "${time_format.format(timestamp)}",
             |     "TaxonomyMatches" : ${taxonomy_matches}
             | }
             |}
             |"""
            val json_rep = parse(json_rep_raw.
              stripMargin)
            output_file.write(
              compact(render(json_rep)) + "\n")
        }
  }
  output_file.close()
}
