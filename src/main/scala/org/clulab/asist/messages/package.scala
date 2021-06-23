package org.clulab.asist

import org.json4s.jackson.Serialization
import org.json4s.NoTypeHints

package object messages {
  // Used so Json serializers can recognize case classes
  implicit val formats = Serialization.formats(NoTypeHints)
}
