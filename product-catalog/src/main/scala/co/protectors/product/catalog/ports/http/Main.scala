package co.protectors.product.catalog.ports.http

import cats.effect.{ ConcurrentEffect, ContextShift, ExitCode, IO, IOApp, Resource, Timer }
import co.protectors.product.catalog.domain.adapters.LiveItemRepository
import co.protectors.product.catalog.domain.infrastructure.repository.sql.Transactor
import co.protectors.product.catalog.domain.item.algebras.ItemAlg
import co.protectors.product.catalog.ports.http.config.DefaultConfig
import co.protectors.product.catalog.ports.http.routes.Routes
import io.circe.config.parser
import org.http4s.server.{ Router, Server }
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.server.middleware.{ AutoSlash, GZip, Timeout }
import org.http4s.implicits._
import cats.syntax.functor._

object Main extends IOApp {
  override def run(args: List[String]): IO[ExitCode] =
    AppResources.create[IO].use(_ => IO.never).as(ExitCode.Success)
}

object AppResources {
  def create[F[_]: ConcurrentEffect: ContextShift: Timer]: Resource[F, Server[F]] =
    for {
      config <- Resource.liftF(parser.decodePathF[F, DefaultConfig]("app"))
      xa <- Transactor.acquire(config.database)
      implicit0(repo: ItemAlg[F]) = LiveItemRepository[F](xa).itemRepository
      routes                      = Routes[F].endPoints
      httpApp                     = Timeout(config.http.timeout)(GZip(AutoSlash(Router("/products" -> routes))))
      server <- BlazeServerBuilder[F]
                 .bindHttp(config.http.port.number, config.http.host.ip)
                 .withHttpApp(httpApp.orNotFound)
                 .resource
    } yield server
}
