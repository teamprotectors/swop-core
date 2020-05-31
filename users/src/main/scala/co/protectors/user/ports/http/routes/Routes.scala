package co.protectors.user.ports.http.routes

import java.util.UUID

import cats.effect.Sync
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import cats.syntax.apply._
import co.protectors.user.domain.user.{Location, User}
import co.protectors.user.domain.user.algebras.UserAlg
import co.protectors.user.ports.http.dtos.{LocationDTO, UserDTO}
import henkan.convert.Syntax._
import cats.syntax.flatMap._
import cats.syntax.functor._
import cats.syntax.option._


final class Routes[F[_]: Sync: UserAlg] private extends Http4sDsl[F]{

  def endPoints: HttpRoutes[F] =
    HttpRoutes.of[F] {
      case request @ POST -> Root =>
        request.decode[UserDTO] { dto =>
          UserAlg[F].createUser(dto.to[User].set(location=dto.location.to[Location].set(id = UUID.randomUUID()))) *> Created(dto)
        }
      case GET -> Root/id =>
      UserAlg[F].getUser(id).map(user=>user.to[UserDTO].set(location=user.location.to[LocationDTO].set(id =user.location.id.some))).flatMap(Ok(_))
    }

}

object Routes{
  def apply[F[_]:Sync: UserAlg]: Routes[F] = new Routes[F]
}