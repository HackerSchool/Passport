package controllers

import play.api.data.format.Formats._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import views._
import models.SocialNetwork

object SocialNetworkManager extends Controller {

  //Social Network
  case class SocialNetworkDTO(fullname: String, url: String, id: Long) {
    def this(network: SocialNetwork) =
      this(network.name, network.rri, network.id.get)
  }

  val socialNetworkForm: Form[SocialNetworkDTO] = Form(
    mapping(
      "Social Network Name" -> nonEmptyText,
      "Public URL" -> nonEmptyText,
      "id" -> of[Long])(SocialNetworkDTO.apply)(SocialNetworkDTO.unapply))

  def showAll = TODO

  def createForm = Action {
    Ok(html.socialNetwork.socialNetworkForm(socialNetworkForm.fill(new SocialNetworkDTO("", "", 0))))
  }

  def editForm(id: Long) = TODO

  def create = TODO

  def show(id: Long) = TODO

}