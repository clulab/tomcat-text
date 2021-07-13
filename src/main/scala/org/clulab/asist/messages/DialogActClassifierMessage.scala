package org.clulab.asist.messages

import org.json4s.jackson.Serialization.read

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 *  Updated:  2021 July
 *
 *  Classifier messages
 *
 *  Messages for communication with the Dialog Act Classifier
 */


// outbound comms from DialogAgent client to Dialog Act Classifier server
case class DialogActClassifierMessage(
  participant_id: String = "",
  text: String = "",
  extractions:Seq[DialogAgentMessageDataExtraction] = Seq.empty
)

