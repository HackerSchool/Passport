package models

case class PassportInstance(id: Option[Long], url: String, apiUrl: String) {

  def parties: Set[Party] = ???
}
