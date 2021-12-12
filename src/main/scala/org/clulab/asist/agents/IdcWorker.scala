package org.clulab.asist.agents

import com.typesafe.scalalogging.LazyLogging
import org.clulab.asist.messages._
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
  val owner: MessageBusAgent
) extends LazyLogging {

  // Actor concurrency system
  implicit val ec:ExecutionContext = ExecutionContext.global

  // persistent state
  var state: IdcWorkerState = new IdcWorkerState()

  // reset the state to the start state
  def reset(): Unit = {
    logger.info("State of the IdcWorker:")
    showState
    logger.info("Resetting the IdcWorker:")
    // empty the queue
    state.queue.dequeueAll(_)
    state = new IdcWorkerState()
    showState
  }

  /** Add an extraction sequence to the back of the queue
   *  @param topic - the Message Bus where the data was read
   *  @param extractions - derived from the data read on the topic
   */
  def enqueue(
    topic: String,
    extractions: Seq[DialogAgentMessageUtteranceExtraction]
  ): Unit = {
    state.queue.enqueue(IdcData(topic, extractions))
    processQueue
  }

  /** show the state of this class instance */
  def showState(): Unit = {
    val len = state.queue.length
    len match {
      case 0 => logger.info("The queue is empty")
      case 1 => logger.info("There is one sequence in the queue")
      case _ => logger.info(s"There are $len sequences in the queue")
    }
  }

  /** Return the next sequence in the queue, or None if queue is empty */
  def nextInQueue(): Option[IdcData] = {
    if(state.queue.isEmpty) None
    else Some(state.queue.head) // note that this does not dequeue the sequence
  }

  /** Entry point for non-blocking processing */
  def processQueue(): Unit = {
    val future: Future[Unit] = Future(doSomeProcessing)
    future onComplete {
      case Success(a: Unit) =>
        logger.info(s"Done processing job $a")
      case Failure(t) =>
        logger.error("Processing error:")
        logger.error(t.toString)
    }
  }

  /** This method runs as a detached process */
  def doSomeProcessing(): Unit = {
    val seconds = 10
    logger.info(s"Starting processing of job for $seconds seconds ...")
    Thread.sleep(seconds*1000)
  }
}
