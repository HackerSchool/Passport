package models.connection

import models.{Event, Hackerspace, Hacker}

trait Guess extends Connection {
  def id: Option[Long]
  def _child: Long
  def _parent: Long

  def isValidConnection: Boolean = child match {
    case c: Hacker => parent match {
      case p: Hackerspace => true
      case p: Event => true
      case _ => false
    }
    case _ => false
  }
}
