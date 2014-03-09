package models
import scala.collection.JavaConversions._
import pt.ist.fenixframework.FenixFramework
import exception.DomainException

object Project {
  def getAll(): Set[Project] = {
     FenixFramework.getDomainRoot().getProjectsSet().toIndexedSeq.toSet
  }
  
 def getAllWithName(name: String): Set[Project] = {
    getAll().filter(_.getName() contains name)
  }
  
  def getAllByRri(rri: String): Set[Project] = {
    getAll().filter(_.getRri() == rri)
  }
  
  def getByOid(oid : Long): Project = {
    val lst = getAll().filter(_.getOid() == oid)
    if (lst.size == 0) 
      throw new DomainException("not.found")
    if (lst.size > 1) 
      throw new DomainException("too.many.entries")
    lst.head
  }
  
}

class Project(name: String, rri: String) extends Project_Base {
	this.setName(name)
	this.setRri(rri)
    this.setRoot(FenixFramework.getDomainRoot())

}
