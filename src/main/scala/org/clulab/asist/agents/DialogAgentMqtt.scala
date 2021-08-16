package org.clulab.asist.agents

import akka.actor.ActorSystem
import akka.http.scaladsl._
import akka.http.scaladsl.model._
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
 * Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 * Updated:  2021 August
 *
 * This class reads input from the message bus on subscribed topics,
 * performs analysis on the input, and then publishes the analysis to
 * the output topic.
 *
 * If the Dialog Act Classification argument is set, a server process is
 * contacted via HTTP request for 
 *
 * Input and output are in json format.
 *
 * @param host MQTT host to connect to.
 * @param port MQTT network port to connect to.
 * @param nMatches  maximum number of taxonomy_matches to return (up to 5)
 */

case class BusMessage (
  topic: String,
  line: String
)

class DialogAgentMqtt(
  val host: String = "",
  val port: String = "",
  override val args: DialogAgentArgs = new DialogAgentArgs
) extends DialogAgent 
    with LazyLogging
    with MessageBusClientListener { 

  val serverLocation = "http://localhost:8000"

  // actors
  implicit val ec = ExecutionContext.global
  implicit val system: ActorSystem = ActorSystem("DialogAgentMqtt")

  val source_type = "message_bus"

  val queue: Queue[BusMessage] = new Queue 

  // this handles the message bus operations.  
  val bus = new MessageBusClient(
    host,
    port,
    subscriptions,
    publications,
    this
  )

  def writeToMessageBus(
    topic: String,
    text: String
  ): Unit = bus.publish(topic, text)

  /** Receive a message from the message bus
   * @param topic:  The message bus topic where the message was published
   * @param text:  A metadata text string, possibly multi-line
   */
  def messageArrived(
    topic: String,
    text: String 
  ): Unit = {

    // if the queue is empty, there is no aynchronous job in
    // progress and it is safe to start a new one.
    val noJobRunning = queue.isEmpty

    // Each line of text becomes a discrete processing job ready to run
    text.split("\n").map{
      line => queue.enqueue(BusMessage(topic, line))
    }

    // start new async job if none are running
    if(noJobRunning) startJob
  }

  /** When finished, remove the queue head and start the next job.  */
  def finishJob: Unit = if(queue.isEmpty) {
    logger.error("finishJob called with empty queue!")
  } else {
    queue.dequeue
    startJob
  }

  /* Use the head of the queue as the next job. */
  def startJob: Unit = if(!queue.isEmpty) queue.head.topic match {
    case `topicSubTrial` => processTrialMessage(queue.head)
    case _ => processDialogAgentMessage (queue.head)
  }  // else all jobs are done.


  /** send VersionInfo if we receive a TrialMessage with subtype "start", 
   * @param input: Message bus traffic with topic and text
   */
  def processTrialMessage(input: BusMessage): Unit = try {
    val tm = read[TrialMessage](input.line)
    if(tm.msg.sub_type == "start") {
      val timestamp = Clock.systemUTC.instant.toString
      writeToMessageBus(topicPubVersionInfo, write(VersionInfo(this, timestamp)))
      if(withClassifications) resetServer  // enter async execution
      else finishJob  // no DAC 
    }
    else finishJob  // no trial start
  } catch {
    case NonFatal(t) => logger.error("Could not parse {}", input.line)
  } 

  /** Reset the DAC server each time we send a VersionInfo message */
  def resetServer(): Unit = {
    logger.info("Resetting DAC server at {}",serverLocation)

    val request = HttpRequest(
      uri = Uri(s"${serverLocation}/reset-model"),
      entity = HttpEntity(ContentTypes.`application/json`,"reset-model")
    )
    val future: Future[HttpResponse] = Http().singleRequest(request)

    try {
      val response: HttpResponse = Await.ready(future, 10 seconds).value.get.get

      val futureReply: Future[String] = response.entity.dataBytes
        .runReduce(_ ++ _)
        .map(line => line.utf8String.split(" ").headOption.getOrElse(" "))

      futureReply onComplete {
        case Success(a) =>
          showStatus("Server Reset", response.status)
          logger.info("DAC Server reset successfully")
          finishJob
        case Failure(t) =>
          showStatus("Server Reset", response.status)
          logger.error(s"An error occured:  ${t}")
          system.terminate
      }
    } catch {
      case NonFatal(t) => 
        logger.error("Could not reset DAC server at: {}",t.toString)
        logger.error("Please ensure the DAC server is running")
        system.terminate
    }
  }

  /** Send DialogAgentMessage for any subsribed topic except "trial" 
   * @param input: Message bus traffic with topic and text
   */
  def processDialogAgentMessage(input: BusMessage): Unit = try {
    val message = getDialogAgentMessage(
      source_type,
      input.topic,
      input.topic,
      read[Metadata](input.line)
    )
    if(withClassifications) {
      runClassification(message)  // enter async execution
    } else {
      val json = write(message)
      writeToMessageBus(topicPubDialogAgent, json)
      finishJob
    }
  } catch {
    case NonFatal(t) => logger.error("Could not parse {}", input.line)
  } 

  /** Same as above but with known good arguments
   * @param message DialogAgentMessage composed from Message Bus input
   * @param metadata Message Bus input parsed as JSON
   */
  def runClassification(
    message: DialogAgentMessage
  ): Unit = {
    val data = message.data
    val requestJson = write(
      new DialogActClassifierMessage(
        Option(data.participant_id).getOrElse(""),
        Option(data.text).getOrElse(""),
        Option(data.extractions).getOrElse(Seq())
      )
    )
    val request = HttpRequest(
      uri = Uri(s"${serverLocation}/classify"),
      entity = HttpEntity(ContentTypes.`application/json`,requestJson)
    )
    val future: Future[HttpResponse] = Http().singleRequest(request)

    try {
      val response: HttpResponse = Await.ready(future, 10 seconds).value.get.get
      val futureClassification: Future[Classification] = response.entity.dataBytes
        .runReduce(_ ++ _)
        .map{ line =>
          val ret = line.utf8String.split(" ").headOption.map(Classification)
          ret.getOrElse(new Classification(""))
        }

      futureClassification onComplete {
        case Success(c: Classification) =>  
          showStatus(message.header.timestamp, response.status)
          val label = c.name.replace("\"","")
          val newData = data.copy(dialog_act_label = label)
          val newMessage = message.copy(data = newData)
          writeToMessageBus(topicPubDialogAgent,write(newMessage))
          finishJob
        case Failure(t) =>
          showStatus(message.header.timestamp, response.status)
          logger.error(s"An error occured:  ${t}")
          system.terminate
      }
    } catch {
      case NonFatal(t) => 
        logger.error("Error:  {}",t.toString)
        system.terminate
    }
  }

  /** Same as above but with known good arguments
   * @param annotation Message regarding returned future
   * @param sc Status of returned future
   */
  def showStatus(annotation: String, sc: StatusCode): Unit = {
    logger.info("{} HttpResponse status = {}", annotation, sc.value)
  }

  /** shutdow the asynchronous actor system */
  def shutdown(): Unit = {
    logger.info("MQTT DAC client shutting down...")
    Thread.sleep(5)
    system.terminate()
  }
}
