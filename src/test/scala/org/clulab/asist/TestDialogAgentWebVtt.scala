/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Updated:  2021 February
 *
 *  Test the DialogAgent by comparing its output with known standards
 */
package org.clulab.asist

import java.io.{File, PrintWriter}
import org.scalatest.{FlatSpec, Matchers}


class TestDialogAgentWebVtt extends FlatSpec with DialogAgent with DialogAgentJson with Matchers  {

  val inputFilename = "TestDialogAgentWebVtt_input.vtt"
  val outputFilename = "TestDialogAgentWebVtt_output.json"

  // create the test file
  def createTestFile: Boolean = {
    val lines = List(
      "WEBVTT",
      "",
      "1",
      "00:00:03.600 --> 00:00:15.450",
      "Jane Doe: Great. So thank you, you will be participating in the simulated search and rescue mission in the Minecraft environment, the entire experiment will take about two and half hours.",
      "",
      "2",
      "00:00:16.320 --> 00:00:23.730",
      "But before we begin, I just want to emphasize that, as this is an experiment, we may say things formerly"
    )

    try {
      val writer = new PrintWriter(new File(inputFilename))
      lines.map(line => writer.write("%s\n".format(line)))
      writer.close
      true
    } catch {
      case t: Throwable => {
        println("Could not create test file %s\n".format(inputFilename))
        false
      }
    }
  }

  val setupOK = createTestFile
  val dialogAgentWebVtt = new DialogAgentWebVtt(inputFilename, outputFilename)

  "Initialization" should "write the test file correctly" in {
    setupOK should be (true)
  }
  "Test run" should "create MQTT agent" in {
    dialogAgentWebVtt should not be (None)
  }


}
