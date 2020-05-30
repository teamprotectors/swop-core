package co.protectors.swop.cart.domain .infrastructure.repository.sql

import co.protectors.swop.cart.domain.infrastructure.repository.rows.ItemWishRow
import doobie.implicits.toSqlInterpolator
import doobie.postgres.implicits._

object ItemWishSQL {
  def insert(cartShop: ItemWishRow): doobie.Update0 =
    sql"""INSERT INTO ITEM_WISH (id_item_wish, name, category, item_id) VALUES(${cartShop.id},${cartShop.name},${cartShop.category}, ${cartShop.idItem})""".update

  def getByIdItem(idItem: String): doobie.ConnectionIO[List[ItemWishRow]] =
    sql"""SELECT id_item, name, quantity, is_interchangeable,
         |request_date, show_date, id_cart from ITEM_WISH WHERE id_cart = $idItem"""
      .stripMargin.query[ItemWishRow].to[List]

  def getAll: doobie.ConnectionIO[List[ItemWishRow]] =
    sql"""SELECT id_item_wish, name, category, item_id
         | from ITEM_WISH""".stripMargin.query[ItemWishRow].to[List]


}

