package controllers

import play.api._
import play.api.mvc._
import models._
import play.api.data._
import play.api.data.Forms._
import java.util.Date

object Application extends Controller {

  def index = Action {
    Redirect(routes.Application.hackers)
  }

  //Register
  def registerHackerspace = Action {
    Ok(views.html.registerHackerspace())
  }
  def newHackerspace = Action {
    Ok
  }
  
  case class HackerData(username: String,
      name: String,
      password: String,
      confirmPassword: String,
      email: String,
      birthday: Date,
      sex: String)
  
  val hackerForm = Form(
	mapping(
	  "username" -> text,
	  "name" -> text,
	  "password" -> text,
	  "confirmPassword" -> text,
	  "email" -> email,
	  "birthday" -> date,
	  "sex" -> text
    )(HackerData.apply)(HackerData.unapply)
  )
  
  def registerHacker = Action {
    Ok(views.html.registerHacker())
  }
  def newHacker = Action {
    Ok
  }
  def register = Action {
    Ok(views.html.register())
  }
  
  //Hackers
  def showHacker(GUID: String) = Action {
	Ok
  }
  def hackers = Action {
    Ok(views.html.hackers())
  }
  def searchHackers = Action {
    Ok
  }
  
  //Hackerspaces
  def showHackerSpace(GUID: String) = Action {
	Ok
  }
  def hackerspaces = Action {
    Ok(views.html.hackerspaces())
  }
  def searchHackerspaces = Action {
    Ok
  }
  
  //EventsProjects
  def showEventsProjects(GUID: String) = Action {
	Ok
  }
  def eventsProjects = Action {
    Ok(views.html.eventsProjects())
  }
  def searchEventsProjects = Action {
    Ok
  }
}