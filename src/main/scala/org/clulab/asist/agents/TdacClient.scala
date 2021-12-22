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

/**
 * Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 * Communications with the TAMU Dialog Act Classification (TDAC) server
 */

class TdacClient (agent: TdacAgent, serverUrl: String) extends LazyLogging {

  // actors
  implicit val ec = ExecutionContext.global
  implicit val system: ActorSystem = ActorSystem("TdacClient")

  // json
  implicit val formats = org.json4s.DefaultFormats

  /** Terminate the actor system */
  def terminateActorSystem: Unit = {
    system.terminate
    Thread.sleep(5000) // allow actor system to gracefully shut down
  }

  /** Reset the TDAC server outside of data processing
   */
  def initServer: Unit = {
    logger.info(s"Initializing TDAC serverat ${serverUrl}")
    try {
      val request = HttpRequest(
        uri = Uri(s"${serverUrl}/reset-model"),
        entity = HttpEntity(ContentTypes.`application/json`,"reset-model")
      )
      val future: Future[HttpResponse] = Http().singleRequest(request)
      val response: HttpResponse = Await.ready(future, 10 seconds).value.get.get
      val futureReply: Future[String] = response.entity.dataBytes
        .runReduce(_ ++ _)
        .map(line => line.utf8String.split(" ").headOption.getOrElse(" "))
      futureReply onComplete {
        case Success(a) => 
          logger.info("TDAC server initialized")
        case Failure(t) => 
          shutdown(t.toString)
      }
    } catch {
      case NonFatal(t) => 
        shutdown(t.toString)
    }
  }

  def shutdown(report: String): Unit = {
    logger.error("Problem encountered during initialization of TDAC server:")
    logger.error(report)
    logger.error("The Agent is shutting down")
    terminateActorSystem
    System.exit(0)
  }

  /** Reset the TDAC server during processing
   *  @param busMessages publish if server reset succeeds
   */
  def resetServer(
    busMessages: List[BusMessage]
  ): Unit = {
    logger.info(s"Resetting TDAC server at: ${serverUrl}")

    try {
      val request = HttpRequest(
        uri = Uri(s"${serverUrl}/reset-model"),
        entity = HttpEntity(ContentTypes.`application/json`,"reset-model")
      )
      val future: Future[HttpResponse] = Http().singleRequest(request)
      val response: HttpResponse = Await.ready(future, 10 seconds).value.get.get
      val futureReply: Future[String] = response.entity.dataBytes
        .runReduce(_ ++ _)
        .map(line => line.utf8String.split(" ").headOption.getOrElse(" "))
      futureReply onComplete {
        case Success(a) =>
          logger.info(s"TDAC server reset succeeded: ${response.status}")
          agent.writeOutput(busMessages)
          agent.iteration
        case Failure(t) =>
          logger.error(s"TDAC server reset failed: ${response.status}")
          agent.handleError
      }
    } catch {
      case NonFatal(t) => 
        logger.error(s"Could not reset TDAC server at: ${serverUrl}")
        logger.error("Please ensure the TDAC server is running")
        agent.handleError
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
    inputText: String,
    data: DialogAgentMessageData,
    metadata: JValue
  ): Unit = {
    val requestJson = JsonUtils.writeJson(
      new DialogActClassifierMessage(
        Option(data.participant_id).getOrElse(""),
        Option(data.text).getOrElse(""),
        Option(data.extractions).getOrElse(Seq())
      )
    )
    try {
      val request = HttpRequest(
        uri = Uri(s"${serverUrl}/classify"),
        entity = HttpEntity(ContentTypes.`application/json`,requestJson)
      )
      val future: Future[HttpResponse] = Http().singleRequest(request)
      val response: HttpResponse = Await.ready(future, 10 seconds).value.get.get
      val futureClassification: Future[Classification] = response.entity.dataBytes
        .runReduce(_ ++ _)
        .map{ line =>
          val ret = line.utf8String.split(" ").headOption.map(Classification)
          ret.getOrElse(new Classification(""))
        }
      futureClassification onComplete {
        case Success(c: Classification) =>  
          val label = c.name.replace("\"","")
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
          agent.iteration
        case Failure(t) =>
          logger.error(s"TDAC Server classification failed: ${response.status}")
          logger.error(s"Input Text: ${inputText}")
          logger.error(s"Error: ${t.toString}")
          agent.handleError
      }
    } catch {
      case NonFatal(t) => 
        logger.error(s"Error processing: ${inputText}")
        logger.error(t.toString)
        agent.handleError
    }
  }
}
