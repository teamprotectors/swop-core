package co.protectors.user.domain.adapters

import cats.effect.Effect
import co.protectors.user.domain.infraestructure.reprositorio.InMemoryRepository
import co.protectors.user.domain.user.algebras.UserAlg
import doobie.util.transactor.Transactor

trait Repository[F[_]] {
  def userRepository: UserAlg[F]
}


final class LiveUserRepository[F[_]] private (repo: UserAlg[F]) extends Repository[F] {
override def userRepository  =repo
}
object LiveUserRepository{
  def apply[F[_]: Effect](xa: Transactor[F]): LiveUserRepository[F] =
    new LiveUserRepository[F](InMemoryRepository[F](xa))
}