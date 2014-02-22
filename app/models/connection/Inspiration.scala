package models.connection

import models.Hackerspace
import models.Project
import models.Hacker
import models.Party

class Inspiration(child: Party, parent: Party) extends Connection {

  def isValidConnection() : Boolean = getChild() match {
    case c: Hacker => getParent() match {
        case p: Hacker => true
        case _ => false
    }
    case c: Hackerspace => getParent() match {
        case p: Hackerspace => true
        case _ => false
    }
    case _ => false
  } 
    
    
}