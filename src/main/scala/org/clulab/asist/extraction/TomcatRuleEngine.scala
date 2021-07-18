package org.clulab.asist.extraction

import com.typesafe.config.{Config, ConfigFactory}
import org.clulab.odin.{ExtractorEngine, Mention, State}
import org.clulab.processors.fastnlp.FastNLPProcessor
import org.clulab.processors.{Document, Processor}
import org.clulab.utils.{Configured, FileUtils}
import org.slf4j.LoggerFactory

class TomcatRuleEngine(val config: Config = ConfigFactory.load()) extends Configured {

  val proc: Processor = new FastNLPProcessor() // TODO: Get from configuration file soon

  override def getConf: Config = config

  protected def getFullName(name: String): String = TomcatRuleEngine.PREFIX + "." + name

  protected def getPath(name: String, defaultValue: String): String = {
    val path = getArgString(getFullName(name), Option(defaultValue))
    TomcatRuleEngine.logger.info(name + ": " + path)
    path
  }

  // These are the values which can be reloaded.  Query them for current assignments.
  class LoadableAttributes(val actions: TomcatActions, val engine: ExtractorEngine)

  object LoadableAttributes {
    val masterRulesPath: String =
      getPath("masterRulesPath", "/org/clulab/asist/grammars/master.yml")
    val taxonomyPath: String =
      getPath("taxonomyPath", "/org/clulab/asist/grammars/taxonomy.yml")

    def apply(): LoadableAttributes = {
      // Reread these values from their files/resources each time based on paths in the config file.
      val masterRules = FileUtils.getTextFromResource(masterRulesPath)
      val actions = TomcatActions()

      new LoadableAttributes(
        actions,
        ExtractorEngine(masterRules, actions, actions.globalAction) // ODIN component
      )
    }
  }

  var loadableAttributes = LoadableAttributes()

  // These public variables are accessed directly by clients which
  // don't know they are loadable and which had better not keep copies.
  def engine = loadableAttributes.engine

  def reload() = loadableAttributes = LoadableAttributes()

  // MAIN PIPELINE METHOD
  def extractFrom(text: String, keepText: Boolean = false): Seq[Mention] = {
    val doc = annotate(text, keepText)
    extractFrom(doc)
  }

  def extractFrom(doc: Document): Vector[Mention] = engine.extractFrom(doc, new State()).toVector

  // ---------- Helper Methods -----------

  // Annotate the text using a Processor and then populate lexicon labels
  def annotate(text: String, keepText: Boolean = false): Document = {
    val doc = proc.annotate(text, keepText)
    doc
  }

}

object TomcatRuleEngine {

  val logger = LoggerFactory.getLogger(this.getClass())
  val PREFIX: String = "TomcatRuleEngine"

  val ENTITY: String = "Entity"
  val VICTIM: String = "Victim"
  val TARGET_ARG: String = "target"

}
