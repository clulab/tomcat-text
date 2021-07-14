package org.clulab.asist.agents

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.util.ByteString
import com.typesafe.scalalogging.LazyLogging
import org.clulab.asist.messages._

import scala.concurrent._

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

  
  Classifier(this, list)


  def finish(json: String, l: List[String], classification: String): Unit = {
    logger.info(s"Classification = ${classification}")
    if(!l.isEmpty) Classifier(this, l)
  }


}


object Classifier extends LazyLogging {
  implicit val system = ActorSystem()
  implicit val dispatcher = system.dispatcher

  case class Classification(name: String)

  def parse(
    line: ByteString, 
    json: String, 
    list: List[String],
    dac: DialogAgentDac
  ): Option[Classification] = {
    val ret = line.utf8String.split(" ").headOption.map(Classification)
    ret.toList.foreach(c => dac.finish(json, list, c.name))
    ret
  }

  // Run and completely consume a single akka http request
  def runRequest(
    req: HttpRequest, 
    json: String,
    list: List[String],
    dac: DialogAgentDac
  ): Future[Option[Classification]] = {
    Http()
      .singleRequest(req)
      .flatMap { response =>
        response.entity.dataBytes
          .runReduce(_ ++ _)
          .map(parse(_, json, list, dac))
      }
  }

  def apply(dac: DialogAgentDac, l: List[String]) = l match {
    case head::tail => 

      val request = HttpRequest(
        uri = Uri("http://localhost:8000/classify"),
        entity = HttpEntity(ContentTypes.`application/json`,head)
      )

      runRequest(request, head, tail, dac)
    case _ =>  // done
  }
}
