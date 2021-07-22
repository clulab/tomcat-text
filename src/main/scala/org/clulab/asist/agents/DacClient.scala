package org.clulab.asist.agents

//FIXME clean up imports
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.util.ByteString
import akka.stream.scaladsl.Source
import com.typesafe.scalalogging.LazyLogging
import java.util.concurrent.TimeoutException
import java.io.PrintWriter
import java.util.concurrent.ConcurrentLinkedQueue
import org.clulab.asist.messages._
import org.json4s.jackson.Serialization.write

import scala.language.postfixOps
import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.control.NonFatal
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


class DacClient extends LazyLogging {

  implicit val system = ActorSystem()
  implicit val dispatcher = system.dispatcher

  // change the data.dialog_act_label field of a DialogAgentMessage
  def classifyMessage(
    message: DialogAgentMessage,
    dialog_act_label: String
  ): DialogAgentMessage = 
      DialogAgentMessage(
      message.header,
      message.msg,
      DialogAgentMessageData(
        participant_id = message.data.participant_id,
        asr_msg_id = message.data.asr_msg_id,
        text = message.data.text,
        dialog_act_label = dialog_act_label,
        source = message.data.source,
        extractions = message.data.extractions
      )
    )

  // Call the DAC with this query.
  def classification(message: DialogAgentMessage): String = synchronized {
    logger.info("classification")
    val data = message.data
    val json = write(new DialogActClassifierMessage(
      data.participant_id,
      data.text,
      data.extractions)
    )
    val request = HttpRequest(
      uri = Uri("http://localhost:8000/classify"),
      entity = HttpEntity(ContentTypes.`application/json`,json)
    )
    val future = Http().singleRequest(request)

    logger.info("DAC request start")
    val response: HttpResponse = Await.ready(future, 20 seconds).value.get.get
    // catch timeout exception
    logger.info("DAC request end")

    val entity: ResponseEntity = response.entity
    val source: Source[ByteString, Any] = entity.dataBytes
    val byteString: ByteString = source.dataBytes

    val ret: String = response.entity.dataBytes
    /*
      .runReduce(_ ++ _)
      .map{ line =>
        val ret = line.utf8String.split(" ").headOption.map(Classification)
        val classification = ret.getOrElse(new Classification(""))
        classification.name
      }
      */
    logger.info(s"ret = ${ret}")

    ret
  }
}
