/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Updated:  2021 February
 *
 *  Test the DialogAgent by comparing its output with known standards
 */
package org.clulab.asist

import org.scalatest.{FlatSpec, Matchers}

class TestDialogAgent 
    extends FlatSpec 
    with DialogAgent 
    with DialogAgentJson 
    with Matchers  {

  val test_message_type = "test_message_type"
  val test_experiment_id = "test_experiment_id"
  val test_trial_id = "test_trial_id"
  val test_timestamp = "test_timestamp"
  val test_source = "test_source"
  val test_sub_type = "test_sub_type"
  val test_version = "test_version"
  val test_replay_root_id = "test_replay_root_id"
  val test_replay_id = "test_replay_id"
  val test_text = "test_text"
  val test_asr_system = "test_asr_system"
  val test_is_final = true
  val test_participant_id = "test_participant_id"
  val test_source_type = "test_source_type"
  val test_source_name = "test_source_name"

  val asrTestArticle: AsrMessage = AsrMessage(
    AsrMessageData(   // data comes first in ASR message.
      test_text,
      test_asr_system,
      test_is_final,
      test_participant_id
    ),
    CommonHeader(
      test_timestamp,
      test_message_type,
      test_version
    ),
    CommonMsg(
      test_experiment_id,
      test_trial_id,
      test_timestamp,
      test_source,
      test_sub_type,
      test_version,
      test_replay_root_id,
      test_replay_id
    )
  )

  val asrJson = toJson(asrTestArticle)
  val asrResult:AsrMessage = toAsrMessage(asrJson).getOrElse(
    new AsrMessage(
      new AsrMessageData,
      new CommonHeader,
      new CommonMsg
    )
  )

  "DialogAgentJson" should "create CommonHeader from ASR json" in {
    val header = asrResult.header
    header.timestamp should be (test_timestamp)
    header.message_type should be (test_message_type)
    header.version should be (test_version)
  }

  it should "create CommonMsg from ASR json" in {
    val msg = asrResult.msg
    msg.experiment_id should be (test_experiment_id)
    msg.trial_id should be (test_trial_id)
    msg.timestamp should be (test_timestamp)
    msg.source should be (test_source)
    msg.sub_type should be (test_sub_type)
    msg.version should be (test_version)
    msg.replay_root_id should be (test_replay_root_id)
    msg.replay_id should be (test_replay_id)
  }

  it should "create AsrMessageData from ASR json" in {
    val data = asrResult.data
    data.text should be (test_text)
    data.asr_system should be(test_asr_system)
    data.is_final should be(test_is_final)
    data.participant_id should be (test_participant_id)
  }

  val dialogAgentResult = 
    toDialogAgentMessage(asrResult, test_source_type, test_source_name)
  "DialogAgent" should "create CommonHeader from AsrMessage" in {
    val header = dialogAgentResult.header
    header.message_type should be (DIALOG_AGENT_MESSAGE_TYPE)
    header.version should be (DIALOG_AGENT_VERSION)
  }
  it should "create CommonMsg from AsrMessage" in {
    val msg = dialogAgentResult.msg
    msg.experiment_id should be (test_experiment_id)
    msg.trial_id should be (test_trial_id)
    msg.source should be (DIALOG_AGENT_SOURCE)
    msg.sub_type should be (DIALOG_AGENT_SUB_TYPE)
    msg.version should be (DIALOG_AGENT_VERSION)
    msg.replay_root_id should be (test_replay_root_id)
    msg.replay_id should be (test_replay_id)
  }
  it should "create DialogAgentMessageData from AsrMessage" in {
    val data = dialogAgentResult.data
    data.participant_id should be (test_participant_id)
    data.text should be (test_text)
    data.source.source_type should be(test_source_type)
    data.source.source_name should be(test_source_name)
  }
}
