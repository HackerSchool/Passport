package models

import scala.collection.Seq
import org.joda.time.DateTime

object User extends ModelEntity {
  def find(query: String): Stream[ModelEntity] = {
    return null
  }
}

class User(var username: String,
           var firstName: String,
           var lastName: String,
           var salt: Long,
           var passwordHash: Long,
           var email: String,
           var birthday: DateTime,
           var joinDatetime: DateTime,
           var lastVisitDateTime: DateTime,
           var location: String,
           var rank: Int,
           var points: Int) {
}