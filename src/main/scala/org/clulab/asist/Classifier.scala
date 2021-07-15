package org.clulab.asist

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.util.ByteString
import com.typesafe.scalalogging.LazyLogging
import java.util.concurrent.TimeoutException
import org.clulab.asist.messages._
import org.json4s.jackson.Serialization.write

import scala.concurrent.{Await, Awaitable, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.control.NonFatal



case class Classification(name: String)

// outbound comms from DialogAgent client to Dialog Act Classifier server
case class DialogActClassifierMessage(
  participant_id: String = "",
  text: String = "",
  extractions:Seq[DialogAgentMessageDataExtraction] = Seq.empty
)

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

  def apply(
    participant_id: String,
    text: String,
    extractions: Seq[DialogAgentMessageDataExtraction]
  ): String = {
    foo(new DialogActClassifierMessage(
      participant_id,
      text,
      extractions)
    )
    null
  }



  def foo(thing: DialogActClassifierMessage): Unit = {

    val json = write(thing)

    val request = HttpRequest(
      uri = Uri("http://localhost:8000/classify"),
      entity = HttpEntity(ContentTypes.`application/json`,json)
    )

    val future = Http().singleRequest(request)

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
