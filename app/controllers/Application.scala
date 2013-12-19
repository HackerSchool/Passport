package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
//import models._
import views._

import java.util.Date

object Application extends Controller {

  def index = Action {
    Ok(html.index())
  }
  
  //Hackers
  def showHacker(GUID: String) = Action {
	Ok
  }
  def hackers = Action {
    Ok(html.hackers())
  }
  def searchHackers = Action {
    Ok
  }
  
  //Hackerspaces
  def showHackerSpace(GUID: String) = Action {
	Ok
  }
  def hackerspaces = Action {
    Ok(html.hackerspaces())
  }
  def searchHackerspaces = Action {
    Ok
  }
  
  //EventsProjects
  def showEventsProjects(GUID: String) = Action {
	Ok
  }
  def eventsProjects = Action {
    Ok(html.eventsProjects())
  }
  def searchEventsProjects = Action {
    Ok
  }
}