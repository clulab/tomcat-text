/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Updated:  2021 February
 *
 *  Test the DialogAgent by comparing its responses on the message bus with 
 *  known standards
 */
package org.clulab.asist

class TestDialogAgent exends DialogAgent {

  "DialogAgent" should "Create DialogAgentMessage properly" in {
    val source_type = "CI testing"
    val source_name = "sbt test"
    val experiment_id = "test 1"
    val participant_id = "jastier"
    val text = "Saving yellow victim"

    val result = toDialogAgentMessage(
      source_type,
      source_name,
      experiment_id,
      participant_id,
      text
    )

    result.msg.experiment_id should be (experiment_id)
  }

}
