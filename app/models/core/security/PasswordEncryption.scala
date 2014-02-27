package models.core.security

import java.security.{InvalidKeyException, NoSuchAlgorithmException, SecureRandom}
import java.security.spec.{InvalidKeySpecException, KeySpec}
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

object PasswordEncryption {

  @throws[NoSuchAlgorithmException]("if the algorithm doesn't exists")
  @throws[InvalidKeySpecException]
  def authenticate(attempedPassword: String, encryptedPassword: Array[Byte], salt: Array[Byte]): Boolean = {
    val encryptedAttempedPassword: Array[Byte] = getEncryptedPassword(attempedPassword, salt)
    encryptedPassword.sameElements(encryptedAttempedPassword)
  }

  @throws[NoSuchAlgorithmException]("if the algorithm doesn't exists")
  @throws[InvalidKeySpecException]
  def getEncryptedPassword(password: String, salt: Array[Byte], algorithm: String = "PBKDF2WithHmacSHA1"): Array[Byte] = {
    val derivedKeyLength: Int = 160
    val iterations: Int = 50000
    val spec: KeySpec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength)
    val factory: SecretKeyFactory = SecretKeyFactory.getInstance(algorithm)
    factory.generateSecret(spec).getEncoded
  }

  @throws[NoSuchAlgorithmException]
  def generateSalt(instance: String = "SHA1PRNG", saltSize: Int = 16): Array[Byte] = {
    val random: SecureRandom = SecureRandom.getInstance(instance)
    val salt: Array[Byte] = Array.ofDim[Byte](saltSize)
    random.nextBytes(salt)
    return salt
  }

}