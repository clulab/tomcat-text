package org.clulab.asist.agents

//FIXME clean up imports
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.util.ByteString
import com.typesafe.scalalogging.LazyLogging
import java.util.concurrent.TimeoutException
import java.io.PrintWriter
import java.util.concurrent.ConcurrentLinkedQueue
import org.clulab.asist.messages._
import org.json4s.jackson.Serialization.write

import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.control.NonFatal
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


abstract class WorkOrder

class DacRequest(
  val message: Option[DialogAgentMessage] = None,
  val callback1: Option[String => Unit] = None,
  val callback2: Option[(String, PrintWriter) => Unit] = None,
  val output: Option[PrintWriter] = None
) extends WorkOrder

class TrialStartRequest(
  val message: Option[VersionInfo] = None,
  val callback1: Option[String => Unit] = None,
  val callback2: Option[(String, PrintWriter) => Unit] = None,
  val output: Option[PrintWriter] = None
) extends WorkOrder

// a queued callback, with the arg
class CallbackRequest(
  val callback: Option[AnyRef => Unit] = None,
  val arg: Option[AnyRef] = None,
) extends WorkOrder

// returned from DAC server
case class Classification(name: String)

class DacQueueManager(val agent: DialogAgent) 
extends LazyLogging {

  implicit val system = ActorSystem()
  implicit val dispatcher = system.dispatcher

  val q: ConcurrentLinkedQueue[WorkOrder] = new ConcurrentLinkedQueue

  // change the data.dialog_act_label field of a DialogAgentMessage
  def classifyMessage(
    message: DialogAgentMessage,
    dialog_act_label: String
  ): DialogAgentMessage = 
      DialogAgentMessage(
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

  /*
  // deliver
  def deliver(
    dr: DacRequest,
    dialog_act_label: String
  ): Unit = synchronized {
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

  // The DAC response to a request.
  def dacResponse(
    dr: DacRequest, 
    response: HttpResponse
  ): Unit = synchronized {
    logger.info("dacResponse")
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
  */

  // Call the DAC with this query.
  def dacRequest(dr: DacRequest): Unit = {
    dr.message.foreach{message  =>
      logger.info("dacRequest")
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
        case Success(response: HttpResponse) => try {
          logger.info("onSuccess")
          response.entity.dataBytes
            .runReduce(_ ++ _)
            .map{ line =>
              val ret = line.utf8String.split(" ").headOption.map(Classification)
              val classification = ret.getOrElse(new Classification(""))
              val dialog_act_label = classification.name
              logger.info(s"Classification name = ${dialog_act_label}")
              dr.message.foreach{ message =>
                val classifiedMessage = classifyMessage(message, dialog_act_label)
                val json = agent.writeJson(classifiedMessage)
                // try to service two-arg callback
                dr.callback2.foreach{callback => 
                  dr.output.foreach{output => 
                    callback(json, output)
                  }
                }
                // try to service one-arg callback
                dr.callback1.foreach{callback => callback(json)}
              }
            }
        } finally {
          finishJob
        }
      }
    }
  }

  def finishJob(): Unit = {
    logger.info("finish job")
    q.poll // remove current element
    startJob
  }


  // start the next job in the queue or do nothing if no more jobs.
  def startJob(): Unit = if(!q.isEmpty) {
    logger.info("start job")
    q.peek match {
      // threaded job 
      case dr: DacRequest =>
        logger.info("serviceWorkOrder with DacRequest")
        dacRequest(dr)
      // threaded job 
      case ts: TrialStartRequest =>
        logger.info("serviceWorkOrder with TrialStartRequest")
      // a callback is expected to be a non-threaded job.
      case cr: CallbackRequest => 
        logger.info("serviceWorkOrder with CallbackRequest")
        cr.arg.foreach{arg =>
          cr.callback.foreach{callback =>
            callback(arg)
            finishJob
          }
        }
    }
  }

  // request
  def nq(wo: WorkOrder): Unit = if(q.isEmpty) {
    logger.info("nq with empty queue")
    q.add(wo)
    startJob
  } else {
    logger.info("nq with non-empty queue")
    q.add(wo)
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
    dog: DialogAgentMessage,
    output: PrintWriter
  ): Unit = {
    logger.info("enqueueClassification with agent, callback, message, output")
    if(agent.withClassifications) {
      // set up classification job with messsage
      val dr = new DacRequest(
        message = Some(dog),
        callback1 = None,
        callback2 = Some(callback),
        output = Some(output)
      )
      nq(dr)
    } else {  
      // call back without classification
      callback(agent.writeJson(dog), output)
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
      val dr = new DacRequest(
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
      val dr = new TrialStartRequest(
        message = Some(message),
        callback1 = None,
        callback2 = Some(callback),
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
      val dr = new TrialStartRequest(
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

  def enqueueCallback(
    callback: (AnyRef) => Unit,
    arg: AnyRef
  ): Unit = {
    if(agent.withClassifications) {
      val cr = new CallbackRequest (
        callback = Some(callback),
        arg = Some(arg)
      )
      nq(cr)
    } else {
      // call back without classification
      callback(arg)
    }
  }
}
