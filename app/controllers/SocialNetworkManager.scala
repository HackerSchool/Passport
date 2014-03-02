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

object SocialNetworkManager extends Controller {

  //Social Network
  case class SocialNetworkDTO(
    fullname: String,
    url: String,
    id: Long){
    def this(nwk : SocialNetwork) = 
      this(nwk.getFullname,nwk.getRri,nwk.getOid)
      
  }
    

  val socialNetworkForm: Form[SocialNetworkDTO] = Form(
    mapping(
      "fullname" -> nonEmptyText,
      "url" -> nonEmptyText,
      "id" -> of[Long]
      )(SocialNetworkDTO.apply)(SocialNetworkDTO.unapply))

  def createForm = Action {
    Ok(html.socialNetwork.socialNetworkForm(socialNetworkForm))
  }

  def editForm(id: Long) = Action {
    val socialNwk = FenixFrameworkUtil.withTransaction[SocialNetwork] {
    	val parties = asScalaSet(FenixFramework.getDomainRoot().getParties())
    	Logger.debug("Searching for id " + id + " in " + parties.size.toString() + " parties")
//    	if(parties.size != 1){
//    	  throw new Exception
//        }
    	parties.head.asInstanceOf[SocialNetwork]
    }
    Ok(html.socialNetwork.socialNetworkForm(socialNetworkForm.fill(new SocialNetworkDTO(socialNwk))))
  }

  def create = Action { implicit request =>
    socialNetworkForm.bindFromRequest.fold(
      errors => BadRequest(html.socialNetwork.socialNetworkForm(errors)),
      socialNetworkDTO => {
        import util.FenixFrameworkUtil
        val id = FenixFrameworkUtil.withTransaction[Long] {
          val socialNet = new SocialNetwork(socialNetworkDTO.fullname, socialNetworkDTO.url)
          Long2long(socialNet.getOid())
        }
        val newSocNwkDTO = SocialNetworkDTO(socialNetworkDTO.fullname,socialNetworkDTO.url, id)
        Ok(html.socialNetwork.socialNetwork(newSocNwkDTO))
      })
  }

}