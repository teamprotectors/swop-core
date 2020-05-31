package co.protectors.swop.cart.domain .infrastructure.repository.sql

import java.util.UUID

import co.protectors.swop.cart.domain.infrastructure.repository.rows.ItemRow
import doobie.implicits.toSqlInterpolator
import doobie.postgres.implicits._
import doobie.implicits.javatime._

object ItemSQL {
  def insert(cartShop: ItemRow): doobie.Update0 =
    sql"""INSERT INTO ITEM_CART (idItem, name, quantity,
           isInterchangeable, requestDate, showDate, idCart) VALUES(${cartShop.idItem},${cartShop.name},
          ${cartShop.quantity},${cartShop.isInterchangeable},${cartShop.requestDate},
          ${cartShop.showDate},
          ${cartShop.idCart})""".update

  def getByIdCart(idCart: UUID): doobie.ConnectionIO[List[ItemRow]] =
    sql"""SELECT idItem, name, quantity, isInterchangeable,requestDate, showDate,
          idCart FROM ITEM_CART WHERE idCart = $idCart"""
      .stripMargin.query[ItemRow].to[List]

  def getAll: doobie.ConnectionIO[List[ItemRow]] =
    sql"""SELECT idItem, name, quantity,
         isInterchangeable, requestDate, showDate, idCart FROM  ITEM_CART""".stripMargin.query[ItemRow].to[List]

}


