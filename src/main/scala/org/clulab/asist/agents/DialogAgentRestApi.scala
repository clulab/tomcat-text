package org.clulab.asist.agents

import java.nio.charset.StandardCharsets.UTF_8
import ai.lum.common.ConfigFactory
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCodes}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import buildinfo.BuildInfo
import com.typesafe.config.Config
import scala.concurrent.{ExecutionContext,Future}
import org.json4s.jackson.Serialization.write

import org.clulab.utils.FileUtils
import org.clulab.odin.impl.RuleReader
import org.clulab.odin.Actions
import org.clulab.asist.extraction.TomcatRuleEngine
import org.clulab.asist.messages.DialogAgentMessageUtteranceExtraction

// Process HTTP requests containing plain text data
// Generate HTTP responses with extractions as JSON

class DialogAgentRestApi (
  val host: String = "localhost",
  val port: Int = 8080,
  override val ruleEngine: TomcatRuleEngine = new TomcatRuleEngine
) extends DialogAgent(ruleEngine) {

  logger.info(s"DialogAgentRestApi version ${BuildInfo.version} starting...")

  // read host and port from configuration file
  private val config: Config = ConfigFactory.load()
  val masterPath = config.getString("TomcatRuleEngine.masterRulesPath")
  //val host = config.getString("DialogAgent.restApiServer.host")
  //val port = config.getInt("DialogAgent.restApiServer.port")

  // define concurrent actor system
  implicit val system = ActorSystem("DialogAgentRestApi")

  // return a status message
  def status: String = {
    val v: String = BuildInfo.version
    val s: Int = uptimeSeconds.toInt
    s"The Dialog Agent REST API version ${v} has been running for ${s} seconds\n"
  }

  private val extractorEngine = ruleEngine.engine
  private val actions: Actions = ruleEngine.loadableAttributes.actions
  private val ruleReader = new RuleReader(actions, UTF_8)
  private val masterFile: String = FileUtils.getTextFromResource(masterPath)

  logger.info("Number of extractors = %s\n".format(extractorEngine.extractors.length))
  val extractionSchemaObjects = ruleReader.extractionSchemaObjects(masterFile)
  val serializedExtractionSchemas = write(extractionSchemaObjects)

  // keep a record of each extraction transaction.
  var count = 1

  // define the REST API endpoints
  val route = concat (
    // GET endpoints
    get {
      path("status") {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, status))
      }
    },
    get {
      path("schemas") {
        complete(HttpEntity(ContentTypes.`application/json`, serializedExtractionSchemas))
      }
    },
    // POST endpoints
    post {
      path("") {
        entity(as[String]) { text =>
          logger.info(s"Transaction ${count}:")
          logger.info(s"input = ${text}")
          val json: String = JsonUtils.writeJsonNoNulls(getExtractions(text))
          logger.info(s"output = ${json}")
          count += 1
          complete(HttpEntity(ContentTypes.`application/json`, json))
        }
      }
    }
  )

  // start server on asynchronous thread
  val bindingFuture = Http().newServerAt(host, port).bind(route)

  // get lazy init out of the way
  startEngine()

  // Advise of successful startup
  logger.info(s"DialogAgentRestApi running at http://${host}:${port}")
}
