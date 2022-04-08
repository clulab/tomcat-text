package org.clulab.asist.messages

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  MQTT structures
 */

// A single subscription or publication on the Message Bus
case class BusMessage(
  topic: String = "N/A",
  text: String = "N/A" // may contain newlines
)
