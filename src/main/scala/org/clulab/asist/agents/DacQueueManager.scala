package org.clulab.asist.agents

//FIXME clean up imports
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


class WorkOrder(
  val callback1: Option[String => Unit] = None,
  val callback2: Option[(String, PrintWriter) => Unit] = None,
  val output: Option[PrintWriter] = None
)  

class DacRequest(
  val message: Option[DialogAgentMessage] = None
) extends WorkOrder

class TrialStartRequest(
  val message: Option[VersionInfo] = None
) extends WorkOrder

class CallbackRequest(
  val message: Option[String] = None
) extends WorkOrder

// returned from DAC server
case class Classification(name: String)

class DacQueueManager(val agent: DialogAgent) 
extends LazyLogging {

  implicit val system = ActorSystem()
  implicit val dispatcher = system.dispatcher

  val q: Queue[WorkOrder] = Queue.empty
  var busy: Boolean = false

  def showQ = {
    logger.info(s"Elements in queue: ${q.length}")
  }

  // change the data.dialog_act_label field of a DialogAgentMessage
  def classifyMessage(
    message: DialogAgentMessage,
    dialog_act_label: String
  ): DialogAgentMessage = DialogAgentMessage(
    message.header,
    message.msg,
    DialogAgentMessageData(
      participant_id = message.data.participant_id,
      asr_msg_id = message.data.asr_msg_id,
      text = message.data.text,
      dialog_act_label = dialog_act_label,
      source = message.data.source,
      extractions = message.data.extractions
    )
  )

  // deliver
  def deliver(
    dr: DacRequest,
    dialog_act_label: String
  ): Unit = {
    logger.info("deliver")

    dr.message.foreach{ message =>
      // DialogAgentMessage with data classification field updated
      val classifiedMessage = classifyMessage(message, dialog_act_label)

      val json = agent.writeJson(classifiedMessage)

      // try to service one-arg callback
      dr.callback1.foreach{callback => callback(json)}

      // try to service two-arg callback
      dr.callback2.foreach{callback => 
        dr.output.foreach{output => 
          callback(json, output)
        }
      }
    }
  }

  // The future has arrived
  def receiveResponse(
    dr: DacRequest, 
    response: HttpResponse
  ): Unit = {
    logger.info("receiveResponse")
    response.entity.dataBytes
      .runReduce(_ ++ _)
      .map{ line =>
        val ret = line.utf8String.split(" ").headOption.map(Classification)
        val classification = ret.getOrElse(new Classification(""))
        val name = classification.name
        logger.info(s"Classification name = ${name}")
        deliver(dr, name)
      }
  }

  // service the next job in the queue or do nothing if no more jobs.
  def startNextJob(): Unit = synchronized {
    if(q.isEmpty) busy = false // release lock, no more jobs
    else {
      busy = true  // take lock, start next job
      dq match {
        case i:
      val wo = dq
      dq.foreach(job => futureRequest(job))
    }
  }

  def dq(): Option[WorkOrder] = {
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
        receiveResponse(dr, c)
        startNextJob
    }
  }

  // request
  def nq(wo: WorkOrder): Unit = {
    logger.info("nq")
    synchronized{
      if(busy) {
        logger.info("busy, enqueueing")
        q.enqueue(wo)
        showQ
      }
      else {
        busy = true
        futureRequest(dr)  // send to a breakout method
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
    callback: (String, PrintWriter) => Unit,
    message: DialogAgentMessage,
    output: PrintWriter
  ): Unit = {
    logger.info("enqueueClassification with agent, callback, message, output")
    if(agent.withClassifications) {
      // set up classification job with messsage
      val dr = new DacRequest(
        callback1 = None,
        callback2 = Some(callback),
        message = Some(message),
        output = Some(output)
      )
      nq(dr)
    } else {  
      // call back without classification
      callback(agent.writeJson(message), output)
    }
  }

  /** Enqueue a classification job 
  *  @param callback Call with the message when the future returns
  *  @param message Use text field for Dialog Act Classification
  *  @param agent Caller with a message
  *  @return Nothing
  */
  def enqueueClassification(
    callback: String => Unit,
    message: DialogAgentMessage
  ): Unit = {
    logger.info("enqueueClassification with agent, callback, message")
    logger.info(s"agent.withClassifications = ${agent.withClassifications}")
    if(agent.withClassifications) {
      // set up classification job with messsage
      val dr = DacRequest(
        callback1 = Some(callback),
        callback2 = None,
        message = Some(message)
      )
      nq(dr)
    } else {
      // call back without classification
      callback(agent.writeJson(message))
    }
  }

  /** Enqueue a classification reset 
  *  @param agent Caller requesting reset
  *  @param callback Call with the message when the future returns
  *  @return Nothing
  */
  def enqueueReset(
    callback: (String, PrintWriter) => Unit,
    message: VersionInfo,
    output: PrintWriter
  ): Unit = {
    logger.info("enqueueReset with agent, callback, message, output")
    logger.info(s"agent.withClassifications = ${agent.withClassifications}")
    if(agent.withClassifications) {
      // set up classification job with messsage
      val dr = DacRequest(
        callback1 = None,
        callback2 = Some(callback),
        message = Some(message),
        output = Some(output)
      )
      nq(dr)
    } else {
      // call back without classification
      callback(agent.writeJson(message), output)
    }
  }

  /** Enqueue a classification reset 
  *  @param agent Caller requesting reset
  *  @param callback Call with the message when the future returns
  *  @return Nothing
  */
  def enqueueReset(
    callback: (String) => Unit,
    message: VersionInfo
  ): Unit = {
    logger.info("enqueueReset with agent, callback, message")
    logger.info(s"agent.withClassifications = ${agent.withClassifications}")
    if(agent.withClassifications) {
      // set up classification job with messsage
      val dr = DacRequest(
        callback1 = Some(callback),
        callback2 = None,
        message = Some(message)
      )
      nq(dr)
    } else {
      // call back without classification
      callback(agent.writeJson(message))
    }
  }
}
