package org.clulab.asist.agents

import akka.actor.ActorSystem
import com.typesafe.scalalogging.LazyLogging
import org.clulab.asist.messages._

import scala.collection.mutable.Queue
import scala.collection.mutable.Stack
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

  logger.info("IDC Worker ready.")

  // Actor concurrency system
  implicit val ec:ExecutionContext = ExecutionContext.global
  implicit val system: ActorSystem = ActorSystem("IdcWorker")

  val queue: Queue[IdcData] = new Queue

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

    //println("data is of type:" + data.getClass)
    //val extract = data.extractions
    //val label = extract(0)
    //println("extract is of type:" + extract.getClass)
    //println(extract)
    //println("label is of type:" + label.getClass)
    //println(label)
    //val labellist = label.labels
    //println("labellist is of type:" + labellist.getClass)
    //println(labellist)
    lookForLabel(data.extractions,labelstring="CriticalVictim" )

    Thread.sleep(seconds*1000)
  }


  def lookForLabel(extractions: Seq[DialogAgentMessageUtteranceExtraction], labelstring: String): Unit ={
    for(extractionObject <- extractions){
      if(extractionObject.labels.contains(labelstring)){
        println(s"$labelstring detected")
      }
      else{
        println(s"no $labelstring")
      }}
  }

  /** constructing a stack to keep track of utterances */
  var utteranceStack = Stack[Any]()
  /**perhaps the next step going to be (some random pseudo code)*/
  def processStack(data: IdcData): Unit ={
    if (lookForLabel(data.extractions,labelstring="CriticalVictim" ) != null){
      utteranceStack.push(data.extractions)
    }else{
      utteranceStack.clear()
    }
  }

  // allow actor system to gracefully shut down
  def close: Unit = {
    val seconds = 3
    if(queue.isEmpty) {
      logger.info("Shutting down the IdcWorker:")
      system.terminate
    }
    else { // keep checking until the queue has finished processing
      Thread.sleep(seconds*1000)
      close
    }
  }
}