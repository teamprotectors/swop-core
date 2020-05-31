package co.protectors.user.ports.http.config

import co.protectors.user.domain.infraestructure.reprositorio.config.DBConfig
import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder

trait AppConfig {
  def http: HttpConfig
  def database: DBConfig
}

final case class  DefaultConfig private(http:HttpConfig,database:DBConfig) extends AppConfig {
}

object DefaultConfig{
  implicit val defaultConfigDec: Decoder[DefaultConfig] =deriveDecoder[DefaultConfig]
}