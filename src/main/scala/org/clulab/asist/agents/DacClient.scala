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

abstract trait DacAgent {
  /** A line to be written to the output of an agent
   * @param rs The runState sent with the orignal message to the DAC client
   * @return A new run state updated with the status of the write operation
   */
  def writeLine(rs: RunState): RunState

  /** States sent by the DAC server, if in use.
   * @param message A DialogAgentMessage with the dialog_act_label value set
   */
  def iteration(rs: RunState): Unit
}


// sent back by the DAC server
case class Classification(name: String)

class DacClient (agent: DacAgent) extends LazyLogging {

  val serverLocation = "http://localhost:8000"

  // actors
  implicit val ec = ExecutionContext.global
  implicit val system: ActorSystem = ActorSystem("DacClient")

  // json
  implicit val formats = org.json4s.DefaultFormats

  // schedule the next iteration after reseting the DAC server
  def resetServer(agent: DacAgent, rs: RunState): Unit = {
    logger.info(s"Resetting DAC server at: ${serverLocation}")

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
          logger.info(s"Server reset succeeded: ${response.status}")
          val done = agent.writeLine(rs)
          agent.iteration(done)
        case Failure(t) =>
          logger.info(s"Server reset failed: ${response.status}")
          agent.iteration(RSM.addError(rs))
      }
    } catch {
      case NonFatal(t) => 
        logger.info(s"Could not reset DAC server at: ${serverLocation}")
        logger.error("Please ensure the DAC server is running")
        agent.iteration(RSM.terminate(rs))
    }
  }


  // call the DAC for classification of this DialogAgentMessage
  def runClassification(
    agent: DacAgent,
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
          logger.info(s"Server query succeeded: ${response.status}")
          val label = c.name.replace("\"","")
          val newData = data.copy(dialog_act_label = label)
          val newMetadata = metadata.replace(
            "data"::Nil,
            Extraction.decompose(newData)
          )
          val done1 = RSM.addDacQuery(rs)
          val done2 = RSM.setOutputLine(done1, write(newMetadata))
          val done3 = agent.writeLine(done2)
          agent.iteration(done3)
        case Failure(t) =>
          logger.error(s"Server query failed: ${response.status}")
          logger.error(s"Input Line: ${rs.inputLine}")
          logger.error(s"Error: ${t.toString}")
          val done = RSM.addError(rs)
          agent.iteration(done)
      }
    } catch {
      case NonFatal(t) => 
        logger.error(s"Error processing: ${rs.inputLine}")
        logger.error(t.toString)
        val done = RSM.terminate(rs)
        agent.iteration(done)
    }
  }

  // shutdow the actor system
  def shutdown(): Unit = {
    logger.info("DAC client shutting down...")
    Thread.sleep(5)
    system.terminate()
  }
}
