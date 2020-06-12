package org.clulab.asist

import scala.io.Source
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap
import scala.collection.mutable.Map
import scala.collection.{immutable, mutable}
import scala.util.parsing.json.JSON
import scala.util.control.Breaks._
import java.util.Properties

import org.clulab.odin.Mention
import org.clulab.odin.EventMention
import org.clulab.odin.TextBoundMention
import org.clulab.asist.AsistEngine
import org.clulab.processors.Processor
import org.clulab.processors.fastnlp.FastNLPProcessor
import edu.stanford.nlp.pipeline.Annotation
import edu.stanford.nlp.pipeline.StanfordCoreNLP
import edu.stanford.nlp.coref.CorefCoreAnnotations
import edu.stanford.nlp.coref.data.CorefChain
//import edu.stanford.nlp.ling.CoreAnnotations.CoreLabel
//import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation
//import edu.stanford.nlp.ling.CoreAnnotations.CollapsedDependenciesAnnotation




object ExtractInfo extends App {
    
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
        if (!(mention.matches(event.get("type").get.asInstanceOf[String]))){
            return false
        }
        //println("mention: ("+mention.sentence+","+mention.startOffset+","+mention.endOffset+")")
        val eventProv = getProv(event)
        //println("event:   "+eventProv)
        //if (eventProv._1 != mention.sentence){
          //  return false
        //}
        if (eventProv._2 != 1+mention.startOffset){
            return false
        }
        //if (eventProv._3 != 1+mention.endOffset){
        //    return false
        //}
        return true    
    }

    def compareLabelLists(labels: ArrayBuffer[Any], estimated: Vector[Mention]): (Int, Int, Int) = {
        var true_pos = 0
        var false_neg = 0

        var found_match = false
        for (label <- labels){
            found_match = false
            breakable{
                for (mention <- estimated){
                    if (checkEquality(label, mention)){
                       true_pos += 1
                       found_match = true
                       break 
                    }
                }
            }
            if (!(found_match)){
                false_neg += 1
            }
        }
        val false_pos = estimated.length - true_pos
        // Should probably calculate F1
        return (true_pos, false_neg, false_pos)
    }
   

    def insideCorefChain(chains: Map[Integer, CorefChain], target: Mention,
                        mention: Mention): Option[CorefChain.CorefMention] = {
        val token_idx = target.tokenInterval(0) + 1
        val sentence_id = target.sentence
        // Check each chain for membership
        for (cc <- chains.values){
            for (mention <- cc.getMentionsInTextualOrder) {
                if (sentence_id == (mention.sentNum - 1) &
                    token_idx >= mention.startIndex & token_idx <= mention.endIndex){
                    return Option(cc.getMentionsInTextualOrder.head: CorefChain.CorefMention)
                }
            }
        }
        return None
    }


    def getCorefHeadLabel(coref_head: CorefChain.CorefMention, mentions: Vector[Mention]): Option[String] = {
        val sent_id = coref_head.sentNum - 1
        for (mention <- mentions) {
            if (sent_id == mention.sentence &
              coref_head.startIndex <= mention.start & mention.end <= coref_head.endIndex) {
                return Option(mention.label)
            }
        }
        return None
    }

    val text_file_name = "all_transcripts.txt"
    val labels_file_name = "all_labels.txt"
    val event_pair_file_name = "event_pairs.tsv"

    // load tsv of acceptable event-action pairs
    val event_pair_iter = Source.fromResource(event_pair_file_name).getLines
    val event_pair_map = new mutable.HashMap[String, Set[String]]
    for (line <- event_pair_iter) {
        val split_line = line.split('\t').map(_.trim)
        if (event_pair_map contains  split_line(0)){
            event_pair_map(split_line(0)).add(split_line(1))
        } else {
            event_pair_map.put(split_line(0), Set(split_line(1)))
        }
    }

    println("[AsistEngine] Initializing the AsistEngine ...")
    val ieSystem = new AsistEngine()
    var proc = ieSystem.proc
    println("[AsistEngine] Completed Initialization ...") 
    
    println("[CoreNLP] Initializing the CoreNLP pipeline ...")
    val corenlp_properties = new Properties()
    corenlp_properties.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref")
    val pipeline = new StanfordCoreNLP(corenlp_properties)
    println("[CoreNLP] Completed Initialization")

    val text_iter = Source.fromResource(text_file_name).getLines
    val label_source = Source.fromResource(labels_file_name)
    val label_str = try label_source.mkString finally label_source.close()
    val json_parse = JSON.parseFull(label_str)
    val labels = json_parse.get.asInstanceOf[immutable.Map[String, List[Any]]].get("extracted").get
    

    var label_index: Int = 0
    var total_true_p = 0
    var total_false_p = 0
    var total_false_n = 0
    var total_labels = 0
    for (i <- 0 to 77){ // The total number of utterances
        // Collect all events for this utterance into array
        val utterance_events = new ArrayBuffer[Any]()
        breakable {
            while(label_index < labels.length){
                val label_utt = getUttNum(labels(label_index))
                if (label_utt == i){
                    utterance_events += labels(label_index)
                } else if (label_utt > i){
                    break
                }
                label_index += 1
            }
        }
        
        val line = text_iter.next
        println(s"Processing text : ${line}" )

        val corenlp_doc = new Annotation(line)
        pipeline.annotate(corenlp_doc)
        val coref_chains = corenlp_doc.get(classOf[CorefCoreAnnotations.CorefChainAnnotation])
        for (cc <- coref_chains.values()){
            val mention = cc.getMentionsInTextualOrder()
            val coref_head = cc.getRepresentativeMention()
            if (mention.size() > 1){
                println("\t" + coref_head.mentionSpan)
                println("\t " + coref_head.startIndex + " " + coref_head.endIndex)
            }
        }

        val doc = ieSystem.annotate(line)
        val mentions = ieSystem.extractFrom(doc).sortBy(m => 
                            (m.sentence, m.getClass.getSimpleName))
        for (m <- mentions){
            if (m.arguments contains "target"){
                val target = m.arguments("target").head
                println(s" ${m.label} -> ")
                for (i <- target.tokenInterval){
                    val sent = m.document.sentences(target.sentence)
                    println(s"   ${target.label}:${sent.words(i)}")
                }
                if (target.label == "Anaphor"){
                    var matched_chain = insideCorefChain(coref_chains.asScala, target, m) match {
                        case Some(coref_head) =>
                            // Now check if coref_head has label other than anaphor
                            var coref_head_label = getCorefHeadLabel(coref_head, mentions) match {
                                case Some(head_label) =>
                                    if ((event_pair_map contains m.label) &
                                      (event_pair_map(m.label) contains head_label)){
                                        println("   Acceptable match")
                                    } else {
                                        println("   Not acceptable match")
                                    }
                                case _ =>
                            }
                        case _ =>
                    }
                        
                }
            } else {
                println(s" ${m.label}")
            }
        }

        val result = compareLabelLists(utterance_events, mentions)
        total_true_p += result._1
        total_labels += result._1 + result._2
        total_false_n += result._2
        total_false_p += result._3
        print(" matched: "+result._1+" not matched: "+result._2+"\n") 
        println(s" Number of true labels: ${utterance_events.map(
                m => m.asInstanceOf[immutable.Map[String, Any]].get("type").get)}")
        println("\n")         
    }

    println("########################################")
    val accuracy: Float = total_true_p.toFloat / total_labels.toFloat * 100
    println("### Final accuracy: "+accuracy+" ("+total_true_p+"/"+total_labels+")")
    val precision: Float = total_true_p.toFloat / (total_true_p.toFloat+total_false_p.toFloat)
    val recall: Float = total_true_p.toFloat / (total_true_p.toFloat+total_false_n.toFloat)
    val f1: Float = 200 * (precision * recall) / (precision + recall)
    println("### Final F1:        "+f1)
    println("### Final Precision: "+precision*100)
    println("### Final Recall:    "+recall*100)
}
