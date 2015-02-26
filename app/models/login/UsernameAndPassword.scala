package models.login

case class UsernameAndPassword(loginMethodId: Long, username: String, password: String, hash: String)
  extends WithLoginMethod
