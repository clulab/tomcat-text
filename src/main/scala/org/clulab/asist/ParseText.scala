package org.clulab.asist

import org.clulab.asist.AsistEngine
import org.clulab.processors.Processor
import org.clulab.processors.fastnlp.FastNLPProcessor

object ParseText extends App {

    val text = "I attack the zombie"
    println(s"Processing sentence : ${text}" )
 
    println("[AsistEngine] Initializing the AsistEngine ...")
    val ieSystem = new AsistEngine()
    var proc = ieSystem.proc
    println("[AsistEngine] Completed Initialization ...") 
    val doc = ieSystem.annotate(text)

    // extract mentions from annotated document
    val mentions = ieSystem.extractFrom(doc).sortBy(m => (m.sentence, m.getClass.getSimpleName))
    println(s"Labels: ${mentions.map(m => m.label).mkString(",\t")}")
    
  
}
