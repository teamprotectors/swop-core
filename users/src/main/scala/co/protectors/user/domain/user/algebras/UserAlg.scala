package co.protectors.user.domain.user.algebras

import co.protectors.user.domain.user.User

trait UserAlg [F[_]]{
def get(id:String):F[User]
def create(user:User):F[User]
}
object UserAlg{
  def apply[F[_]:UserAlg](locationAlg: LocationAlg[F]): UserAlg [F]= implicitly
}