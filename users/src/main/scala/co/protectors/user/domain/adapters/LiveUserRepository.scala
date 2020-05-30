package co.protectors.user.domain.adapters

import co.protectors.user.domain.user.User
import co.protectors.user.domain.user.algebras.UserAlg

final class LiveUserRepository[F[_]:UserAlg] private {

  def get(id:String):F[User] =UserAlg[F].get(id)
  def create(user:User):F[User] =UserAlg[F].create(user)
}
object LiveUserRepository{
  def apply[F[_]:UserAlg]: LiveUserRepository[F] = new LiveUserRepository[F]
}