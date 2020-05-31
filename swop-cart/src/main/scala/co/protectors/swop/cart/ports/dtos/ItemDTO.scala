package co.protectors.swop.cart.ports.dtos

import java.time.ZonedDateTime
import java.util.UUID

import cats.effect.Sync
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}
import org.http4s.EntityEncoder
import org.http4s.circe.jsonEncoderOf

case class ItemDTO (
                     id:Option[UUID],
                     name: String,
                     quantity: Int,
                     description: Option[String],
                     isInterchangeable: Boolean,
                     requestDate: Option[ZonedDateTime],
                     showDate: Option[ZonedDateTime],
                     wishItems: List[ItemWishDTO])
object ItemDTO {
  implicit val cartShopDecoder: Decoder[ItemDTO] = deriveDecoder[ItemDTO]
  implicit val cartShopEncoder: Encoder.AsObject[ItemDTO] =
    deriveEncoder[ItemDTO]

  implicit def itemListDTOEncoderList[F[_]: Sync]: EntityEncoder[F, List[ItemDTO]] =
    jsonEncoderOf[F, List[ItemDTO]]

  implicit def itemListDTOEncoder[F[_]: Sync]: EntityEncoder[F, ItemDTO] =
    jsonEncoderOf[F, ItemDTO]

}