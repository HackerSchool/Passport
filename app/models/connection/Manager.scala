package models.connection

import models.Hackerspace
import models.Project
import models.Hacker
import models.Party
import models.Event

class Manager(child: Party, parent: Party) extends Connection {

  def isValidConnection() : Boolean = getChild() match {
    case c: Hacker => getParent() match {
        case p: Event => true
        case p: Hackerspace => true
        case p: Project => true
        case _ => false
    }
    case _ => false
  } 
    
    
} 