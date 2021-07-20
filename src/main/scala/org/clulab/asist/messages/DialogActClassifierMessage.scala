package org.clulab.asist.messages

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 *  Updated:  2021 July
 *
 *  Classifier messages
 *
 *  Messages for communication with the Dialog Act Classifier (DAC)
 */

// outbound comms from DialogAgent client to DAC server
case class DialogActClassifierMessage(
  participant_id: String = "",
  text: String = "",
  extractions:Seq[DialogAgentMessageDataExtraction] = Seq.empty
)

// returned from DAC server
case class Classification(name: String)

