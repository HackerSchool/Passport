package controllers

import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.data.Forms.nonEmptyText
import play.api.mvc.Controller

object Login extends Controller {

  case class UserLoginDTO(username: String, password: String) extends Serializable

  val loginForm: Form[UserLoginDTO] = Form(
    mapping(
      "username" -> nonEmptyText,
      "password" -> nonEmptyText(minLength = 6))(UserLoginDTO.apply)(UserLoginDTO.unapply))

  def index = TODO

  def submit = TODO
}