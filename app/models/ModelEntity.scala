package models

import anorm._
import play.api.db.DB
import play.api.Play.current
import java.util.UUID

trait ModelEntity {
  def findByUUID(UUID: UUID): ModelEntity = {
    DB.withConnection { implicit c ⇒
      val query = SQL("""
		SELECT * 
		FROM {modelEntityName}
		WHERE {modelEntityName}_UUID= {UUID}
	  """).on(
	    "modelEntityName" -> this.getClass().getSimpleName(),
        "UUID" -> UUID)
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
	    ({modelEntityName}_UUID, , ,)
		VALUES
        (,,)
        ON DUPLICATE KEY UPDATE
	  """).on(
	    "modelEntityName" -> this.getClass().getSimpleName())
      query.executeInsert()
    }
  }
}