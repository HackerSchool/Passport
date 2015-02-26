package models.party

import java.time.OffsetDateTime

case class Hackerspace(partyId: Long, country: String, city: String, foudationDate: Option[OffsetDateTime])
  extends WithParty
