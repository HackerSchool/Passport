name := "Passport"

version := "1.0-SNAPSHOT"

organization := "HackerSchool"

scalaVersion := "2.11.5"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-slick" % "0.8.1",
  "mysql" % "mysql-connector-java" % "5.1.34",
  "org.webjars" %% "webjars-play" % "2.3.0-2",
  "org.webjars" % "bootstrap" % "3.3.2-1",
  "org.webjars" % "bootstrap-datepicker" % "1.3.1",
  "org.webjars" % "font-awesome" % "4.3.0-1"
)