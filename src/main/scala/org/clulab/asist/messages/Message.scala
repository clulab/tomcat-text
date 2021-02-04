/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *  updated:  2021 February
 *
 *  Components usable by more than one message class.
 */
package org.clulab.asist

/** Generic message header */
case class MessageHeader(
  val timestamp: String = "",  
  val message_type: String = "",
  val version: String = ""
)
