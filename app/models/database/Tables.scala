package models.database

import java.net.URI
import java.sql.Date
import java.time.{ZoneOffset, LocalTime, OffsetDateTime}

import models.GPS
import models.login.{UsernameAndPassword, LoginMethod}
import models.party._
import models.connection.Connection
import play.api.db.slick.Config.driver.simple._

object Tables {

  // Party
  val party = TableQuery[Parties]
  val hacker = TableQuery[Hackers]
  val event = TableQuery[Events]
  val hackerspace = TableQuery[Hackerspaces]
  val project = TableQuery[Projects]

  // Login Method
  val loginMethod = TableQuery[LoginMethods]
  val usernameAndPassword = TableQuery[UsernamesAndPasswords]

  // Other
  val gps = TableQuery[GPSes]

  /******************************************************************
    *  Parties:
    *****************************************************************/

  class Parties(tag: Tag) extends Table[Party](tag, "PARTY") {
    implicit val offsetDateTimeToSqlTime = MappedColumnType.base[OffsetDateTime, Date](
    { dt => new Date(dt.toEpochSecond)},
    { t => OffsetDateTime.of(t.toLocalDate, LocalTime.of(0,0,0), ZoneOffset.UTC)}
    )

    implicit val uriToString = MappedColumnType.base[URI, String](_.toString, new URI(_))

    def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
    def name = column[String]("NAME")
    def creationDate = column[OffsetDateTime]("CREATION_DATE")
    def logoURI = column[URI]("LOGO_URI")
    def description = column[String]("DESCRIPTION")

    def * = (id.?, name, creationDate, logoURI.?, description.?) <> (Party.tupled, Party.unapply)
  }

  class Hackers(tag: Tag) extends Table[Hacker](tag, "HACKER") {
    implicit val offsetDateTimeToSqlTime = MappedColumnType.base[OffsetDateTime, Date](
    { dt => new Date(dt.toEpochSecond)},
    { t => OffsetDateTime.of(t.toLocalDate, LocalTime.of(0,0,0), ZoneOffset.UTC)}
    )

    def partyId = column[Long]("PARTY_ID", O.PrimaryKey)
    def loginMethodId = column[Long]("LOGIN_METHOD_ID")
    def country = column[String]("COUNTRY")
    def city = column[String]("CITY")
    def birthday = column[OffsetDateTime]("FOUNDATION_DATE")

    def * = (partyId, loginMethodId, country.?, city.?, birthday.?) <> (Hacker.tupled, Hacker.unapply)
    def partyIdFk = foreignKey("PARTY_ID_FK", partyId, party)(_.id)
    def loginMethodIdFk = foreignKey("LOGIN_METHOD_ID_FK", loginMethodId, party)(_.id)
  }

  class Events(tag: Tag) extends Table[Event](tag, "EVENT") {
    implicit val offsetDateTimeToSqlTime = MappedColumnType.base[OffsetDateTime, Date](
      { dt => new Date(dt.toEpochSecond)},
      { t => OffsetDateTime.of(t.toLocalDate, LocalTime.of(0,0,0), ZoneOffset.UTC)}
    )

    def partyId = column[Long]("PARTY_ID", O.PrimaryKey)
    def startDate = column[OffsetDateTime]("START_DATE")
    def endDate = column[OffsetDateTime]("END_DATE")
    def gpsId = column[Long]("GPS_ID")

    def * = (partyId, startDate, endDate, gpsId) <> (Event.tupled, Event.unapply)
    def partyIdFk = foreignKey("PARTY_ID_FK", partyId, party)(_.id)
    def gpsIdFk = foreignKey("GPS_ID_FK", gpsId, gps)(_.id)
  }

  class Hackerspaces(tag: Tag) extends Table[Hackerspace](tag, "HACKERSPACE") {
    implicit val offsetDateTimeToSqlTime = MappedColumnType.base[OffsetDateTime, Date](
      { dt => new Date(dt.toEpochSecond)},
      { t => OffsetDateTime.of(t.toLocalDate, LocalTime.of(0,0,0), ZoneOffset.UTC)}
    )

    def partyId = column[Long]("PARTY_ID", O.PrimaryKey)
    def country = column[String]("COUNTRY")
    def city = column[String]("CITY")
    def foundationDate = column[OffsetDateTime]("FOUNDATION_DATE")

    def * = (partyId, country, city, foundationDate.?) <> (Hackerspace.tupled, Hackerspace.unapply)
    def partyIdFk = foreignKey("PARTY_ID_FK", partyId, party)(_.id)
  }

  class Projects(tag: Tag) extends Table[Project](tag, "PROJECT") {
    implicit val offsetDateTimeToSqlTime = MappedColumnType.base[OffsetDateTime, Date](
    { dt => new Date(dt.toEpochSecond)},
    { t => OffsetDateTime.of(t.toLocalDate, LocalTime.of(0,0,0), ZoneOffset.UTC)}
    )

    def partyId = column[Long]("PARTY_ID", O.PrimaryKey)
    def startDate = column[OffsetDateTime]("START_DATE")

    def * = (partyId, startDate) <> (Project.tupled, Project.unapply)
    def partyIdFk = foreignKey("PARTY_ID_FK", partyId, party)(_.id)
  }

  /******************************************************************
   *  LOGIN METHODS:
   *****************************************************************/

  class LoginMethods(tag: Tag) extends Table[LoginMethod](tag, "LOGIN_METHOD") {
    def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
    def email = column[String]("EMAIL")

    def * = (id.?, email) <> (LoginMethod.tupled, LoginMethod.unapply)
  }

  class UsernamesAndPasswords(tag: Tag) extends Table[UsernameAndPassword](tag, "USERNAME_PASSWORD") {
    def loginMethodId = column[Long]("LOGIN_METHOD_ID", O.PrimaryKey)
    def username = column[String]("USERNAME")
    def password = column[String]("PASSWORD")
    def salt = column[String]("SALT")

    def * = (loginMethodId, username, password, salt) <> (UsernameAndPassword.tupled, UsernameAndPassword.unapply)
    def loginMethodIdFk = foreignKey("LOGIN_METHOD_ID_FK", loginMethodId, loginMethod)(_.id)
  }

  /******************************************************************
    *  OTHER:
    *****************************************************************/
  class Connections(tag: Tag) extends Table[Connection](tag, "CONNECTION") {
    implicit val offsetDateTimeToSqlTime = MappedColumnType.base[OffsetDateTime, Date](
    { dt => new Date(dt.toEpochSecond)},
    { t => OffsetDateTime.of(t.toLocalDate, LocalTime.of(0,0,0), ZoneOffset.UTC)}
    )

    def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
    def childId = column[Long]("CHILD_ID")
    def parentId = column[Long]("PARENT_ID")
    def creationDate = column[OffsetDateTime]("CREATION_DATE")
    def endDate = column[OffsetDateTime]("END_DATE")
    def discriminator = column[String]("DISCRIMINATOR")

    def * = (id.?, childId, parentId, creationDate, endDate.?, discriminator) <> (Connection.tupled, Connection.unapply)
    def childIdFk = foreignKey("CHILD_ID_FK", childId, party)(_.id)
    def parentIdFk = foreignKey("PARENT_ID_FK", parentId, party)(_.id)
  }

  class GPSes(tag: Tag) extends Table[GPS](tag, "GPS") {
    def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
    def name = column[String]("NAME")
    def latitude = column[String]("LATITUDE")
    def longitude = column[String]("LONGITUDE")

    def * = (id.?, name, latitude, longitude) <> (GPS.tupled, GPS.unapply)
  }
}
