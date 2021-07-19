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

// Dialog Act Classification Request
case class DacRequest(
  message: Option[DialogAgentMessage] = None,
  owner: Option[DialogAgent] = None,
  ownerParam: Option[Any] = None
)


class DacQueueManager() extends LazyLogging {

  private val queue: Queue[DialogAgentMessage] = new Queue[DialogAgentMessage]

  val q: Queue[Int] = Queue.empty
  var busy: Boolean = false

  // slow function that executes remotely
  def take5(i: Int): Int = {
    println("take5")
    Thread.sleep(5000)
    i
  }

  def showQ = {
    if(q.isEmpty) println("Q = []")
    else println(s"Q = ${q.mkString(", ")}")
  }

  // deliver
  def doSomethingWithValue(i: Int): Unit =
    println(s"doSomethingWithValue: ${i}")

  def dq(): Option[Int] = {
    println("dq")
    showQ
    if(q.isEmpty) {
      println("ERROR - tried to dequeue from empty queue!")
      None
    } else {
      Some(q.dequeue)
    }
  }

  def futureInt(i: Int): Unit = {
    println("futureInt")
    val f = Future(take5(i))
    f onComplete {
      case Success(x: Int) => 
        println("onSuccess")
        doSomethingWithValue(i)
        synchronized {
          if(q.isEmpty) busy = false // release lock
          else {
            dq.foreach{ n =>
              busy = true  // take lock
              futureInt(n)
            }
          }
        }
      case Failure(t) => 
        println("onFailure: " + t.toString)
        // end execution, in real life we would continue
    }
  }

  // request
  def nq(i: Int): Unit = {
    println(s"nq: ${i}")
    synchronized{
      if(busy) {
        println("busy, enqueueing")
        q.enqueue(i)
        showQ
      }
      else {
        busy = true
        futureInt(i)
      }
    }
  }

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
    if(agent.withClassifications) {
      // set up classification job with messsage
    } else {
      // call back without classification
      callback(message)
    }
  }
}
