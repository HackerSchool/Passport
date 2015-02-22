package models

trait Party {
  def rri: String
  def name: String
  def logoUrl: String

  def instance: PassportInstance = ???
  def child: Party = ???
  def parent: Party = ???
}