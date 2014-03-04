package exception

case class PassportException(val context: String, var description: String = "") extends RuntimeException {

  override def getMessage: String = {
    "[" + context + "]: " + description
  }
}
