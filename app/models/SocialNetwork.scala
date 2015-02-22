package models

case class SocialNetwork(id: Option[Long], fullname: String, url: String, rri: String, name: String,
                         logoUrl: String) extends Party

object SocialNetwork {
  def getAll: Set[SocialNetwork] = ???

  def getAllWithName(name: String): Set[SocialNetwork] = ???

  def getAllByRri(rri: String): Set[SocialNetwork] = ???

  def getById(id : Long): Option[SocialNetwork] = ???
}