package co.protectors.product.catalog.ports.http.routes

import java.util.UUID

import cats.effect.Sync
import co.protectors.product.catalog.domain.item.algebras.ItemAlg
import co.protectors.product.catalog.ports.http.dtos.ItemDTO
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import cats.syntax.apply._
import co.protectors.product.catalog.domain.item.Item
import henkan.convert.Syntax._
import cats.syntax.functor._
import cats.syntax.flatMap._
import cats.syntax.option._

final class Routes[F[_]: Sync: ItemAlg] private extends Http4sDsl[F] {

  def endPoints: HttpRoutes[F] =
    HttpRoutes.of[F] {
      case request @ POST -> Root =>
        request.decode[ItemDTO] { dto =>
          ItemAlg[F].create(dto.to[Item].set(id = UUID.randomUUID())) *> Created(dto)
        }
      case GET -> Root =>
        ItemAlg[F].getAll.map(_.map(item => item.to[ItemDTO].set(id = item.id.some))).flatMap(Ok(_))
    }
}
object Routes {
  def apply[F[_]: Sync: ItemAlg]: Routes[F] = new Routes[F]
}
