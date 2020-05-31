package co.protectors.user.ports.http

import cats.effect.{ConcurrentEffect, ContextShift, ExitCode, IO, IOApp, Resource, Timer}
import io.circe.config.parser
import org.http4s.server.{Router, Server}
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.server.middleware.{AutoSlash, CORS, CORSConfig, GZip, Timeout}
import org.http4s.implicits._
import cats.syntax.functor._
import co.protectors.user.domain.adapters.LiveUserRepository
import co.protectors.user.domain.infraestructure.reprositorio.sql.Transactor
import co.protectors.user.domain.user.algebras.UserAlg
import co.protectors.user.ports.http.config.DefaultConfig
import co.protectors.user.ports.http.routes.Routes
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
      implicit0(repo: UserAlg[F]) = LiveUserRepository[F](xa).userRepository
      // todo: we should provide a web proxy to avoid enable CORS
      methodConfig = CORSConfig(
        anyOrigin = true,
        anyMethod = false,
        allowedMethods = Some(Set("GET", "POST")),
        allowCredentials = true,
        maxAge = 1.day.toSeconds
      )
      routes  = CORS(Routes[F].endPoints, methodConfig)
      httpApp = Timeout(config.http.timeout)(GZip(AutoSlash(Router("/users" -> routes))))
      server <- BlazeServerBuilder[F]
        .bindHttp(config.http.port.number, config.http.host.ip)
        .withHttpApp(httpApp.orNotFound)
        .resource
} yield server
}
