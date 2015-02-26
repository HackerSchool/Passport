package models.party

import java.time.OffsetDateTime

case class Event(partyId: Long, start_date: OffsetDateTime, end_date: OffsetDateTime, gpsId: Long) extends WithParty
