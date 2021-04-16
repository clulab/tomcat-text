/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Updated:  2021 April
 *
 *  This metadata structure will overlay the discrete structures produced by 
 *  Chat, University of Arizona ASR, and Aptima ASR sources, eliminating the
 *  need for discrete message types.
 *
 */
package org.clulab.asist

case class MetadataData(

  // From Chat metadata
  val sender: String = null, // "player_1"

  // From University of Arizona ASR metadata
  val participant_id: String = null, // "player_1"

  // From Aptima ASR metadata
  val playername: String = null, // "player_1"

  // From all metadata
  val text: String = null // "You want me to share my screen?"
)

/* This is an abbreviation of the CommonMsg type with only the fields we use
 */
case class MetadataMsg (
  val experiment_id: String = null, // "123e4567-e89b-12d3-a456-426655440000"
  val trial_id: String = null, //  "123e4567-e89b-12d3-a456-426655440000"
  val replay_root_id: String = null, // "123e4567-e89b-12d3-a456-426655440000"
  val replay_id: String = null // "876e4567-ab65-cfe7-b208-426305dc1234"
)

// topic gets read first.  If it's one we recognize then we process Metadata
case class MetadataLookahead(
  val topic: String = "",
)

case class Metadata(
  val header: CommonHeader,
  val data: MetadataData,
  val msg: MetadataMsg
)
