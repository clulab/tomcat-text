/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  updated:  2021 February
 *
 *  Components used by more than one message class.
 */
package org.clulab.asist

/** Generic message header */
case class MessageHeader(
  val timestamp: String = null, // "2019-12-26T12:47:23.1234Z" 
  val message_type: String = null,  // "event"
  val version: String = null // "1.0"
)
