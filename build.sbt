name := "Passport"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "5.1.21",
  jdbc,
  anorm,
  cache
)     

play.Project.playScalaSettings
