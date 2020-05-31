package co.protectors.swop.cart.ports


import cats.effect.{ConcurrentEffect, ContextShift, ExitCode, IO, IOApp, Resource, Timer}
import cats.syntax.functor._
import co.protectors.swop.cart.domain.adapters.LiveItemRepository
import co.protectors.swop.cart.domain.infrastructure.repository.sql.Transactor
import co.protectors.swop.cart.domain.item.algebras.CartShopAlg
import co.protectors.swop.cart.ports.config.DefaultConfig
import co.protectors.swop.cart.ports.routes.Routes
import io.circe.config.parser
import org.http4s.implicits._
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.server.middleware._
import org.http4s.server.{Router, Server}

import scala.concurrent.duration._

object Main extends IOApp {
  override def run(args: List[String]): IO[ExitCode] =
    AppResources.create[IO].use(_ => IO.never).as(ExitCode.Success)
}

object AppResources {
  def create[F[_]: ConcurrentEffect: ContextShift: Timer]: Resource[F, Server[F]] =
    for {
      config <- Resource.liftF(parser.decodePathF[F, DefaultConfig]("app"))
      xa <- Transactor.acquire(config.database)
      implicit0(repo: CartShopAlg[F]) = LiveItemRepository[F](xa).itemRepository
      // todo: we should provide a web proxy to avoid enable CORS
      methodConfig = CORSConfig(
        anyOrigin = true,
        anyMethod = false,
        allowedMethods = Some(Set("GET", "POST")),
        allowCredentials = true,
        maxAge = 1.day.toSeconds)
      routes  = CORS(Routes[F].endPoints, methodConfig)
      httpApp = Timeout(config.http.timeout)(GZip(AutoSlash(Router("/cart" -> routes))))
      server <- BlazeServerBuilder[F]
        .bindHttp(config.http.port.number, config.http.host.ip)
        .withHttpApp(httpApp.orNotFound)
        .resource
    } yield server
}
