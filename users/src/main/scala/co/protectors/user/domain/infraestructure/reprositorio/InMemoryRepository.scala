package co.protectors.user.domain.infraestructure.reprositorio

import java.util.UUID

import cats.effect.{Effect}
import cats.syntax.functor._
import cats.syntax.flatMap._
import co.protectors.user.domain.infraestructure.reprositorio.row.{LocationRow, UserRow}
import co.protectors.user.domain.infraestructure.reprositorio.sql.{LocationSQL, UserSQL}
import co.protectors.user.domain.user.{Location, User}
import co.protectors.user.domain.user.algebras.{LocationAlg, UserAlg}
import doobie.implicits._
import doobie.util.transactor.Transactor
import henkan.convert.Syntax._

final class InMemoryRepository[F[_]: Effect](xa: Transactor[F]) extends  UserAlg[F] with LocationAlg[F]{

  override def getUser(id: String): F[User] =
    for {
      queryUser <- UserSQL.get(id).unique.transact(xa)
      location <- getLocation(queryUser.idLocation)
    } yield (queryUser.to[User].set(location = location))

  override def createUser(user: User): F[User] =
    for{
     _<- createLocation(user.location)
    _ <- UserSQL.insert(user.to[UserRow].set(idLocation=user.location.id)).run.transact(xa)
    }yield user

  override def getLocation(id: UUID): F[Location] = LocationSQL.get(id).unique.transact(xa).map(row=>row.to[Location]())

  override def createLocation(location: Location): F[Location] =
  LocationSQL.insert(location.to[LocationRow]()).run.transact(xa).map(_ => location)
}

object InMemoryRepository {
  def apply[F[_]: Effect](xa: Transactor[F]): InMemoryRepository[F] = new InMemoryRepository(xa)
}