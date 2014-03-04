package controllers

import java.util.Date
import exception.HackerspaceAlreadyExistsException
import scala.collection.JavaConversions._
import models.Hackerspace
import play.api._
import play.api.data._
import play.api.data.Forms._
import play.api.data.validation._
import play.api.data.validation.Constraints._
import play.api.mvc._
import views._
import pt.ist.fenixframework.FenixFramework
import util.FenixFrameworkUtil._
import play.Logger
import exception.HackerspaceAlreadyExistsException

object Register extends Controller {
  //Index
  def registerIndex = Action {
    Ok(html.register.index())
  }
  
  /********************************************************************************\
   *         _   _            _                                                   *
   *        | | | | __ _  ___| | _____ _ __ ___ _ __   __ _  ___ ___  ___         *
   *        | |_| |/ _` |/ __| |/ / _ \ '__/ __| '_ \ / _` |/ __/ _ \/ __|        *
   *        |  _  | (_| | (__|   <  __/ |  \__ \ |_) | (_| | (_|  __/\__ \        *
   *        |_| |_|\__,_|\___|_|\_\___|_|  |___/ .__/ \__,_|\___\___||___/        *
   *                                           |_|                                *
   *                                                                              *
  \********************************************************************************/
  case class HackerspaceDTO(
      name: String/*,
      password: String,
      email: String,
      location: String,
      GPS: String,
      dateFounded: Date*/
  )
  
  val hackerspaceForm: Form[HackerspaceDTO] = Form(
	mapping(
	  //"username" -> nonEmptyText,
	  "name" -> nonEmptyText/*,
	  "password" -> tuple(
        "main" -> nonEmptyText(minLength = 6),
        "confirm" -> nonEmptyText(minLength = 6)
      ).verifying("Passwords don't match", passwords => passwords._1 == passwords._2),
	  "email" -> email.verifying(nonEmpty),
	  "location" -> nonEmptyText,
	  "GPS" -> nonEmptyText,
	  "dateFounded" -> date("dd-mm-yyyy")*/
    )(HackerspaceDTO.apply)(HackerspaceDTO.unapply)/*{
      // Binding: Create a Hacker from the mapping result (ignore the second password)
      (username, name, passwords, email, location, GPS, dateFounded) => HackerspaceDTO(username, name, passwords._1, email, location, GPS, dateFounded) 
    }{
      // Unbinding: Create the mapping values from an existing Hacker value
      hackerspaceDTO => Some(hackerspaceDTO.username, hackerspaceDTO.name, (hackerspaceDTO.password, hackerspaceDTO.password), hackerspaceDTO.email, hackerspaceDTO.location, hackerspaceDTO.GPS, hackerspaceDTO.dateFounded)
    }*/
  )
  
  def hackerspace = Action {
    Ok(html.register.hackerspace(hackerspaceForm))
  }
  
  def isValidHackerspaceName(name: String) = Action {
    Ok(Hackerspace.isValidName(name).toString)    
  }
  
  def newHackerspace = Action { implicit request ⇒
    val form = hackerspaceForm.bindFromRequest
    form.fold(
      errors ⇒ BadRequest(html.register.hackerspace(errors)),
      hackerspaceDTO ⇒ atomic[HackerspaceAlreadyExistsException, Hackerspace] {
        new Hackerspace(hackerspaceDTO.name)
      } match {
        case Left(ex) ⇒ ex match {
          case al: HackerspaceAlreadyExistsException ⇒ {
            val formWithError = form.withError("name", "hackerspaceNameAlreadyTaken", form("name").value.getOrElse(""))
            BadRequest(html.register.hackerspace(formWithError))
          }
          case _ ⇒ Ok("An error occured: " + ex.getMessage)
        }
        case Right(hackerspace) ⇒ Ok(html.register.index())
      })
  }
  
  /************************************************\
   *        _   _            _                    *
   *       | | | | __ _  ___| | _____ _ __        *
   *       | |_| |/ _` |/ __| |/ / _ \ '__|       *
   *       |  _  | (_| | (__|   <  __/ |          *
   *       |_| |_|\__,_|\___|_|\_\___|_|          *
   *                                              *  
  \************************************************/
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