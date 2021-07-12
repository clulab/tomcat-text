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
) extends DialogAgent  {

  val test = new TamuDialogAgentMessage(
    participant_id = "Foo",
    text = "{\"text\":\"Here is a green victim\"}", 
    extractions = Seq.empty
  )

  TamuClientSingleRequest(test)

}


object TamuClientSingleRequest extends LazyLogging {

  def apply(outboundMessage: TamuDialogAgentMessage): Unit = {
    implicit val system = ActorSystem(Behaviors.empty, "SingleRequest")
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.executionContext

    val responseFuture: Future[HttpResponse] = 
      Http().singleRequest(HttpRequest(uri = "http://0.0.0.0:8000"))

    responseFuture
      .onComplete {
        case Success(res) => println(res)
        case Failure(e)   => 
          logger.error("Error communicating with server")
          logger.error(e.toString)
      }
  }
}
