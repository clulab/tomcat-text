package org.clulab.asist

import java.text.SimpleDateFormat

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

class Transcript(val file_name: String) {

  override def toString: String = {
    var big_string = ""
    for (line <- Source.fromFile(file_name).getLines) {
      big_string += line + "\n"
    }
    big_string
  }

  def getCleanDoc(): (String) = {
    val zoom_lines = Source.fromFile(file_name).getLines
    zoom_lines.next
    var output_string = ""; var cur = ""
    val punctuation = Set('?', '!', '.')
    for (line <- zoom_lines) {
      cur = line.stripLineEnd
      if (cur.size > 3) {
        if (!cur.charAt(0).isDigit) {
          if (cur.contains(":")) {
            cur = cur.split(":")(1)
          }
          if (!punctuation(cur.charAt(cur.size - 1))) {
            cur += "."
          }
          output_string += cur
        }
      }
    }
    output_string
  }

  def isVictimQuestion(utterance: String): Boolean = {
    val gold_tokens =
      "Which type of victim will you save next Yellow green or whoever comes next"
        .split(' ')
        .toSet
    var tokens = utterance.split(' ').toSet
    var match_count = 0
    for (tok <- tokens) {
      if (gold_tokens contains tok) {
        match_count += 1
      }
    }
    (match_count > 4)
  }

  def getTimeMap(): (
      ArrayBuffer[Int],
      ArrayBuffer[java.util.Date],
      ArrayBuffer[Int],
      ArrayBuffer[Int]
  ) = {
    // This returns the ending char for each utterance the timestamp of each utterance, and the
    //  index of researcher utterances
    val zoom_lines = Source.fromFile(file_name).getLines
    val char_values = ArrayBuffer[Int]()
    val date_values = ArrayBuffer[java.util.Date]()
    val researcher_char_values = ArrayBuffer[Int]()
    val researcher_questions = ArrayBuffer[Int]()
    val time_format = new SimpleDateFormat("HH:mm:ss.SSS")
    val punctuation = Set('?', '!', '.')

    zoom_lines.next
    var char_index = 0
    var researcher_flag = false
    var prev = ""; var cur = ""
    for (line <- zoom_lines) {
      cur = line.stripLineEnd
      if (cur.size > 3) {
        if (cur.charAt(0).isDigit) {
          prev = cur
        } else {
          if (cur.contains(":")) {
            // All utterances following a labeled utterance belong to the root label
            //   If a utterance has a ':' char, then its a root utterance
            researcher_flag = (cur.substring(0, 3) != "Par")
            cur = cur.split(":")(1)
          }
          if (!punctuation(cur.charAt(cur.size - 1))) {
            cur += "."
          }
          char_index += cur.size
          prev = prev.split(" --> ")(0) //0 is start time and 1 is end time
          val timestamp = time_format.parse(prev)
          char_values.append(char_index)
          date_values.append(timestamp)
          if (researcher_flag) {
            researcher_char_values.append(char_values.size - 1)
            if (isVictimQuestion(cur)) {
              researcher_questions.append(char_index)
            }
          }
        }
      }
    }
    (char_values, date_values, researcher_char_values, researcher_questions)
  }
}
