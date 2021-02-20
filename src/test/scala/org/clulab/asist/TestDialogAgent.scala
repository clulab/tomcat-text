/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Updated:  2021 February
 *
 *  Test the DialogAgent by comparing its output with known standards
 */
package org.clulab.asist

import org.scalatest.{FlatSpec, Matchers}

class TestDialogAgent extends FlatSpec with DialogAgent with Matchers  {

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

  "DialogAgent" should "create output correctly" in {
    result.data.source.source_type should be (source_type)
    result.data.source.source_name should be (source_name)
    result.msg.experiment_id should be (experiment_id)
    result.data.participant_id should be (participant_id)
    result.data.text should be (text)
  }
}
