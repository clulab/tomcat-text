package org.clulab.asist

import java.util.Date

import com.typesafe.config.{Config, ConfigFactory}
import com.typesafe.config.ConfigFactory
import org.clulab.odin.{ExtractorEngine, Mention, State}
import org.clulab.processors.{Document, Processor}
import org.clulab.processors.fastnlp.FastNLPProcessor
import org.clulab.processors.clu.CluProcessor
import org.clulab.utils.{Configured, FileUtils}
import org.slf4j.LoggerFactory

import scala.collection.mutable.ArrayBuffer

class AsistEngine(
    var timeintervals: (ArrayBuffer[Int], ArrayBuffer[Int], ArrayBuffer[Int]) =
      (new ArrayBuffer[Int], new ArrayBuffer[Int], new ArrayBuffer[Int]),
    val config: Config = ConfigFactory.load("stub")
) extends Configured {

  val proc: Processor =
    new FastNLPProcessor() // TODO: Get from configuration file soon

  override def getConf: Config = config

  protected def getFullName(name: String) = AsistEngine.PREFIX + "." + name

  protected def getPath(name: String, defaultValue: String): String = {
    val path = getArgString(getFullName(name), Option(defaultValue))

    AsistEngine.logger.info(name + ": " + path)
    path
  }

  class LoadableAttributes(
      // These are the values which can be reloaded.  Query them for current assignments.
      val actions: StubActions,
      val engine: ExtractorEngine
  )

  object LoadableAttributes {
    val masterRulesPath: String =
      getPath("masterRulesPath", "/org/clulab/asist/grammars/master.yml")
    val taxonomyPath: String =
      getPath("taxonomyPath", "/org/clulab/asist/grammars/taxonomy.yml")

    def apply(): LoadableAttributes = {
      // Reread these values from their files/resources each time based on paths in the config file.
      val masterRules = FileUtils.getTextFromResource(masterRulesPath)
      val actions = StubActions(timeintervals)

      new LoadableAttributes(
        actions,
        ExtractorEngine(masterRules, actions) // ODIN component
      )
    }
  }

  var loadableAttributes = LoadableAttributes()

  // These public variables are accessed directly by clients which
  // don't know they are loadable and which had better not keep copies.
  def engine = loadableAttributes.engine

  def reload() = loadableAttributes = LoadableAttributes()

  // MAIN PIPELINE METHOD
  def extractFromText(text: String, keepText: Boolean = false): Seq[Mention] = {
    val doc = annotate(text, keepText)
    val odinMentions = extractFrom(doc)
    //println(s"\nodinMentions() -- entities : \n\t${odinMentions.map(m => m.text).sorted.mkString("\n\t")}")

    odinMentions
  }

  def extractFrom(doc: Document): Vector[Mention] = {
    val events = engine.extractFrom(doc, new State()).toVector
    //println(s"In extractFrom() -- res : ${res.map(m => m.text).mkString(",\t")}")

    // todo: some appropriate version of "keepMostComplete"
    events
  }

  // ---------- Helper Methods -----------

  // Annotate the text using a Processor and then populate lexicon labels
  def annotate(text: String, keepText: Boolean = false): Document = {
    val doc = proc.annotate(text, keepText)
    //    doc.sentences.foreach(addLexiconNER)
    doc
  }

}

object AsistEngine {

  val logger = LoggerFactory.getLogger(this.getClass())
  val PREFIX: String = "AsistEngine"

}
