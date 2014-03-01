package exception

class UserNotFoundException(username: String, context: String) extends PassportException(context, "User " + username +  " not found") {
}
