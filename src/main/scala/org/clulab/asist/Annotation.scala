package org.clulab.asist

import edu.stanford.nlp.ling.CoreAnnotations.EntityClassAnnotation

// Add id info
class ActionAnnotation(
    var span: String,
    var start_offset: Int,
    var end_offset: Int,
    var action_type: String,
    var id: String,
    var target_span: String = "-1",
    var target_id: String = "-1",
    val factual: Boolean
) {

  def shallowCompare(
      other_type: String,
      other_start: Int,
      other_end: Int
  ): Boolean = {
    if (
      (action_type == other_type) &&
      (start_offset <= other_start && end_offset >= other_end)
    ) {
      return true
    } else {
      return false
    }
  }

  def deepCompare(
      other_type: String,
      other_start: Int,
      other_end: Int,
      other_target: Option[String] = None
  ): Boolean = {
    if (!shallowCompare(other_type, other_start, other_end)) {
      return false
    } else {
      other_target match {
        case Some(other_span) =>
          if (target_span contains other_span) {
            return true
          } else {
            return false
          }
        case None =>
          if (target_span == "-1") {
            return true
          } else {
            return false
          }
      }
    }
  }

  override def toString: String =
    span + " : " + start_offset + ", " + end_offset
}

class EntityAnnotation(
    var span: String,
    var start_offset: Int,
    var end_offset: Int,
    var id: String
) {

  override def toString: String =
    span + " : " + start_offset + ", " + end_offset
}
