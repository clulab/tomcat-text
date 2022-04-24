package org.clulab.asist.messages

import buildinfo.BuildInfo
import com.typesafe.config.Config
import ai.lum.common.ConfigFactory

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Testbed specification:
 *  ???
 *
 */

// part of the RollcallResponseMessage class
case class RollcallResponseMessageData(
  state: String = "N/A",
  active: Boolean = false,
  status: String = "N/A",
  rollcall_id: String = "not_set"
)

// published on the Message Bus
case class RollcallResponseMessage (
  header: CommonHeader,
  msg: CommonMsg,
  data: RollcallResponseMessageData
) 

// member functions
object RollcallResponseMessage {
  // remember config settings
  private val config: Config = ConfigFactory.load()

  val header: CommonHeader = CommonHeader(
    message_type = config.getString("RollcallResponse.header.message_type"),
  )
  val msg: CommonMsg =  CommonMsg(
    source = config.getString("RollcallResponse.msg.source"),
    sub_type = config.getString("RollcallResponse.msg.sub_type"),
    version = BuildInfo.version,
  )
  val data: RollcallResponseMessageData = RollcallResponseMessageData(
    status = config.getString("RollcallResponse.data.status")
  )

  /** Build from RollcallRequest
   *  @param request A RollcallRequestMessage that this message will respond to
   *  @return A new RollcallResponseMessage based on the request message
   */
  def apply(
    request: RollcallRequestMessage
  ): RollcallResponseMessage = RollcallResponseMessage(
    header.copy(
      version = request.header.version
    ),
    msg.copy(
      trial_id = request.msg.trial_id,
      experiment_id = request.msg.experiment_id
    ),
    data.copy()
  )
}
