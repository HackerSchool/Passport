package models

import pt.ist.fenixframework.FenixFramework
import scala.collection.JavaConversions._
import exception.DomainException

object Hacker {
  private def getAll(): Set[Hacker] = {
     FenixFramework.getDomainRoot().getHackersSet().toIndexedSeq.toSet
  }
  
 def getAllWithName(name: String): Set[Hacker] = {
    getAll().filter(_.getName() contains name)
  }
  
  def getByName(name: String):Hacker = {
    val lst = getAll().filter(_.getName() == name)
    if (lst.size == 0) 
      throw new DomainException("not.found")
    if (lst.size > 1) 
      throw new DomainException("too.many.entries")
    lst.head
  }
  
  def getAllByRri(rri: String): Set[Hacker] = {
    getAll().filter(_.getRri() == rri)
  }
  
  def getByOid(oid : Long): Hacker = {
    val lst = getAll().filter(_.getOid() == oid)
    if (lst.size == 0) 
      throw new DomainException("not.found")
    if (lst.size > 1) 
      throw new DomainException("too.many.entries")
    lst.head
  }
  
  def isValidName(name: String): Boolean = {
	val lst = getAll().filter(_.getName() == name)
	return lst.size == 0
  }
}

class Hacker extends Hacker_Base {
  setRoot(FenixFramework.getDomainRoot())
}
