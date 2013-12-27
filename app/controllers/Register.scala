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
  def hackerspace = Action {
    Ok(html.register.hackerspace())
  }
  def newHackerspace = Action {
    Ok
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