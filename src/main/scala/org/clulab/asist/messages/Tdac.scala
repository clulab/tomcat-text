package org.clulab.asist.messages
import org.json4s.JValue

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

// returned to TdacAgent after classification
case class TdacMessage(
  topic: String = "N/A",
  text: String = "N/A", // may contain newlines
  label: String = "N/A",
  json: JValue = JNothing
)
