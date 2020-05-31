package org.clulab.asist

import scala.io.Source
import scala.collection.JavaConversions._

import java.util.Properties

import org.clulab.asist.AsistEngine
import org.clulab.processors.Processor
import org.clulab.processors.fastnlp.FastNLPProcessor

import edu.stanford.nlp.pipeline.Annotation
import edu.stanford.nlp.pipeline.StanfordCoreNLP
import edu.stanford.nlp.coref.CorefCoreAnnotations
//import edu.stanford.nlp.ling.CoreAnnotations.CoreLabel
//import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation
//import edu.stanford.nlp.ling.CoreAnnotations.CollapsedDependenciesAnnotation

/*
object ParseText extends App {

    println("[AsistEngine] Initializing the AsistEngine ...")
    val ieSystem = new AsistEngine()
    var proc = ieSystem.proc
    println("[AsistEngine] Completed Initialization ...") 
    
    println("[CoreNLP] Initializing the CoreNLP pipeline ...")
    val corenlp_properties = new Properties()
    corenlp_properties.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref")
    val pipeline = new StanfordCoreNLP(corenlp_properties)
    println("[CoreNLP] Completed Initialization")
    
    val text_file_name = "all_transcripts.txt"
    val labels_file_name = "all_labels.txt"
    val text_iter = Source.fromResource(text_file_name).getLines;
    val label_iter = Source.fromResource(labels_file_name).getLines;
    for (i <- 0 to 77){ //99) {
        val line = text_iter.next
        //val labels = label_iter.next
        println(s"Processing text : ${line}" )
        val corenlp_doc = new Annotation(line)
        pipeline.annotate(corenlp_doc)
        for (cc <- corenlp_doc.get(classOf[CorefCoreAnnotations.CorefChainAnnotation]).values()){
            val mention = cc.getMentionsInTextualOrder()
            if (mention.size() > 1){
                println("\t" + cc)
            }
        }
        //println(s"           labels: ${labels}")
        val doc = ieSystem.annotate(line)
        val mentions = ieSystem.extractFrom(doc).sortBy(m => 
                            (m.sentence, m.getClass.getSimpleName))
        println(s" Estimated labels: ${mentions.map(m => m.label).mkString(" ")}")
    } 
}*/
