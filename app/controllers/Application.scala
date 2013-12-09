package controllers

import play.api._
import play.api.mvc._
import models._

object Application extends Controller {

  def index = Action {
    Redirect(routes.Application.hackers)
  }

  def hackers = Action {
    Ok(views.html.hackers())
  }
  def showHacker(GUID: String) = Action {
    Ok(views.html.hackers())
  }
  def hackerspaces = Action {
    Ok(views.html.hackerspaces())
  }
  def showHackerSpace(GUID: String) = Action {
    Ok(views.html.hackerspaces())
  }
  def eventsProjects = Action {
    Ok(views.html.eventsProjects())
  }
  def showEventsProjects(GUID: String) = Action {
    Ok(views.html.eventsProjects())
  }
}