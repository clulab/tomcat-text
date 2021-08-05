package org.clulab.asist.agents

import akka.actor.ActorSystem
import akka.http.scaladsl._
import akka.http.scaladsl.model._
import com.typesafe.scalalogging.LazyLogging
import org.clulab.asist.messages._
import org.json4s.{Extraction,_}
import org.json4s.jackson.Serialization.{read,write}

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._
import scala.language.postfixOps
import scala.util.control.NonFatal
import scala.util.{Failure, Success}

/**
 * Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 * Updated:  2021 July
 *
 * Manage communications with the Dialog Act Classification (DAC) server
 *
 */

// sent back by the DAC server
case class Classification(name: String)

class DacClient (
  val agent: DialogAgentReprocessor
) extends LazyLogging {

  val serverLocation = "http://localhost:8000"

  // actors
  implicit val ec = ExecutionContext.global
  implicit val system: ActorSystem = ActorSystem("DacClient")

  // json
  implicit val formats = org.json4s.DefaultFormats

  // schedule the next iteration after reseting the DAC server
  def resetServer(s: RunState): Unit = {
    logger.info("Resetting DAC server at {}",serverLocation)

    val request = HttpRequest(
      uri = Uri(s"${serverLocation}/reset-model"),
      entity = HttpEntity(ContentTypes.`application/json`,"reset-model")
    )
    val future: Future[HttpResponse] = Http().singleRequest(request)

    try {
      val response: HttpResponse = Await.ready(future, 10 seconds).value.get.get

      val futureReply: Future[String] = response.entity.dataBytes
        .runReduce(_ ++ _)
        .map(line => line.utf8String.split(" ").headOption.getOrElse(" "))

      futureReply onComplete {
        case Success(a) =>
          showStatus("Server Reset", response.status)
          logger.info("DAC Server reset successfully")
          agent.iteration(agent.addDacReset(s))
        case Failure(t) =>
          showStatus("Server Reset", response.status)
          logger.error(s"An error occured:  ${t}")
          agent.iteration(agent.addError(s))
      }
    } catch {
      case NonFatal(t) => 
        logger.error("Could not reset DAC server at: {}",t.toString)
        logger.error("Please ensure the DAC server is running")
        agent.iteration(agent.terminate(s))
    }
  }


  // call the DAC for classification of this DialogAgentMessage
  def runClassification(
    s: RunState, 
    json: String
  ): Unit = {
    val message = read[DialogAgentMessage](json)
    val data = message.data
    val requestJson = write(
      new DialogActClassifierMessage(
        Option(data.participant_id).getOrElse(""),
        Option(data.text).getOrElse(""),
        Option(data.extractions).getOrElse(Seq())
      )
    )
    val request = HttpRequest(
      uri = Uri(s"${serverLocation}/classify"),
      entity = HttpEntity(ContentTypes.`application/json`,requestJson)
    )
    val future: Future[HttpResponse] = Http().singleRequest(request)

    try {
      val response: HttpResponse = Await.ready(future, 10 seconds).value.get.get
      val futureClassification: Future[Classification] = response.entity.dataBytes
        .runReduce(_ ++ _)
        .map{ line =>
          val ret = line.utf8String.split(" ").headOption.map(Classification)
          ret.getOrElse(new Classification(""))
        }

      futureClassification onComplete {
        case Success(c: Classification) =>  
          showStatus(message.header.timestamp, response.status)
          agent.parseJValue(json).toList.map{metadata =>
            val label = c.name.replace("\"","")
            val newData = data.copy(dialog_act_label = label)
            val newMetadata = metadata.replace(
              "data"::Nil,
              Extraction.decompose(newData)
            )
            val newMetadataJson = write(newMetadata)
            val done = s.copy(dacQueries = s.dacQueries + 1)
            agent.futureIteration(done, List(newMetadataJson))
          }
        case Failure(t) =>
          showStatus(message.header.timestamp, response.status)
          logger.error(s"An error occured:  ${t}")
          val done = s.copy(errors = s.errors + 1)
          agent.iteration(done)
      }
    } catch {
      case NonFatal(t) => 
        logger.error("Error processing {}", json)
        logger.error("{}",t.toString)
        val done = s.copy(terminated = true)
        agent.iteration(done)
    }
  }

  def showStatus(annotation: String, sc: StatusCode): Unit = {
    logger.info("{} HttpResponse status = {}", annotation, sc.value)
  }

  // shutdow the actor system
  def shutdown(): Unit = {
    logger.info("DAC client shutting down...")
    Thread.sleep(5)
    system.terminate()
  }
}
