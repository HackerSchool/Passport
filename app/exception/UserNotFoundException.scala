package exception

case class UserNotFoundException(username: String, context: String) extends PassportException(context) {

  override def getMessage: String = {
    super.getMessage + username + " not found."
  }
}
