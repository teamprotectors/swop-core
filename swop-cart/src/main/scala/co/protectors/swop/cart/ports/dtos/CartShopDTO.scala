package co.protectors.swop.cart.ports.dtos

import java.time.ZonedDateTime
import java.util.UUID

import cats.effect.Sync
import co.protectors.swop.cart.domain.item.{CartShop, Item, ItemToChange}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}
import org.http4s.circe.{jsonEncoderOf, jsonOf}
import org.http4s.{EntityDecoder, EntityEncoder}

case class CartShopDTO (idCart: Option[UUID], idUser: String, items: List[ItemDTO])

object CartShopDTO {
  def apply(dto: CartShopDTO): CartShop = {
    CartShop(
      id = dto.idCart.getOrElse(UUID.randomUUID()),
      idUser = dto.idUser ,
      dto.items.map(
        itemDTO => Item(
          id = UUID.randomUUID(),
          name = itemDTO.name,
          quantity = itemDTO.quantity,
          isInterchangeable = itemDTO.isInterchangeable,
          requestDate = ZonedDateTime.now(),
          showDate = ZonedDateTime.now(),
          wishItems = itemDTO.wishItems.map(wishItems =>
            ItemToChange(
              id = wishItems.id,
              name = wishItems.name,
              category = wishItems.category)))))
  }

  def fromDomain(domain: CartShop): CartShopDTO =
    CartShopDTO(
      idCart = Some(domain.id),
      idUser = domain.idUser,
      domain.items.map(domainItems =>
        ItemDTO(
          id = Some(domainItems.id),
          name = domainItems.name,
          quantity = domainItems.quantity,
          isInterchangeable = domainItems.isInterchangeable,
          requestDate = Some(domainItems.requestDate),
          showDate = Some(domainItems.showDate),
          wishItems = domainItems.wishItems.map(wish =>
            ItemWishDTO(
              id = wish.id,
              name = wish.name,
              category = wish.category
            )))))

  implicit val cartShopDecoder: Decoder[CartShopDTO] = deriveDecoder[CartShopDTO]
  implicit val cartShopEncoder: Encoder.AsObject[CartShopDTO] =
    deriveEncoder[CartShopDTO]
  implicit def cartShopDecoderF[F[_]: Sync]: EntityDecoder[F, CartShopDTO] =
    jsonOf[F, CartShopDTO]
  implicit def cartShopEncoderF[F[_]: Sync]: EntityEncoder[F, CartShopDTO] =
    jsonEncoderOf[F, CartShopDTO]

}
