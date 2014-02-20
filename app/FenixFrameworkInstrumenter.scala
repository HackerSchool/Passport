import scala.Console._
import pt.ist.esw.advice.ProcessAnnotations;
import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.atomic.AtomicContextFactory;
import pt.ist.fenixframework.core.FullPostProcessDomainClasses
import java.io.File

object FenixFrameworkInstrumenter {
    def main(args: Array[String]) = {
      val projectName = args(0)
      val classesDirectory = new File(args(1))
      
      FullPostProcessDomainClasses.apply(projectName, new File(""), Thread.currentThread.getContextClassLoader)//The second argument is not used
	  println("[" + WHITE + BOLD + "Fenix-Framework" + RESET + "] Ran the FullPostProcessDomainClasses")
	
      //For this to work the line that sets the ContextClassLoader must have been executed
	  new ProcessAnnotations(
	    new ProcessAnnotations.ProgramArgs(
	      classOf[Atomic],
	      classOf[AtomicContextFactory],
	      classesDirectory
	    )
	  ).process()
	  println("[" + WHITE + BOLD + "Fenix-Framework" + RESET + "] Ran the ProcessAtomicAnnotations")
    }
}
