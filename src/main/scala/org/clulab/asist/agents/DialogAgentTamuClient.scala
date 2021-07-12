package org.clulab.asist.agents

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import com.typesafe.scalalogging.LazyLogging
import org.clulab.asist.messages._

import scala.concurrent.Future
import scala.util.{ Failure, Success }


class DialogAgentTamu (
  override val nMatches: Int = 0
) extends DialogAgent with LazyLogging {

  val test = new TamuDialogAgentMessage(
    participant_id = "Foo",
    text = "Here is a green victim",
    extractions = Seq.empty
  )

  TamuClientSingleRequest(writeJson(test))

}


object TamuClientSingleRequest extends LazyLogging {

  def apply(json :String): Unit = {

    implicit val system = ActorSystem(Behaviors.empty, "SingleRequest")
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.executionContext

    val responseFuture: Future[HttpResponse] = 
      Http().singleRequest(
        HttpRequest(
          method=HttpMethods.GET,
          uri = "http://localhost:8000/classify",
          entity = HttpEntity(
            ContentTypes.`application/json`,
            json
          )
        )
      )

    responseFuture
      .onComplete {
        case Success(res) => println(res)
        case Failure(e)   => 
          logger.error("Error communicating with server")
          logger.error(e.toString)
      }
  }
}
