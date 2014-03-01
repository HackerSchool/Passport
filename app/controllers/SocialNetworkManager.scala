package controllers

import play.api._
import play.api.data.format.Formats._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import views._
import java.util.Date
import java.util.UUID
import scala.util.Random
import util.FenixFrameworkUtil
import models.SocialNetwork

object SocialNetworkManager extends Controller {

  //Social Network
  case class SocialNetworkDTO(
    fullname: String,
    url: String
    //id: Long)
    )

  val socialNetworkForm: Form[SocialNetworkDTO] = Form(
    mapping(
      "fullname" -> nonEmptyText,
      "url" -> nonEmptyText
      //"id" -> of[Long]
      )(SocialNetworkDTO.apply)(SocialNetworkDTO.unapply))

  def createForm = Action {
    Ok(html.socialNetwork.socialNetworkForm(socialNetworkForm))
  }

  def editForm = Action {
    Ok(html.socialNetwork.socialNetworkForm(socialNetworkForm.fill(SocialNetworkDTO("YAYAYA", "YAYYA"))))
  }

  def create = Action { implicit request =>
    socialNetworkForm.bindFromRequest.fold(
      errors => BadRequest(html.socialNetwork.socialNetworkForm(errors)),
      socialNetworkDTO => {
        import util.FenixFrameworkUtil
        val id = FenixFrameworkUtil.withTransaction[Long] {
          val socialNet = new SocialNetwork(socialNetworkDTO.fullname, socialNetworkDTO.url)
         // Long2long(socialNet.getOid())
          0
        }
//        val newSocNwkDTO = SocialNetworkDTO(socialNetworkDTO.fullname,socialNetworkDTO.url, id)
        Ok(html.socialNetwork.socialNetwork(socialNetworkDTO))
      })
  }

}