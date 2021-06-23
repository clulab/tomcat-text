package org.clulab.asist.messages

/**
 *  Authors:  Joseph Astier, Adarsh Pyarelal
 *
 *  updated:  2021 June
 *
 *  Trial Message Format based on:
 *
 *  https://gitlab.asist.aptima.com/asist/testbed/-/blob/develop/MessageSpecs/Trial/trial.md
 *
 */


/** Contains only the needed fields of the Trial Message data format */
case class TrialMessage (
  msg: CommonMsg
) 
