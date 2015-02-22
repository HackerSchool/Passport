package models

import models.core.security.PasswordEncryption

case class User(id: Option[Long], private val hackerId: Long, username: String, email: String,
                private val password: String, private val salt: String = PasswordEncryption.generateSalt()) {

  def hacker: Hacker = ???

  def password_=(password: String): User = {
    this.copy(password = PasswordEncryption.getEncryptedPassword(password, salt))
  }

  def authenticate(password: String): Boolean = {
    PasswordEncryption.authenticate(password, password, salt)
  }
}
