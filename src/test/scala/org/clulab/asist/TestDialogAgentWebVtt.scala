/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  Updated:  2021 March
 *
 *  Test the WebVtt agent by reading a test file and comparing its the output
 *  with known standards
 */
package org.clulab.asist

import java.io.{File, PrintWriter}
import scala.io.Source
import org.scalatest.{FlatSpec, Matchers}
import org.slf4j.LoggerFactory


class TestDialogAgentWebVtt 
    extends FlatSpec 
    with DialogAgent 
    with DialogAgentJson 
    with Matchers  {

  val inputFilename = "TestDialogAgentWebVtt_input.vtt"
  val outputFilename = "TestDialogAgentWebVtt_output.json"

  private val logger = LoggerFactory.getLogger(this.getClass())


  // create the test input data
  val inputLines = List(
    "WEBVTT",
    "",
    "1",
    "00:00:03.600 --> 00:00:15.450",
    "Jane Doe: Great. So thank you, you will be participating in the simulated search and rescue mission in the Minecraft environment, the entire experiment will take about two and half hours.",
    "",
    "2",
    "00:00:16.320 --> 00:00:23.730",
    "Jane Doe But before we begin, I just want to emphasize that, as this is an experiment, we may say things formerly",
    ""
    )


  // should really abort the test if these exist, and tell the user to move them aside 
  "web_vtt test initialization" should "remove existing test files" in {
    if(new File(inputFilename).exists) new File(inputFilename).delete
    if(new File(outputFilename).exists) new File(outputFilename).delete
    val inputExists = new File(inputFilename).exists
    val outputExists = new File(outputFilename).exists
    inputExists should be (false)
    outputExists should be (false)
  }


  // write the test input file
  it should "write the test input file '%s'".format(inputFilename) in {
    val inputFileWritten = try {
      val writer = new PrintWriter(new File(inputFilename))
      inputLines.map(line => writer.write("%s\n".format(line)))
      writer.close
      logger.info("test input file written successfully")
      true
    } catch {
      case t: Throwable => {
        logger.error("Could not write test file %s\n".format(inputFilename))
        logger.error(t.toString)
        false
      }
    }
    inputFileWritten should be (true)
  }

  // if the input file was written successfully, then we test the web_vtt agent:
  it should "write the output file '%s'".format(outputFilename) in {
    val runOK = DialogAgentWebVtt(inputFilename, outputFilename)
    val outputExists = new File(outputFilename).exists
    runOK should be (true)
    outputExists should be (true)
  }


  val outputSource = Source.fromFile(outputFilename)
  val output= outputSource
    .getLines
    .toList
    .map(toDialogAgentMessage(_))
    .flatten
  outputSource.close


  "Test output" should "have two elements" in {
    output.size should be (2)
  }

  "First output element" should "have correct header info" in {
    val header = output(0).header
    header.message_type should be ("event")
    header.version should be ("0.1")
  }
  it should "have correct msg info" in {
    val msg = output(0).msg
    msg.source should be ("tomcat_textAnalyzer")
    msg.experiment_id should be (null)
    msg.sub_type should be ("Event:dialogue_event")
    msg.version should be ("0.1")
  }

}
