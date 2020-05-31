package co.protectors.user.ports.http.dtos

import cats.effect.Sync
import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import org.http4s.{ EntityDecoder, EntityEncoder }
import org.http4s.circe.{ jsonEncoderOf, jsonOf }


final case class UserDTO (
  age: Int,
  id: String,
  occupation: String,
  userName: String,
  typeId: String,
  location:LocationDTO
)

object UserDTO{

  implicit val userDecoder: Decoder[UserDTO] = deriveDecoder[UserDTO]
  implicit val userEncoder: Encoder.AsObject[UserDTO] =
    deriveEncoder[UserDTO]

  implicit def userDTODecoder[F[_]: Sync]: EntityDecoder[F,UserDTO] =jsonOf[F,UserDTO]
  implicit def userDTOEncoser[F[_]: Sync]: EntityEncoder[F, UserDTO] = jsonEncoderOf[F,UserDTO]

}