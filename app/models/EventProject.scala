package models

import org.joda.time.DateTime
import java.util.Date
import java.util.UUID

object EventProject extends ModelEntity {
  def find(query: String): Stream[ModelEntity] = {
    return null
  }
}

case class EventProject(var UUID: UUID,
                        var logoPath: String,
                        var name: String,
                        var URL: String,
                        var date: Date,
                        var hackerSpaceUUID: UUID) {
}