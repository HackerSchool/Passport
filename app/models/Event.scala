package models

import java.time.OffsetDateTime

case class Event(id: Option[Long], beginDate: OffsetDateTime, endDate: OffsetDateTime, rri: String, name: String,
                 logoUrl: String) extends Party

object Event {
  def getAll: Set[Event] = ???

  def getAllWithName(name: String): Set[Event] = ???

  def getAllByRri(rri: String): Set[Event] = ???

  def getById(id: Long): Option[Event] = ???
}
