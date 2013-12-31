package models

import java.util.Date
import java.util.UUID

object HackerSpace extends ModelEntity {
  def find(query: String): Stream[ModelEntity] = {
    return null
  }
}

case class HackerSpace(var UUID: UUID,
                       var logoPath: String,
                       var name: String,
                       var location: String,
                       var GPS: String,
                       var dateFounded: Date,
                       var URL: String,
                       var email: String) {
}