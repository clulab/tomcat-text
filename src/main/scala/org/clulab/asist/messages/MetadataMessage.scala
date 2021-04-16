/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Updated:  2021 April
 *
 *  This is a structure that has the "speaker" field present in:
 *
 *    ChatMessageData
 *    AptimaAsrMessageData
 *    UazAsrMessageData
 *
 *  It can read any of them as input
 */
package org.clulab.asist

case class MetadataMessageData(

  // from UazAsrMessageData
  val sender: String = null, // "Aptiminer1"

  // from UazAsrMessageData
  val participant_id: String = null,  // "participant_1"

  // from AptimaAsrMessageData
  val playername: String = null, // "intermonk"

  // from all
  val text: String = null // "You want me to share my screen?"
)

// topic gets read first
case class MetadataLookahead(
  val topic: String = "",
)

case class MetadataMessage(
  val header: CommonHeader,
  val data: MetadataMessageData,
  val msg: CommonMsg
)
