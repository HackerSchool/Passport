package models.party

import java.time.OffsetDateTime

case class Hacker(partyId: Long, loginMethodId: Long, country: Option[String], city: Option[String],
                  birthday: Option[OffsetDateTime]) extends WithParty
