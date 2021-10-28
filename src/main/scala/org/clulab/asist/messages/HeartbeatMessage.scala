package org.clulab.asist.messages

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Heartbeat Message based on:
 *
 *  https://gitlab.asist.aptima.com/asist/testbed/-/blob/develop/AsistDataIngesterContainer/src/AsistDataIngester/Models/HeartbeatMessage.cs
 *
 * EXAMPLE:
 *
 * {
 *   "header": {
 *     "timestamp": "2021-10-16T01:07:52.824Z",
 *     "message_type": "status",
 *     "version": "0.1"    // dialog agent version
 *   },
 *   "msg": {
 *     "timestamp": "2021-10-16T01:07:52.824Z",
 *     "source": "uaz_dialog_agent",
 *     "sub_type": "heartbeat",
 *     "version": "2.0.2021-10-15-spiral-1",  // testbed version
 *     "trial_id": "t",
 *     "experiment_id": "e"
 *   },
 *   "data": {
 *     "state": "ok",
 *     "active": true,
 *     "status": "I am processing messages"
 *   }
 * }
 *
 */

case class HeartbeatMessageMsg(
  timestamp: String = "",
  source: String = "uaz_dialog_agent",
  sub_type: String = "heartbeat",
  version: String = "", // Trial.data.testbed_version from Message bus
  trial_id: String = "", // Trial.msg.trial_id from Message bus
  experiment_id: String = "" // Trial.msg.experiment_id from Message bus
)

case class HeartbeatMessageData(
  state: String = "ok",
  active: Boolean = true,
  status: String = "I am processing messages"
)

/** Contains the complete specification for a Heartbeat message */
case class HeartbeatMessage (
  header: CommonHeader = new CommonHeader("","status",""),
  msg: HeartbeatMessageMsg = new HeartbeatMessageMsg,
  data: HeartbeatMessageData = new HeartbeatMessageData
) 
