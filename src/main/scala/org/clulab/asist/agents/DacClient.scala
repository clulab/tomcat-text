package org.clulab.asist.agents

import akka.actor.ActorSystem
import akka.http.scaladsl._
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer
import akka.util.ByteString
import com.typesafe.scalalogging.LazyLogging
import java.io.{File, PrintWriter}
import java.nio.file.Paths
import java.time.Clock
import org.clulab.asist.messages._
import org.clulab.utils.LocalFileUtils
import org.json4s.{Extraction,_}
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read,write}
import org.json4s.JField

import scala.annotation.tailrec
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
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

  logger.info("DacClient is active!")

  // actors
  implicit val ec = ExecutionContext.global
  implicit val system: ActorSystem = ActorSystem("DacClient")

  // json
  implicit val formats = org.json4s.DefaultFormats

  // shutdow the actor system
  def shutdown(): Unit = {
    system.terminate()
  }

  // schedule the next iteration after reseting the DAC server
  def resetServer(s: RunState): Unit = {
    logger.info("Resetting DAC server at http://localhost:8000")

    val request = HttpRequest(
      uri = Uri("http://localhost:8000/reset-model"),
      entity = HttpEntity(ContentTypes.`application/json`,"reset-model")
    )
    val future: Future[HttpResponse] = Http().singleRequest(request)
    val response: HttpResponse = Await.ready(future, 10 seconds).value.get.get

    val futureReply: Future[String] = response.entity.dataBytes
      .runReduce(_ ++ _)
      .map(line => line.utf8String.split(" ").headOption.getOrElse(" "))

    futureReply onComplete {
      case Success(a) =>
        logger.info("Server reset replied successfully")
        agent.iteration(agent.addDacReset(s))
      case Failure(t) =>
        logger.error(s"An error occured:  ${t}")
        agent.iteration(agent.addError(s))
    }
  }

  // call the DAC for classification of this DialogAgentMessage
  def runClassification(
    s: RunState, 
    json: String
  ): Unit = {
    val message = read[DialogAgentMessage](json)
    val data = message.data
    val requestJson = write(new DialogActClassifierMessage(
      data.participant_id,
      data.text,
      data.extractions)
    )
    val request = HttpRequest(
      uri = Uri("http://localhost:8000/classify"),
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
        agent.parseJValue(json).toList.map{metadata =>
          val label = c.name.replace("\"","")
          val newData = data.copy(dialog_act_label = label)
          val newMetadata = metadata.replace(
            "data"::Nil,
            Extraction.decompose(newData)
          )
          val newMetadataJson = write(newMetadata)
          finishClassification(agent.addDacQuery(s), newMetadataJson)
        }
      case Failure(t) =>
        logger.error(s"An error occured:  ${t}")
        finishClassification(agent.addError(s), json)
    }
  }


  def finishClassification(s: RunState, line: String): Unit = {
    val done = agent.writeLine(s, line)
    agent.iteration(done)
  }
}
