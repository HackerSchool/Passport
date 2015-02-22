package models.core.security

import java.security.{NoSuchAlgorithmException, SecureRandom}
import java.security.spec.{InvalidKeySpecException, KeySpec}
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

object PasswordEncryption {

  @throws[NoSuchAlgorithmException]("if the algorithm doesn't exists")
  @throws[InvalidKeySpecException]
  def authenticate(attemptedPassword: String, encryptedPassword: String, salt: String): Boolean = {
    val encryptedAttemptedPassword: String = getEncryptedPassword(attemptedPassword, salt)
    encryptedPassword == encryptedAttemptedPassword
  }

  @throws[NoSuchAlgorithmException]("if the algorithm doesn't exists")
  @throws[InvalidKeySpecException]
  def getEncryptedPassword(password: String, salt: String, algorithm: String = "PBKDF2WithHmacSHA1"): String = {
    val derivedKeyLength: Int = 160
    val iterations: Int = 50000
    val spec: KeySpec = new PBEKeySpec(password.toCharArray, salt.grouped(2).map(Integer.parseInt(_, 16).toByte).toArray, iterations, derivedKeyLength)
    val factory: SecretKeyFactory = SecretKeyFactory.getInstance(algorithm)
    factory.generateSecret(spec).getEncoded.mkString
  }

  @throws[NoSuchAlgorithmException]
  def generateSalt(instance: String = "SHA1PRNG", saltSize: Int = 16): String = {
    val random: SecureRandom = SecureRandom.getInstance(instance)
    val salt: Array[Byte] = Array.ofDim[Byte](saltSize)
    random.nextBytes(salt)
    salt.map("%02X" format _).mkString
  }

}