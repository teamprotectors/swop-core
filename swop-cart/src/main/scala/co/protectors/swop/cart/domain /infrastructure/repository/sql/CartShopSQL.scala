package co.protectors.swop.cart.domain .infrastructure.repository.sql

import co.protectors.swop.cart.domain.infrastructure.repository.rows.CartShopRow
import doobie.implicits.toSqlInterpolator
import doobie.postgres.implicits._

object CartShopSQL {
  def insert(cartShop: CartShopRow): doobie.Update0 =
    sql"""INSERT INTO CART_SHOP (id_cart) VALUES(${cartShop.id})""".update
}