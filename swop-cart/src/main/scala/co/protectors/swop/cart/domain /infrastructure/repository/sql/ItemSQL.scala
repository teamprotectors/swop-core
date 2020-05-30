package co.protectors.swop.cart.domain .infrastructure.repository.sql

import java.util.UUID

import co.protectors.swop.cart.domain.infrastructure.repository.rows.ItemRow
import doobie.implicits.toSqlInterpolator
import doobie.postgres.implicits._
import doobie.implicits.javatime._

object ItemSQL {
  def insert(cartShop: ItemRow): doobie.Update0 =
    sql"""INSERT INTO ITEM_CART (id_item, name, quantity,
         | is_interchangeable, request_date, show_date, id_cart)
         | VALUES(${cartShop.idItem},${cartShop.name},
         | ${cartShop.quantity},${cartShop.isInterchangeable},${cartShop.requestDate},
         | ${cartShop.showDate}
         | ${cartShop.idCart})""".update

  def getByIdCart(idCart: UUID): doobie.ConnectionIO[List[ItemRow]] =
    sql"""SELECT id_item, name, quantity, is_interchangeable,
         |request_date, show_date,
         |id_cart from ITEM_CART WHERE id_cart = $idCart"""
      .stripMargin.query[ItemRow].to[List]

  def getAll: doobie.ConnectionIO[List[ItemRow]] =
    sql"""SELECT id_item, name, quantity,
         |is_interchangeable, request_date, show_date,id_cart from  ITEM_CART""".stripMargin.query[ItemRow].to[List]

}


