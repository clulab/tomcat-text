package org.clulab.asist.agents

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import com.typesafe.scalalogging.LazyLogging
import org.clulab.asist.messages._
import org.json4s.{Extraction, JValue}

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext
import scala.util.control.NonFatal
import scala.util.{Failure, Success}
import scala.language.postfixOps

/**
 * Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 * Communications with the Texas A&M Dialog Act Classification (TDAC) server
 */

class TdacClient (agent: TdacAgent, serverUrl: String) extends LazyLogging {

  // actors
  private implicit val ec = ExecutionContext.global
  private implicit val system: ActorSystem = ActorSystem("TdacClient")
  system.registerOnTermination(onTerminate)


  // json
  private implicit val formats = org.json4s.DefaultFormats

  logger.info(s"TDAC server URL: ${serverUrl}")
  logger.info("TDAC client ready.")

  /** Reset the TDAC server outside of data processing
   */
  def initServer: Unit = {
    logger.info(s"Initializing TDAC server at ${serverUrl}")
    try {
      val request = HttpRequest(
        uri = Uri(s"${serverUrl}/reset-model"),
        entity = HttpEntity(ContentTypes.`application/json`,"reset-model")
      )
      val future: Future[HttpResponse] = Http().singleRequest(request)
      val response: HttpResponse = 
        Await.ready(future, 10 seconds).value.get.get
      val futureReply: Future[String] = response.entity.dataBytes
        .runReduce(_ ++ _)
        .map(line => line.utf8String.split(" ").headOption.getOrElse(" "))
      futureReply onComplete {
        case Success(a) => 
          logger.info("TDAC server initialized")
          agent.doNextJob
        case Failure(t) => 
          logger.info("Unable to initialize TDAC server")
          logger.error(t.toString)
          requestTermination
      }
    } catch {
      case NonFatal(t) => 
        logger.info("Unable to initialize TDAC server")
        logger.error(t.toString)
        requestTermination
    }
  }

  /** Reset the TDAC server during processing
   *  @param busMessages publish if server reset succeeds
   */
  def resetServer(): Unit = {
    logger.info(s"Resetting TDAC server at: ${serverUrl}")

    try {
      val request = HttpRequest(
        uri = Uri(s"${serverUrl}/reset-model"),
        entity = HttpEntity(ContentTypes.`application/json`,"reset-model")
      )
      val future: Future[HttpResponse] = Http().singleRequest(request)
      val response: HttpResponse = 
        Await.ready(future, 10 seconds).value.get.get
      val futureData: Future[String] = response.entity.dataBytes
        .runReduce(_ ++ _)
        .map(line => line.utf8String.split(" ").headOption.getOrElse(" "))
      futureData onComplete {
        case Success(a) =>
          logger.info(s"TDAC server reset succeeded: ${response.status}")
          agent.doNextJob
        case Failure(t) =>
          logger.info("Unable to reset TDAC server")
          logger.error(t.toString)
          requestTermination
      }
    } catch {
      case NonFatal(t) => 
        logger.info("Unable to reset TDAC server")
        logger.error(t.toString)
        requestTermination
    }
  }

  /** call the TDAC Server for classification of this DialogAgentMessage
   * @param outputTopic destination topic for the classification results
   * @param inputText as read from the message bus
   * @param data Part of the input line, includes text to be classified
   * @param metadata Entire input line being processed by the agent
   */
  def runClassification(
    outputTopic: String,
    data: DialogAgentMessageData,
    metadata: JValue
  ): Unit = {
    try {
      val request = HttpRequest(
        uri = Uri(s"${serverUrl}/classify"),
        entity = HttpEntity(
          ContentTypes.`application/json`,
          JsonUtils.writeJson(
            DacData(
              data.participant_id,
              data.text,
              data.extractions
            )
          )
        )
      )
      val future: Future[HttpResponse] = Http().singleRequest(request)
      val response: HttpResponse =
        Await.ready(future, 10 seconds).value.get.get
      val futureData: Future[DacLabel] =
        response.entity.dataBytes
          .runReduce(_ ++ _)
          .map{ line =>
            line.utf8String.split(" ").headOption.map(DacLabel)
              .getOrElse(new DacLabel(""))
          }
      futureData onComplete {
        case Success(dacLabel: DacLabel) =>
          val label = dacLabel.name.replace("\"","")
          val newData = data.copy(dialog_act_label = label)
          val newMetadata = metadata.replace(
            "data"::Nil,
            Extraction.decompose(newData)
          )
          val output = BusMessage(
            outputTopic,
            JsonUtils.writeJson(newMetadata)
          )
          agent.writeOutput(List(output))
          agent.doNextJob
        case Failure(t) =>
          logger.error(s"TDAC Server HttpResponse failed:")
          logger.error(s"HTTP Response status: ${response.status}")
          logger.error(s"Error: ${t.toString}")
          agent.doNextJob
      }
    } catch {
      case NonFatal(t) =>
        logger.error(s"TDAC Server classification failed:")
        logger.error(t.toString)
        agent.doNextJob
    }
  }

  /** Terminate the actor system */
  def terminate: Unit = {
    logger.info("TDAC client is shutting down")
    system.terminate
  }

  private def requestTermination(): Unit = {
    logger.info("TDAC client requesting agent shutdown.")
    agent.terminate
  }

  def onTerminate(): Unit =  {
    logger.info("TDAC client has shut down.")
  }
}
