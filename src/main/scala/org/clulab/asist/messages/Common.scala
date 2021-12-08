package org.clulab.asist.messages
import buildinfo.BuildInfo

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 *  Components used by more than one message class.
 */

case class CommonHeader(
  timestamp: String = null, // "2019-12-26T12:47:23.1234Z"
  message_type: String = null, // "agent"
  version: String = "0.1" // testbed version from trial start, or 0.1 if not available
)

// This is not the testbed CommonMsg struct, it contains only the fields we use
case class CommonMsg (
  experiment_id: String = null, // "123e4567-e89b-12d3-a456-426655440000"
  trial_id: String = null, //  "123e4567-e89b-12d3-a456-426655440000"
  timestamp: String = null, // "2019-12-26T14:05:02.1412Z"
  source: String = null, // "uaz_dialog_agent",
  sub_type: String = null, // "event"
  version: String = BuildInfo.version,  // "3.1.1"
  replay_root_id: String = null, // "123e4567-e89b-12d3-a456-426655440000"
  replay_id: String = null // "876e4567-ab65-cfe7-b208-426305dc1234"
)
