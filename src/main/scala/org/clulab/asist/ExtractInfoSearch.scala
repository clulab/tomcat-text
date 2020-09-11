package org.clulab.asist

import java.io.{BufferedWriter, File, FileWriter}
import java.util.Properties
import java.text.SimpleDateFormat

import org.clulab.factuality.Factuality
import edu.stanford.nlp.coref.CorefCoreAnnotations
import edu.stanford.nlp.coref.data.CorefChain
import edu.stanford.nlp.dcoref.CoNLL2011DocumentReader.Document
import edu.stanford.nlp.pipeline.{Annotation, StanfordCoreNLP}
import org.clulab.odin.{EventMention, Mention, TextBoundMention}
import org.json4s.JObject
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._

import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import scala.collection.mutable.{ArrayBuffer, Map}
import scala.collection.{immutable, mutable}
import scala.io.Source
import scala.util.control.Breaks._
import scala.util.parsing.json.{JSON, JSONType}


object ExtractInfoSearch extends App {

    println("[AsistEngine] Initializing the AsistEngine ...")
    val ieSystem = new AsistEngine()
    var proc = ieSystem.proc
    println("[AsistEngine] Completed Initialization ...")

    println("[CoreNLP] Initializing the CoreNLP pipeline ...")
    val corenlp_properties = new Properties()
    corenlp_properties.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref")
    val pipeline = new StanfordCoreNLP(corenlp_properties)
    println("[CoreNLP] Completed Initialization")


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
      var output_string = ""; var cur = ""
      val punctuation = Set('?', '!', '.')
      for (line <- zoom_lines){
        cur = line.stripLineEnd
        if (cur.size > 3) {
          if (cur.substring(0, 3) == "Par") {
            cur = cur.split(":")(1)
            if (!punctuation(cur.charAt(cur.size-1))) {
              cur += "."
            }
            output_string += cur
          }
        }
      }
      output_string
    }

    def getTimeMap(zoom_file_name: String): (ArrayBuffer[Int], ArrayBuffer[java.util.Date]) = {
      val zoom_lines = Source.fromFile(zoom_file_name).getLines
      val char_values = ArrayBuffer[Int]()
      val date_values = ArrayBuffer[java.util.Date]()
      val time_format = new SimpleDateFormat("HH:mm:ss.SSS")
      val punctuation = Set('?', '!', '.')

      var char_index = 0
      var prev = ""; var cur = ""
      for (line <- zoom_lines){
        cur = line.stripLineEnd
        if (cur.size > 0) {
          if (cur.charAt(0).isDigit) {
            prev = cur
          } else if (cur.substring(0, 3) == "Par") {
            cur = cur.split(":")(1)
            if (!punctuation(cur.charAt(cur.size-1))) {
              cur += "."
            }
            char_index += cur.size
            prev = prev.split(" --> ")(0) //0 is start time and 1 is end time
            val timestamp = time_format.parse(prev)
            char_values.add(char_index)
            date_values.add(timestamp)
          }
        }
      }
      (char_values, date_values)
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

    val input_file_name = if (args.length > 0){
        args(0)
    } else {
        throw new Exception("Must include transcript file path: {transcript file} {metadata|None} {annotation|None")
    }
    var raw_text = getCleanDoc(input_file_name)
    var time_lists = getTimeMap(input_file_name)
    var doc:org.clulab.processors.Document = null
    val all_events = new ArrayBuffer[Array[Any]]
    val (extractions, extracted_doc) = runExtraction(raw_text, "")
    doc = extracted_doc
    extractions.foreach(event_array =>
        all_events.append(event_array)
    )
    println("Completed event extraction step")
    /*
    val factuality = Factuality("org/clulab/factuality/models/FTrainFDevScim3")
    println("Initialized factuality")
    val words = "Parthenolide induced apoptosis and inhibited cell proliferation and the expression of VEGF in vitro .".split(' ')
    val predicateIndex = 1 // induced
    val prediction: Float = factuality.predict(words, predicateIndex)
    println(s"Prediction: $prediction")
    */

    // Only given transcript & metadata, print extracted events in JSON format
    if (args.length < 3) {
      var experiment_id = ""
      var trial_id = ""
      // If no metadata file is given, just put NULL for missing info
      if (args.length < 2) {
        experiment_id = "NULL"
        trial_id = "NULL"
      } else {
        val metadata = getMetaData(args(1))
        experiment_id = metadata._1
        trial_id = metadata._2
      }
      val time_format = new SimpleDateFormat("HH:mm:ss.SSS")
      var text: String = ""; var startOffset = 0; var endOffset = 0
      println("About to print "+all_events.size+" event json!")
      for (extraction <- all_events){
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
            val json_rep = parse(
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
                  |     "timestamp" : "${time_format.format(timestamp)}"
                  |  }
                  |}
                  |""".stripMargin
            )
            println(compact(render(json_rep)))
        }
    // If there are 3 args then third must be annotation file, evaluate performance
    } else {
        val annotation_tuple = getAnnotations(args(2))
        val annotation_events = annotation_tuple._1
        val coref_events = annotation_tuple._2

        println("Number of true labels: "+annotation_events.size)
        var anno_index = 0
        var cur_anno = annotation_events(anno_index)
        var num_shallow_match = 0
        var num_deep_match = 0
        for (m <- all_events){
            val cur = m(0).asInstanceOf[Mention]
            var label = cur.label
            var text = ""
            var startOffset = 0
            var endOffset = 0
            cur match {
                case cur: EventMention =>
                    text = cur.trigger.text
                    startOffset = cur.trigger.startOffset
                    endOffset = cur.trigger.endOffset
                case cur: TextBoundMention =>
                    text = cur.text
                    startOffset = cur.startOffset
                    endOffset = cur.endOffset
            }
            println("Looking at extraction: "+text+" : "+doc.sentences(cur.sentence).getSentenceText)
            println("   comparing to: " + cur_anno)
            if (anno_index < annotation_events.size) {
                cur_anno = annotation_events(anno_index)
                breakable{
                    while (startOffset > cur_anno.start_offset && endOffset > cur_anno.end_offset) {
                        anno_index += 1
                        if (anno_index >= annotation_events.size) break
                        cur_anno = annotation_events(anno_index)
                        println("   comparing to: " + cur_anno)
                    }
                }
                if (cur_anno.shallowCompare(label, startOffset, endOffset)) {
                    println("--------shallow match-----------")
                    num_shallow_match += 1
                }
                if (cur.arguments contains "target") {
                    if (cur_anno.deepCompare(label, startOffset, endOffset,
                       Some(cur.arguments("target")(0).text))) {
                        println("--------deep match-----------")
                        num_deep_match += 1
                    }
                } else {
                    if (cur_anno.deepCompare(label, startOffset, endOffset)) {
                        println("--------deep match-----------")
                        num_deep_match += 1
                    }
                }
            }
        }
        println("\nNumber of matched events (shallow): "+num_shallow_match)
        println("Number of matched events (deep): "+num_deep_match)
        println("Number of true positive events: "+annotation_events.size)
        println("Number of estimated positive events: "+all_events.size)

        val shallow_recall = num_shallow_match.toDouble/all_events.size.toDouble
        val shallow_precision = num_shallow_match.toDouble/annotation_events.size
        val shallow_f1 = 2.0 * (shallow_recall * shallow_precision) / (shallow_recall + shallow_precision)
        println("\n- - - Shallow Match - - -")
        println("Recall: "+shallow_recall)
        println("Precision: "+shallow_precision)
        println("F1: "+shallow_f1)

        val deep_recall = num_deep_match.toDouble/all_events.size.toDouble
        val deep_precision = num_deep_match.toDouble/annotation_events.size
        val deep_f1 = 2.0 * (deep_recall * deep_precision) / (deep_recall + deep_precision)
        println("\n- - - Deep Match - - -")
        println("Recall: "+deep_recall)
        println("Precision: "+deep_precision)
        println("F1: "+deep_f1)

    }
}
