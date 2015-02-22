package models

import java.time.OffsetDateTime

case class Project(id: Option[Long], beginDate: OffsetDateTime, rri: String, name: String,
                   logoUrl: String) extends Party

object Project {
  def getAll: Set[Project] = ???

  def getAllWithName(name: String): Set[Project] = ???
  
  def getAllByRri(rri: String): Set[Project] = ???
  
  def getById(id : Long): Option[Project] = ???
}
