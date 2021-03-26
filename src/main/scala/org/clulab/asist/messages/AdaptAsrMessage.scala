/** 
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  updated:  2021 March
 *
 *  ADAPT Automated Speech Recognition (ASR) message 
 *
 *  We only extract the fields needed to create DialogAgentMessage objects
 *
 */
package org.clulab.asist

/** Part of the AsrMessage class */
case class AdaptAsrMessageData(
  val text: String = null, // "You want me to share my screen?"
  val playername: String = null //"intermonk"
)

/** Contains the full structure of an ADAPT message */
case class AdaptAsrMessage (
  val msg: CommonMsg,
  val data: AdaptAsrMessageData,
  val header: CommonHeader
)
