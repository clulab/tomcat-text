package org.clulab.asist.messages

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Texas A&M Dialog Act Classifier (TDAC) messages
 *
 *  Messages for communication with the TDAC server
 */

// sent from TdacAgent for classification
case class DialogActClassifierMessage(
  participant_id: String = "",
  text: String = "",
  extractions:Seq[DialogAgentMessageUtteranceExtraction] = Seq.empty
)

// returned from DAC server
case class Classification(
  name: String = ""
)
