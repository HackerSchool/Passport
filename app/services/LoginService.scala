package services

import services.dto.UserLoginDTO
import pt.ist.fenixframework.{DomainRoot, FenixFramework}
import models.User
import scala.collection.JavaConversions._
import exception.{PassportException, UserNotFoundException}
import java.security.NoSuchAlgorithmException
import java.security.spec.InvalidKeySpecException

class LoginService(val dto: UserLoginDTO) extends PassportService {

  private val description: String = "Running login service"

  @throws[UserNotFoundException]
  @throws[PassportException]
  override protected def dispatch: Unit = {
    val root: DomainRoot = FenixFramework.getDomainRoot()
    val userOption = root.getUsersSet.filter(_.getUsername == dto.username).headOption

    if (userOption.isDefined) {
      try {
        val user: User = userOption.get
        user.authenticate(dto.password)
      } catch {
          case e: NoSuchAlgorithmException => throw new PassportException(description, "there was a problem performing authentication")
          case e: InvalidKeySpecException => throw new PassportException(description, "there was a problem performing authentication")
      }
    } else {
      throw UserNotFoundException(dto.username, description)
    }

  }
}
