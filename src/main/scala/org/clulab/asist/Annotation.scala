package org.clulab.asist

  // Add id info
class ActionAnnotation(var span: String, var start_offset: Int,
                       var end_offset: Int, var action_type: String,
                        var id: Int, var target_id: Int = -1) {

  def shallowCompare(other_type: String, other_start: Int, other_end: Int): Boolean = {
    if ((action_type == other_type) &&
        (start_offset <= other_start && end_offset >= other_end)){
      return true
    } else {
      return false
    }
  }

  def deepCompare(other_type: String, other_start: Int, other_end: Int, other_ent: EntityAnnotation): Unit ={
    if (!shallowCompare(other_type, other_start, other_end)){
      return false
    } else {
      if (other_ent.id == target_id){
        return true
      } else {
        return false
      }
    }
  }

  override def toString: String =
    span + " : " + start_offset + ", " + end_offset
}


class EntityAnnotation(var span: String, var start_offset: Int,
                       var end_offset: Int, var id: Int){

  override def toString: String =
    span + " : " + start_offset + ", " + end_offset
}