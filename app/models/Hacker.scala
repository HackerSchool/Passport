package models

import pt.ist.fenixframework.FenixFramework
import scala.collection.JavaConversions._

object Hacker {
  private def getAll(): Set[Hacker] = {
     FenixFramework.getDomainRoot().getHackersSet().toIndexedSeq.toSet
  }
  
  def getAllByName(name: String): Set[Hacker] = {
   getAll().filter(_.getName() == name)
  }
  def getAllByRri(rri: String): Set[Hacker] = {
    getAll().filter(_.getRri() == rri)
  }
  def getByOid(oid : Long): Set[Hacker] = {
    getAll().filter(_.getOid() == oid)
  }
}
class Hacker extends Hacker_Base {
  setRoot(FenixFramework.getDomainRoot())
}
