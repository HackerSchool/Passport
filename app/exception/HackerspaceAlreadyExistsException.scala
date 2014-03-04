package exception

case class HackerspaceAlreadyExistsException(context: String) extends PassportException(context)