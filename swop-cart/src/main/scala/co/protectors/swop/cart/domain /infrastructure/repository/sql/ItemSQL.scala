package co.protectors.swop.cart.domain .infrastructure.repository.sql

import co.protectors.swop.cart.domain.infrastructure.repository.rows.ItemRow
import doobie.implicits.toSqlInterpolator
import doobie.postgres.implicits._
import doobie.implicits.javatime._

object ItemSQL {
  def insert(cartShop: ItemRow): doobie.Update0 =
    sql"""INSERT INTO ITEM_CART (id_item, name, quantity,
         | isInterchangeable, requestDate, showDate)
         | VALUES(${cartShop.idItem},${cartShop.name},
         | ${cartShop.quantity},${cartShop.isInterchangeable},${cartShop.requestDate},
         | ${cartShop.showDate})""".update
}


