package models.connection;

import org.joda.time.DateTime
import exception.InvalidConnectionException

abstract class Connection extends Connection_Base {
  
  setCreationDate(new DateTime())
  setState(ConnectionState.UNINIALIZED)
 
  def init = {
	  if (!isValidConnection)
	  	throw new InvalidConnectionException("Cannont create connection")
	  setStartDate(new DateTime())
	  setState(ConnectionState.CREATED)
  }

  def isValidConnection : Boolean
  def verify : Boolean = {
    setState(ConnectionState.VERIFIED)
    return true
  }
}
