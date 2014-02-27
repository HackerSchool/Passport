// Comment to get more information during initialization
logLevel := Level.Warn

libraryDependencies ++= Seq(
  "pt.ist" % "fenix-framework-backend-jvstm-ojb-code-generator" % "2.4.0"
)

//The Fenix Ashes repository
resolvers += "fenix-ashes-maven-repository" at "https://fenix-ashes.ist.utl.pt/nexus/content/groups/fenix-ashes-maven-repository"

// The Typesafe repository 
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

// Use the Play sbt plugin for Play projects
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.2.1")