name := "Passport"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  "pt.ist" % "fenix-framework-backend-jvstm-ojb-code-generator" % "2.4.0",
  jdbc,
  cache
)

val destDirectory = settingKey[File]("The top level directory where to generate the non-base classes.")

val destDirectoryBase = settingKey[File]("The top level directory where to generate the base classes.")

val dmlsDirectory = settingKey[File]("The top level directory where the dml files can be found.")

val backendGeneratorClass = settingKey[String]("The code generator to use.")


destDirectory := baseDirectory.value / "app"

destDirectoryBase := (sourceManaged in Compile).value

dmlsDirectory := baseDirectory.value / "app" / "dml"

backendGeneratorClass := "pt.ist.fenixframework.backend.jvstmojb.codeGenerator.FenixCodeGeneratorOneBoxPerObject"

sourceGenerators in Compile <+= Def.task[Seq[File]]{
  //https://github.com/fenix-framework/fenix-framework/blob/develop/core/dml-compiler/code-generator/src/main/java/pt/ist/fenixframework/dml/CompilerArgs.java
  //https://github.com/fenix-framework/fenix-framework/blob/develop/maven/dml-maven-plugin/src/main/java/pt/ist/fenixframework/dml/maven/DmlCodeGeneratorMojo.java
  //https://github.com/fenix-framework/fenix-framework/blob/develop/maven/dml-maven-plugin/src/main/java/pt/ist/fenixframework/dml/maven/AbstractDmlCodeGeneratorMojo.java
  //https://github.com/fenix-framework/fenix-framework/blob/develop/maven/dml-maven-plugin/src/main/java/pt/ist/fenixframework/dml/maven/DmlMojoUtils.java
  var generatedFiles = Seq[File]()
  for (dmlFile <- (dmlsDirectory.value ** "*.dml").get) {
	  val cachedFun = FileFunction.cached(cacheDirectory.value / dmlFile.getName, FilesInfo.lastModified, FilesInfo.exists) { (inFiles: Set[File]) =>
			import java.net.URL
		  import java.util.{ArrayList, HashMap}
		  import scala.collection.JavaConverters._
		  val compileArgs = new pt.ist.fenixframework.dml.CompilerArgs(
		    name.value,
		    destDirectory.value,
		    destDirectoryBase.value,
		    "",//This is not used
		    false,
		    Class.forName(backendGeneratorClass.value).asInstanceOf[Class[_ <: pt.ist.fenixframework.dml.CodeGenerator]],
		    Seq(dmlFile.toURI.toURL).asJava,
		    getURLsOfDMLFilesInTheDependentJars.value.asJava,
		    new HashMap[String, String])
		  pt.ist.fenixframework.DmlCompiler.compile(compileArgs)
		  import scala.Console._
      println("[" + WHITE + BOLD + "Fenix-Framework" + RESET + "] Ran DmlCompiler for \"" + dmlFile.getName + "\"")
		  val pathfinder = (destDirectoryBase.value ** "*_Base.java") +++ (destDirectoryBase.value / "pt" / "ist" / "fenixframework" ** "*.java")
		  pathfinder.get.toSet
    }
    generatedFiles = generatedFiles ++ cachedFun(Set(dmlFile)).toSeq
  }
  val localNonBaseFilesFileFilter = (destDirectoryBase.value ** "*_Base.java") --- (destDirectoryBase.value / "pt" / "ist" / "fenixframework" ** "*.java")
  val localNonBaseFiles = localNonBaseFilesFileFilter.get.map{f =>
  	val filePath: String = f.getAbsolutePath.replace(destDirectoryBase.value.getAbsolutePath, "").replace("_Base.java", ".java")
    val nonBaseFile = (destDirectory.value / filePath).getAbsoluteFile
    //Deletes the non base files since they were generated for Java
    //println(nonBaseFile.getAbsolutePath)
    IO.delete(nonBaseFile)
//    nonBaseFile
  }
  generatedFiles // ++ localNonBaseFiles
}

lazy val getURLsOfDMLFilesInTheDependentJars = TaskKey[Seq[java.net.URL]]("getURLsOfDMLFilesInTheDependentJars","prints stuff")

getURLsOfDMLFilesInTheDependentJars := {
  import java.util.jar.JarInputStream
  import java.io.FileInputStream
  import java.net.URL
  var urlsToTheDMLFiles = Seq[URL]()
  val dependentJars: Seq[File] = (dependencyClasspath in Compile).value.files
  for (jarFile <- dependentJars if jarFile.getName.startsWith("fenix-framework")) {
    val jarInputStream = new JarInputStream(new FileInputStream(jarFile));
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

unmanagedResourceDirectories in Compile += dmlsDirectory.value

unmanagedResourceDirectories in Runtime += dmlsDirectory.value

managedClasspath in Runtime += (classDirectory in Compile).value

lazy val postProcessDomainClassesAndAtomicAnnotations = TaskKey[Unit]("postProcessDomainClassesAndAtomicAnnotations","Full Post Process Domain Classes")

postProcessDomainClassesAndAtomicAnnotations := {
  val _ = (exportedProducts in Compile).value
  /*val r = (runner in Runtime).value
  r.run(
    "FenixFrameworkInstrumenter",
    (fullClasspath in Runtime).value.files,
    Array(
      name.value,
      (classDirectory in Compile).value.getAbsolutePath
    ),
    streams.value.log)*/
  //https://github.com/fenix-framework/fenix-framework/blob/develop/core/dml-compiler/dml/src/main/java/pt/ist/fenixframework/core/Project.java
  //https://github.com/fenix-framework/fenix-framework/blob/develop/maven/dml-maven-plugin/src/main/java/pt/ist/fenixframework/dml/maven/DmlPostProcessorMojo.java
  //https://github.com/fenix-framework/fenix-framework/blob/develop/maven/dml-maven-plugin/src/main/java/pt/ist/fenixframework/dml/maven/AbstractDmlPostProcessorMojo.java
  //https://github.com/fenix-framework/fenix-framework/blob/develop/maven/dml-maven-plugin/src/main/java/pt/ist/fenixframework/dml/maven/DmlMojoUtils.java
  import pt.ist.fenixframework.core.FullPostProcessDomainClasses
  ClassLoaderUtil.withClassLoader(getClass.getClassLoader, (classDirectory in Compile).value) {
    FullPostProcessDomainClasses.apply(name.value, new File(""), Thread.currentThread.getContextClassLoader)//The second argument is not used
    import scala.Console._
    println("[" + WHITE + BOLD + "Fenix-Framework" + RESET + "] Ran the FullPostProcessDomainClasses")
    import pt.ist.esw.advice.ProcessAnnotations;
    new ProcessAnnotations(
      new ProcessAnnotations.ProgramArgs(
        classOf[pt.ist.fenixframework.Atomic],
        classOf[pt.ist.fenixframework.atomic.AtomicContextFactory],
        (classDirectory in Compile).value
      )
    ).process()
    println("[" + WHITE + BOLD + "Fenix-Framework" + RESET + "] Ran the ProcessAtomicAnnotations")
  }
}

play.Project.playScalaSettings
