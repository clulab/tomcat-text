package org.clulab.utils

import org.clulab.odin.{Attachment, CrossSentenceMention, EventMention, Mention, RelationMention, TextBoundMention}

object MentionUtils {
  protected def withOnlyAttachments(mention: Mention, setOfAttachments: Set[Attachment]): Mention = {
    mention match {
      case m: TextBoundMention => m.copy(attachments = setOfAttachments)
      case m: RelationMention => m.copy(attachments = setOfAttachments)
      case m: EventMention => m.copy(attachments = setOfAttachments)
      case _ => throw new RuntimeException(s"Unsupported mention type: ${mention.getClass}")
    }
  }

  def withOnlyAttachments(mention: Mention, seqOfAttachments: Seq[Attachment]): Mention =
    withOnlyAttachments(mention, seqOfAttachments.toSet)

  def withMoreAttachments(mention: Mention, attachments: Seq[Attachment]): Mention =
    if (attachments.isEmpty) mention
    else withOnlyAttachments(mention, mention.attachments ++ attachments)
}
