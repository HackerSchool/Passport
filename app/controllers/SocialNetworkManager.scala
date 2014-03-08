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
import pt.ist.fenixframework.FenixFramework
import models.Party
import models.Party
import util.FenixFrameworkUtil._
import exception.DomainException

object SocialNetworkManager extends Controller {

  //Social Network
  case class SocialNetworkDTO(
    fullname: String,
    url: String,
    id: Long) {
    def this(nwk: SocialNetwork) =
      this(nwk.getName(), nwk.getRri(), nwk.getOid())

  }

  val socialNetworkForm: Form[SocialNetworkDTO] = Form(
    mapping(
      "fullname" -> nonEmptyText,
      "url" -> nonEmptyText,
      "id" -> of[Long])(SocialNetworkDTO.apply)(SocialNetworkDTO.unapply))

  def showAll = Action {
    val socialNwkList = atomic[List[SocialNetworkDTO]]{
      (for (s <- SocialNetwork.getAll())
    	yield new SocialNetworkDTO(s.asInstanceOf[SocialNetwork]))(collection.breakOut)
    }
    Ok(html.socialNetwork.socialNetworks(socialNwkList))
  }

  def createForm = Action {
    Ok(html.socialNetwork.socialNetworkForm(socialNetworkForm.fill(new SocialNetworkDTO("", "", 0))))
  }

  def editForm(name: String) = Action {
    var dto = atomic[SocialNetworkDTO] {
      val opt = SocialNetwork.getAllByName(name).headOption
      if(opt.isDefined)
    	  new SocialNetworkDTO(opt.get)
      else
        throw new DomainException("error")
    }
    Ok(html.socialNetwork.socialNetworkForm(socialNetworkForm.fill(dto)))

  }

  def create = Action { implicit request =>
    socialNetworkForm.bindFromRequest.fold(
      errors => BadRequest(html.socialNetwork.socialNetworkForm(errors)),
      socialNetworkDTO => atomic[Result] {
        val socialNet = new SocialNetwork(socialNetworkDTO.fullname, socialNetworkDTO.url)
        val newSocNwkDTO = SocialNetworkDTO(socialNetworkDTO.fullname, socialNetworkDTO.url, socialNet.getOid())
        Ok(html.socialNetwork.socialNetwork(newSocNwkDTO))
      })
  }

  def show(id: Long) = Action { implicit request =>
    atomic[Result] {
      val socialNwkDTO = new SocialNetworkDTO(SocialNetwork.getByOid(id).head)
      Ok(html.socialNetwork.socialNetwork(socialNwkDTO))
    }
  }

}