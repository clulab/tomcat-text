//  DialogAnalysisMessage
//
//  Author:  Joseph Astier, Adarsh Pyarelal
//  Date:  2020 December
//
//
package org.clulab.asist

/** This trait handles printing of test results */
trait MessageTest {

  /** All output happens here
   *  @param str a string to print on a single line
   */
  def show(str: String): Unit = println(str)

  /** Print the inputs in a consistant manner 
   *  @param key the name of a DialogAgentMessage structure field
   *  @param a a value associated with the key
   *  @param b a value associated with the key
   *  @param status the result of comparison of the keys 
   */
  def display(key: String, a: String, b: String, status: String): Unit = 
    show("%s (\"%s\", \"%s\") %s".format(key, a, b, status))

  /** No test, just show the inputs and with a status of not tested
   *  @param key the name of a DialogAgentMessage structure field
   *  @param a a value associated with the key
   *  @param b a value associated with the key
   */
  def show(key: String, a: String, b: String): Unit = 
    display(key, a, b, "NOT TESTED")
  
  /** Comparison of two key values 
   *  @param key the name of a DialogAgentMessage structure field
   *  @param a a value associated with the key
   *  @param b a value associated with the key
   *  @return true if the values are equal
   */
  def test(key: String, a: String, b: String): Boolean = {
    val result = (a == b)
//    if(result) display(key, a, b, "OK") else display(key, a, b, "FAIL")
    if(!result) display(key, a, b, "FAIL")
    result
  }
}


/** Compare two DialogAgentMessage structures */
object MessageTestDialogAgent extends MessageTest {

  /** Compare two DialogAgentMessage structures 
   *  @param a a complete DialogAgentMessage structure
   *  @param b a complete DialogAgentMessage structure
   *  @return true if a and b are identical
   */
  def apply(a: DialogAgentMessage, b: DialogAgentMessage): Boolean = {
    val h = header(a.header, b.header)
    val m = msg(a.msg, b.msg)
    val d = data(a.data, b.data)
    h && m && d 
  }

  /** Compare two MessageHeader structures 
   *  @param a a DialogAgentMessage header structure
   *  @param b a DialogAgentMessage header structure
   *  @return true if a and b are identical
   */
  def header(a: MessageHeader, b: MessageHeader): Boolean = {
    show("header:")
    show("  timestamp:", a.timestamp, b.timestamp)
    val m = test("  message_type:", a.message_type, b.message_type)
    val v = test("  version:", a.version, b.version) 
    m && v
  }

  /** Compare two DialogAgentMessageMsg structures 
   *  @param a a DialogAgentMessage msg structure
   *  @param b a DialogAgentMessage msg structure
   *  @return true if a and b are identical
   */
  def msg(a: DialogAgentMessageMsg, b: DialogAgentMessageMsg): Boolean = {
    show("msg:")
    val s = test("  source:", a.source, b.source)
    val e = test("  experiment_id:", a.experiment_id, b.experiment_id)
    show("  timestamp:", a.timestamp, b.timestamp)
    val st= test("  sub_type:", a.sub_type, b.sub_type)
    val v = test("  version:", a.version, b.version)
    s && e && st && v
  }

  /** Compare two DialogAgentMessageData structures 
   *  @param a a DialogAgentMessage data structure
   *  @param b a DialogAgentMessage data structure
   *  @return true if a and b are identical
   */
  def data(a: DialogAgentMessageData, b: DialogAgentMessageData): Boolean = {
    show("data:")
    val l = List(
      test("  participant_id:", a.participant_id, b.participant_id),
      test("  text", a.text, b.text) 
    )
    show("  source:")
    val s = source(a.source, b.source)
    show("  extractions:")
    val e = extractions(0, a.extractions,b.extractions,List(true))
    (e::s::l).foldLeft(true)(_ && _)
  }

  /** Compare two DialogAgentMessageDataSource structures 
   *  @param a a DialogAgentMessageData source structure
   *  @param b a DialogAgentmessageData source structure
   *  @return true if a and b are identical
   */
  def source(
      a: DialogAgentMessageDataSource, 
      b: DialogAgentMessageDataSource): Boolean = {
    val s = test("    source_type:", a.source_type, b.source_type)
    val t = test("    source_name:", a.source_name, b.source_name)
    s && t
  }

  /** Compare two DialogAgentMessageDataExtraction sequences 
   *  @param i zero-based sequence index 
   *  @param a a sequence of DialogAgent message extractions
   *  @param b a sequence of DialogAgent message extractions
   *  @param l list of results of comparisons
   *  @return true if a and b are identical
   */
  def extractions(
      i: Int,
      a: Seq[DialogAgentMessageDataExtraction],
      b: Seq[DialogAgentMessageDataExtraction],
      l: List[Boolean]): Boolean = {
    val empty = new DialogAgentMessageDataExtraction
    (a,b) match {
      case (aval::atail, bval::btail) => 
        extractions(i+1, atail, btail, extraction(i, aval, bval)::l)
      case(aval::atail, _) => 
        extractions(i+1, atail, Seq.empty, extraction(i, aval, empty)::l)
      case(_, bval::btail) =>
        extractions(i+1, Seq.empty, btail, extraction(i, empty, bval)::l)
      case _ => l.foldLeft(true)(_ && _)
    }
  }

  /** Compare two DialogAgentMessageDataExtraction structures 
   *  @param i zero-based sequence index 
   *  @param a a DialogAgent message extraction
   *  @param b a DialogAgent message extraction
   *  @return true if a and b are identical
   */
  def extraction(
      i: Int,
      a: DialogAgentMessageDataExtraction,
      b: DialogAgentMessageDataExtraction): Boolean = {
    show("    %d".format(i))
    val e = List (
      test("      label:",a.label, b.label),
      test("      span:",a.span, b.span),
      test("      arguments:",a.arguments, b.arguments),
    )
    show("      taxonomy_matches:")
    val tm = taxonomyMatches(0, a.taxonomy_matches, b.taxonomy_matches,List(true))
    (tm::e).foldLeft(true)(_ && _)
  }

  /** Compare two taxonomy key-value pair sequences 
   *  @param i zero-based sequence index 
   *  @param a a sequence of key-value pairs
   *  @param b a sequence of key-value pairs
   *  @param l list of results of comparisons
   *  @return true if a and b are identical
   */
  def taxonomyMatches(
      i: Int,
      a: Seq[(String, String)],
      b: Seq[(String, String)],
      l: List[Boolean]): Boolean = {
    val empty = ("","")
    (a,b) match {
      case (aval::atail, bval::btail) => 
        taxonomyMatches(i+1, atail, btail, taxonomyMatch(i, aval, bval)::l)
      case(aval::atail, _) => 
        taxonomyMatches(i+1, atail, Seq.empty, taxonomyMatch(i, aval, empty)::l)
      case(_, bval::btail) =>
        taxonomyMatches(i+1, Seq.empty, btail, taxonomyMatch(i, empty, bval)::l)
      case _ => l.foldLeft(true)(_ && _)
    }
  }

  /** Compare two taxonomy key-value pairs
   *  @param i zero-based sequence index 
   *  @param a key-value pair
   *  @param b key-value pair
   *  @return true if a and b are identical
   */
  def taxonomyMatch(i: Int, a:(String, String), b:(String, String)): Boolean = {
    show("        %d".format(i))
    test("          ","%s".format(a),"%s".format(b))
  }
}

