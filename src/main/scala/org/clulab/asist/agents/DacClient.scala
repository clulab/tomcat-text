package org.clulab.asist.agents

//FIXME clean up imports
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source
import akka.util.ByteString
import com.typesafe.scalalogging.LazyLogging
import java.io.{File, PrintWriter}
import java.nio.file.Paths
import java.time.Clock
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.TimeoutException
import org.clulab.asist.messages._
import org.clulab.utils.LocalFileUtils
import org.json4s.{Extraction,_}
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization.{read,write}
import org.json4s.JField

import scala.annotation.tailrec
import scala.collection.mutable.Queue
import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.language.postfixOps
import scala.util.control.NonFatal
import scala.util.{Failure, Success}

//import spray.json.DefaultJsonProtocol._

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


class DacClient extends LazyLogging {
  implicit val system = ActorSystem("System")
//  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer
  implicit val dispatcher = system.dispatcher

  // Call the DAC with this query.
  def classifyAndWrite(
    message: DialogAgentMessage,
    output: PrintWriter
  ): Unit = synchronized {
    logger.info("classification")
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

    logger.info("DAC request start")
    val response: HttpResponse = Await.ready(future, 20 seconds).value.get.get
    // FIXME catch timeout exception
    logger.info("DAC request end")

    response.entity.dataBytes
      .runReduce(_ ++ _)
      .map{ line =>
        val ret = line.utf8String.split(" ").headOption.map(Classification)
        val classification = ret.getOrElse(new Classification(""))
        val rawName = classification.name
        val name = rawName.replace("\"","")
        logger.info(s"Classification name = ${name}")
        val newMessage = DialogAgentMessage(
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
        val json = write(newMessage)
        output.write(s"${json}\n")
        output.flush
        logger.info(s"Wrote: ${json}")
      }

    val ret = response.toString
    logger.info(ret)
    ret
  }
}
