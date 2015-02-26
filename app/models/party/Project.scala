package models.party

import java.time.OffsetDateTime

case class Project(partyId: Long, start_date: OffsetDateTime) extends WithParty
