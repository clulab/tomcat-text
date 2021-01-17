//  ChatAnalysis
//
//  Author:  Joseph Astier, Adarsh Pyarelal
//  Date:  2020 December
//
//  ChatAnalysisMessage object JSON serializer/deserializer
//
//
package org.clulab.asist

import org.json4s._
import org.json4s.jackson.JsonMethods.{compact, parse, render}
import scala.io.Source
import scala.util.parsing.json.JSON
import spray.json._
import spray.json.DefaultJsonProtocol._

import scala.collection.immutable


/*
import scala.collection.mutable.ArrayBuffer
import scala.util.parsing.json.JSON
import spray.json._
import spray.json.DefaultJsonProtocol._
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence
*/


// A simple class just to test the serialization
case class TestJson(
  val timestamp: String = "timestamp",
  val messageType: String = "message_type",
  val version: String = "version",
)


class ChatJson {
  val headerMapResourcePath = "message_specs/header/header.json"

  val headerMapRaw = Source.fromResource(headerMapResourcePath).mkString

  log("headerMapRaw = \n%s".format(headerMapRaw))

  val headerJson = JsonParser(headerMapRaw)



  log("Done.")

  def log(msg: String): Unit = {
    println("ChatJson: %s".format(msg))
  }

  def toJson(cam: ChatAnalysisMessage): String = {
    "json_serialization"
  }

  def fromJson(json: String): ChatAnalysisMessage = {
    new ChatAnalysisMessage
  }

}

