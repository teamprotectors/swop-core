package co.protectors.swop.cart.domain .infrastructure.repository.sql

import java.util.UUID

import co.protectors.swop.cart.domain.infrastructure.repository.rows.ItemWishRow
import doobie.implicits.toSqlInterpolator
import doobie.postgres.implicits._

object ItemWishSQL {
  def insert(cartShop: ItemWishRow): doobie.Update0 =
    sql"""INSERT INTO ITEM_WISH (idItemWish, name, category, itemId) VALUES(${cartShop.id},${cartShop.name},${cartShop.category}, ${cartShop.idItem})""".update

  def getByIdItem(idItem: UUID): doobie.ConnectionIO[List[ItemWishRow]] =
    sql"""SELECT idItemWish, name, category, itemId
          from ITEM_WISH WHERE itemId = $idItem"""
      .stripMargin.query[ItemWishRow].to[List]

  def getAll: doobie.ConnectionIO[List[ItemWishRow]] =
    sql"""SELECT idItemWish, name, category, itemId
         | from ITEM_WISH""".stripMargin.query[ItemWishRow].to[List]

}

