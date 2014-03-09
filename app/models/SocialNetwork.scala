package models

import pt.ist.fenixframework.FenixFramework
import scala.collection.JavaConversions._
import exception.DomainException

object SocialNetwork {
  def getAll(): Set[SocialNetwork] = {
     FenixFramework.getDomainRoot().getSocialNetworksSet().toIndexedSeq.toSet
  }
 
  def getAllWithName(name: String): Set[SocialNetwork] = {
   getAll().filter(_.getName() contains name)
  }
  def getAllByRri(rri: String): Set[SocialNetwork] = {
    getAll().filter(_.getRri() == rri)
  }
  def getByOid(oid : Long): SocialNetwork = {
    val lst = getAll().filter(_.getOid() == oid)
    if (lst.size == 0) 
      throw new DomainException("not.found")
    if (lst.size > 1) 
      throw new DomainException("too.many.entries")
    lst.head
  }
}

class SocialNetwork(fullname: String, url: String) extends SocialNetwork_Base {
  this.setRoot(FenixFramework.getDomainRoot())
  this.setName(fullname)
  this.setRri(url)
}
