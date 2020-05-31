package co.protectors.swop.cart.domain .item

import java.time.ZonedDateTime
import java.util.UUID

case class Item (
                 id:UUID,
                 name: String,
                 quantity: Int,
                 isInterchangeable: Boolean,
                 requestDate: ZonedDateTime,
                 showDate: ZonedDateTime,
                 wishItems: List[ItemToChange])

final case class ItemToChange(
                               id: UUID,
                               name: String,
                               category: String
                             )

