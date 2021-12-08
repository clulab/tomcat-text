package org.clulab.asist.agents

import ai.lum.common.ConfigFactory
import akka.actor.ActorSystem
import akka.http.scaladsl._
import akka.http.scaladsl.model._
import buildinfo.BuildInfo
import com.typesafe.config.Config
import com.typesafe.scalalogging.LazyLogging
import java.time.Clock
import org.clulab.asist.messages._
import org.clulab.utils.{MessageBusClient, MessageBusClientListener}
import org.json4s.{Extraction,_}
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization.{read, write}
import scala.collection.mutable.Queue
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._
import scala.language.postfixOps
import scala.util.control.NonFatal
import scala.util.{Failure, Success}

/**
 * Authors:  Joseph Astier, Adarsh Pyarelal
 *
 * Enqueue message bus extractions and process in a new thread
 *
 * @param agent Owning class
 */

class IdcWorker(val agent: MessageBusAgent) extends LazyLogging {

  // Actor concurrency system
  implicit val ec:ExecutionContext = ExecutionContext.global
  implicit val system: ActorSystem = ActorSystem("IdcWorker")

  // enqueue extractions produced by the DialogAgent
  val queue: Queue[Seq[DialogAgentMessageUtteranceExtraction]] = new Queue 

  /** Add an extraction sequence to the back of the queue
   *  @param job Job to add
   */
  def enqueue(
    extractions: Seq[DialogAgentMessageUtteranceExtraction]
  ): Unit = {
    logger.info("Enqueueing an extraction sequence")
    queue.enqueue(extractions)
    processQueue
  }

  /** show the state of the queue */
  def queueStatus(): Unit = {
    val len = queue.length
    len match {
      case 0 => logger.info("The queue is empty")
      case 1 => logger.info("There is one sequence in the queue")
      case _ => logger.info(s"There are $len sequences in the queue")
    }
  }

  /** Remove the next sequence in the queue if the queue is not empty */
  def dequeue: Unit = {
    logger.info("Dequeueing an extraction sequence")
    if(!queue.isEmpty)queue.dequeue 
    queueStatus
  }

  /** Return the next sequence in the queue, or None if queue is empty */
  def nextInQueue: Option[Seq[DialogAgentMessageUtteranceExtraction]] = {
    if(queue.isEmpty) None
    else Some(queue.head) // note that this does not dequeue the sequence
  }

  /** Entry point for non-blocking processing */
  def processQueue(): Unit = {
    val future: Future[Int] = Future(doSomeProcessing(queue.length))
    future onComplete {
      case Success(a: Int) =>
        logger.info(s"Done processing job $a")
      case Failure(t) =>
        logger.error("Processing error:")
        logger.error(t.toString)
    }
  }

  def reset(): Unit = {
    // clear the queue now
    logger.info("IDC has been reset!")
  }

  def doSomeProcessing(a: Int): Int = {
    val seconds = 10
    logger.info(s"Starting processing of job $a for $seconds seconds ...")
    queueStatus
    Thread.sleep(seconds*1000)
    a+1
  }
}
