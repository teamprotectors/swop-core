package co.protectors.user.ports.http.dtos

import java.util.UUID

import cats.effect.Sync
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}
import org.http4s.circe.{jsonEncoderOf, jsonOf}
import org.http4s.{EntityDecoder, EntityEncoder}

 final case class LocationDTO (
                    id:Option[UUID],
                    adress: String,
                    country: String,
                    city: String,
                    zipCode: String
                  )

object LocationDTO{

  implicit val locationDecoder: Decoder[LocationDTO] = deriveDecoder[LocationDTO]
  implicit val locationEncoder: Encoder.AsObject[LocationDTO] = deriveEncoder[LocationDTO]

  implicit def locationDTODecoder[F[_]:Sync]: EntityDecoder[F, LocationDTO] =jsonOf[F,LocationDTO]
  implicit def locationDTOEncoder[F[_]:Sync]: EntityEncoder[F, LocationDTO] =jsonEncoderOf[F,LocationDTO]
}