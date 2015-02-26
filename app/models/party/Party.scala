package models.party

import java.net.URI
import java.time.OffsetDateTime

// http://slick.typesafe.com/talks/2013_scaladays/2013_scaladays.pdf (Pages 40-44)
case class Party(id: Option[Long], name: String, creationDate: OffsetDateTime, logoUrl: Option[URI],
                     description: Option[String])
