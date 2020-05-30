package co.protectors.swop.cart.domain .infrastructure.repository.sql

import co.protectors.swop.cart.domain.infrastructure.repository.rows.ItemWishRow
import doobie.implicits.toSqlInterpolator

import doobie.postgres.implicits._

object ItemWishSQL {
  def insert(cartShop: ItemWishRow): doobie.Update0 =
    sql"""INSERT INTO ITEM_WISH (id_item_wish, name, category, itemId) VALUES(${cartShop.id},${cartShop.name},${cartShop.category}, ${cartShop.idItem})""".update
}

