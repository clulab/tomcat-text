package org.clulab.asist

import com.typesafe.scalalogging.LazyLogging
import org.clulab.odin.Actions
import org.clulab.odin.impl.Taxonomy
import org.clulab.utils.FileUtils
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor

class StubActions(val taxonomy: Taxonomy) extends Actions with LazyLogging {

}

object StubActions {

  def apply(taxonomyPath: String) =
    new StubActions(readTaxonomy(taxonomyPath))

  private def readTaxonomy(path: String): Taxonomy = {
    val input = FileUtils.getTextFromResource(path)
    val yaml = new Yaml(new Constructor(classOf[java.util.Collection[Any]]))
    val data = yaml.load(input).asInstanceOf[java.util.Collection[Any]]
    Taxonomy(data)
  }
}
