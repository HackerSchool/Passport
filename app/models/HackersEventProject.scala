package models

import org.joda.time.DateTime
import java.util.Date
import java.util.UUID

object HackersEventProject extends ModelEntity {
  def find(query: String): Stream[ModelEntity] = {
    return null
  }
}

case class HackersEventProject(var hackerSpaceUUID: UUID,
                               var eventProjectUUID: UUID,
                               var signature: String) {
}