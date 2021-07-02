package org.clulab.asist.messages

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 *  Updated:  2021 June
 *
 *  This metadata structure will overlay the discrete structures produced by 
 *  Chat, University of Arizona ASR, and Aptima ASR sources, eliminating the
 *  need for discrete message types.
 *
 */

case class MetadataData(

  // From Chat metadata
  sender: String = null, // "player_1"

  // From University of Arizona ASR metadata
  participant_id: String = null, // "player_1"
  id: String = null,

  // From Aptima ASR metadata
  playername: String = null, // "player_1"

  // From all metadata
  text: String = null // "You want me to share my screen?"
)

/* This is an abbreviation of the CommonMsg type with only the fields we use
 */
case class MetadataMsg (
  experiment_id: String = null, // "123e4567-e89b-12d3-a456-426655440000"
  trial_id: String = null, //  "123e4567-e89b-12d3-a456-426655440000"
  replay_root_id: String = null, // "123e4567-e89b-12d3-a456-426655440000"
  replay_id: String = null // "876e4567-ab65-cfe7-b208-426305dc1234"
)

// 
case class Metadata(
  data: MetadataData,
  msg: MetadataMsg
)

// If we recognize the topic we will process the message.
case class MetadataLookahead(
  topic: String = ""
)

// used to get just the text out of the DialogAgent metadata 
case class MetadataText(
  text: String = ""
)
case class MetadataDataText(
  data: MetadataText
)
