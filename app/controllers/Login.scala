package controllers

import play.api.data._
import play.api.data.Forms._
import play.api.mvc._
import views._
import services.dto._

object Login extends Controller {
  
  val loginForm: Form[UserLoginDTO] = Form(
  	mapping (
  		"username" -> nonEmptyText,
  		"password" -> nonEmptyText(minLength = 6)
  	) (UserLoginDTO.apply)(UserLoginDTO.unapply)
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