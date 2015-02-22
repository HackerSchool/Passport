package models

import java.security.KeyPair
import java.time.OffsetDateTime

case class Hackerspace(id: Option[Long], location: String, gpsId: Long, dateFounded: OffsetDateTime, keys: KeyPair,
                       rri : String, name: String, logoUrl: String) extends Party

object Hackerspace {
  private def getAll: Set[Hackerspace] = ???
  
  def getAllWithName(name: String): Set[Hackerspace] = ???
  
  def getByName(name: String):Hackerspace = ???
  
  def getAllByRri(rri: String): Set[Hackerspace] = ???
  
  def getById(id : Long): Hackerspace = ???
  
  def isValidName(name: String): Boolean = ???
}
