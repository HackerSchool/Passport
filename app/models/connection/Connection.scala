package models.connection;

import org.joda.time.DateTime
import exception.InvalidConnectionException
import pt.ist.fenixframework.FenixFramework

abstract class Connection extends Connection_Base {
  
  setRoot(FenixFramework.getDomainRoot())
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
