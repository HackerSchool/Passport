package models

import scala.collection.JavaConversions._
import pt.ist.fenixframework.FenixFramework
import exception.DomainException

object Event {
  def getAll(): Set[Event] = {
    FenixFramework.getDomainRoot().getEventsSet().toIndexedSeq.toSet
  }

  def getAllWithName(name: String): Set[Event] = {
    getAll().filter(_.getName() contains name)
  }
  def getAllByRri(rri: String): Set[Event] = {
    getAll().filter(_.getRri() == rri)
  }
  def getByOid(oid: Long): Event = {
    val lst = getAll().filter(_.getOid() == oid)
    if (lst.size == 0)
      throw new DomainException("not.found")
    if (lst.size > 1)
      throw new DomainException("too.many.entries")
    lst.head
  }

}

class Event(name: String, rri: String) extends Event_Base() {
  this.setName(name)
  this.setRri(rri)
  this.setRoot(FenixFramework.getDomainRoot())
} 
