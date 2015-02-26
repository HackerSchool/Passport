package controllers

import play.api.data.format.Formats._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import views._
import models.party.Project

object ProjectManager extends Controller {

  case class ProjectDTO(name: String, id: Long) {
    def this(project: Project) =
      this(project.name, project.partyId)
  }

  val projectForm: Form[ProjectDTO] = Form(
    mapping(
      "Project Name" -> nonEmptyText,
      "id" -> of[Long]
    )(ProjectDTO.apply)(ProjectDTO.unapply))

  def showAll() = TODO

  def createForm() = Action {
    Ok(html.project.projectForm(projectForm.fill(new ProjectDTO("", 0))))
  }

  def editForm(id: Long) = TODO

  def create() = TODO

  def show(id: Long) = TODO

}