package models.connection

import models.Hackerspace
import models.Project
import models.Hacker
import models.Party
import models.SocialNetwork
import models.SocialNetwork
import models.Event

class SocialNetworkConnection(child: Party, parent: Party) extends Connection {

  def isValidConnection(): Boolean = getParent() match {
    case p: SocialNetwork => getChild() match {
      case c: Hacker => true
      case c: Hackerspace => true
      case c: Event => true
      case c: Project => true
      case _ => false
    }
    case _ => false
  }

}