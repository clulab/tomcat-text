package org.clulab.asist.messages
/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 *  Contains only needed fields of the Testbed Trial Message metadata format
 */
case class TrialMessage (
  header: CommonHeader = new CommonHeader(),
  msg: CommonMsg = new CommonMsg()
) 
