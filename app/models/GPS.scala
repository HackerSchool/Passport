package models

case class GPS(id: Option[Long], name: String, latitude: String = "0.0", longitude: String = "0.0")
