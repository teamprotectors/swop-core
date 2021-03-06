package co.protectors.swop.cart.ports.config

import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder
import io.circe.config.syntax.durationDecoder

import scala.concurrent.duration.FiniteDuration

final case class HttpHost(ip: String) extends AnyVal
final case class HttpPort(number: Int) extends AnyVal
final case class HttpConfig(host: HttpHost, port: HttpPort, timeout: FiniteDuration)

object HttpConfig {
  implicit val hostConfigDec: Decoder[HttpHost]   = deriveDecoder[HttpHost]
  implicit val portConfigDec: Decoder[HttpPort]   = deriveDecoder[HttpPort]
  implicit val httpConfigDec: Decoder[HttpConfig] = deriveDecoder[HttpConfig]
}
