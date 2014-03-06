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
import scala.collection.JavaConversions._
import util.FenixFrameworkUtil
import models.SocialNetwork
import pt.ist.fenixframework.FenixFramework
import models.Party
import models.Party
import util.FenixFrameworkUtil._

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
    val socialNwk = FenixFramework.getDomainRoot().getSocialNetworksSet()
    Logger.debug("ASDSGGNHg")
    Logger.debug("orig siz: " + FenixFramework.getDomainRoot().getSocialNetworksSet().size())
    Logger.debug("qrlqlknrengkrg")
    val socialNwkList: List[SocialNetworkDTO] = (for (
      s <- socialNwk.filter(p => p.isInstanceOf[SocialNetwork])
    ) yield new SocialNetworkDTO(s.asInstanceOf[SocialNetwork]))(collection.breakOut)
    // Logger.debug("Size of : " + socialNwkList.size)
    Ok(html.socialNetwork.socialNetworks(socialNwkList))
  }

  def createForm = Action {
    Ok(html.socialNetwork.socialNetworkForm(socialNetworkForm.fill(new SocialNetworkDTO("", "", 0))))
  }

  def editForm(id: Long) = Action {
    val socialNwk = atomic[SocialNetwork] {
      val root = FenixFramework.getDomainRoot()
      Logger.debug(root.toString())
      val socNwks = root.getSocialNetworksSet()
      val filteredSocNwks = socNwks.filter(_.getOid() == id)
      Logger.debug("farties " + filteredSocNwks.toString)
      Logger.debug("farties size: " + filteredSocNwks.size())
      if (filteredSocNwks.size() != 1) {
        throw new Exception()
      }
      filteredSocNwks.head
    }
    Logger.debug("RESULTADO: " + socialNwk.toString())

    Ok(html.socialNetwork.socialNetworkForm(socialNetworkForm.fill(new SocialNetworkDTO(socialNwk))))
  }

  def create = Action { implicit request =>
    socialNetworkForm.bindFromRequest.fold(
      errors => BadRequest(html.socialNetwork.socialNetworkForm(errors)),
      socialNetworkDTO => {
        val id = atomic[Long] {
          val socialNet = new SocialNetwork(socialNetworkDTO.fullname, socialNetworkDTO.url)
          Long2long(socialNet.getOid())
        }
        val newSocNwkDTO = SocialNetworkDTO(socialNetworkDTO.fullname, socialNetworkDTO.url, id)
        Ok(html.socialNetwork.socialNetwork(newSocNwkDTO))
      })
  }

  def show(id: Long) = Action { implicit request =>

    val socialNwk = atomic[SocialNetwork] { FenixFramework.getDomainRoot().getSocialNetworksSet().filter(_.getOid() == id).head }
    Logger.debug("RESULTADO: " + socialNwk.toString())
    val socNwkDTO = new SocialNetworkDTO(socialNwk)

    Ok(html.socialNetwork.socialNetwork(socNwkDTO))
  }

}