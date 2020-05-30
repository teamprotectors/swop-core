package co.protectors.swop.cart.domain .infrastructure.repository.rows

import java.util.UUID

import co.protectors.swop.cart.domain.item.{Item, ItemToChange}

final case class ItemWishRow(
                               id: UUID,
                               name: String,
                               category: String,
                               idItem: UUID
                             )
object ItemWishRow {
  def apply(itemDomain : Item): List[ItemWishRow] =
    itemDomain.wishItems.map{
      itemToWish =>
        ItemWishRow(
          id = itemToWish.id,
          name = itemToWish.name,
          category = itemToWish.category,
          idItem = itemDomain.id
        )
    }

  def toDomain(item:  ItemWishRow) =
      ItemToChange(
        id =  item.id,
        name = item.name,
        category = item.category
      )
}

