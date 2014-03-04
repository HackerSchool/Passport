import models.User
import play.api._
import pt.ist.fenixframework.FenixFramework
import pt.ist.fenixframework.Transaction
import pt.ist.fenixframework.backend.BackEndId
import java.util.concurrent.Callable
//import pt.ist.fenixframework.backend.jvstmojb.JvstmOJBConfig

object Global extends GlobalSettings {  
  override def onStart(app: Application) {
    //This code is here to help debug the FenixFramework initialization problems
	/*val classOfBackEndId = Class.forName("pt.ist.fenixframework.backend.CurrentBackEndId", true, classOf[BackEndId].getClassLoader()).asInstanceOf[Class[BackEndId]]
	val beid = classOfBackEndId.newInstance();
	println(beid.getAppName())*/
    
    //This forces the FenixFramework to auto init
    FenixFramework.isInitialized()
  }
}
