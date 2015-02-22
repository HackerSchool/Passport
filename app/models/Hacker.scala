package models

import java.time.OffsetDateTime

case class Hacker(id: Option[Long], private val _userId: Long, birthday: OffsetDateTime, sex: String,
                  joinDate: OffsetDateTime, rri: String, name: String, logoUrl: String) extends Party {
  def user: User = ???
}

object Hacker {
  private def getAll: Set[Hacker] = ???

  def getAllWithName(name: String): Set[Hacker] = ???
  
  def getByName(name: String): Option[Hacker] = ???

  def getAllByRri(rri: String): Set[Hacker] = ???
  
  def getById(id : Long): Option[Hacker] = ???
  
  def isValidName(name: String): Boolean = ???
}
