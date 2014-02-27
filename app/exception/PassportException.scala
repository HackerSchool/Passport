package exception

class PassportException(context: String, description: String = "") extends RuntimeException {

  override def getMessage: String = {
    "[" + context + "]: " + description
  }
}
