package models.connections

import org.joda.time.DateTime
import java.util.UUID
import models.EventProject
import models.Hacker
import models.HackerSpace
import models.ModelEntity

object Connection {
  def find(query: String): Stream[ModelEntity] = {
    return null
  }
}

abstract case class Connection extends ModelEntity {
  var UUID: UUID
  var creationDate: DateTime
  var startDate: DateTime
}

abstract class Hacker2HackerConnection extends Connection {
  var firstHacker: Hacker
  var secondHacker: Hacker
}


abstract class Hacker2SpaceConnection extends Connection  {
  var hacker: Hacker
  var space: HackerSpace
}


abstract class Space2SpaceConnection extends Connection  {
  var firstSpace: Hacker
  var secondSpace: HackerSpace
}

abstract class Hacker2EventConnection extends Connection  {
  var hacker: Hacker
  var event: EventProject
}


abstract class Event2SpaceConnection extends Connection  {
  var event: EventProject
  var space: HackerSpace
}