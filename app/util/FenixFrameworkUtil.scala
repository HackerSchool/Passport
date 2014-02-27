package util

import pt.ist.fenixframework.FenixFramework
import java.util.concurrent.Callable

object FenixFrameworkUtil {
  def withTransaction[T](f: => T): T = {
    FenixFramework.getTransactionManager().withTransaction(new Callable[T]() {
      override def call(): T = {
	    f
      }
    })
  }
}