/**
 * Authors:  Joseph Astier, Adarsh Pyarelal
 *
 * Updated:  2021 May
 *
 * Use the ODIN infrastructure to capture rules from .yml grammar files
 *
 * @param inputFilename A file or directory of files to process.
 * @param outputFilename The results of all file processing are written here
 */
package org.clulab.asist

import java.io.File
import java.io.InputStream
import java.io.FileInputStream
import java.io.PrintWriter

import org.clulab.asist.AsistEngine._

import java.util.Collection
import java.util.LinkedHashMap
import java.util.ArrayList
import org.slf4j.LoggerFactory
import scala.io.Source
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor
import org.clulab.utils.{Configured, FileUtils}

import scala.collection.JavaConverters._

import java.io._
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets.UTF_8

import scala.io.{ Codec, Source }
import scala.reflect.ClassTag
import org.clulab.processors.Document
import org.clulab.odin.{Actions, ExtractorEngine}
import org.clulab.odin.impl.RuleReader

import java.net.URL
import java.util.{ Collection, Map => JMap }
import java.nio.charset.Charset

import org.apache.commons.text.StrSubstitutor

import scala.collection.JavaConverters._
import scala.io.{ Codec, Source }

import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.{ Constructor, ConstructorException }
import org.clulab.odin._
import org.clulab.odin.impl._
import org.clulab.odin.impl.RuleReader.Rule

import org.slf4j.LoggerFactory



class RuleReaderDemo (
  override val actions: Actions, 
  override val charset: Charset) 
    extends RuleReader(actions, charset) {

  private lazy val logger = LoggerFactory.getLogger(this.getClass())



  def demoReadSimpleFile(input: String): Seq[Rule] = {

    
    val yaml = new Yaml(new Constructor(classOf[Collection[JMap[String, Any]]]))
    val jRules = yaml.load(input).asInstanceOf[Collection[JMap[String, Any]]]
    // no resources are specified
    val resources = OdinResourceManager(Map.empty)
    val config = OdinConfig(resources = resources)
    val rules = demoReadRules(jRules, config)
    rules
  }

  def demoReadMasterFile(input: String): Seq[Rule] = {
    val yaml = new Yaml(new Constructor(classOf[JMap[String, Any]]))
    val master = yaml.load(input).asInstanceOf[JMap[String, Any]].asScala.toMap
    val taxonomy = master.get("taxonomy").map(readTaxonomy)
    val vars = getVars(master)
    val resources = demoReadResources(master)
    val jRules = master("rules").asInstanceOf[Collection[JMap[String, Any]]]
    val graph = getGraph(master)
    val config = OdinConfig(taxonomy = taxonomy, resources = resources, variables = vars, graph = graph)
    val rules = demoReadRules(jRules, config)
    rules
  }

    // reads a taxonomy from data, where data may be either a forest or a file path
  def readTaxonomy(data: Any): Taxonomy = data match {
    case t: Collection[_] => Taxonomy(t.asInstanceOf[Collection[Any]])
    case path: String =>
      val url = RuleReader.mkURL(path)
      val source = Source.fromURL(url)
      val input = source.mkString
      source.close()
      val yaml = new Yaml(new Constructor(classOf[Collection[Any]]))
      val data = yaml.load(input).asInstanceOf[Collection[Any]]
      Taxonomy(data)
  }

    // reads resources from data, and constructs an OdinResourceManager instance
  def demoReadResources(data: Map[String, Any]): OdinResourceManager = {
    //println(s"resources: ${data.get("resources")}")
    val resourcesMap: Map[String, String] = data.get("resources") match {
      case Some(m: JMap[_, _]) => m.asScala.map(pair => (pair._1.toString, pair._2.toString)).toMap
      case _ => Map.empty
    }
    OdinResourceManager(resourcesMap)
  }



  def demoReadRules(
      rules: Collection[JMap[String, Any]],
      config: OdinConfig
  ): Seq[Rule] = {

    // return Rule objects
    rules.asScala.toSeq.flatMap { r =>
      val m = r.asScala.toMap
      if (m contains "import") {
        // import rules from a file and return them
        demoImportRules(m, config)
      } else {
        // gets a label and returns it and all its hypernyms
        val expand: String => Seq[String] = label => config.taxonomy match {
          case Some(t) => t.hypernymsFor(label)
          case None => Seq(label)
        }
        // interpolates a template variable with ${variableName} notation
        // note that $variableName is not supported and $ can't be escaped
        val template: Any => String = a => demoReplaceVars(a.toString, config.variables)
        // return the rule (in a Seq because this is a flatMap)
        Seq(mkRule(m, expand, template, config))
      }
    }
  }


  // StrSubstitutor doesn't support whitespace in var (ex. ${ varName } )
  def demoCleanVar(s: String): String = {
    val clean = s.
      replaceAll("\\$\\{\\s+", "\\$\\{").
      replaceAll("\\s+\\}", "\\}")
    clean
  }



  def demoImportRules(
      data: Map[String, Any],
      config: OdinConfig
  ): Seq[Rule] = {
    // apply variable substitutions to import
    val path = {
      val p = data("import").toString
      val res = demoReplaceVars(p, config.variables)
      res
    }
    val url = RuleReader.mkURL(path)
    val source = Source.fromURL(url)
    val input = source.mkString // slurp
    source.close()
    // read rules and vars from file
    val (jRules: Collection[JMap[String, Any]], localVars: Map[String, String]) = try {
      // try to read file with rules and optional vars by trying to read a JMap
      val yaml = new Yaml(new Constructor(classOf[JMap[String, Any]]))
      val data = yaml.load(input).asInstanceOf[JMap[String, Any]].asScala.toMap
      // read list of rules
      val jRules = data("rules").asInstanceOf[Collection[JMap[String, Any]]]
      // read optional vars
      val localVars = getVars(data)
      (jRules, localVars)
    } catch {
      case e: ConstructorException =>
        // try to read file with a list of rules by trying to read a Collection of JMaps
        val yaml = new Yaml(new Constructor(classOf[Collection[JMap[String, Any]]]))
        val jRules = yaml.load(input).asInstanceOf[Collection[JMap[String, Any]]]
        (jRules, Map.empty)
    }
    // variables specified by the call to `import`
    val importVars = getVars(data)
    // variable scope:
    // - an imported file may define its own variables (`localVars`)
    // - the importer file can define variables (`importerVars`) that override `localVars`
    // - a call to `import` can include variables (`importVars`) that override `importerVars`
    val updatedVars = localVars ++ config.variables ++ importVars
    val newConf = config.copy(variables = updatedVars)
    demoReadRules(jRules, newConf)
  }


  def demoReplaceVars(s: String, vars: Map[String, String]): String = {
    val valuesMap = vars.asJava
    // NOTE: StrSubstitutor is NOT threadsafe
    val sub = new StrSubstitutor(valuesMap)
    // allow for recursive substitution
    sub.setEnableSubstitutionInVariables(true)
    val clean = demoCleanVar(s)
    sub.replace(clean)
  }
}


