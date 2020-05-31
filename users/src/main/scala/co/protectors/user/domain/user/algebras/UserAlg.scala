package co.protectors.user.domain.user.algebras

import co.protectors.user.domain.user.User

trait UserAlg [F[_]]{
def getUser(id:String):F[User]
def createUser(user:User):F[User]
}
object UserAlg{
  def apply[F[_]:UserAlg]: UserAlg [F]= implicitly
}