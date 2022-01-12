package org.clulab.asist.agents

import akka.actor.ActorSystem
import com.typesafe.scalalogging.LazyLogging
import org.clulab.asist.messages._

import scala.collection.mutable.Queue
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

/**
 * Authors:  Joseph Astier, Adarsh Pyarelal
 *
 * Enqueue message bus extractions and process in a new thread
 *
 * @param agent Owning class
 */

class IdcWorker(
  val owner: DialogAgent
) extends LazyLogging {

  // Actor concurrency system
  implicit val ec:ExecutionContext = ExecutionContext.global
  implicit val system: ActorSystem = ActorSystem("IdcWorker")
  system.registerOnTermination(onTerminate)

  private val queue: Queue[IdcData] = new Queue

  logger.info("IDC worker ready.")

  /** Add an extraction sequence to the back of the queue
   *  @param topic - the Message Bus where the data was read
   *  @param extractions - derived from the data read on the topic
   */
  def enqueue(
    extractions: Seq[DialogAgentMessageUtteranceExtraction]
  ): Unit = {
    showState
    val busy = !queue.isEmpty
    val data = IdcData(extractions, IdcWorkerState(0))
    queue.enqueue(data)
    if(!busy)doNextJob
  }

  /** show the state of this class instance */
  def showState(): Unit = logger.info(s"Size of queue is ${queue.length}")

  /** Return the next sequence in the queue, or None if queue is empty */
  def doNextJob(): Unit = {
    showState
    if(!queue.isEmpty) {
      val future: Future[Unit] = Future(doSomeProcessing(queue.head))
      future onComplete {
        case Success(a: Unit) =>
          logger.info(s"Done processing job")
          queue.dequeue
          doNextJob
        case Failure(t) =>
          logger.error("Processing error:")
          logger.error(t.toString)
      }
    }
  }

  /** This method runs as a detached process */
  def doSomeProcessing(data: IdcData): Unit = {
    val seconds = 2
    logger.info(s"Starting processing of job for $seconds seconds ...")
    Thread.sleep(seconds*1000)
  }

  // allow actor system to gracefully shut down
  def terminate: Unit = queue.length match {
    case 0 => 
      logger.info("IDC worker is shutting down ...")
      system.terminate
    case n: Int =>  // keep checking until the queue has finished processing
      val seconds = 2
      logger.info(s"IDC worker jobs remaining: ${n}")
      Thread.sleep(seconds*1000)
      terminate
  }

  def onTerminate(): Unit =  {
    logger.info("IDC worker has shut down.")
  }
}
