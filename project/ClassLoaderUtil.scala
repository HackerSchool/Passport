import java.lang.ClassLoader
import java.net.URL
import java.io.File

object ClassLoaderUtil {
  def withClassLoader[T](classLoader: ClassLoader)(f: => T): T = {
    val prev = Thread.currentThread.getContextClassLoader
    try {
      Thread.currentThread.setContextClassLoader(classLoader)
      f
    } finally {
     Thread.currentThread.setContextClassLoader(prev)
    }
  }
  def withClassLoader[T](files: File*)(f: => T): T = {
    import sbt.classpath.ClasspathUtilities
    val classLoader = ClasspathUtilities.toLoader(files.toSeq)
    withClassLoader(classLoader)(f)
  }
  def withClassLoader[T](parentClassloader: ClassLoader, files: File*)(f: => T): T = {
    import sbt.classpath.ClasspathUtilities
    val classLoader = ClasspathUtilities.toLoader(files.toSeq, parentClassloader)
    withClassLoader(classLoader)(f)
  }

  def inClassLoader[T](`class`: Class[_])(f: => T): T = {
    withClassLoader(`class`.getClassLoader)(f)
  }

  def getURLsOfDMLFilesInTheDependentJars(dependentJars: Seq[File]): Seq[URL] = {
    import java.util.jar.JarInputStream
    import java.io.FileInputStream

    var urlsToTheDMLFiles = Seq[URL]()
    
    for (jarFile <- dependentJars if jarFile.getName.startsWith("fenix-framework")) {
      val jarInputStream = new JarInputStream(new FileInputStream(jarFile))
      var jarEntry = jarInputStream.getNextJarEntry
      while (jarEntry != null) {
        if (jarEntry.getName.endsWith(".dml")) {
          urlsToTheDMLFiles :+= Thread.currentThread.getContextClassLoader.getResource(jarEntry.getName)
        }
        jarEntry = jarInputStream.getNextJarEntry
      }
      jarInputStream.close
    }

    urlsToTheDMLFiles.sorted(new Ordering[URL] {
      def compare(a: URL, b: URL) = a.toExternalForm().compareTo(b.toExternalForm())
    })
  }
}

/*class AveMariaLoader(urls: Seq[URL]) extends LoaderBase(urls.toArray, Thread.currentThread.getContextClassLoader) {
	def doLoadClass(className: String): Class[_] = {

	}
}*/