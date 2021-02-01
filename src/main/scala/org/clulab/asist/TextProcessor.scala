/**  ChatAnalysis
 *
 *  Author:  Joseph Astier, Adarsh Pyarelal
 *  Date:  2020 December
 *
 *  Convert a text chat message to a DialogAgentMessage object
 */
package org.clulab.asist

import edu.stanford.nlp.pipeline.{Annotation, StanfordCoreNLP}
import edu.stanford.nlp.pipeline.StanfordCoreNLP
import java.time.Clock
import java.util.Properties
import org.clulab.odin.{EventMention, Mention, TextBoundMention}
import scala.collection.immutable
import scala.collection.mutable.{ArrayBuffer, Map}
import scala.io.Source
import spray.json._
import spray.json.DefaultJsonProtocol._

import org.clulab.utils.DisplayUtils

/** Process text using the StanfordCoreNLP */
class TextProcessor {

  /** Build an extractor for our tokens */
  val pipeline = new StanfordCoreNLP(new Properties {
    setProperty(
      "annotators", 
      "tokenize, ssplit, pos, lemma, ner, parse, dcoref"
    )
  })


  /** Load the taxonomy map from resource file */
  val taxonomy_map = JsonParser(
    Source.fromResource("taxonomy_map.json").mkString
  ).convertTo[immutable.Map[String, Array[immutable.Map[String, String]]]]

  /** Create the extractor using the pipeline and taxonomy map */
  val extractor = new Extractor(pipeline, new AsistEngine(), taxonomy_map)

  // Run the extractor after instantiation so lazy init will happen
  extractor.runExtraction("saving green victim","")


  /** Return the taxonomy matches found in the mention label */
  def taxonomyMatches(mentionLabel: String) = 
    taxonomy_map(mentionLabel).map(x => (x("term") -> x("score"))).toSeq

}
