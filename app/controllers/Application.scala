package controllers

import controllers.EventManager._
import play.api.mvc._
import views._
import scala.util.Random

object Application extends Controller {

  def index = Action {
    Ok(html.index())
  }
  
  //Hackers
  def showHacker(GUID: String) = TODO

  def hackers = Action {
    Ok(html.hackers())
  }

  def searchHackers = TODO
  
  //Hackerspaces
  def showHackerSpace(GUID: String) = TODO

  def hackerspaces = Action {
    Ok(html.hackerspaces())
  }

  def searchHackerspaces = TODO
  
  //EventsProjects
  def showEventsProjects(GUID: String) = TODO

  def eventsProjects = Action {
    Ok(html.eventsProjects())
  }

  def searchEventsProjects = TODO

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