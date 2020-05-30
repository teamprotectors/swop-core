package co.protectors.swop.cart.domain .infrastructure.repository.rows

import java.time.ZonedDateTime
import java.util.UUID

import co.protectors.swop.cart.domain.item.CartShop

case class ItemRow (
                     idItem:UUID,
                     name: String,
                     quantity: Int,
                     isInterchangeable: Boolean,
                     requestDate: ZonedDateTime,
                     showDate: ZonedDateTime,
                     idCart: UUID
                   )

object ItemRow  {
  def apply(cartShop: CartShop): Seq[ItemRow] =
    cartShop.items map( item =>
      ItemRow(
        idItem = item.id,
        name = item.name,
        quantity = item.quantity,
        isInterchangeable = item.isInterchangeable,
        requestDate = item.requestDate,
        showDate = item.showDate,
        idCart = cartShop.idCart
    ))
}