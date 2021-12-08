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

class IdcWorker(val agent: MessageBusAgent) 
    extends Thread 
    with LazyLogging {

  // Actor concurrency system
  implicit val ec:ExecutionContext = ExecutionContext.global
  implicit val system: ActorSystem = ActorSystem("MessageBusAgent")

  // enqueue extractions produced by the DialogAgent
  val queue: Queue[Seq[DialogAgentMessageUtteranceExtraction]] = new Queue 

  /* detach the thread */
  override def run()
  {
    logger.info("IDC worker thread is running")
  }

  /** Add an extraction sequence to the back of the queue
   *  @param job Job to add
   */
  def enqueue(
    extractions: Seq[DialogAgentMessageUtteranceExtraction]
  ): Unit = {
    queue.enqueue(extractions)
    processQueue
  }

  /** Remove the next sequence in the queue if the queue is not empty */
  def dequeue: Unit = {
    if(!queue.isEmpty)queue.dequeue 

  /** Return the next sequence in the queue, or None if queue is empty */
  def nextInQueue: Option[Seq[DialogAgentMessageUtteranceExtraction]] = 
    if(queue.isEmpty) None
    else Some(queue.head)
  }

  /** Entry point for queue processing */
  def processQueue(): Unit = {
    logger.info("Processing extraction queue")
    val len = queue.length
    len match {
      case 0 => logger.info("The queue is empty")
      case 1 => logger.info("There is one sequence in the queue")
      case _ => logger.info(s"There are $len sequences in the queue")
    }
  }

}
