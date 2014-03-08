package models

import scala.collection.JavaConversions._
import pt.ist.fenixframework.FenixFramework

object Event {
  private def getAll(): Set[Event] = {
     FenixFramework.getDomainRoot().getEventsSet().toIndexedSeq.toSet
  }
  
  def getAllByName(name: String): Set[Event] = {
   getAll().filter(_.getName() == name)
  }
  def getAllByRri(rri: String): Set[Event] = {
    getAll().filter(_.getRri() == rri)
  }
  def getByOid(oid : Long): Set[Event] = {
    getAll().filter(_.getOid() == oid)
  }
}

class Event extends Event_Base() {
  setRoot(FenixFramework.getDomainRoot())
} 
