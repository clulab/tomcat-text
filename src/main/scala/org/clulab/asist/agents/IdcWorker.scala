package org.clulab.asist.agents

import akka.actor.ActorSystem
import com.typesafe.scalalogging.LazyLogging
import org.clulab.asist.messages._

import scala.collection.mutable.Queue
import scala.collection.mutable.Stack
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

/**
 * Authors:  Joseph Astier, Adarsh Pyarelal, Remo Nitschke, Yuwei Wang
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

  //def checkRescueDep()

  /** This method runs as a detached process */
  def doSomeProcessing(data: IdcData): Unit = {
    val seconds = 2
    val extraction = data
    logger.info(s"Starting processing of job for $seconds seconds ...")
    whatis(data)
    processUttQueue(data)
    logger.info(s"${utteranceQueue.size} extractions are being tracked")
    checkLabelSeq(queueState = utteranceQueue,firstlabel = "CriticalVictim",secondlabel = "MoveTo")

    //Thread.sleep(seconds*1000)
  }

  /** method to inspect the data type of your object, will print the data type to the command line */
  def whatis(yourdata: Any): Unit={
    println("your data is of type: " + yourdata.getClass)
  }

  /** simple function that allows you to look for a simple label, returns a Boolean value */
  def lookForLabel(extractions: Seq[DialogAgentMessageUtteranceExtraction], labelstring: String): Boolean = {
    for (extractionObject <- extractions
        if extractionObject.labels.contains(labelstring))
           return true //this return statement only goes in effect if the filtered for-loop is satisfied
  false //we need this so the method returns a false if the above loop is not satisfied
  }


  /** This method takes 3 args, a Queue and 2 labels. It then searches all objects in the queue for the first label. If the first label is found, it checks for the 2nd label.  */
    /** Currently this method is "stupid" ie it onl checks whether both labels are present in the queue, regardless of order */
  def checkLabelSeq(queueState: Queue[Seq[DialogAgentMessageUtteranceExtraction]], firstlabel: String, secondlabel: String): Unit={
    for(vector: Seq[DialogAgentMessageUtteranceExtraction] <- queueState){
      if(lookForLabel(vector: Seq[DialogAgentMessageUtteranceExtraction],firstlabel)){
          logger.info("first label detected")
        if(lookForLabel(vector: Seq[DialogAgentMessageUtteranceExtraction],firstlabel)){
          logger.info(s"$firstlabel and $secondlabel sequence detected")
        }
      }
    }
  }


  /** constructing a queue to keep track of utterances */
  var utteranceQueue: Queue[Seq[DialogAgentMessageUtteranceExtraction]] = new Queue
  /** a function to allow the queue to keep track of 5 objects */
  def processUttQueue(data: IdcData): Unit ={
    val extract= data.extractions
    utteranceQueue.enqueue(extract:Seq[DialogAgentMessageUtteranceExtraction])
    if (utteranceQueue.size > 5){
      utteranceQueue.dequeue()
    }
  }

  // allow actor system to gracefully shut down
  def close: Unit = {
    val seconds = 3
    if(queue.isEmpty) {
      system.terminate
      logger.info("IDC worker has shut down.")
    }
    else { // keep checking until the queue has finished processing
      Thread.sleep(seconds*1000)
      close
    }
  }
}