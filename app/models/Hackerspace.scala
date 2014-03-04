package models

import play.Logger
import pt.ist.fenixframework.FenixFramework
import scala.collection.JavaConversions._
import util.FenixFrameworkUtil._
import _root_.exception.HackerspaceAlreadyExistsException
import pt.ist.fenixframework.consistencyPredicates.ConsistencyPredicate


object Hackerspace {
  def isValidName(name: String): Boolean = atomic {
    val hackerspaces = FenixFramework.getDomainRoot().getHackerspacesSet()
    hackerspaces.count(_.getName() == name) == 0
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
