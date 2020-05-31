package co.protectors.swop.cart.ports.config


import co.protectors.swop.cart.domain.infrastructure.repository.config.DBConfig
import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder

trait AppConfig {
  def http: HttpConfig
  def database: DBConfig
}

final case class DefaultConfig private (http: HttpConfig, database: DBConfig) extends AppConfig

object DefaultConfig {
  implicit val defaultConfigDec: Decoder[DefaultConfig] =
    deriveDecoder[DefaultConfig]
}