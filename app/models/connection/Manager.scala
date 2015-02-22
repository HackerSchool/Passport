package models.connection

import models.Hackerspace
import models.Project
import models.Hacker
import models.Party
import models.Event

class Manager(id: Option[Long], _child: Long, _parent: Long) extends Connection {

  def isValidConnection: Boolean = child match {
    case c: Hacker => parent match {
        case p: Event => true
        case p: Hackerspace => true
        case p: Project => true
        case _ => false
    }
    case _ => false
  } 
    
    
} 