package services

import jvstm.Atomic
import exception.PassportException

trait PassportService {

  @Atomic
  def execute: Unit = {
    try {
      dispatch
    } catch {
      case e: PassportException => println(e.getMessage)
    }
  }

  protected def dispatch: Unit
}
