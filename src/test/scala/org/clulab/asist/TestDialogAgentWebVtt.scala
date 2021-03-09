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
    "Jane Doe: You will be playing in Minecraft in this experiment.",
    "",
    "2",
    "00:00:16.320 --> 00:00:23.730",
    "This is an experiment, we may say things formerly.",
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


  "Test output" should "have two dialog agent messages" in {
    output.size should be (2)
  }

  "First dialog agent message" should "have correct header fields" in {
    val header = output(0).header
    header.message_type should be ("event")
    header.version should be ("0.1")
  }
  it should "have correct msg fields" in {
    val msg = output(0).msg
    msg.source should be ("tomcat_textAnalyzer")
    msg.experiment_id should be (null)
    msg.sub_type should be ("Event:dialogue_event")
    msg.version should be ("0.1")
  }
  it should "have correct data fields" in {
    val data = output(0).data
    data.participant_id should be ("Jane Doe")
    data.text should be (" You will be playing in Minecraft in this experiment. ")
  }
  it should "have correct source fields" in {
    val source = output(0).data.source
    source.source_type should be ("file")
    source.source_name should be (inputFilename)
  }
  it should "have two extractions" in {
    val extractions = output(0).data.extractions
    extractions.size should be (2)
  }

}
