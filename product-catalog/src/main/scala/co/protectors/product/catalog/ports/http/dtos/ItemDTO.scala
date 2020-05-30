package co.protectors.product.catalog.ports.http.dtos

import java.util.UUID

import cats.effect.Sync
import io.circe.{ Decoder, Encoder }
import io.circe.generic.semiauto.{ deriveDecoder, deriveEncoder }
import org.http4s.{ EntityDecoder, EntityEncoder }
import org.http4s.circe.{ jsonEncoderOf, jsonOf }

final case class ItemDTO(
    id: Option[UUID],
    name: String,
    category: String
)

object ItemDTO {

  implicit val itemDecoder: Decoder[ItemDTO] = deriveDecoder[ItemDTO]
  implicit val itemEncoder: Encoder.AsObject[ItemDTO] =
    deriveEncoder[ItemDTO]
  implicit def itemDTODecoder[F[_]: Sync]: EntityDecoder[F, ItemDTO] =
    jsonOf[F, ItemDTO]
  implicit def itemDTOEncoder[F[_]: Sync]: EntityEncoder[F, ItemDTO] =
    jsonEncoderOf[F, ItemDTO]

  implicit def itemsDTOEncoder[F[_]: Sync]: EntityEncoder[F, List[ItemDTO]] =
    jsonEncoderOf[F, List[ItemDTO]]
}
