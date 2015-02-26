package models.connection

import java.time.OffsetDateTime

abstract class Connection(val id: Option[Long], val child: Long, val parent: Long, val creationDate: OffsetDateTime,
                          val endDate: Option[OffsetDateTime], val discriminator: String)

object Connection {
  def tupled(t: (Option[Long], Long, Long, OffsetDateTime, Option[OffsetDateTime], String)) = new Connection(t._1, t._2,
    t._3, t._4, t._5, t._6){}

  def unapply(connection: Connection) = Some((connection.id, connection.child, connection.parent,
    connection.creationDate, connection.endDate, connection.discriminator))
}
