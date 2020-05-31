package co.protectors.swop.cart.ports.dtos

import java.util.UUID

import cats.effect.Sync
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}
import org.http4s.EntityEncoder
import org.http4s.circe.jsonEncoderOf

final case class ItemWishDTO(  id: UUID,
                               name: String,
                               category: String
                            )
 object ItemWishDTO {

  implicit val itemWishDecoder: Decoder[ItemWishDTO] = deriveDecoder[ItemWishDTO]
  implicit val itemWishEncoder: Encoder.AsObject[ItemWishDTO] = deriveEncoder[ItemWishDTO]
  implicit def itemWishListDTOEncoder[F[_]: Sync]: EntityEncoder[F, List[ItemWishDTO]] =
    jsonEncoderOf[F, List[ItemWishDTO]]

   implicit def itemWishListDTOEncoderList[F[_]: Sync]: EntityEncoder[F, ItemWishDTO] =
     jsonEncoderOf[F, ItemWishDTO]

}