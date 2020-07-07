package org.clulab.asist

import java.io.{BufferedWriter, File, FileWriter}
import java.util.Properties

import edu.stanford.nlp.coref.CorefCoreAnnotations
import edu.stanford.nlp.coref.data.CorefChain
import edu.stanford.nlp.pipeline.{Annotation, StanfordCoreNLP}
import edu.stanford.nlp.util.MapList
import org.clulab.odin.serialization.json.EventMention
import org.clulab.odin.{EventMention, Mention, TextBoundMention}

import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import scala.collection.mutable.{ArrayBuffer, Map}
import scala.collection.{immutable, mutable}
import scala.io.Source
import scala.util.control.Breaks._
import scala.util.parsing.json.JSON
//import edu.stanford.nlp.ling.CoreAnnotations.CoreLabel
//import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation
//import edu.stanford.nlp.ling.CoreAnnotations.CollapsedDependenciesAnnotation


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


    def runExtraction(transcript: String, transcription_id: String): ArrayBuffer[Array[Any]] = {
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
            all_events += Array(m, transcription_id)
            if (m.arguments contains "target") {
                val target = m.arguments("target").head
                //println(s" ${m.label} -> ")
                for (i <- target.tokenInterval) {
                    val sent = m.document.sentences(target.sentence)
                    //println(s"   ${target.label}:${sent.words(i)}")
                }
                if (use_coref_resolution && target.label == "Anaphor") {
                    insideCorefChain(coref_chains.asScala, target, m) match {
                        case Some(coref_head) =>
                            // Now check if coref_head has label other than anaphor
                            getCorefHeadLabel(coref_head, mentions) match {
                                case Some(head_label) =>
                                    if (event_pair_map contains m.label) {
                                        if (event_pair_map(m.label) contains head_label) {
                                            println("   Acceptable match")
                                        } else {
                                            println("   Not acceptable match")
                                        }
                                    } else {
                                        println("   Unrestricted match")
                                    }
                                case _ =>
                            }
                        case _ =>
                    }

                }
            } else {
                //println(s" ${m.label}")
            }
        }

        all_events
    }

    // SCRIPT START



    val input_file_name = if (args.length > 0){
        args(0)
    } else {
        throw new Exception("Must include transcript file path: {transcript file} {annotation file}")
    }
    val input_lines = Source.fromFile(input_file_name).getLines


    val annotation_file_name = if (args.length > 1) {
        args(1)
    } else {
        throw new Exception("Must include annotation file path: {transcript file} {annotation file}")
    }
    val annotated_transcript = Source.fromFile(annotation_file_name).getLines.mkString

    var annotation_events = new ArrayBuffer[ActionAnnotation]
    var coref_events = new ArrayBuffer[EntityAnnotation]
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
                    var temp = raw_attributes.split(" ")
                    var extract_id = temp(1).slice(4, temp(1).size-1)
                    coref_events.append(new EntityAnnotation(output_string, start_offset, end_offset, extract_id.toInt))
                    coref_label = false
                } else {
                    println(output_string + " : " + start_offset + ", " + end_offset)
                    var temp = raw_attributes.split(" ")
                    println("\t"+raw_attributes)
                    var extract_type = temp(1).slice(6, temp(1).size-2)
                    var extract_target = -1
                    var extract_id = -1
                    if (temp.size > 2){
                        extract_target = temp(2).slice(8, temp(2).size-2).toInt
                        extract_id = temp(3).slice(4, temp(3).size-1)    .toInt
                    } else {
                        extract_target = -1
                        extract_id = temp(2).slice(4, temp(2).size-2).toInt
                    }

                    annotation_events.append(new ActionAnnotation(output_string, start_offset, end_offset,
                        extract_type, extract_id, extract_target))
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

    println("Number of true labels: "+annotation_events.size)

    val all_events = new ArrayBuffer[Array[Any]]
    for (line <- input_lines) {
        //val temp = line.split("\t")
        runExtraction(line, "").foreach(event_array => all_events.append(event_array))
    }

    var anno_index = 0
    var cur_anno = annotation_events(anno_index)
    var num_shallow_match = 0
    for (m <- all_events){
        val cur = m(0)
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
        println("Looking at extraction: "+text+" : "+startOffset+", "+endOffset)
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
            if (cur_anno.shallowCompare("TODO", startOffset, endOffset)) {
                println("--------match-----------")
                num_shallow_match += 1
            }
            println(" current anno_index is: " + anno_index)
        }
    }
    println("\nNumber of matched events (shallow): "+num_shallow_match)
    println("Number of true positive events: "+annotation_events.size)
    println("Number of estimated positive events: "+all_events.size)
    val recall = num_shallow_match.toDouble/all_events.size.toDouble
    val precision = num_shallow_match.toDouble/annotation_events.size
    val f1_score = 2.0 * (recall * precision) / (recall + precision)
    println("\nRecall: "+recall)
    println("Precision: "+precision)
    println("F1: "+f1_score)
    /*
    val out_file = new File("output_predictions.tsv")
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
            print("\t\t" + k + " : ")
            file_buffer.write(k + " : ")
            v.foreach(x => print(x.text))
            v.foreach(x => file_buffer.write(x.text))
            file_buffer.write('|')
            print('\n')
        }
        file_buffer.write('\t')
        println('\t' + m.document.sentences(m.sentence).getSentenceText)
        file_buffer.write(m.document.sentences(m.sentence).getSentenceText + '\n')
        println("\t" + session_id + "\n")
        file_buffer.write("\t" + session_id + "\n")
        eventMap.update(key, eventMap.getOrElse(key, 0) + 1)
    }
    file_buffer.close()

    println("Number of events detected: " + all_events.size)
    val action_counts = new mutable.ListMap[String, Int]()
    for (event <- all_events){
        val action = event(0).asInstanceOf[Mention]
        if (action_counts.containsKey(action.label)){
            action_counts.update(action.label, action_counts(action.label) + 1)
        } else {
            action_counts.update(action.label, 1)
        }
    }
    println("\nExtracted Actions:")
    for ((key, count) <- action_counts){
        println(key+": "+count.toString)
    }
    */

    //action_counts.forEach((key, count) => println(key+": "+count.toString))

}
