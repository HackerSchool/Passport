package controllers

import play.api.data.format.Formats._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.party.Event

object EventManager extends Controller {
  case class EventDTO(name: String, id: Long) {
    def this(event: Event) =
      this(event.name, event.partyId)
  }

  val eventForm: Form[EventDTO] = Form(
    mapping(
      "Event Name" -> nonEmptyText,
      "id" -> of[Long]
    )(EventDTO.apply)(EventDTO.unapply))

  def showAll = TODO

  def createForm = TODO

  def editForm(id: Long) = TODO

  def create = TODO

  def show(id: Long) = TODO
}