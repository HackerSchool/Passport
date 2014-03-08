package models

import pt.ist.fenixframework.FenixFramework
import scala.collection.JavaConversions._

object SocialNetwork {
  def getAll(): Set[SocialNetwork] = {
     FenixFramework.getDomainRoot().getSocialNetworksSet().toIndexedSeq.toSet
  }
 
  def getAllByName(name: String): Set[SocialNetwork] = {
   getAll().filter(_.getName() == name)
  }
  def getAllByRri(rri: String): Set[SocialNetwork] = {
    getAll().filter(_.getRri() == rri)
  }
  def getByOid(oid : Long): Set[SocialNetwork] = {
    getAll().filter(_.getOid() == oid)
  }
}

class SocialNetwork(fullname: String, url: String) extends SocialNetwork_Base {
  this.setRoot(FenixFramework.getDomainRoot())
  this.setName(fullname)
  this.setRri(url)
}
