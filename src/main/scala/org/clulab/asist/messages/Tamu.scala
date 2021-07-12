package org.clulab.asist.messages

import org.json4s.jackson.Serialization.read

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 *  Updated:  2021 July
 *
 *  TAMU messages
 *
 *  Messages for communication with the TAMU Dialog Act Classifier
 */


// outbound comms from DialogAgent client to TAMU server
case class TamuDialogAgentMessage(
  participant_id: String = "",
  text: String = "",
  extractions:Seq[DialogAgentMessageDataExtraction] = Seq.empty
)

