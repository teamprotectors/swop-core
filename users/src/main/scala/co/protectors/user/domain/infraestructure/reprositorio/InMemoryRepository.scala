package co.protectors.user.domain.infraestructure.reprositorio

import cats.effect.Sync
import cats.syntax.functor._
import co.protectors.user.domain.infraestructure.reprositorio.row.UserRow
import co.protectors.user.domain.infraestructure.reprositorio.sql.UserSQL
import co.protectors.user.domain.user.User
import co.protectors.user.domain.user.algebras.UserAlg
import doobie.implicits._
import doobie.util.transactor.Transactor
import henkan.convert.Syntax._

class InMemoryRepository[F[_]: Sync](xa: Transactor[F]) extends  UserAlg[F]{
  override def get(id: String): F[User] = UserSQL.get(id).map(_.to[User])

  override def create(user: User): F[User] =
  UserSQL.insert(user.to[UserRow]()).run.transact(xa).map(_ => user)
}
