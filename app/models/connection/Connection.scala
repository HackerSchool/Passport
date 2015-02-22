package models.connection

import java.time.OffsetDateTime

import models.Party

trait Connection {
  var creationDate: OffsetDateTime = OffsetDateTime.now()
  var state = ConnectionState.NotInitialized

  def isValidConnection: Boolean

  def init() = if (isValidConnection) {
    creationDate = OffsetDateTime.now()
    state = ConnectionState.Created
  }

  def parent: Set[Party] = ???
  def child: Party = ???

  def verify(): Boolean = {
    state = ConnectionState.Verified
    true
  }
}
