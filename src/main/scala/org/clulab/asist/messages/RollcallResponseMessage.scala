package org.clulab.asist.messages

import ai.lum.common.ConfigFactory
import buildinfo.BuildInfo
import com.typesafe.config.Config
import java.time.Clock

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Testbed specification:
 *  ???
 *
 */

// part of the RollcallResponseMessage class.  
// TODO find this spec on the testbed
case class RollcallResponseMessageData(
  version: String = "N/A",  
  status: String = "N/A",
  uptime: Double = 0.0,   // currently reported in seconds
  rollcall_id: String = "not_set"
)

// published on the Message Bus
case class RollcallResponseMessage (
  topic: String = "N/A",
  header: CommonHeader,
  msg: CommonMsg,
  data: RollcallResponseMessageData
) 

// member functions
object RollcallResponseMessage {
  // remember config settings
  private val config: Config = ConfigFactory.load()

  val topic: String = config.getString("RollcallResponse.topic")
  val header: CommonHeader = CommonHeader(
    message_type = config.getString("RollcallResponse.header.message_type"),
  )
  val msg: CommonMsg =  CommonMsg(
    source = config.getString("RollcallResponse.msg.source"),
    sub_type = config.getString("RollcallResponse.msg.sub_type"),
    version = BuildInfo.version,
  )
  val data: RollcallResponseMessageData = RollcallResponseMessageData(
    status = config.getString("RollcallResponse.data.status"),
    version = BuildInfo.version,
  )

  /** Build from RollcallRequest
   *  @param request A RollcallRequestMessage that this message will respond to
   *  @return A new RollcallResponseMessage based on the request message
   */
  def apply(
    uptimeMillis: Double,
    request: RollcallRequestMessage
  ): RollcallResponseMessage = {
    val timestamp = Clock.systemUTC.instant.toString
    RollcallResponseMessage(
      topic,
      header.copy(
        version = request.header.version,
        timestamp = timestamp
      ),
      msg.copy(
        trial_id = request.msg.trial_id,
        experiment_id = request.msg.experiment_id,
        timestamp = timestamp
      ),
      data.copy(
        rollcall_id = request.data.rollcall_id,
        status = "up",
        uptime = uptimeMillis/1000.0
      )
    )
  }
}
