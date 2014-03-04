package util

import pt.ist.fenixframework.FenixFramework
import java.util.concurrent.Callable
import jvstm.cps.ConsistencyException
import exception.PassportException

object FenixFrameworkUtil {
  def atomic[T](f: => T): T = {
    FenixFramework.getTransactionManager().withTransaction(new Callable[T]() {
      override def call(): T = {
	    f
      }
    })
  }
  
  def atomic[Ex <: PassportException, T](f: => T): Either[Ex, T] = {
    FenixFramework.getTransactionManager().withTransaction(new Callable[Either[Ex, T]]() {
      override def call(): Either[Ex, T] = {
        try {
          Right(f)
        } catch {
          case e: PassportException => Left(e.asInstanceOf[Ex])
        }
      }
    })
  }
}