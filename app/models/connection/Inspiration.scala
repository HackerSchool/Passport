package models.connection

import models.Hackerspace
import models.Project
import models.Hacker
import models.Party

class Inspiration(id: Option[Long], _child: Long, _parent: Long) extends Connection {

  def isValidConnection : Boolean = child match {
    case c: Hacker => parent match {
        case p: Hacker => true
        case _ => false
    }
    case c: Hackerspace => child match {
        case p: Hackerspace => true
        case _ => false
    }
    case _ => false
  } 
    
    
}