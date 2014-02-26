package models

import models.core.security.PasswordEncryption

class User(var username: String, var registryEmail: String, val password: String) extends User_Base {

  this.setUsername(username)
  this.setRegistryEmail(registryEmail)
  this.password_=(password)
  this.salt()
  
  def password_=(password: String) = {
    setPassword(PasswordEncryption.getEncryptedPassword(password, getSalt()))
  }
  
  def salt() = {
    if (getSalt().isEmpty) {
      setSalt(PasswordEncryption.generateSalt())
    }
  }

  override protected def setPassword(password: Array[Byte]): Unit = {
    super.setPassword(password)
  }
  
  override protected def setSalt(salt: Array[Byte]): Unit = {
    super.setSalt(salt)
  }
  
  def authenticate(password: String): Boolean = {
    PasswordEncryption.authenticate(password, getPassword(), getSalt());
  }
}
