package models.connection;

import models.exception.InvalidConnectionException
import org.joda.time.DateTime

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
