package models.connection

import models.Hackerspace
import models.Project
import models.Hacker
import models.Party
import models.Event

class Organizer(child: Party, parent: Party) extends Connection {

  def parentMatch(): Boolean = getParent() match {
      case p: Event => true
      case _ => false
    }
  
  def isValidConnection(): Boolean = getChild() match {
    case c: Hacker => parentMatch()
    case c: Hackerspace => parentMatch()
    case _ => false
  }

} 