package org.clulab.asist.messages

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Texas A&M Dialog Act Classifier (TDAC) messages
 *
 *  Messages for communication with the TDAC server
 */

// sent from TdacAgent to TDAC server for classification
case class DacData(
  participant_id: String = "",
  text: String = "",
  extractions:Seq[DialogAgentMessageUtteranceExtraction] = Seq.empty
)

// returned from TDAC server
case class DacLabel(
  name: String = ""
)
