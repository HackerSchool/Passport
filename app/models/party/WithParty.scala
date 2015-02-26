package models.party

import models.database.Tables

import play.api.db.slick._
import play.api.db.slick.Config.driver.simple._
import play.api.Play.current

abstract class WithParty {
  def partyId: Long

  private lazy val party = DB.withSession {
    implicit session => Tables.party.filter(_.id === partyId).first
  }

  // Methods to get [[Party]] attributes
  def name = party.name
  def creationDate = party.creationDate
  def logoUrl = party.logoUrl
  def description = party.description
}
