package org.clulab.asist.agents

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


/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 *  Updated:  2021 July
 *
 *  Manage data flow to and from the Dialog Act Classifier server.
 */

case class Classification(name: String)

// outbound comms from DialogAgent client to Dialog Act Classifier server
case class DialogActClassifierMessage(
  participant_id: String = "",
  text: String = "",
  extractions:Seq[DialogAgentMessageDataExtraction] = Seq.empty
)

class Classifier extends LazyLogging {

  // the DAC can only run one job at a time.
  private var locked: Boolean = false

  implicit val system = ActorSystem()
  implicit val dispatcher = system.dispatcher

  def isBusy: Boolean = locked

  def classify(
    participant_id: String,
    text: String,
    extractions: Seq[DialogAgentMessageDataExtraction],
  ): Unit = {

    locked = true

    val json = write(new DialogActClassifierMessage(
      participant_id,
      text,
      extractions)
    )

    val request = HttpRequest(
      uri = Uri("http://localhost:8000/classify"),
      entity = HttpEntity(ContentTypes.`application/json`,json)
    )

    val future = Http().singleRequest(request)

   future.flatMap { response =>
      response.entity.dataBytes
        .runReduce(_ ++ _)
        .map(parse)
    }

  }

  def parse(
    line: ByteString 
  ): Unit = {
    val ret = line.utf8String.split(" ").headOption.map(Classification)
    val classification = ret.getOrElse(new Classification(""))
    locked = false
    logger.info(s"Classification = ${classification.name}")
//    da.classificationCallback(classification)
  }

 /*
  // Run and completely consume a single akka http request
  def runRequest(
    req: HttpRequest, 
  ): Future[Option[Classification]] = {
    Http()
      .singleRequest(req)
      .flatMap { response =>
        response.entity.dataBytes
          .runReduce(_ ++ _)
          .map(parse)
      }
  }
  */

}
