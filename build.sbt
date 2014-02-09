name := "Passport"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  "pt.ist" % "fenix-framework-backend-jvstm-ojb-code-generator" % "2.4.0",
  "mysql" % "mysql-connector-java" % "5.1.21",
  jdbc,
  cache,
  anorm
)

val destDirectory = settingKey[File]("The top level directory where to generate the non-base classes.")

val destDirectoryBase = settingKey[File]("The top level directory where to generate the base classes.")

val localDomainSpecsDirectory = settingKey[File]("The top level directory where the dml files can be found.")

val backendGeneratorClass = settingKey[String]("The code generator to use.")

destDirectory := baseDirectory.value / "app"

destDirectoryBase := (sourceManaged in Compile).value

localDomainSpecsDirectory := baseDirectory.value / "app" / "dml"

backendGeneratorClass := "pt.ist.fenixframework.backend.jvstmojb.codeGenerator.FenixCodeGeneratorOneBoxPerObject"

lazy val dmlCompiler = TaskKey[Unit]("dmlCompiler","Compiles the dml file and generates the _Base and concrete classes")

dmlCompiler := {
  import java.net.URL
  import java.util.ArrayList
  import java.util.HashMap
  import pt.ist.fenixframework.dml.CompilerArgs
  import scala.collection.JavaConverters._
  val compileArgs = new CompilerArgs(
    name.value,
    destDirectory.value,
    destDirectoryBase.value,
    "",
    false,
    //Class.forName(backendGeneratorClass.value),
    classOf[pt.ist.fenixframework.backend.jvstmojb.codeGenerator.FenixCodeGeneratorOneBoxPerObject],
    CompilerArgs.convertFilenamesToURLs((localDomainSpecsDirectory.value ** "*.dml").get.map(_.getAbsolutePath).asJava),
    getURLsOfDMLFilesInTheDependentJars.value.asJava,
    new HashMap[String, String])
  pt.ist.fenixframework.DmlCompiler.compile(compileArgs)
  val pathfinder = (destDirectoryBase.value ** "*_Base.java") +++ (destDirectoryBase.value / "pt" ** "*.java")
  pathfinder.get
}

lazy val getURLsOfDMLFilesInTheDependentJars = TaskKey[Seq[java.net.URL]]("printStuff","prints stuff")

getURLsOfDMLFilesInTheDependentJars := {
  import java.util.jar.JarInputStream
  import java.io.FileInputStream
  import java.net.URL
  var urlsToTheDMLFiles = Seq[URL]()
  val cp: Seq[File] = (dependencyClasspath in Compile).value.files
  for (f <- cp if f.getName.startsWith("fenix-framework")) {
    val jarInputStream = new JarInputStream(new FileInputStream(f));
    var jarEntry = jarInputStream.getNextJarEntry
    while (jarEntry != null) {
      if (jarEntry.getName.endsWith(".dml")) {
        urlsToTheDMLFiles :+= getClass.getResource(jarEntry.getName)
      }
      jarEntry = jarInputStream.getNextJarEntry
    }
    jarInputStream.close
  }
  urlsToTheDMLFiles.sorted(new Ordering[URL] {
    def compare(a: URL, b: URL) = a.toExternalForm().compareTo(b.toExternalForm())
  })
}

lazy val fullPostProcessDomainClasses = TaskKey[Unit]("fullPostProcessDomainClasses","Full Post Process Domain Classes")

fullPostProcessDomainClasses := {
  //val proj = new Project(name.value, version.value, dmls, dependencies, shouldCompile)
  //Criar uma instancia de project
  //Invocar o metodo generateProjectProperties
  //https://github.com/fenix-framework/fenix-framework/blob/develop/maven/dml-maven-plugin/src/main/java/pt/ist/fenixframework/dml/maven/DmlMojoUtils.java
  import pt.ist.fenixframework.core.FullPostProcessDomainClasses
  FullPostProcessDomainClasses.apply(name.value, (classDirectory in Compile).value, getClass.getClassLoader)
}


play.Project.playScalaSettings
