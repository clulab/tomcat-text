package org.clulab.asist.agents

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import com.typesafe.scalalogging.LazyLogging
import org.clulab.asist.messages._
import org.json4s.{Extraction, JValue}
import org.json4s.jackson.Serialization.write

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._
import scala.language.postfixOps
import scala.util.control.NonFatal
import scala.util.{Failure, Success}

/**
 * Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 * Communications with the TAMU Dialog Act Classification (TDAC) server
 */

class TdacClient (agent: TdacAgent, serverUrl: String) extends LazyLogging {

  // sent back by the TDAC server
  case class Classification(name: String)

  // actors
  implicit val ec = ExecutionContext.global
  implicit val system: ActorSystem = ActorSystem("TdacClient")

  // json
  implicit val formats = org.json4s.DefaultFormats

  /** Reset the TDAC server outside of data processing
   */
  def initServer: Unit = {
    logger.info("Initializing TDAC server...")
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
    system.terminate
    Thread.sleep(5000) // allow actor system to gracefully shut down
    logger.error(report)
    logger.info(s"The TDAC server was not found at ${serverUrl}")
    logger.info("Please check that the server is running")
    logger.info("The Agent is shutting down")
    System.exit(0)
  }


  /** Reset the TDAC server during processing
   * @param rs The current execution state of the agent
   */
  def resetServer(rs: RunState): Unit = {
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
          val rs1 = RSM.addDacReset(rs)
          val rs2 = agent.writeOutput(rs1)
          agent.iteration(rs2)
        case Failure(t) =>
          logger.info(s"TDAC server reset failed: ${response.status}")
          agent.handleError(rs)
      }
    } catch {
      case NonFatal(t) => 
        logger.info(s"Could not reset TDAC server at: ${serverUrl}")
        logger.error("Please ensure the TDAC server is running")
        agent.handleError(rs)
    }
  }


  /** call the TDAC Server for classification of this DialogAgentMessage
   * @param rs The current execution state of the agent
   * @param data Part of the input line, includes text to be classified
   * @param metadata Entire input line being processed by the agent
   */
  def runClassification(
    rs: RunState, 
    data: DialogAgentMessageData,
    metadata: JValue
  ): Unit = {
    val requestJson = write(
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
          val rs1 = RSM.addDacQuery(rs)
          val rs2 = RSM.setOutputLine(rs1, write(newMetadata))
          val rs3 = agent.writeOutput(rs2)
          agent.iteration(rs3)
        case Failure(t) =>
          logger.error(s"TDAC Server classification failed: ${response.status}")
          logger.error(s"Input Line: ${rs.inputLine}")
          logger.error(s"Error: ${t.toString}")
          agent.handleError(rs)
      }
    } catch {
      case NonFatal(t) => 
        logger.error(s"Error processing: ${rs.inputLine}")
        logger.error(t.toString)
        agent.handleError(rs)
    }
  }

  /** shutdow the actor system, allowing time for actors to finish */
  def shutdown(): Unit = {
    logger.info("TDAC client shutting down...")
    Thread.sleep(5)
    system.terminate()
  }
}
