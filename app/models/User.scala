package models

import models.core.security.PasswordEncryption
import pt.ist.fenixframework.FenixFramework

class User(private val _username: String, private val _registryEmail: String, private val _password: String) extends User_Base {
  this.setRoot(FenixFramework.getDomainRoot)
  this.setUsername(_username)
  this.setRegistryEmail(_registryEmail)
  this.setSalt(generateSalt)
  this.setPassword(_password)
  
  override def setPassword(password: String): Unit = {
    super.setPassword(PasswordEncryption.getEncryptedPassword(_password, getSalt()))
  }
  
  private def generateSalt() : String = {
    PasswordEncryption.generateSalt()
  }
  
  override protected def setSalt(salt: String): Unit = {
    if (getSalt() == null) {
      super.setSalt(salt)
    }
  }
  
  def authenticate(password: String): Boolean = {
    PasswordEncryption.authenticate(password, getPassword(), getSalt());
  }
}
