package models.connection

object ConnectionState extends Enumeration {
  type ConnectionState = Value
  val NotInitialized, Created, Verified = Value
}
