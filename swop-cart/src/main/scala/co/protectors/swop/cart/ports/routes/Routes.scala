  package co.protectors.swop.cart.ports.routes

  import java.util.UUID

  import cats.effect.Sync
  import cats.syntax.applicative._
  import cats.syntax.applicativeError._
  import cats.syntax.flatMap._
  import cats.syntax.functor._
  import co.protectors.swop.cart.domain.item.CartNotFound
  import co.protectors.swop.cart.domain.item.algebras.CartShopAlg
  import co.protectors.swop.cart.ports.dtos.CartShopDTO
  import co.protectors.swop.cart.ports.dtos.CartShopDTO._
  import org.http4s.HttpRoutes
  import org.http4s.dsl.Http4sDsl

  final class Routes[F[_]: Sync: CartShopAlg] private extends Http4sDsl[F] {
    def endPoints: HttpRoutes[F] =
      HttpRoutes.of[F] {
        case request @ POST -> Root =>
          request.decode[CartShopDTO] { dto =>
            CartShopAlg[F].sendCart(CartShopDTO(dto))
              .flatMap(domain => Created(CartShopDTO.fromDomain(domain)))
          }
        case GET -> Root / idCart =>
          CartShopAlg[F].getByID(UUID.fromString(idCart)).value map (domain =>
              domain.fold(CartNotFound("NotFound").raiseError[F, CartShopDTO])(
                cart => CartShopDTO.fromDomain(cart).pure[F]) ) flatMap(Ok(_))

        case GET -> Root  =>
          CartShopAlg[F].getAll.flatMap(
            domain =>
              Ok(domain.map(cart =>
                CartShopDTO.fromDomain(cart))))
      }
  }

  object Routes {
    def apply[F[_] : Sync : CartShopAlg]: Routes[F] = new Routes()
  }
