package models.connection

import models.Hackerspace
import models.Project
import models.Hacker

class Founder(id: Option[Long], _child: Long, _parent: Long) extends Connection {

  override def isValidConnection : Boolean = child match {
    case c: Hacker => parent match {
        case p: Hackerspace => true
        case p: Project=> true
        case _ => false
    }
    case _ => false
  }

}