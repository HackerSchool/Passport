package models

import play.Logger
import pt.ist.fenixframework.FenixFramework
import scala.collection.JavaConversions._
import util.FenixFrameworkUtil._
import _root_.exception.HackerspaceAlreadyExistsException
import pt.ist.fenixframework.consistencyPredicates.ConsistencyPredicate
import scala.collection.JavaConversions._

object Hackerspace {
  private def getAll(): Set[Hackerspace] = {
     FenixFramework.getDomainRoot().getHackerspacesSet().toIndexedSeq.toSet
  }
  
  def getAllByName(name: String): Set[Hackerspace] = {
   getAll().filter(_.getName() == name)
  }
  def getAllByRri(rri: String): Set[Hackerspace] = {
    getAll().filter(_.getRri() == rri)
  }
  def getByOid(oid : Long): Set[Hackerspace] = {
    getAll().filter(_.getOid() == oid)
  }
  
  def isValidName(name: String): Boolean = atomic {
    getAllByName(name).size == 0
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
