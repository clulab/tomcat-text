package org.clulab.asist.messages

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal, Rebecca Sharp
 *
 *  Updated:  2021 June
 *
 *  Trial Message Format based on:
 *
 *  https://gitlab.asist.aptima.com/asist/testbed/-/blob/develop/MessageSpecs/Trial/trial.md
 *
 */


/** Contains only the needed fields of the Trial Message metadata format */
case class TrialMessage (
  header: CommonHeader,
  msg: CommonMsg
) 
