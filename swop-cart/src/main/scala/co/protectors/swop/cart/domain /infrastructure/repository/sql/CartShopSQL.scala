package co.protectors.swop.cart.domain .infrastructure.repository.sql

import java.util.UUID

import co.protectors.swop.cart.domain.infrastructure.repository.rows._
import doobie.implicits.toSqlInterpolator
import doobie.postgres.implicits._

object CartShopSQL {
  def insert(cartShop: CartShopRow): doobie.Update0 =
    sql"""INSERT INTO CART_SHOP (id_cart) VALUES(${cartShop.id})""".update

  def getAll: doobie.ConnectionIO[List[CartShopRow]] =
    sql"""SELECT id_cart from CART_SHOP""".stripMargin.query[CartShopRow].to[List]

  def getById(id: UUID): doobie.Query0[CartShopRow] =
    sql"""SELECT id_cart from CART_SHOP where id_cart=$id""".stripMargin.query[CartShopRow]

}