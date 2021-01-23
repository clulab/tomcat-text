//  Message
//
//  Author:  Joseph Astier, Adarsh Pyarelal
//  Date:  2021 January
//
//  Components usable by more than one message class.
//
package org.clulab.asist


/** Generic message header */
case class MessageHeader(
  val timestamp: String = "",  
  val message_type: String = "",
  val version: String = ""
)
