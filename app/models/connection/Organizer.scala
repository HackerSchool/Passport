package models.connection

import models.Hackerspace
import models.Project
import models.Hacker
import models.Party
import models.Event

class Organizer(id: Option[Long], _child: Long, _parent: Long) extends Connection {

  def parentMatch: Boolean = parent match {
      case p: Event => true
      case _ => false
    }
  
  def isValidConnection: Boolean = child match {
    case c: Hacker => parentMatch
    case c: Hackerspace => parentMatch
    case _ => false
  }

} 