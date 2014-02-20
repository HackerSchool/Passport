package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import views._
import java.util.Date
import java.util.UUID
import scala.util.Random
import pt.ist.fenixframework.FenixFramework

object Application extends Controller {

  def index = Action {
    Ok(html.index())
  }
  
  //Hackers
  def showHacker(GUID: String) = Action {
	Ok
  }
  def hackers = Action {
    Ok(html.hackers())
  }
  def searchHackers = Action {
    Ok
  }
  
  //Hackerspaces
  def showHackerSpace(GUID: String) = Action {
	Ok
  }
  def hackerspaces = Action {
    Ok(html.hackerspaces())
  }
  def searchHackerspaces = Action {
    Ok
  }
  
  //EventsProjects
  def showEventsProjects(GUID: String) = Action {
	Ok
  }
  def eventsProjects = Action {
    Ok(html.eventsProjects())
  }
  def searchEventsProjects = Action {
    Ok
  }

  //About
  def about = Action {
    Ok(html.about())
  }
  
  def getRandomIconName(): String = {
    val values = List("camera", "trello", "bullhorn", "coffee", "dashboard", "asterisk", "beer", "book", "bug", "crosshairs", "eye", "film", "flag", "flask", "gear", "globe", "gavel", "headphones", "cogs", "puzzle-piece", "magic", "plane", "rss")
    return values(Random.nextInt(values.size))
  }
  
  
  def hackerDetail = Action {
    //println(FenixFramework.isInitialized())
    //val hacker = new Hacker("Simão Martins")
    Ok("Simao"/*html.hacker.detail(hacker)*/)
  }
}