package exception

class DomainException(context: String) extends PassportException(context){
  
  override def getMessage: String = context
}