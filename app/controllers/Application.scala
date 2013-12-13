package controllers

import play.api._
import play.api.mvc._
//import models._
import views._
import play.api.data._
import play.api.data.Forms._
import java.util.Date

object Application extends Controller {

  def index = Action {
    Redirect(routes.Application.hackers)
  }

  //Register
  def registerHackerspace = Action {
    Ok(html.register.hackerspace())
  }
  def newHackerspace = Action {
    Ok
  }
  
  case class Hacker(
      username: String,
      name: String,
      password: String,
      email: String,
      birthday: Date,
      sex: String
  )
  
  val hackerForm: Form[Hacker] = Form(
	mapping(
	  "username" -> nonEmptyText,
	  "name" -> nonEmptyText,
	  "password" -> tuple(
        "main" -> text(minLength = 6),
        "confirm" -> text
      ).verifying("Passwords don't match", passwords => passwords._1 == passwords._2),
	  "email" -> email,
	  "birthday" -> date,
	  "sex" -> text
    ){
      // Binding: Create a Hacker from the mapping result (ignore the second password)
      (username, name, passwords, email, birthday, sex) => Hacker(username, name, passwords._1, email, birthday, sex) 
    }{
      // Unbinding: Create the mapping values from an existing Hacker value
      Hacker => Some(Hacker.username, Hacker.name, (Hacker.password, Hacker.password), Hacker.email, Hacker.birthday, Hacker.sex)
    }
  )
  
  def registerHacker = Action {
    Ok(html.register.hacker(hackerForm))
  }
  def newHacker = Action {implicit request =>
    hackerForm.bindFromRequest.fold(
      // Form has errors, redisplay it
      errors => BadRequest(html.register.hacker(errors)),
      
      // We got a valid Hacker value, display the summary
      hacker => Ok(html.register.index())
    )
  }
  def register = Action {
    Ok(html.register.index())
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