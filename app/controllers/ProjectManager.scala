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
import models.Project
import pt.ist.fenixframework.FenixFramework
import models.Party
import models.Party
import util.FenixFrameworkUtil._
import exception.DomainException

object ProjectManager extends Controller {

  case class ProjectDTO(
    name: String,
    url: String,
    id: Long) {
    def this(nwk: Project) =
      this(nwk.getName(), nwk.getRri(), nwk.getOid())

  }

  val projectForm: Form[ProjectDTO] = Form(
    mapping(
      "Project Name" -> nonEmptyText,
      "Public URL" -> nonEmptyText,
      "id" -> of[Long])(ProjectDTO.apply)(ProjectDTO.unapply))

  def showAll = Action {
    val projectList = atomic[List[ProjectDTO]] {
      (for (s <- Project.getAll())
        yield new ProjectDTO(s.asInstanceOf[Project]))(collection.breakOut)
    }
    Ok(html.project.projects(projectList))
  }

  def createForm = Action {
    Ok(html.project.projectForm(projectForm.fill(new ProjectDTO("", "", 0))))
  }

  def editForm(id: Long) = Action {
    var dto = atomic[ProjectDTO] {
    	new ProjectDTO(Project.getByOid(id))
      }
    Ok(html.project.projectForm(projectForm.fill(dto)))
  }

  def create = Action { implicit request =>
    projectForm.bindFromRequest.fold(
      errors => BadRequest(html.project.projectForm(errors)),
      projectDTO => {val id = atomic[Long] {
        val project = new Project(projectDTO.name, projectDTO.url)
        project.getOid()
      }
      Redirect("/project/"+id)
      })
  }

  def show(id: Long) = Action { implicit request =>
    atomic[Result] {
      val projectDTO = new ProjectDTO(Project.getByOid(id))
      Ok(html.project.project(projectDTO))
    }
  }

}