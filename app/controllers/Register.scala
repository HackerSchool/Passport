package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.validation._
import play.api.data.validation.Constraints._
import play.api.data.Forms._
//import models._
import views._

import java.util.Date

object Register extends Controller {
  //Index
  def registerIndex = Action {
    Ok(html.register.index())
  }
  
  //Hackerspace
  case class HackerspaceDTO(
      username: String,
      name: String,
      password: String,
      email: String,
      location: String,
      GPS: String,
      dateFounded: Date
  )
  
  val hackerspaceForm: Form[HackerspaceDTO] = Form(
	mapping(
	  "username" -> nonEmptyText,
	  "name" -> nonEmptyText,
	  "password" -> tuple(
        "main" -> nonEmptyText(minLength = 6),
        "confirm" -> nonEmptyText(minLength = 6)
      ).verifying("Passwords don't match", passwords => passwords._1 == passwords._2),
	  "email" -> email.verifying(nonEmpty),
	  "location" -> nonEmptyText,
	  "GPS" -> nonEmptyText,
	  "dateFounded" -> date("dd-mm-yyyy")
    ){
      // Binding: Create a Hacker from the mapping result (ignore the second password)
      (username, name, passwords, email, location, GPS, dateFounded) => HackerspaceDTO(username, name, passwords._1, email, location, GPS, dateFounded) 
    }{
      // Unbinding: Create the mapping values from an existing Hacker value
      hackerspaceDTO => Some(hackerspaceDTO.username, hackerspaceDTO.name, (hackerspaceDTO.password, hackerspaceDTO.password), hackerspaceDTO.email, hackerspaceDTO.location, hackerspaceDTO.GPS, hackerspaceDTO.dateFounded)
    }
  )
  
  def hackerspace = Action {
    Ok(html.register.hackerspace(hackerspaceForm))
  }
  def newHackerspace = Action {implicit request =>
    hackerspaceForm.bindFromRequest.fold(
      // Form has errors, redisplay it
      errors => BadRequest(html.register.hackerspace(errors)),
      
      // We got a valid Hacker value, display the summary
      hackerspaceDTO => Ok(html.register.index())
    )
  }
  
  //Hacker
  case class HackerDTO(
      username: String,
      name: String,
      password: String,
      email: String,
      birthday: Date,
      sex: String
  )
  
  val hackerForm: Form[HackerDTO] = Form(
	mapping(
	  "username" -> nonEmptyText,
	  "name" -> nonEmptyText,
	  "password" -> tuple(
        "main" -> nonEmptyText(minLength = 6),
        "confirm" -> nonEmptyText(minLength = 6)
      ).verifying("Passwords don't match", passwords => passwords._1 == passwords._2),
	  "email" -> email.verifying(nonEmpty),
	  "birthday" -> date("dd-mm-yyyy"),
	  "sex" -> nonEmptyText
    ){
      // Binding: Create a Hacker from the mapping result (ignore the second password)
      (username, name, passwords, email, birthday, sex) => HackerDTO(username, name, passwords._1, email, birthday, sex) 
    }{
      // Unbinding: Create the mapping values from an existing Hacker value
      hackerDTO => Some(hackerDTO.username, hackerDTO.name, (hackerDTO.password, hackerDTO.password), hackerDTO.email, hackerDTO.birthday, hackerDTO.sex)
    }
  )
  
  def hacker = Action {
    Ok(html.register.hacker(hackerForm))
  }
  def newHacker = Action {implicit request =>
    hackerForm.bindFromRequest.fold(
      // Form has errors, redisplay it
      errors => BadRequest(html.register.hacker(errors)),
      
      // We got a valid Hacker value, display the summary
      hackerDTO => Ok(html.register.index())
    )
  }
}