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

// needed?  
//import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
//import spray.json.DefaultJsonProtocol._

// Process HTTP requests containing text spans
// Generate HTTP responses with extractions

class DialogAgentRestApi (
  override val ruleEngine: TomcatRuleEngine = new TomcatRuleEngine
) extends DialogAgent(ruleEngine) {

  logger.info(s"DialogAgentRestApi version ${BuildInfo.version} starting...")

  private val config: Config = ConfigFactory.load()
  val host = config.getString("DialogAgent.restApiServer.host")
  val port = config.getInt("DialogAgent.restApiServer.port")
  implicit val system = ActorSystem("DialogAgentRestApi")

  def status: String = {
    "Dialog Agent REST API is running"
  }

  // keep a record of each extraction transaction.
  var count = 1

  val route = concat (
    get {
      path("status") {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, status))
      }
    },
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

  val bindingFuture = Http().newServerAt(host, port).bind(route)

  startEngine()
  logger.info(s"DialogAgentRestApi running at http://${host}:${port}")
}
