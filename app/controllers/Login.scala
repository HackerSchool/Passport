package controllers

import java.util.concurrent.Callable

import scala.collection.JavaConversions._

import models.User
import views._
import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.data.Forms.nonEmptyText
import play.api.mvc.Action
import play.api.mvc.Controller
import play.api.mvc.Result
import pt.ist.fenixframework.DomainRoot
import pt.ist.fenixframework.FenixFramework

object Login extends Controller {

  case class UserLoginDTO(val username: String, val password: String) extends Serializable
  val loginForm: Form[UserLoginDTO] = Form(
    mapping(
      "username" -> nonEmptyText,
      "password" -> nonEmptyText(minLength = 6))(UserLoginDTO.apply)(UserLoginDTO.unapply))

  def index() = Action {
    FenixFramework.getTransactionManager.withTransaction(new Callable[Unit]() {
      override def call(): Unit = {
        new User("magicknot", "david.duarte@tecnico.ulisboa.pt", "123456")
      }
    })
    Ok(html.login.index(loginForm))
  }

  def submit = Action { implicit request =>
    loginForm.bindFromRequest.fold(
      errors => BadRequest(html.login.index(errors)),
      userDTO => {
        FenixFramework.getTransactionManager().withTransaction(new Callable[Result]() {
          override def call(): Result = {
            val root: DomainRoot = FenixFramework.getDomainRoot()
            val userOption = root.getUsersSet.filter(_.getUsername == userDTO.username).headOption
            var result: Boolean = false
            if (userOption.isDefined) {
              val user: User = userOption.get
              result = user.authenticate(userDTO.password)
              if (result) {
                Redirect(routes.Application.index)
              } else {
                Ok(html.login.index(loginForm, "Password incorrect"))
              }
            } else {
              Ok(html.login.index(loginForm, "User not found"))
            }
          }
        })
      })
  }
}