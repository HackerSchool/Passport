import play.api._
import pt.ist.fenixframework.FenixFramework
import pt.ist.fenixframework.Config
import pt.ist.fenixframework.backend.BackEndId
import pt.ist.fenixframework.backend.CurrentBackEndId
//import pt.ist.fenixframework.backend.jvstmojb.JvstmOJBConfig

object Global extends GlobalSettings {
  
  override def onStart(app: Application) {
	val classOfBackEndId = Class.forName("pt.ist.fenixframework.backend.CurrentBackEndId", true, classOf[BackEndId].getClassLoader()).asInstanceOf[Class[BackEndId]]
	println(classOfBackEndId.getName())
	val beid = classOfBackEndId.newInstance();
	println(beid.getAppName())
  println(FenixFramework.isInitialized())
	/*FenixFramework.initialize(new JvstmOJBConfig() {
      {
          this.appName = "Passport"
          this.domainModelURLs = Config.resourceToURLArray("dml.dml")
          this.dbAlias = "localhost/passportFenix"
          this.dbUsername = "passportFenix"
          this.dbPassword = "passportRootPassword"
      }
    })*/
  }
}
