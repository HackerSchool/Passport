package models

import play.Logger
import pt.ist.fenixframework.FenixFramework
import scala.collection.JavaConversions._
import util.FenixFrameworkUtil._
import _root_.exception.HackerspaceAlreadyExistsException
import pt.ist.fenixframework.consistencyPredicates.ConsistencyPredicate
import scala.collection.JavaConversions._
import exception.DomainException
import exception.DomainException

object Hackerspace {
  private def getAll(): Set[Hackerspace] = {
     FenixFramework.getDomainRoot().getHackerspacesSet().toIndexedSeq.toSet
  }
  
  def getAllWithName(name: String): Set[Hackerspace] = {
    getAll().filter(_.getName() contains name)
  }
  
  def getByName(name: String):Hackerspace = {
    val lst = getAll().filter(_.getName() == name)
    if (lst.size == 0) 
      throw new DomainException("not.found")
    if (lst.size == 1) 
      throw new DomainException("too.many.entries")
    lst.head
  }
  
  def getAllByRri(rri: String): Set[Hackerspace] = {
    getAll().filter(_.getRri() == rri)
  }
  
  def getByOid(oid : Long): Hackerspace = {
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

class Hackerspace(name: String) extends Hackerspace_Base {
  if (Hackerspace.isValidName(name) == false) {
    deleteDomainObject()
    throw new HackerspaceAlreadyExistsException("Hackerspace")
  }

  this.setName(name)
  this.setRoot(FenixFramework.getDomainRoot)
}
