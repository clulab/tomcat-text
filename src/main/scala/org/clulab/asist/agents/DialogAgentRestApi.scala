package org.clulab.asist.agents

import ai.lum.common.ConfigFactory
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import buildinfo.BuildInfo
import com.typesafe.config.Config
import scala.concurrent.{ExecutionContext,Future}

import org.clulab.asist.extraction.TomcatRuleEngine
import org.clulab.asist.messages.DialogAgentMessageUtteranceExtraction

// Process HTTP requests containing plain text data
// Generate HTTP responses with extractions as JSON

class DialogAgentRestApi (
  override val ruleEngine: TomcatRuleEngine = new TomcatRuleEngine
) extends DialogAgent(ruleEngine) {

  logger.info(s"DialogAgentRestApi version ${BuildInfo.version} starting...")

  // read host and port from configuration file
  private val config: Config = ConfigFactory.load()
  val host = config.getString("DialogAgent.restApiServer.host")
  val port = config.getInt("DialogAgent.restApiServer.port")

  // define concurrent actor system
  implicit val system = ActorSystem("DialogAgentRestApi")

  // return a status message
  def status: String = {
    val v: String = BuildInfo.version
    val s: Int = uptimeSeconds.toInt
    s"The Dialog Agent REST API version ${v} has been running for ${s} seconds\n"
  }

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
