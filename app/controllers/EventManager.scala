package controllers

import play.api._
import play.api.data.format.Formats._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import views._
import java.util.Date
import java.util.UUID
import scala.util.Random
import util.FenixFrameworkUtil
import models.Event
import pt.ist.fenixframework.FenixFramework
import models.Party
import models.Party
import util.FenixFrameworkUtil._
import exception.DomainException

object EventManager extends Controller {

  case class EventDTO(
    name: String,
    url: String,
    id: Long) {
    def this(nwk: Event) =
      this(nwk.getName(), nwk.getRri(), nwk.getOid())

  }


  val eventForm: Form[EventDTO] = Form(
    mapping(
      "Event Name" -> nonEmptyText,
      "Public URL" -> nonEmptyText,
      "id" -> of[Long])(EventDTO.apply)(EventDTO.unapply))

  def showAll = Action {
    val eventList = atomic[List[EventDTO]] {
      (for (s <- Event.getAll())
        yield new EventDTO(s.asInstanceOf[Event]))(collection.breakOut)
    }
    Ok(html.event.events(eventList))
  }

  def createForm = Action {
    Ok(html.event.eventForm(eventForm.fill(new EventDTO("", "", 0))))
  }

  def editForm(id: Long) = Action {
    var dto = atomic[EventDTO] {
    	new EventDTO(Event.getByOid(id))
      }
    Ok(html.event.eventForm(eventForm.fill(dto)))
  }

  def create = Action { implicit request =>
    eventForm.bindFromRequest.fold(
      errors => {
        Logger.debug("Erros"+errors)
        BadRequest(html.event.eventForm(errors))},
      eventDTO => {val id = atomic[Long] {
        Logger.debug("WRITING")
        val event = new Event(eventDTO.name, eventDTO.url)
        Logger.debug("OID")
        event.getOid()
      }
      Logger.debug("OK")
      Redirect("/event/"+id)
      })
  }

  def show(id: Long) = Action { implicit request =>
    atomic[Result] {
      val eventDTO = new EventDTO(Event.getByOid(id))
      Ok(html.event.event(eventDTO))
    }
  }

}