package org.clulab.asist.agents

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.util.ByteString
import com.typesafe.scalalogging.LazyLogging
import java.util.concurrent.TimeoutException
import org.clulab.asist.messages._

import scala.concurrent.{Await, Awaitable, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.control.NonFatal


class DialogAgentDac (
  override val nMatches: Int = 0
) extends DialogAgent with LazyLogging {


  val test = new DialogActClassifierMessage(
    participant_id = "Participant 21",
    text = 
      "Five because at least I know there was one yellow victim that died so",
    extractions = Seq(
      DialogAgentMessageDataExtraction(
        label = "Sight",
        span = "was one yellow victim",
        arguments = Map.empty,
        start_offset = 20,
        end_offset = 25,
        taxonomy_matches = Seq(
          (
            "stop-triaging", "11.709686762798679"
          ),
          (
           "no-victims-spotted", "10.767969549025242"
          )
        )
      ),
      DialogAgentMessageDataExtraction(
        label = "Victim",
        span = "Victim",
        arguments = Map.empty,
        start_offset = 60,
        end_offset = 75,
        taxonomy_matches = Seq(
          (
            "stop-triaging-victim", "18.593763750341402"
          ),
          (
            "start-triaging-victim", "17.326888048081006"
          )
        )
      )
    )
  )

  val json = writeJson(test)

  val list = List(json, json, json, json, json)
  
  list.foreach(json => Classifier(json))
}

case class Classification(name: String)

object Classifier extends LazyLogging {

  implicit val system = ActorSystem()
  implicit val dispatcher = system.dispatcher


  def parse(
    line: ByteString 
  ): Option[Classification] = {
    val ret = line.utf8String.split(" ").headOption.map(Classification)
    ret
  }

  // Run and completely consume a single akka http request
  /*
  def runRequest(
    req: HttpRequest, 
  ): Future[Option[Classification]] = {
    Http()
      .singleRequest(req)
      .flatMap { response =>
        response.entity.dataBytes
          .runReduce(_ ++ _)
          .map(parse))
      }
  }
  */

  // used by 'time' method
  implicit val baseTime = System.currentTimeMillis

  def apply(json: String)  {

    val request = HttpRequest(
      uri = Uri("http://localhost:8000/classify"),
      entity = HttpEntity(ContentTypes.`application/json`,json)
    )

    val future = Http().singleRequest(request).withTimeout(Duration(5 seconds))

    // wait five seconds for a server response then proceed without it.
    try {
      val response: HttpResponse = Await.result(future, 5 seconds)
    } catch {
      case NonFatal(t) => logger.error(t.toString)
      case t: TimeoutException => logger.error(t.toString)
    }


    future.flatMap{response => 
      response.entity.dataBytes
          .runReduce(_ ++ _)
          .map(parse)
    }
  }
}
