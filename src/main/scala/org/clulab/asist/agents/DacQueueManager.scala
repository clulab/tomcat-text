package org.clulab.asist.agents

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.util.ByteString
import com.typesafe.scalalogging.LazyLogging
import java.util.concurrent.TimeoutException
import java.io.PrintWriter
import org.clulab.asist.messages._
import org.json4s.jackson.Serialization.write

import scala.concurrent.{Await, Awaitable, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.control.NonFatal
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
import scala.collection.mutable.Queue



/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 *  Updated:  2021 July
 *
 *  The role of this class is to take completed DialogAgentMessage structs
 *  from the DialogAgent in a "fire and forget" manner, and to add Dialog Act
 *  Classifications to these messages before returning them to their requesting
 *  classes.
 *
 *  Maintain a FIFO queue of jobs to the Dialog Act Classifier server
 */


// returned from DAC server
case class Classification(name: String)


// Dialog Act Classification Request 
case class DacRequest(
  callback1: Option[DialogAgentMessage => Unit] = None,
  callback2: Option[(DialogAgentMessage, PrintWriter) => Unit] = None,
  message: Option[DialogAgentMessage] = None,
  output: Option[PrintWriter] = None
)

class DacQueueManager() extends LazyLogging {

  implicit val system = ActorSystem()
  implicit val dispatcher = system.dispatcher

  private val queue: Queue[DialogAgentMessage] = new Queue[DialogAgentMessage]

  val q: Queue[DacRequest] = Queue.empty
  var busy: Boolean = false

  def showQ = {
    logger.info(s"Elements in queue: ${q.length}")

  }

  // deliver
  def doSomethingWithValue(dr: DacRequest, response: HttpResponse): Unit = {
    logger.info("doSomethingWithValue")
    response.entity.dataBytes
      .runReduce(_ ++ _)
      .map{ line =>
        val ret = line.utf8String.split(" ").headOption.map(Classification)
        val classification = ret.getOrElse(new Classification(""))
        val name = classification.name
        logger.info(s"Classification name = ${name}")
      }
  }


  def dq(): Option[DacRequest] = {
    logger.info("dq")
    showQ
    if(q.isEmpty) {
      logger.info("ERROR - tried to dequeue from empty queue!")
      None
    } else {
      Some(q.dequeue)
    }
  }

  def futureRequest(dr: DacRequest): Unit = dr.message.foreach{message  =>
    logger.info("futureRequest")
    val data = message.data
    val json = write(new DialogActClassifierMessage(
      data.participant_id,
      data.text,
      data.extractions)
    )
    val request = HttpRequest(
      uri = Uri("http://localhost:8000/classify"),
      entity = HttpEntity(ContentTypes.`application/json`,json)
    )
    val future = Http().singleRequest(request)

    future onComplete {
      case Failure(t) => 
        logger.info("onFailure: " + t.toString)
        // end execution, in real life we would continue
      case Success(c: HttpResponse) => 
        logger.info("onSuccess")
        doSomethingWithValue(dr, c)
        synchronized {
          if(q.isEmpty) busy = false // release lock, no more jobs
          else {
            busy = true  // take lock, start next job
            dq.foreach(job => futureRequest(job))
          }
        }
    }
  }

  // request
  def nq(dr: DacRequest): Unit = {
    logger.info("nq")
    synchronized{
      if(busy) {
        logger.info("busy, enqueueing")
        q.enqueue(dr)
        showQ
      }
      else {
        busy = true
        futureRequest(dr)
      }
    }
  }


  // the following queue entrypoints could use some abstraction.

  /** Enqueue a classification job with a user parameter
  *  @param agent Caller with a message and an output param
  *  @param callback Called with the message when the future returns
  *  @param message Use text field for Dialog Act Classification
  *  @param output Used by file reading Agents to write output files
  *  @return Nothing
  */
  def enqueueClassification(
    agent: DialogAgent,
    callback: (DialogAgentMessage, PrintWriter) => Unit,
    message: DialogAgentMessage,
    output: PrintWriter
  ): Unit = {
    logger.info("enqueueClassification with agent, callback, message, output")
    if(agent.withClassifications) {
      val dr = DacRequest(
        callback1 = None,
        callback2 = Some(callback),
        message = Some(message),
        output = Some(output)
      )
      nq(dr)
      // set up classification job with messsage and output
    } else {  
      // call back without classification
      callback(message, output)
    }
  }

  /** Enqueue a classification job 
  *  @param callback Call with the message when the future returns
  *  @param message Use text field for Dialog Act Classification
  *  @param agent Caller with a message
  *  @return Nothing
  */
  def enqueueClassification(
    agent: DialogAgent,
    callback: DialogAgentMessage => Unit,
    message: DialogAgentMessage
  ): Unit = {
    logger.info("enqueueClassification with agent, callback, message")
    logger.info(s"agent.withClassifications = ${agent.withClassifications}")
    if(agent.withClassifications) {
      // set up classification job with messsage
    } else {
      // call back without classification
      callback(message)
    }
  }

  /** Enqueue a classification reset 
  *  @param agent Caller requesting reset
  *  @param callback Call with the message when the future returns
  *  @return Nothing
  */
  def enqueueReset(
    agent: DialogAgent,
    callback: (VersionInfo, PrintWriter) => Unit,
    message: VersionInfo,
    output: PrintWriter
  ): Unit = {
    logger.info("enqueueReset with agent, callback, message, output")
    logger.info(s"agent.withClassifications = ${agent.withClassifications}")
    if(agent.withClassifications) {
      // set up classification job with messsage
    } else {
      // call back without classification
      callback(message, output)
    }
  }

  /** Enqueue a classification reset 
  *  @param agent Caller requesting reset
  *  @param callback Call with the message when the future returns
  *  @return Nothing
  */
  def enqueueReset(
    agent: DialogAgent,
    callback: (VersionInfo) => Unit,
    message: VersionInfo
  ): Unit = {
    logger.info("enqueueReset with agent, callback, message")
    logger.info(s"agent.withClassifications = ${agent.withClassifications}")
    if(agent.withClassifications) {
      // set up classification job with messsage
    } else {
      // call back without classification
      callback(message)
    }
  }
}
