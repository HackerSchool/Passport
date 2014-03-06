package models

import pt.ist.fenixframework.FenixFramework

class SocialNetwork(fullname: String, url: String) extends SocialNetwork_Base {
  this.setRoot(FenixFramework.getDomainRoot())
  this.setName(fullname)
  this.setRri(url)
}
