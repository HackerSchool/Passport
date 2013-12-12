package models

import scala.collection.Seq
import org.joda.time.DateTime
import java.util.Date
import java.util.UUID

object Hacker extends ModelEntity {
  def find(query: String): Stream[ModelEntity] = {
    return null
  }
}

case class Hacker(var UUID: UUID,
                  var photoPath: String,
                  var name: String,
                  var dateOfBirth: Date,
                  var sex: String,
                  var hackerSpace: String,
                  var dateJoined: Date,
                  var membershipStatus: String) {
}