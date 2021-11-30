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
 *     "version": "0.1"    // current testbed version
 *   },
 *   "msg": {
 *     "timestamp": "2021-10-16T01:07:52.824Z",
 *     "source": "uaz_dialog_agent",
 *     "sub_type": "heartbeat",
 *     "version": 3.1.1 (or whatever the current dialog agent version is)
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

case class HeartbeatMessageData(
  state: String = "OK",
  active: String = "true",
  status: String = "I am processing messages"
)

/** Contains the complete specification for a Heartbeat message */
case class HeartbeatMessage (
  header: CommonHeader = new CommonHeader(message_type = "status"),
  msg: CommonMsg = new CommonMsg(sub_type = "heartbeat"),
  data: HeartbeatMessageData = new HeartbeatMessageData
) 
