package controllers

import play.api._
import play.api.data._
import play.api.data.Forms._
import play.api.data.validation.Constraints._
import play.api.mvc._
import views._

object Login extends Controller {
  
  case class UserDTO(username: String, password: String)
  
  val loginForm: Form[UserDTO] = Form(
  	mapping (
  		"username" -> nonEmptyText,
  		"password" -> nonEmptyText(minLength = 6)
  	) (UserDTO.apply)(UserDTO.unapply)
  )

  def index() = Action  {
	Ok(html.login.index(loginForm))
  }
  
  def submit = Action {implicit request => 
	loginForm.bindFromRequest.fold(
		errors => BadRequest(html.login.index(errors)),
		UserDTO => Redirect(routes.Application.index)
	)
  }
}