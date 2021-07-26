package org.clulab.asist.agents

import akka.actor.ActorSystem
import akka.http.scaladsl._
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer
import akka.util.ByteString
import com.typesafe.scalalogging.LazyLogging
import java.io.{File, PrintWriter}
import org.clulab.asist.messages._
import org.json4s.jackson.Serialization.{read,write}
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.language.postfixOps
import scala.util.control.NonFatal
import scala.util.{Failure, Success}

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

case class Classification(name: String)


class DacClient (
  agent: DialogAgentReprocessor, 
  input: List[String],
  outputFileName: String
) extends LazyLogging {

  implicit val ec = ExecutionContext.global
  implicit val system: ActorSystem = ActorSystem("test")

  val iterator = input.iterator

  val fileWriter = new PrintWriter(new File(outputFileName))

  if(iterator.hasNext) classify(iterator.next)

  def rejoin(line: String): Unit = synchronized {
    logger.info("classifyOther")
    fileWriter.write(s"${line}\n")
    if(iterator.hasNext) classify(iterator.next)
    else {
      logger.info("No more lines to process")
      fileWriter.close
      logger.info("filewriter closed")
      system.terminate()  // fix sbt hang
    }
  }

  def classify(line: String): Unit = synchronized {
    val lookAhead = readMetadataLookahead(line)
    if(lookAhead.topic == agent.topicPubDialogAgent) {
      try {
        val message = read[DialogAgentMessage](line)
        classifyDialogAgentMessage(message)
      } catch {
        case NonFatal(t) => {
          logger.error("Some kind of error occured: {}",t.toString)
          logger.info("failing over to classifyOther")
          classifyOther(line)
        }
      }
    }
    else classifyOther(line)
  }

  def classifyOther(line: String): Unit = {
    logger.info("classifyOther")
    val f: Future[String]  = Future {
      line
    }

    f onComplete {
      case Success(finishedLine) => rejoin(finishedLine)
      case Failure(t) => logger.error(s"An error occured:  ${t}")
    }
  }

  def classifyDialogAgentMessage(message: DialogAgentMessage): Unit = {
    logger.info("classifyDialogAgentMessage")
    val data = message.data
    val requestJson = write(new DialogActClassifierMessage(
      data.participant_id,
      data.text,
      data.extractions)
    )

    val request = HttpRequest(
      uri = Uri("http://localhost:8000/classify"),
      entity = HttpEntity(ContentTypes.`application/json`,requestJson)
    )
    val future: Future[HttpResponse] = Http().singleRequest(request)

    val response: HttpResponse = Await.ready(future, 5 seconds).value.get.get

    val futureMessage: Future[DialogAgentMessage] = response.entity.dataBytes
      .runReduce(_ ++ _)
      .map{ line =>
        val ret = line.utf8String.split(" ").headOption.map(Classification)
        val classification = ret.getOrElse(new Classification(""))
        val rawName = classification.name
        val name = rawName.replace("\"","")
        logger.info(s"Classification name = ${name}")
        DialogAgentMessage(
          message.header,
          message.msg,
          DialogAgentMessageData(
            participant_id = message.data.participant_id,
            asr_msg_id = message.data.asr_msg_id,
            text = message.data.text,
            dialog_act_label = name,
            source = message.data.source,
            extractions = message.data.extractions
          )
        )
      }

    futureMessage onComplete {
      case Success(m: DialogAgentMessage) => rejoin(write(m))
      case Failure(t) => 
        logger.error(s"An error occured:  ${t}")
        rejoin("{error}")
    }
  }


  /** Scan the line as a MetadataLookahead, return default if not readable
   *  @param line:  A single JSON line
   *  @return A MetadataLookahead struct
   */
  def readMetadataLookahead(line: String): MetadataLookahead = try {
    read[MetadataLookahead](line)
  } catch {
    case NonFatal(t) => new MetadataLookahead
  }
}

object Classifier extends LazyLogging {
  implicit val system = ActorSystem()
  implicit val dispatcher = system.dispatcher

  case class Classification(name: String)

  def parse(line: ByteString): Option[Classification] = {
    logger.info("parse")
    val ret = line.utf8String.split(" ").headOption.map(Classification)
    ret.toList.foreach(c => logger.info(s"Classification = ${c.name}"))
    ret
  }

  // Run and completely consume a single akka http request
  def runRequest(req: HttpRequest): Future[Option[Classification]] = {
    logger.info("runRequest")
    Http()
      .singleRequest(req)
      .flatMap { response =>
        response.entity.dataBytes
          .runReduce(_ ++ _)
          .map(parse)
      }
  }

  def apply(json: String) = {
    logger.info("apply")

    val request = HttpRequest(
      uri = Uri("http://localhost:8000/classify"),
      entity = HttpEntity(ContentTypes.`application/json`,json)
    )
    runRequest(request)
  }
}

