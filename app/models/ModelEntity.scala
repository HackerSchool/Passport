package models

import anorm._
import play.api.db.DB
import play.api.Play.current

trait ModelEntity {
  def findByID(id: Int): ModelEntity = {
    DB.withConnection { implicit c ⇒
      val query = SQL("""
		SELECT * 
		FROM {modelEntityName}
		WHERE {modelEntityName}_id = {id}
	  """).on(
	    "modelEntityName" -> this.getClass().getSimpleName(),
        "id" -> id)
      val row = query.apply().head
      return null //TODO: change this to create the appropriate ModelEntity type
    }
  }
  def findAll(): Stream[ModelEntity] = {
    DB.withConnection { implicit c ⇒
      val query = SQL("""
		SELECT * 
		FROM {modelEntityName}
      """).on(
        "modelEntityName" -> this.getClass().getSimpleName())
      val rows = query.apply
      return null //TODO: convert from anorm.SqlRow to the appropriate ModelEntity type
    }
  }
  def find(query: String): Stream[ModelEntity]
  def save() = {
    DB.withConnection { implicit c ⇒
      val query = SQL("""
		INSERT INTO {modelEntityName}
	    ({modelEntityName}_id, , ,)
		VALUES
        (,,)
        ON DUPLICATE KEY UPDATE
	  """).on(
	    "modelEntityName" -> this.getClass().getSimpleName())
      query.executeInsert()
    }
  }
}