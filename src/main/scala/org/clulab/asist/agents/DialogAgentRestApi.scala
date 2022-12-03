package org.clulab.asist.agents

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.model._

import scala.concurrent.ExecutionContext

import buildinfo.BuildInfo
import com.typesafe.config.Config
import ai.lum.common.ConfigFactory
import org.clulab.asist.extraction.TomcatRuleEngine

// Process HTTP requests containing text spans
// Generate HTTP responses with extractions


class DialogAgentRestApi (
  override val ruleEngine: TomcatRuleEngine = new TomcatRuleEngine
) extends DialogAgent(ruleEngine) {

  private val config: Config = ConfigFactory.load()

  val host = config.getString("DialotAgent.restApiServer.host")
  val port = config.getInt("RollcallRequest.restApiServer.port")

  logger.info(s"DialogAgentRestApi version ${BuildInfo.version} starting...")

  // get rule engine lazy init out of the way
//  startEngine()

  implicit val system = ActorSystem("DialogAgentRestApi")
  implicit val executionContext = ExecutionContext.global


    val requestHandler: HttpRequest => HttpResponse = {

      // extraction request
      case HttpRequest(GET, Uri.Path("/"), _, _, _) =>
        HttpResponse(entity = HttpEntity(
          ContentTypes.`text/html(UTF-8)`,
          "<html><body>Hello world!</body></html>"))

      // status request
      case HttpRequest(GET, Uri.Path("/status"), _, _, _) =>
        HttpResponse(entity = "Dialog Agent REST API status")

      // unhandled request
      case r: HttpRequest =>
        r.discardEntityBytes() 
        HttpResponse(404, entity = "Request not found")
    }

    val bindingFuture = Http().newServerAt(host, port).bindSync(requestHandler)

    logger.info(s"DialogAgentRestApi running at http://${host}:${port}")
}
